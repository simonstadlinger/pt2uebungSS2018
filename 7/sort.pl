quicksort([], []).

quicksort([H | T], Y) :-
qpart(H, T, L, Ge),
quicksort(L, SL),
quicksort(Ge, SGe),
append(SL, [H | SGe], Y),!.


qpart(Pivot, [H | T], [H | Less], GrEq) :-
H < Pivot,
qpart(Pivot, T, Less, GrEq).

qpart(Pivot, [H | T], Less, [H | GrEq]) :-
H >= Pivot,
qpart(Pivot, T, Less, GrEq).

qpart(_, [], [], []).




mergesort([], []) :- !.

mergesort([X], [X]) :- !.

mergesort(X, Y) :-
mpart(X, A, B),
mergesort(A, SA),
mergesort(B, SB),
merge(SA, SB, Y),!.


mpart([], [], []).
mpart([X], [X], []).
mpart([X, Y | T], [X | A], [Y | B]) :-
mpart(T, A, B).


merge([HA | TA], [HB | TB], [HA | Y]) :-
HA < HB,
merge(TA, [HB | TB], Y).

merge([HA | TA], [HB | TB], [HB | Y]) :-
HA >= HB,
merge([HA | TA], TB, Y).

merge([], X, X).

merge(X, [], X).

