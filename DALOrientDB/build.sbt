name := """DALOrientDB"""

version := "1.0"

scalaVersion := "2.11.5"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.0.0-beta5"

// Uncomment to use Akka
libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "1.8.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.2"

lazy val dal = RootProject(file("../DAL"))
lazy val domainModel = RootProject(file("../DomainModel"))
lazy val util = RootProject(file("../Util"))
val main = Project(id = "DALOrientDB", base = file(".")).dependsOn(domainModel, dal, util) 

