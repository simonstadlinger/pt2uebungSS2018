
CC=gcc
CFLAGS=-g #-ansi -pedantic -W -Wall 		
TARGETS=expr 

all: $(TARGETS)

expr: expr.c expr.h
	$(CC) -o expr expr.c expr.h




clean:
	    rm -f $(TARGETS)

.PHONY: all clean
