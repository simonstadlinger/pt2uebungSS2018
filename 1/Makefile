LIBARENA ?= libarena
ABGABE=abgabe.tar

all: libarena.a testapp

testapp: CFLAGS += -I$(LIBARENA)
testapp: LDFLAGS += -L$(LIBARENA)
testapp: LDLIBS += -larena

libarena.a:
	make -C $(LIBARENA) libarena.a

clean:
	$(RM) testapp $(ABGABE)

rclean: clean
	make -C $(LIBARENA) clean

abgabe: rclean
	tar -cf $(ABGABE) --exclude $(ABGABE) .
