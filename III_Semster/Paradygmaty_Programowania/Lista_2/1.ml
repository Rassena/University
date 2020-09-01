

let podziel (xs) =
[kwadratCo2(xs,false)] @ [mnoz3Co2 (xs, false)] ;;

let rec kwadratCo2  (xs, b) = 
if xs=[] then []
else
match b with
|false ->(List.hd xs * List.hd xs) :: kwadratCo2 (List.tl xs, true)
|true ->kwadratCo2(List.tl xs, false);;

let rec mnoz3Co2 (xs,b) =
if xs=[] then []
else
match b with
|false ->mnoz3Co2 (List.tl xs, true)
|true ->(List.hd xs * 3) :: mnoz3Co2 (List.tl xs, false);;

podziel [3;6;8;9;13];; 
podziel [];;
podziel [1];;
podziel [1;2;3;4;5;6];;


