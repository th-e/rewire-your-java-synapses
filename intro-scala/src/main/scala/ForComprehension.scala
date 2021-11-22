object ForComprehension:
  def findSum() =
    val array = List(1, 2, 3, 5, 8, 13)
    val n = 16
    for
      i <- array
      j <- array if i + j == n
    yield (i, j)
