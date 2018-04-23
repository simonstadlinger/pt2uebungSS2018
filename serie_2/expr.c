#include <stdio.h>

#include "expr.h"

Expr* plus(Expr *e1, Expr* e2)
{
	ALLOCATE(Plus, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	return &r->_binary._expr;
}

void Plus_print(...)
{
	...
	print(this->_binary.e1, f);
	fprintf(f, " + ");
	print(this->_binary.e2, f);
}

int Plus_precedence(...)
{
	return PREC_TERM;
}

double Plus_eval(...)
{
	...
	return eval(this->_binary.e1) + eval(this->_binary.e2);
}

int main(int argc, char *argv[])
{
	Expr *p = plus(number(2), times(number(4), over(number(10), number(3))));
	print(p, stdout);
	printf(" => %g\n", eval(p));

/*  Für Zusatzaufgabe <string.h> einbinden und auskommentierten Code aktivieren
	if (argc > 1 && !strcmp(argv[1], "--zusatz")) {
		printf("p is Plus: %s\n", isinstance(p, Plus) ? "yes" : "no");
		printf("p is Expr: %s\n", isinstance(p, Expr) ? "yes" : "no");
		printf("p is Number: %s\n", isinstance(p, Number) ? "yes" : "no");
	}
*/

	return 0;
}
