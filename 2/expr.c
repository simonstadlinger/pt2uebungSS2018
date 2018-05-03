#include <stdio.h>
#include <stdlib.h>


#include "expr.h"


void print(Expr * e, FILE* f)
{
	e->_vtable->print(e, f);
}

double eval(Expr * e)
{
	return e->_vtable->eval(e);
}

int precedence(Expr * e)
{
	return e->_vtable->precedence();
}

void Number_print(Expr * this, FILE* f)
{
	fprintf(f, "%f", ((Number*) this)->val);
}

int Number_precedence()
{
	return PREC_LITERAL;
}

double Number_eval(Expr * this)
{
	return ((Number*) this)->val;
}

Expr_vtable vtable_Number = {
	.print = Number_print,
	.eval = Number_eval,
	.precedence = Number_precedence };

Expr* number(double v)
{
	Number * r;
	ALLOCATE(Number, r);
	r->val = v;
	r->_expr._vtable = &vtable_Number;
	return &r->_expr;
}


void Plus_print(Expr * this, FILE* f)
{
	
	// lowest precedence, no need to put parentheses

	print(((Plus*) this)->_binary.e1, f);
	fprintf(f, " + ");
	print(((Plus*) this)->_binary.e2, f);
}

int Plus_precedence()
{
	return PREC_TERM;
}

double Plus_eval(Expr * this)
{

	return eval(((Plus*) this)->_binary.e1) + eval(((Plus*) this)->_binary.e2);
}

Expr_vtable vtable_Plus = {
	.print = Plus_print,
	.eval = Plus_eval,
	.precedence = Plus_precedence };

Expr* plus(Expr *e1, Expr* e2)
{
	Plus * r;
	ALLOCATE(Plus, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr._vtable = &vtable_Plus;
	return &r->_binary._expr;
}


void Minus_print(Expr * this, FILE* f)
{
	Expr* e1 = ((Minus*) this)->_binary.e1;
	Expr* e2 = ((Minus*) this)->_binary.e2;

	// second operand needs parentheses unless it has higher precedence
	print(e1, f);
	fprintf(f, " - ");
	if (precedence(e2) <= PREC_TERM)
	{
		fprintf(f, "("); print(e2, f); fprintf(f, ")");
	}
	else 
	{
		print(e2, f);
	}
}

int Minus_precedence(Expr * this)
{
	return PREC_TERM;
}

double Minus_eval(Expr * this)
{
	Expr* e1 = ((Minus*) this)->_binary.e1;
	Expr* e2 = ((Minus*) this)->_binary.e2;

	return eval(e1) - eval(e2);
}

Expr_vtable vtable_Minus = {
	.print = Minus_print,
	.eval = Minus_eval,
	.precedence = Minus_precedence };

Expr* minus(Expr *e1, Expr* e2)
{
	Minus * r;
	ALLOCATE(Minus, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr._vtable = &vtable_Minus;
	return &r->_binary._expr;
}

void Times_print(Expr * this, FILE* f)
{
	Expr* e1 = ((Times*) this)->_binary.e1;
	Expr* e2 = ((Times*) this)->_binary.e2;

	// operands need parentheses if they have lower precedence
	if (precedence(e1) < PREC_FACTOR)
	{
		fprintf(f, "("); print(e1, f); fprintf(f, ")");
	}
	else
	{
		print(e1, f);
	}
	fprintf(f, " * ");
	if (precedence(e2) < PREC_FACTOR)
	{
		fprintf(f, "("); print(e2, f); fprintf(f, ")");
	}
	else
	{
		print(e2, f);
	}
}

int Times_precedence(Expr * this)
{
	return PREC_FACTOR;
}

double Times_eval(Expr * this)
{
	Expr* e1 = ((Times*) this)->_binary.e1;
	Expr* e2 = ((Times*) this)->_binary.e2;

	return eval(e1) * eval(e2);
}

Expr_vtable vtable_Times = {
	.print = Times_print,
	.eval = Times_eval,
	.precedence = Times_precedence };

Expr* times(Expr *e1, Expr* e2)
{
	Times * r;
	ALLOCATE(Times, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr._vtable = &vtable_Times;
	return &r->_binary._expr;
}

void Over_print(Expr * this, FILE* f)
{
	Expr* e1 = ((Over*) this)->_binary.e1;
	Expr* e2 = ((Over*) this)->_binary.e2;

	// second operand needs parentheses even if at same priority
	if (precedence(e1) < PREC_FACTOR)
	{
		fprintf(f, "("); print(e1, f); fprintf(f, ")");
	}
	else
	{
		print(e1, f);
	}
	fprintf(f, " / ");
	if (precedence(e2) <= PREC_FACTOR)
	{
		fprintf(f, "("); print(e2, f); fprintf(f, ")");
	}
	else
	{
		print(e2, f);
	}
}

int Over_precedence(Expr * this)
{
	return PREC_FACTOR;
}

double Over_eval(Expr * this)
{
	Expr* e1 = ((Over*) this)->_binary.e1;
	Expr* e2 = ((Over*) this)->_binary.e2;

	return eval(e1) / eval(e2);
}

Expr_vtable vtable_Over = {
	.print = Over_print,
	.eval = Over_eval,
	.precedence = Over_precedence };

Expr* over(Expr *e1, Expr* e2)
{
	Over * r;
	ALLOCATE(Over, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr._vtable = &vtable_Over;
	return &r->_binary._expr;
}

int main(int argc, char *argv[])
{
	Expr *p = plus(number(2), times(number(4), over(number(10), number(3))));
	print(p, stdout);
	printf(" => %f\n", eval(p));

/*  Für Zusatzaufgabe <string.h> einbinden und auskommentierten Code aktivieren
	if (argc > 1 && !strcmp(argv[1], "--zusatz")) {
		printf("p is Plus: %s\n", isinstance(p, Plus) ? "yes" : "no");
		printf("p is Expr: %s\n", isinstance(p, Expr) ? "yes" : "no");
		printf("p is Number: %s\n", isinstance(p, Number) ? "yes" : "no");
	}
*/

	return 0;
}
