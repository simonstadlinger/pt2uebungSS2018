
<<<<<<< HEAD
CC  = gcc
CFLAGS := -g -ansi -pedantic -W -Wall -std=c99 
=======
CC=gcc
CFLAGS=-g #-ansi -pedantic -W -Wall 		
TARGETS=expr 
>>>>>>> 831121d7b029840da3a07541e66af078aefe6fd5

all: $(TARGETS)

expr: expr.c expr.h
	$(CC) -o expr expr.c expr.h




clean:
	    rm -f $(TARGETS)

.PHONY: all clean
