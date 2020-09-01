def nierosnace(x: (Int, Int, Int)): Boolean = {
  (x._1 >= x._2 || x._2 >= x._3)
}

nierosnace(3, 2, 4)
nierosnace(2, 3, 4)
nierosnace(1, 1, 1)
nierosnace(3, 2, 1)
nierosnace(3, 3, 4)
