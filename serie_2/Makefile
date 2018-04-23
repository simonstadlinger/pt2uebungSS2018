LIBARENA = libarena

CC := gcc
CFLAGS := -g -ansi -pedantic -W -Wall -std=c99 -I $(LIBARENA)
LDLIBS := -L$(LIBARENA) -larena

TARGETS := libarena.a testapp

libarena.a:
	    $(MAKE) --directory=$(LIBARENA) $@

all: $(TARGETS)

clean:
	    rm -f $(TARGETS)

.PHONY: all clean
