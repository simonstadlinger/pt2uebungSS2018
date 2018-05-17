JFLAGS = -g
JC = javac
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Account.java \
	 AccountTest.java \
	 F2C.java \
	 C2F.java \
	 Minimum.java

all: classes
abgabe: clean 
	tar cfvz abgabe.tar.gz $(CLASSES) In.java TempConv/Celsius.java TempConv/Conversion.java TempConv/Fahrenheit.java Makefile

classes: $(CLASSES:.java=.class)
clean:
	$(RM) *.class
	$(RM) TempConv/*.class
	$(RM) abgabe.tar.gz  

	
