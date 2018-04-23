#!/usr/bin/env python2
# -*- coding: UTF-8 -*-

"""
PT2/SS18/U1/Validator/v2

Danke, dass du dieses Validatorscript heruntergeladen hast.
Wenn es dir lokal noch hilfreicher sein soll, kannst du dir
die Fehler rot einfärben lassen.

Bash-Nutzer führen dazu einfach folgendes aus (alle anderen googeln):

	$ color() (set -o pipefail; "$@" 2>&1>&3 | sed $'s,.*,\e[31m&\e[m,' >&2) 3>&1
	$ color python2 path/to/validator.py

Viel Spaß!
"""

import operator
import os
import subprocess
import sys

#-------------------------------------------------------------------------------

def log(msg="", newLine=True, okay=False):
	f = sys.stdout if okay else sys.stderr
	f.write("%s\n" % msg if newLine else msg)
	f.flush()

def silent_atoi(s):
	try:
		return int(s)
	except ValueError:
		return None

#-------------------------------------------------------------------------------

class Test(object):

	def exec_(self, input_text, *cmd_and_args):
		p = subprocess.Popen(cmd_and_args, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
		return tuple(map(lambda s: s.strip(), p.communicate(input=input_text)[:2])) + (p.returncode, )

	def run_prog(self, *cmd_and_args):
		return self.exec_("", *cmd_and_args)[0]

	def test(self):
		self.run()
		okay = self.okay()
		log(self.what(), okay=okay)
		return okay

	def run(self):
		raise NotImplementedError

	def what(self):
		raise NotImplementedError

	def okay(self):
		raise NotImplementedError

class EmptyTestLine(Test):

	def __init__(self, text=""):
		self.text = text

	def run(self):
		pass

	def what(self):
		return self.text

	def okay(self):
		return True

class TestGroup(Test):
	"""A group of tests, where all tests are executed"""

	def __init__(self, *tests):
		self.tests = tests
		self.all_okay = False

	def test(self):
		self.all_okay = all([t.test() for t in self.tests])
		return self.all_okay

	def okay(self):
		return self.all_okay

class AbortingTestGroup(TestGroup):
	"""A group of tests, where the first failed test stops the entire group"""

	def test(self):
		self.all_okay = all(t.test() for t in self.tests)
		return self.all_okay

class FileMimeTest(Test):
	"""Is the specified file existant of a given mimetype?"""

	def __init__(self, fname, expected_mime_toplevel):
		self.fname = fname
		self.expected_mime_toplevel = expected_mime_toplevel

	def run(self):
		self.exists = os.path.exists(self.fname)
		self.mimetype = self.run_prog("file", "--mime-type", "-b", self.fname)
		self.ttype = self.mimetype.split("/", 1)[0]

	def what(self):
		if not self.exists:
			return '# file "%s" does not exist' % self.fname
		return '# file "%s" exists and is "%s" (expected "%s/*")' % (self.fname, self.mimetype, self.expected_mime_toplevel)

	def okay(self):
		return self.exists and self.ttype == self.expected_mime_toplevel

class ReturnCodeTest(Test):

	def __init__(self, progname, args=None, retcode=0, showstdout=False, inputText = ""):
		self.progname = progname
		self.inputText = inputText
		self.cmdline = [progname] + map(str, args or [])
		self.expected = retcode
		self.exception = None
		self.showstdout = showstdout
		self.output = None
		self.stdout = ""

	def _get_output(self, tpl):
		self.stdout = tpl[0]
		return tpl[2]

	def run(self):
		try:
			self.output = self._get_output(self.exec_(self.inputText, *self.cmdline))
		except Exception as e:
			self.exception = e
	
	def _s(self, a):
		return " ".join(map(str, a))

	def _formatCall(self):
		if self.inputText:
			return "echo -ne %r | %s" % (self.inputText, self._s(self.cmdline))
		return self._s(self.cmdline)

	def what(self):
		return "$ %s #- exited with %s (expected %d)%s" % (
					self._formatCall(),
					str(self.output) if self.exception is None else repr(str(self.exception)),
					self.expected,
				"" if not (self.showstdout and self.stdout) else ("\n%s" % self.stdout))

	def okay(self):
		return self.output == self.expected

class ExecutionTest(ReturnCodeTest):
	"""Run a programm and compare the lines of stdout to a list of expected output."""

	def __init__(self, progname, args, expected, compare=operator.eq, key=lambda x: x, inputText = ""):
		super(ExecutionTest, self).__init__(progname, args, expected, inputText=inputText)
		self.compare = compare
		self.key = key

	def _get_output(self, tpl):
		return tpl[0].split("\n")

	def what(self):
		_r = repr
		return "$ " + self._formatCall() + "\n" + \
				(("received: " + _r(self.output))
						if self.exception is None
						else str(self.exception)) + "\n" + \
			   "expected: " + _r(self.expected)

	def okay(self):
		return self.exception is None and self.compare(self.key(self.output), self.key(self.expected))

class CreateFile(Test):

	def __init__(self, fname, contents, silent=False):
		self.fname = fname
		self.contents = contents
		self.failed = False
		self.silent = silent

	def run(self):
		try:
			with open(self.fname, "w") as f:
				f.write(self.contents)
		except:
			self.failed = True

	def okay(self):
		return not self.failed

	def what(self):
		if self.failed:
			return "Cannot setup input file %s. My bad :(" % self.fname
		return "Creating input file %s ..." % self.fname \
				if self.silent \
				else "$ echo -ne %r > %s" % (self.contents, self.fname)


class FileContentTest(ExecutionTest):

	def __init__(self, fname, expected):
		super(FileContentTest, self).__init__("cat", [fname], expected)
		
class FileContentSHA256Test(ExecutionTest):

	def __init__(self, fname, expected):
		super(FileContentSHA256Test, self).__init__("shasum", ["-a", "256", fname], ["%s  %s" % (expected, fname)])


#-------------------------------------------------------------------------------

class ArenaTestApp(ExecutionTest):

	def __init__(self, progname):
		super(ArenaTestApp, self).__init__(progname, [], "<A list with exactly one duplication>", compare=self.wasAddressReused)

	def _formatCall(self):
		return self.progname		

	def wasAddressReused(self, got, ignored):
		addresses = set([l.partition(" = ")[2] for l in got])
		return len(addresses) == 2


#-------------------------------------------------------------------------------

def validate():
	tests = TestGroup(
		AbortingTestGroup(
			# FileContentSHA256Test("testapp.c", "5579c557a8f0f597aeeeff6b7693134716d15628475e3c43325ce9f6646b8ecf"),
			EmptyTestLine("If your testapp doesn't print a thing, make sure you got the latest version."),
			ArenaTestApp("./testapp"),
			EmptyTestLine("[OKAY]"),
		),
	)

	return tests.test()

if __name__ == "__main__":
	sys.exit(0 if validate() else 1)

