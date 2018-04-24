
CC  = gcc
CFLAGS := -g -ansi -pedantic -W -Wall -std=c99 

TARGETS := expr 

expr: expr.c
	$(CC) $(CFLAGS) $< -o $@


all: $(TARGETS)

clean:
	    rm -f $(TARGETS)

.PHONY: all clean
