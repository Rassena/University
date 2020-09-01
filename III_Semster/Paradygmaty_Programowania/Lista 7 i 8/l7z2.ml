module type QUEUE_MUT = sig
  type 'a t
  (* The type of queues containing elements of type ['a]. *)
  exception Empty of string
  (* Raised when [first q] is applied to an empty queue [q]. *)
  exception Full of string
  (* Raised when [enqueue(x,q)] is applied to a full queue [q]. *)
  val empty: int -> 'a t
  (* [empty n] returns a new queue of length [n], initially empty. *)
  val enqueue: 'a * 'a t -> unit
  (* [enqueue (x,q)] adds the element [x] at the end of a queue [q]. *)
  val dequeue: 'a t -> unit
  (* [dequeue q] removes the first element in queue [q] *)
  val first: 'a t -> 'a
  (* [first q] returns the first element in queue [q] without removing
  it from the queue, or raises [Empty] if the queue is empty. *) val isEmpty: 'a t -> bool
  (* [isEmpty q] returns [true] if queue [q] is empty, otherwise returns [false]. *)
  val isFull: 'a t -> bool
  (* [isFull q] returns [true] if queue [q] is full,
  otherwise returns [false]. *)
  end;;

module QUEUE_CYC : QUEUE_MUT = struct
  type 'a t = {arr: 'a option array; mutable f:int; mutable r:int}
  exception Empty of string
  exception Full of string
  let empty n = {arr = Array.make (n+1) None; f = 0; r = 0}
  let isFull q = (q.f - q.r = 1) || (q.r - q.f = ((Array.length q.arr) -1) )
  let isEmpty q = q.f = q.r
  let enqueue (elem,q) = 
    if isFull q then raise (Full "queue full")
    else q.arr.(q.r) <- Some elem; q.r <- (q.r+1);
    if q.r = Array.length q.arr then q.r <- 0
  let dequeue q = 
    if isEmpty q then raise (Empty "queue empty")
    else q.f <- q.f+1;
    if q.f = Array.length q.arr then q.f <- 0
    
  let first q =
    if (isEmpty q) then raise (Empty "queue is empty")
    else match (q.arr.(q.f)) with
    | Some value -> value
    | None -> raise (Empty "ERROR")
  
end;;