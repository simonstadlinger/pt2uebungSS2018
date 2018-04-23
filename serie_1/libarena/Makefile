libarena.a:	arena.o allocate.o
	ar rcs $@ $^

arena.o: arena.h
allocacate.o: allocate.h arena.h

clean:
	$(RM) arena.o libarena.a allocate.o

