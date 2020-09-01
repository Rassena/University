def replikuj[T](x: T, count: Int): List[T] = {
  if (count <= 0) Nil
  else List(x) ++ replikuj(x, count - 1)
}

replikuj(8, 4)
replikuj("a", 3)
replikuj(0, 0)
replikuj(1, -1)
replikuj(-1, 1)