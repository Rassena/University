type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t;; 
	let odd n = n mod 2 <> 0;; 

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

	let rec lparzyste k = 
		if (odd k) then lparzyste (k+1)
		else LCons (k, lazy (lparzyste (k+2)));; 

	let rec mainllist k = 
		if (k<6) then LCons (k, lazy (mainllist (k+1)))
		else LCons (k, lazy LNil);;

	let rec lpolacz lxs1 lxs2 =
		if(lxs2 = LNil) then LCons (lhd lxs1, lazy lxs1)
		else if (lxs1 =LNil) then LCons (lhd lxs2, lazy lxs2)
		else if(lhd lxs1< lhd lxs2) then LCons (lhd lxs1, lazy(lpolacz (ltl lxs1) lxs2))
		else LCons (lhd lxs2, lazy(lpolacz lxs1 (ltl lxs2)));;


	let lxsn = lnieparzyste 1;;
	let lxsp = lparzyste 1;;
	let lxsm = mainllist 0;;

	ltake(10,lpolacz lxsn lxsp);;
	ltake(10,lpolacz lxsn lxsm);;
	ltake(10,lpolacz lxsp lxsm);;
	ltake(10,lpolacz lxsp lxsp);;
