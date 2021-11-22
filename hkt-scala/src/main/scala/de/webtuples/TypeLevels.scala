package de.webtuples

import cats.effect.IO
import cats.*
import cats.Monad.ops.toAllMonadOps //uff

object TypeLevels:

  def firstLevel(a: Int, b: Int) =
    a + b

  def secondLevel[T](a: List[T], b: List[T]) =
    a ++ b

  def thirdLevel[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] = 
    for
      x <- a
      y <- b
    yield x + y
