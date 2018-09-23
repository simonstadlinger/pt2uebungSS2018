member(X, [X]).
member(H, [H|_]).
member(X,[_|T]) :- member(X, T).

generate(X) :-
permutation([lukas, david, alexander, florian], [N1, N2, N3, N4]),
permutation([11, 12, 13, 14], [A1, A2, A3, A4]),
permutation([berlin, mainz, leipzig, hamburg], [S1, S2, S3, S4]),
permutation([klavier, schach, fussball, handball], [H1, H2, H3, H4]),
X = [[N1, A1, S1, H1], [N2, A2, S2, H2], [N3, A3, S3, H3], [N4, A4, S4, H4]].


raetsel :-
generate(X),

member([david, _, berlin, _], X),

member([_, A1, _, schach], X),
member([_, A2, mainz, _], X),
A1 < A2,

member([alexander, _, leipzig, _], X),

member([florian, B1, _, klavier], X),
member([david, B2, _, _], X),
B1 > B2,

member([_, C1, leipzig, _], X),
C2 is C1 + 1,
member([_, C2, _, handball], X),

member([_, 11, _, fussball], X),

member([lukas, _, hamburg, _], X),

member([alexander, 12, _, _], X),

member([lukas, 13, _, _], X),

schreiben(X),!.


schreiben([[N1, A1, S1, H1], [N2, A2, S2, H2], [N3, A3, S3, H3], [N4, A4, S4, H4]]) :-
format("~w ist ~w Jahre alt, wohnt in ~w und spielt ~w.~n", [N1, A1, S1, H1]),
format("~w ist ~w Jahre alt, wohnt in ~w und spielt ~w.~n", [N2, A2, S2, H2]),
format("~w ist ~w Jahre alt, wohnt in ~w und spielt ~w.~n", [N3, A3, S3, H3]),
format("~w ist ~w Jahre alt, wohnt in ~w und spielt ~w.~n", [N4, A4, S4, H4]).