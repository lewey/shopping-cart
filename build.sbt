import sbt.Keys.libraryDependencies

name := "ShoppingCart"
version := "0.1"
scalaVersion := "2.13.7"

val compile: Seq[ModuleID] = Seq("org.scalactic" %% "scalactic" % "3.2.10")

val test: Seq[ModuleID] = Seq(
  "org.scalatest" %% "scalatest" % "3.2.10",
  "org.scalatest" %% "scalatest-wordspec" % "3.2.10",
  "org.mockito" % "mockito-core" % "3.12.4"
).map(_ % "test")

val all: Seq[ModuleID] = compile ++ test

libraryDependencies ++= all