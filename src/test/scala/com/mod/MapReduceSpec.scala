package com.mod

/**
  * Created by mehmetoguzdivilioglu on 01/01/2017.
  */

import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import ParseSyntax._
import MapReduce._
import cats.instances.all._

class MapReduceSpec extends FlatSpec with Matchers {

  import scala.concurrent.ExecutionContext.Implicits.global

  val lines = List("one test me", "two test me", "three testing me")

  "Using of interface method countWords " should "give the correct numbers" in {
    "one two three".countWords should be(3)
  }
  "Using of interface method getLetterMap " should "give the correct numbers" in {
    "three".getLetterMap.get('e') should be(Some(2))
  }
  "Paralel total word count " should "give the correct calculation result" in {
    val totalCount = Await.result(foldMapP(lines)(x => x.countWords), Duration.Inf)
    totalCount should be(9)
  }
  "Paralel each word count " should "give the correct calculation result" in {
    val totalCount = (Await.result(foldMapP(lines.flatMap(x => x split " "))(x => Map(x -> 1)), Duration.Inf))
    totalCount.get("me") should be(Some(3))
  }

  "Paralel total character count" should "give the correct calculation result" in {
    val totalCount = Await.result(foldMapP(lines)(x => x.getLetterMap), Duration.Inf)
    totalCount.get('t') should be(Some(8))
  }
}
