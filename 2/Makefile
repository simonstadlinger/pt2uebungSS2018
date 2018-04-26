ABGABE=abgabe.tar
CC=gcc
CFLAGS=-g #-ansi -pedantic -W -Wall 		
TARGETS=expr 

all: $(TARGETS)

expr: expr.c expr.h
	$(CC) expr.c -o expr

clean:
	rm -f $(TARGETS)
	rm -f abgabe.tar
abgabe: clean
	tar -cf $(ABGABE) --exclude $(ABGABE) --exclude expr.cpp --exclude Serie2.pdf .

.PHONY: all clean abgabe
