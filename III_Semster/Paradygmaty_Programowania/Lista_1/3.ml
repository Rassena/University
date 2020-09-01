let fun_kwad x = x*x;;

let rec przeksztalc (xs, func) = 
if xs=[] then []
else (func(List.hd xs) :: przeksztalc(List.tl xs,func));;

przeksztalc ([4;1;5;1;5;9],fun_kwad);;
przeksztalc ([],fun_kwad);;
przeksztalc ([-2;0;3],fun_kwad);;
