type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t;; 
	
	let lhd = function
 		LNil -> failwith "lhd"
		| LCons (x, _) -> x;;

	let ltl = function 
    		LNil -> failwith "ltl"
  		| LCons (_, lazy t) -> t;;

	let rec ldluzsza = function
		(0,_) -> true
		| (_, LNil) -> false
		| (n, LCons(x,lazy xs)) -> ldluzsza(n-1,xs);;

	let rec ltake = function 
		(0,_) -> []
		| (_, LNil) -> []
		| (n, LCons(x,lazy xs)) -> x::ltake(n-1,xs);; 
	
	let rec mainllist k = 
		if (k<6) then LCons (k, lazy (mainllist (k+1)))
		else LCons (k, lazy LNil);;

	let rec llistofllist k =
		if(k>0) then LCons (mainllist k, lazy (llistofllist (k-1)))
		else LCons (LNil, lazy LNil);;
	
	let rec lfiltr n lxs =
		if lxs =LNil then LNil
		else if (ldluzsza (n, (lhd lxs))) then LCons (lhd lxs, lazy (lfiltr n (ltl lxs)))
		else lfiltr n (ltl lxs);;


	let lxs = llistofllist 10;;
	let flxs =lfiltr 3 lxs;;
	ltake (10,lxs);;
	ltake (10,flxs);;