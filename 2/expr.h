#define PREC_TERM    0
#define PREC_FACTOR  1
#define PREC_LITERAL 2

typedef struct expr
{
	 void (*print) (void * this, FILE *f);
	 double (*eval) (void * this);
	 int (*precedence) ();
} Expr;

typedef struct binary
{
	Expr _expr;

	Expr e1;
	Expr e2;
} Binary;

typedef struct number
{
	Expr _expr;

	double val;

	_expr.print = Number_print;
	_expr.eval = Number_eval;
	_expr.precedence = Number_precedence;
} Number;

typedef struct plus
{
	Binary _binary;

	_binary._expr.print = Plus_print;
	_binary._expr.eval = Plus_eval;
	_binary._expr.precedence = Plus_precedence;
} Plus;

typedef struct minus
{
	Binary _binary;

	_binary._expr.print = Minus_print;
	_binary._expr.eval = Minus_eval;
	_binary._expr.precedence = Minus_precedence;
} Minus;

typedef struct times
{
	Binary _binary;

	_binary._expr.print = Times_print;
	_binary._expr.eval = Times_eval;
	_binary._expr.precedence = Times_precedence;
} Times;

typedef struct over
{
	Binary _binary;

	_binary._expr.print = Over_print;
	_binary._expr.eval = Over_eval;
	_binary._expr.precedence = Over_precedence;
} Over;
