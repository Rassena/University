	type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);; 

	let lhd = function
    		LNil         -> failwith "lhd"
		| LCons (x, _) -> x;; 

	let ltl = function
    		LNil -> failwith "ltl"
  		| LCons (_, xf) -> xf();;

	let rec lfrom k = LCons (k, function () -> lfrom (k+1));; 

	let rec ltakeHelper lxs xs =
		match lxs with
		(0, _)  ->xs
		|(_, LNil) -> xs 
		|(n, LCons(x, fx)) -> ltakeHelper ((n-1),fx()) (xs@[x]);;

	let ltake (n,lxs) = ltakeHelper (n,lxs) [];;

	ltake(10,lfrom 30);;
	ltake(0,lfrom 30);;
	ltake(5,lfrom (-30));;