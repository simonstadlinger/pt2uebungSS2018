#include <stdlib.h>

#define PREC_TERM    0
#define PREC_FACTOR  1
#define PREC_LITERAL 2

#define ALLOCATE(typ, p) (p = (typ*) malloc(sizeof(typ)))

typedef struct expr Expr;

typedef struct 
{
	void (*print) (Expr * this, FILE *f);
	double (*eval) (Expr * this);
	int (*precedence) ();
} Expr_vtable;

struct expr
{ 
	Expr_vtable * _vtable;
};

typedef struct binary
{
	Expr _expr;

	Expr *e1;
	Expr *e2;
} Binary;

typedef struct number
{
	Expr _expr;
	double val;

} Number;

typedef struct plus
{
	Binary _binary;

} Plus;

typedef struct minus
{
	Binary _binary;

} Minus;

typedef struct times
{
	Binary _binary;

} Times;

typedef struct over
{
	Binary _binary;

} Over;
