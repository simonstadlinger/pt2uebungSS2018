#include <stdio.h>

#include "expr.h"

Expr* number(double v)
{
	ALLOCATE(Number, r);
	r->val = v;
	return &r->_expr;
}

void Number_print(void * this, FILE* f)
{
	//...
	print(this->val, f);
}

int Number_precedence()
{
	return PREC_LITERAL;
}

double Number_eval(void * this)
{
	//Todo evtl
	return this->val;
}

Expr* plus(Expr *e1, Expr* e2)
{
	ALLOCATE(Plus, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	return &r->_binary._expr;
}

void Plus_print(void * this, FILE* f)
{
	// lowest precedence, no need to put parentheses
	print(this->_binary.e1, f);
	fprintf(f, " + ");
	print(this->_binary.e2, f);
}

int Plus_precedence()
{
	return PREC_TERM;
}

double Plus_eval(void * this)
{
	//...
	return eval(this->_binary.e1) + eval(this->_binary.e2);
}

Expr* minus(Expr *e1, Expr* e2)
{
	ALLOCATE(Minus, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	return &r->_binary._expr;
}

void Minus_print(void * this, FILE* f)
{
	// second operand needs parentheses unless it has higher precedence
	print(this->_binary.e1, f);
	fprintf(f, " - ");
	if (this->_binary.e1.precedence() <= PREC_TERM)
	{
		fprintf(f, "("); print(this->_binary.e2, f); fprintf(f, ")");
	}
	print(this->_binary.e2, f);
}

int Minus_precedence()
{
	return PREC_TERM;
}

double Minus_eval(void * this)
{
	//...
	return eval(this->_binary.e1) - eval(this->_binary.e2);
}

Expr* times(Expr *e1, Expr* e2)
{
	ALLOCATE(Times, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	return &r->_binary._expr;
}

void Times_print(void * this, FILE* f)
{
	//TODO operands need parentheses if they have lower precedence
	print(this->_binary.e1, f);
	fprintf(f, " * ");
	print(this->_binary.e2, f);
}

int Times_precedence()
{
	return PREC_FACTOR;
}

double Times_eval(void * this)
{
	//...
	return eval(this->_binary.e1) * eval(this->_binary.e2);
}

Expr* over(Expr *e1, Expr* e2)
{
	ALLOCATE(Over, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	return &r->_binary._expr;
}

void Over_print(void * this, FILE* f)
{
	//TODO second operand needs parentheses even if at same priority
	print(this->_binary.e1, f);
	fprintf(f, " / ");
	print(this->_binary.e2, f);
}

int Over_precedence()
{
	return PREC_FACTOR;
}

double Over_eval(void * this)
{
	//...
	return eval(this->_binary.e1) / eval(this->_binary.e2);
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
