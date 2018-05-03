ABGABE=abgabe.tar.gz
CC=gcc
CFLAGS=-g 
TARGETS=expr 

all: $(TARGETS)

expr: expr.c expr.h
	$(CC) expr.c -o expr

clean:
	rm -f $(TARGETS)
	rm -f $(ABGABE)
abgabe: clean
	tar -czvf $(ABGABE) --exclude expr.cpp --exclude Serie2.pdf .

.PHONY: all clean abgabe
