package com.mod

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io._
import MapReduce._
import ParseSyntax._
import cats._
import cats.instances.all._

/**
  * Created by mehmetoguzdivilioglu on 01/01/2017.
  */

object Main {
  def main(args: Array[String]): Unit = {

    import scala.concurrent.ExecutionContext.Implicits.global

    val lines = Source.fromFile(inputFilePath).getLines().toList
    val wordList = lines.flatMap(x => x split " ")
    val totalNumberOfWords = Await.result(foldMapP(lines)(x => x.countWords), Duration.Inf)
    val countPerWords =  Await.result(foldMapP(wordList)(x => Map(x -> 1)), Duration.Inf)
    val countPerLetters = Await.result(foldMapP(wordList)(x => x.getLetterMap), Duration.Inf)

    println(s"Total number of words: $totalNumberOfWords")
    println(s"Top 5 words: ${countPerWords.toList.sortBy(- _._2).take(5)}")
    println(s"Top 5 letters: ${countPerLetters.toList.sortBy(- _._2).take(5)}")
  }
}
