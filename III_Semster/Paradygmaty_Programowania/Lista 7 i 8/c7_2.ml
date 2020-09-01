module CyclicArrayQueue : QUEUE_MUT =
  struct
		type 'a t = { a : 'a option array; mutable f: int; mutable r: int }
		exception Empty of string
		exception Full of string
		
		let empty n =
			{ a = Array.make (n + 1) None; f = 0; r = 0 }
			
		let isEmpty q =
			q.r = q.f
		
		let isFull q =
			q.r - q.f = -1 || q.r - q.f = Array.length q.a - 1
			
		let enqueue (element, q) =
			if (isFull q) then raise (Full "module CyclicArrayQueue: enqueue")
			else 
				q.a.(q.r) <- Some element;
				if q.r = Array.length q.a - 1 then q.r <- 0 
				else q.r <- succ(q.r)

		let dequeue q =
			if (isEmpty q) then ()
			else
				if q.f = Array.length q.a - 1 then q.f <- 0 
				else q.f <- succ(q.f)

		let first q =
			if (isEmpty q) then raise (Empty "module CyclicArrayQueue: first")
			else match (q.a.(q.f)) with
			| Some value -> value
			| None ->  failwith "module CyclicArrayQueue: first (implementation error!!!)"
	end;;
(***************************************************************************************)		

module type QUEUE_MUT =
	sig
		type 'a t
		exception Empty of string
		exception Full of string
		val empty: int -> 'a t
		val enqueue: 'a * 'a t -> unit
		val dequeue: 'a t -> unit
		val first: 'a t -> 'a
		val isEmpty: 'a t -> bool
		val isFull: 'a t -> bool
	end;;

let q = CyclicArrayQueue.empty 3;;
CyclicArrayQueue.isEmpty q = true;;
CyclicArrayQueue.isFull q = false;;
try CyclicArrayQueue.first q; false with CyclicArrayQueue.Empty "module CyclicArrayQueue: first" -> true;;
CyclicArrayQueue.enqueue ('a', q);;
CyclicArrayQueue.isEmpty q = false;;
CyclicArrayQueue.enqueue ('b', q);;
CyclicArrayQueue.enqueue ('c', q);;
CyclicArrayQueue.isFull q = true;;
try CyclicArrayQueue.enqueue ('d', q); false with CyclicArrayQueue.Full "module CyclicArrayQueue: enqueue" -> true;;
CyclicArrayQueue.first q = 'a';;
CyclicArrayQueue.dequeue q;;
CyclicArrayQueue.first q = 'b';;
CyclicArrayQueue.isFull q = false;;
CyclicArrayQueue.enqueue ('d', q);;
CyclicArrayQueue.isFull q = true;;
CyclicArrayQueue.dequeue q;;
CyclicArrayQueue.first q = 'c';;
CyclicArrayQueue.enqueue ('e', q);;
CyclicArrayQueue.dequeue q;;
CyclicArrayQueue.first q = 'd';;
CyclicArrayQueue.dequeue q;;
CyclicArrayQueue.first q = 'e';;
CyclicArrayQueue.dequeue q;;
CyclicArrayQueue.isEmpty q = true;;
CyclicArrayQueue.dequeue q;;

