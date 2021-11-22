package de.webtuples

import cats.effect.IOApp
import cats.effect.IO

object Main extends IOApp.Simple:

  def run: IO[Unit] =
    println(TypeLevels.firstLevel(2, 3))

    println(TypeLevels.secondLevel(List(1, 2, 4), List(5)))
    println(TypeLevels.secondLevel(List("7", "8"), List("9")))

    println(TypeLevels.thirdLevel(Option(3), Option(4)))
    println(TypeLevels.thirdLevel(List(1, 2, 3), List(4, 5)))
    
    
    IO.delay("Hello HKTs").flatMap(IO.println)

