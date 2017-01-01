package com
import scala.collection.Iterable
import com.typesafe.config.{ConfigFactory, ConfigObject, ConfigValue}

/**
  * Created by mehmetoguzdivilioglu on 01/01/2017.
  */
package object mod {
  val config = ConfigFactory.load()
  val numberOfParallelism = config.getInt("numberOfParallelism")
  val delimiter = config.getString("delimiter")
  val inputFilePath = config.getString("inputFilePath")
}
