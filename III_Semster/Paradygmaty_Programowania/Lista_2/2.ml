
let rec grupuj xs = 
if xs = [] then []
else (grupa (List.hd xs, xs) ::  grupuj (pozostale(List.hd xs, xs)));;

let rec grupa (x, xs) =
if xs = [] then []
else if List.hd xs = x then List.hd xs :: grupa(x, List.tl xs)
else [];;

let rec pozostale (x, xs) =
if xs = [] then []
else if List.hd xs = x then pozostale(x, List.tl xs)
else xs;;

grupuj ["1";"1";"1";"1";"2";"3";"3";"1";"1";"4";"4"];; 

