module type QUEUE_FUN = sig
  type 'a t
  exception Empty of string
  val empty: unit -> 'a t
  val enqueue: 'a * 'a t -> 'a t 
  val dequeue: 'a t -> 'a t
  val first: 'a t -> 'a
  val isEmpty: 'a t -> bool
  end;;

module QUEUE_LIST : QUEUE_FUN = 
struct
  type 'a t = Queue of 'a list
  exception Empty of string
  let empty () = Queue([])
  let enqueue (x,queue) = 
    match queue with
    |Queue([]) -> Queue([x])
    |Queue(lst) -> Queue(lst@[x])
  let dequeue queue = 
    match queue with
    |Queue([]) -> raise (Empty "empty queue")
    |Queue(h::t) -> Queue(t)
  let first queue = 
    match queue with
    |Queue([])-> raise (Empty "empty queue")
    |Queue(h::t) -> h
  let isEmpty queue = 
    match queue with
    |Queue([]) -> true
    |Queue(_) -> false
end;;

module QUEUE_LIST_BETTER : QUEUE_FUN = 
struct
  type 'a t = Queue of 'a list * 'a list
  exception Empty of string
  let empty () = Queue([],[])
  let enqueue (x, queue) = 
    match queue with
    |Queue([], lst2) -> Queue(List.rev (x::lst2), [])
    |Queue(lst1, lst2) -> Queue(lst1, x::lst2)
  let rec dequeue queue = 
    match queue with
    |Queue([], _) -> raise (Empty "empty queue")
    |Queue([x], lst2) -> Queue(List.rev lst2, [])
    |Queue(h::t, lst2) -> Queue(t, lst2)
  let first queue = 
    match queue with
    |Queue([], _) -> raise (Empty "empty queue")
    |Queue(h::t, _) -> h
  let isEmpty queue = 
    match queue with
    |Queue([],[]) -> true
    |Queue(_,_) -> false
end;;
