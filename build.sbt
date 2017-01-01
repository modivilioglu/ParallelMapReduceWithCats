name := "ParallelMapReduceWithCats"
version := "1.0"

val catsVersion = "0.7.2"
val catsAll = "org.typelevel" %% "cats" % catsVersion
val specs2Version = "3.6" // use the version used by discipline
val specs2Core  = "org.specs2" %% "specs2-core" % specs2Version
val specs2Scalacheck = "org.specs2" %% "specs2-scalacheck" % specs2Version
val scalacheck = "org.scalacheck" %% "scalacheck" % "1.12.4"

lazy val root = (project in file(".")).
  settings(
    organization := "com.example",
    name := "something",
    scalaVersion := "2.11.6",
    libraryDependencies ++= Seq(
      catsAll,
      specs2Core % Test, specs2Scalacheck % Test, scalacheck % Test
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.6" % "test",
      "com.typesafe" % "config" % "1.3.0",
      "org.scalatest" %% "scalatest" % "2.2.4" % "test"),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-language:_"
    )
  )
