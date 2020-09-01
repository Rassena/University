module PairOfListsQueue : QUEUE_FUN =
  struct
		type 'a t = 'a list * 'a list
		
    exception Empty of string
    
    let empty () = ([], [])
		
		let normalize = function
			| ([], endOfQueue) -> (List.rev endOfQueue, [])
			| normalQueue -> normalQueue
		
		let enqueue (element, queue) =
			normalize(fst queue, element :: snd queue)
			
		let dequeue = function
			| ([], _) -> ([], [])
			| (head :: tail, endOfQueue) -> normalize(tail, endOfQueue)

		let first queue = 
			match fst queue with
			| [] -> raise (Empty "module PairOfListsQueue: first")
			| (head :: tail) -> head

		let isEmpty queue =
			fst queue = []
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

let q = PairOfListsQueue.empty();;
PairOfListsQueue.isEmpty(PairOfListsQueue.enqueue("e1", q)) = false;;
PairOfListsQueue.isEmpty(q) = true;;
PairOfListsQueue.dequeue(PairOfListsQueue.enqueue("e1", PairOfListsQueue.enqueue("e2", q))) = PairOfListsQueue.enqueue("e1", PairOfListsQueue.dequeue(PairOfListsQueue.enqueue("e2", q)));;
PairOfListsQueue.dequeue(PairOfListsQueue.enqueue("e1", PairOfListsQueue.empty())) = PairOfListsQueue.empty();;
PairOfListsQueue.dequeue(PairOfListsQueue.empty()) = PairOfListsQueue.empty();;
PairOfListsQueue.first(PairOfListsQueue.enqueue("e1", PairOfListsQueue.enqueue("e2", q))) = PairOfListsQueue.first(PairOfListsQueue.enqueue("e2", q));;
PairOfListsQueue.first(PairOfListsQueue.enqueue("e1", PairOfListsQueue.empty())) = "e1";;
try PairOfListsQueue.first(PairOfListsQueue.empty()) with PairOfListsQueue.Empty "module PairOfListsQueue: first" -> true;;