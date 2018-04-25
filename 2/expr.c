#include <stdio.h>
#include <stdlib.h>


#include "expr.h"



void Number_print(void * this, FILE* f)
{
	fprintf(f, "%f", ((Number*) this)->val);
}

int Number_precedence()
{
	return PREC_LITERAL;
}

double Number_eval(void * this)
{
	return ((Number*) this)->val;
}

Expr* number(double v)
{
	Number * r;
	r->val = v;
	r->_expr.print = Number_print;
	r->_expr.eval = Number_eval;
	r->_expr.precedence = Number_precedence;
	return &r->_expr;
}



void Plus_print(void * this, FILE* f)
{
	Expr* e1 = ((Plus*) this)->_binary.e1;
	Expr* e2 = ((Plus*) this)->_binary.e2;

	// lowest precedence, no need to put parentheses
	e1->print(e1, f);
	fprintf(f, " + ");
	e2->print(e2, f);
}

int Plus_precedence()
{
	return PREC_TERM;
}

double Plus_eval(void * this)
{
	Expr* e1 = ((Plus*) this)->_binary.e1;
	Expr* e2 = ((Plus*) this)->_binary.e2;

	return e1->eval(e1) + e2->eval(e2);
}

Expr* plus(Expr *e1, Expr* e2)
{
	Plus * r;
	ALLOCATE(Plus, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr.print = Plus_print;
	r->_binary._expr.eval = Plus_eval;
	r->_binary._expr.precedence = Plus_precedence;
	return &r->_binary._expr;
}


void Minus_print(void * this, FILE* f)
{
	Expr* e1 = ((Minus*) this)->_binary.e1;
	Expr* e2 = ((Minus*) this)->_binary.e2;

	// second operand needs parentheses unless it has higher precedence
	e1->print(e1, f);
	fprintf(f, " - ");
	if (e2->precedence() <= PREC_TERM)
	{
		fprintf(f, "("); e2->print(e2, f); fprintf(f, ")");
	}
	else 
	{
		e2->print(e2, f);
	}
}

int Minus_precedence()
{
	return PREC_TERM;
}

double Minus_eval(void * this)
{
	Expr* e1 = ((Minus*) this)->_binary.e1;
	Expr* e2 = ((Minus*) this)->_binary.e2;

	return e1->eval(e1) - e2->eval(e2);
}

Expr* minus(Expr *e1, Expr* e2)
{
	Minus * r;
	ALLOCATE(Minus, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr.print = Minus_print;
	r->_binary._expr.eval = Minus_eval;
	r->_binary._expr.precedence = Minus_precedence;
	return &r->_binary._expr;
}

void Times_print(void * this, FILE* f)
{
	Expr* e1 = ((Times*) this)->_binary.e1;
	Expr* e2 = ((Times*) this)->_binary.e2;

	// operands need parentheses if they have lower precedence
	if (e1->precedence() < PREC_FACTOR)
	{
		fprintf(f, "("); e1->print(e1, f); fprintf(f, ")");
	}
	else
	{
		e1->print(e1, f);
	}
	fprintf(f, " * ");
	if (e2->precedence() < PREC_FACTOR)
	{
		fprintf(f, "("); e2->print(e2, f); fprintf(f, ")");
	}
	else
	{
		e2->print(e2, f);
	}
}

int Times_precedence()
{
	return PREC_FACTOR;
}

double Times_eval(void * this)
{
	Expr* e1 = ((Times*) this)->_binary.e1;
	Expr* e2 = ((Times*) this)->_binary.e2;

	return e1->eval(e1) * e2->eval(e2);
}

Expr* times(Expr *e1, Expr* e2)
{
	Times * r;
	ALLOCATE(Times, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr.print = Times_print;
	r->_binary._expr.eval = Times_eval;
	r->_binary._expr.precedence = Times_precedence;
	return &r->_binary._expr;
}

void Over_print(void * this, FILE* f)
{
	Expr* e1 = ((Over*) this)->_binary.e1;
	Expr* e2 = ((Over*) this)->_binary.e2;

	// second operand needs parentheses even if at same priority
	if (e1->precedence() < PREC_FACTOR)
	{
		fprintf(f, "("); e1->print(e1, f); fprintf(f, ")");
	}
	else
	{
		e1->print(e1, f);
	}
	fprintf(f, " / ");
	if (e2->precedence() <= PREC_FACTOR)
	{
		fprintf(f, "("); e2->print(e2, f); fprintf(f, ")");
	}
	else
	{
		e2->print(e2, f);
	}
}

int Over_precedence()
{
	return PREC_FACTOR;
}

double Over_eval(void * this)
{
	Expr* e1 = ((Over*) this)->_binary.e1;
	Expr* e2 = ((Over*) this)->_binary.e2;

	return e1->eval(e1) / e2->eval(e2);
}

Expr* over(Expr *e1, Expr* e2)
{
	Over * r;
	ALLOCATE(Over, r);
	r->_binary.e1 = e1;
	r->_binary.e2 = e2;
	r->_binary._expr.print = Over_print;
	r->_binary._expr.eval = Over_eval;
	r->_binary._expr.precedence = Over_precedence;
	return &r->_binary._expr;
}

int main(int argc, char *argv[])
{
	Expr *p = plus(number(2), times(number(4), over(number(10), number(3))));
	p->print(p, stdout);
	printf(" => %f\n", p->eval(p));

/*  Für Zusatzaufgabe <string.h> einbinden und auskommentierten Code aktivieren
	if (argc > 1 && !strcmp(argv[1], "--zusatz")) {
		printf("p is Plus: %s\n", isinstance(p, Plus) ? "yes" : "no");
		printf("p is Expr: %s\n", isinstance(p, Expr) ? "yes" : "no");
		printf("p is Number: %s\n", isinstance(p, Number) ? "yes" : "no");
	}
*/

	return 0;
}
