#include <allocate.h>

#include <stdio.h>

int main()
{
	void *f, *g, *h;
	f = allocate();
	g = allocate();
	deallocate(g);
	h = allocate();
	deallocate(f);
	deallocate(h);
	printf("f = %p\n", f);
	printf("g = %p\n", g);
	printf("h = %p\n", h);
	return 0;
}
