#include <cstdio>

using namespace std;

#define PREC_TERM    0
#define PREC_FACTOR  1
#define PREC_LITERAL 2

// Abstract base class for expressions
class Expr 
{
public:
	virtual void print(FILE *f) = 0;
	virtual double eval() = 0;
	virtual int precedence() = 0;
};

// Representation for literal numbers
class Number: public Expr
{
	double val;

public:
	Number(double v)
		: val(v)
	{
		;;
	}
	
	void print(FILE *);

	double eval()
	{
		return val;
	}

	int precedence()
	{
		return PREC_LITERAL;
	}
};

// Base class for binary operators
class Binary: public Expr
{
protected:
	Expr *e1, *e2;

	Binary(Expr *a, Expr *b)
		: e1(a), e2(b)
	{
		;;
	}
};

// a+b
class Plus: public Binary
{
public:
	Plus(Expr *e1, Expr *e2)
		: Binary(e1, e2)
	{
		;;
	}

	void print(FILE *);
	
	double eval()
	{
		return e1->eval() + e2->eval();
	}

	int precedence()
	{
		return PREC_TERM;
	}
};

// a-b
class Minus: public Binary
{
public:
	Minus(Expr *e1, Expr *e2)
		: Binary(e1, e2)
	{
		;;
	}

	void print(FILE *);
	
	double eval()
	{
		return e1->eval() - e2->eval();
	}

	int precedence()
	{
		return PREC_TERM;
	}
};

// a*b
class Times: public Binary
{
public:
	Times(Expr *e1, Expr *e2)
		: Binary(e1,e2)
	{
		;;
	}

	void print(FILE *);
	
	double eval()
	{
		return e1->eval() * e2->eval();
	}

	int precedence()
	{
		return PREC_FACTOR;
	}
};


// a/b
class Over: public Binary
{
public:
	Over(Expr *e1, Expr *e2)
		: Binary(e1, e2)
	{
		;;
	}

	void print(FILE *);
	
	double eval()
	{
		return e1->eval() / e2->eval();
	}

	int precedence()
	{
		return PREC_FACTOR;
	}
};

// Implementation of print methods

void Number::print(FILE* f)
{
	fprintf(f, "%f", val);
}

void Plus::print(FILE* f)
{
	// lowest precedence, no need to put parentheses
	e1->print(f);
	fprintf(f, " + ");
	e2->print(f);
}

void Minus::print(FILE* f)
{
	// second operand needs parentheses unless it has higher precedence
	e1->print(f);
	fprintf(f, " - ");
	if (e2->precedence() <= PREC_TERM) {
		fprintf(f, "("); e2->print(f); fprintf(f, ")"); 
	} else {
		e2->print(f);
	}
}

void Times::print(FILE* f)
{
	// operands need parentheses if they have lower precedence
	if (e1->precedence() < PREC_FACTOR) {
		fprintf(f, "("); e1->print(f); fprintf(f, ")"); 
	} else { 
		e1->print(f);
	}
	fprintf(f, " * ");
	if (e2->precedence() < PREC_FACTOR) {
		fprintf(f, "("); e2->print(f); fprintf(f, ")"); 
	} else {
		e2->print(f);
	}
}

void Over::print(FILE* f)
{
	// second operand needs parentheses even if at same priority
	if (e1->precedence() < PREC_FACTOR) {
		fprintf(f, "("); e1->print(f); fprintf(f, ")"); 
	} else {
		e1->print(f);
	}
	fprintf(f, " / ");
	if (e2->precedence() <= PREC_FACTOR) {
		fprintf(f, "("); e2->print(f); fprintf(f, ")"); 
	} else {
		e2->print(f);
	}
}


// Test application

int main()
{
	Expr *p = new Plus(new Number(2), 
		               new Times(new Number(4), 
			           new Over(new Number(10), 
					   new Number(3))));
	p->print(stdout);
	printf(" => %g\n", p->eval());
	
	// Intentionally lacking delete for the sake of this task's simplicity
	// Don't do this at home (or in your job)
}
