def gray (k:Int)= {
  if (k<=0) List()
  else
  grayHelper (k,List("0","1"))
}

def grayHelper (k:Int,xs:List[String]):List[String] = {
  if (k==0) xs
  else{
    grayHelper (k-1,(addToStringList("0",xs):::((addToStringList("1",xs)).reverse)))
  }
}

def addToStringList (x:String,xs:List[String]):List[String] = {
  if(xs==Nil) Nil
  else
  (x+xs.head):: addToStringList (x,xs.tail);
}

gray(1);
gray(3);
gray(-1);
gray(0);