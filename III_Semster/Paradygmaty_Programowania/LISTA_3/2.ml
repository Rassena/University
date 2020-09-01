let polacz (xs1,xs2) =
if (List.length xs1 >= List.length xs2) then polaczHelper (xs1,xs2)
else polaczHelper(xs2,xs1);;

let rec polaczHelper  (xs1,xs2)  =
if (xs1=[]) then []
else if xs2=[] then List.hd xs1 ::polaczHelper(List.tl xs1,[])
else (List.hd xs1 + List.hd xs2) :: polaczHelper(List.tl xs1,List.tl xs2);;

polacz ([],[]);;
polacz ([5;4;3;2],[1;2;3;4;5;6]);; 
polacz ([1;2;3],[]);;
polacz ([],[3;2;1]);;
polacz ([-1],[-2]);;