package com.mod

/**
  * Created by mehmetoguzdivilioglu on 31/12/2016.
  */

import cats.Monoid
import cats.syntax.semigroup._

import scala.concurrent._
import scala.concurrent.duration._
import scala.language.higherKinds

object MapReduce {

  def foldMap[A, B: Monoid](values: Iterable[A])(func: A => B): B =
    values.foldLeft(Monoid[B].empty)((accB, a) => func(a) |+| accB)

  def foldMapP[A, B: Monoid](values: Iterable[A])(func: A => B)
                            (implicit ec: ExecutionContext): Future[B] = {
    val partitioned = values.grouped(numberOfParallelism).toList
    // Each partition in one thread: Fold elements in partition using func for first grouping

    val groupResults = partitioned.map(partition => Future { foldMap(partition)(func) })
    val partitionResultsInFuture = Future.sequence(groupResults)

    // Associative reducing of each partition result
    val resultInFuture = partitionResultsInFuture.map(partitionResults => {
      partitionResults.foldLeft(Monoid[B].empty)((acc, a) => acc |+| a)
    })
    resultInFuture
  }
}
