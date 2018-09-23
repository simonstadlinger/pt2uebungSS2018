% handle empty list
odd_indices([], []).

% handle list with only one element
odd_indices([_], []) :-
!.

% handle lists with more than one element
odd_indices([_, O | T], Y) :-
odd_indices(T, Z),
append([O], Z, Y).
