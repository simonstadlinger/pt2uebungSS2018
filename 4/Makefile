ABGABE=abgabe.tar.gz
TARGETS= a1-empirisch.pdf\
	 a1-theoretisch.pdf\
	 a2.pdf
CLATEX= latexmk -pdf -use-make

all:$(TARGETS)

a1-empirisch.pdf: a1-empirisch.tex
	$(CLATEX) a1-empirisch.tex 

a1-theoretisch.pdf: a1-theoretisch.tex
	$(CLATEX) a1-theoretisch.tex 

a2.pdf: a2.tex
	$(CLATEX) a2.tex 

abgabe: all clean
	tar cfvz $(ABGABE) $(TARGETS) anhaenge

clean: 
	$(RM) *.aux
	$(RM) *.log
	$(RM) *.synctex.gz
	$(RM) *fdb_latexmk
	$(RM) *.fls
	$(RM) *.tex.bak
	$(RM) $(ABGABE)
		
.PHONY: clean all
