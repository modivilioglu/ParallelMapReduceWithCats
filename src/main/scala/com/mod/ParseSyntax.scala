package com.mod

import cats.Show
import cats.Monoid
import cats.instances.all._
import cats.syntax.semigroup._
/**
  * Created by mehmetoguzdivilioglu on 01/01/2017.
  */
object ParseSyntax {

  implicit class ShowOps[A](value: A) {
    def countWords(implicit show: Show[A]): Int = show.show(value).split(delimiter).length

    def getLetterMap(implicit show: Show[A]): Map[Char, Int] =
      show.show(value).foldLeft(Map[Char, Int]())((acc, a) => acc |+| Map(a -> 1)) //We can not use ++ operator here
  }

}
