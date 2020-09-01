module ListQueue : QUEUE_FUN =
  struct
		type 'a t = 'a list
		
    exception Empty of string
    
    let empty () = []
		
		let enqueue (element, queue) =
			queue @ [element]
			
		let dequeue = function
			| [] -> []
			| head :: tail -> tail

		let first = function
			| [] -> raise (Empty "module ListQueue: first")
			| head :: tail -> head

		let isEmpty queue =
			queue = []
	end;;


(****************************************************************)
module type QUEUE_FUN =
	sig
		type 'a t
		exception Empty of string
		val empty: unit -> 'a t
		val enqueue: 'a * 'a t -> 'a t
		val dequeue: 'a t -> 'a t
		val first: 'a t -> 'a
		val isEmpty: 'a t -> bool
	end;;

let q = ListQueue.empty();;
ListQueue.isEmpty(ListQueue.enqueue("e1", q)) = false;;
ListQueue.isEmpty(q) = true;;
ListQueue.dequeue(ListQueue.enqueue("e1", ListQueue.enqueue("e2", q))) = ListQueue.enqueue("e1", ListQueue.dequeue(ListQueue.enqueue("e2", q)));;
ListQueue.dequeue(ListQueue.enqueue("e1", ListQueue.empty())) = ListQueue.empty();;
ListQueue.dequeue(ListQueue.empty()) = ListQueue.empty();;
ListQueue.first(ListQueue.enqueue("e1", ListQueue.enqueue("e2", q))) = ListQueue.first(ListQueue.enqueue("e2", q));;
ListQueue.first(ListQueue.enqueue("e1", ListQueue.empty())) = "e1";;
try ListQueue.first(ListQueue.empty()) with ListQueue.Empty "module ListQueue: first" -> true;;