	let odd n = n mod 2 <> 0;; 
	type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t;; 

	let lhd = function
 		LNil -> failwith "lhd"
		| LCons (x, _) -> x;;

	let ltl = function 
    		LNil -> failwith "ltl"
  		| LCons (_, lazy t) -> t;;

	let rec ltake = function 
		(0,_) -> []
		| (_, LNil) -> []
		| (n, LCons(x,lazy xs)) -> x::ltake(n-1,xs);; 
	
	let rec lnieparzyste k = 
		if (odd k) then LCons (k, lazy (lnieparzyste (k+2)))
		else lnieparzyste(k+1);; 

	ltake (10,lnieparzyste 3);;
	ltake (10,lnieparzyste 2);;
	ltake (0,lnieparzyste 2);;
	ltake (10,lnieparzyste (-5));;
	ltake (10,lnieparzyste (-8));;