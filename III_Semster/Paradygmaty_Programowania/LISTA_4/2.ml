let rec parzystaHelper (xs,p) = 
  if (xs=[]) then []
  else
  if (p) then List.hd xs :: parzystaHelper(List.tl xs,false)
  else parzystaHelper(List.tl xs, true);;

let parzyste xs = parzystaHelper (xs,false);;
let nieparzyste xs = parzystaHelper (xs,true);;

let rec parzystaHelper (xs,p) = 
  if (xs=[]) then []
  else
  if (p) then List.hd xs :: parzystaHelper(List.tl xs,false)
  else parzystaHelper(List.tl xs, true);;

let rec razy2 xs =
if xs=[] then []
else
(List.hd xs)*2 :: razy2 (List.tl xs);;

let rec kwadrat xs =
if xs=[] then []
else
(List.hd xs)*(List.hd xs) :: kwadrat (List.tl xs);;

let for_yield xs filter f =
f (filter xs);;

for_yield [1;2;3;4] parzyste razy2;; 
for_yield [1;2;3;4;5;6;7;8;9;10] parzyste razy2;; 
for_yield [1] parzyste razy2;; 
for_yield [] parzyste razy2;; 
for_yield [1;2;3;4] nieparzyste razy2;; 
for_yield [1;2;3;4;5;6;7;8;9;10] nieparzyste kwadrat;;