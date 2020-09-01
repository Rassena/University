def podziel [A](xs:List[A]):(List[A],List[A]) = {
  if (xs==Nil) (Nil,Nil)
  else
  (parzysta(xs,false), parzysta(xs,true))
}

def parzysta [A](xs:List[A],p:Boolean):List[A] = {
  if (xs==Nil) Nil
  else
  if (p) xs.head :: parzysta(xs.tail,false)
  else parzysta(xs.tail, true)
}

podziel (List(1,2,3,4,5,6));
podziel (List(3;6;8;9;13)); 
podziel (List()); 
podziel (List(0)); 