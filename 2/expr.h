#include <stdlib.h>

#define PREC_TERM    0
#define PREC_FACTOR  1
#define PREC_LITERAL 2

#define ALLOCATE(typ, p) (p = (typ*) malloc(sizeof(typ)))

typedef struct expr
{
	 void (*print) (void * this, FILE *f);
	 double (*eval) (void * this);
	 int (*precedence) ();
} Expr;

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
