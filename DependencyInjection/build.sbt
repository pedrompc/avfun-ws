name := """DependencyInjection"""

version := "1.0"

scalaVersion := "2.11.5"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test" 

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.0.0-beta5"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

lazy val dal = RootProject(file("../DAL"))
lazy val dalOrientDB = RootProject(file("../DALOrientDB"))
lazy val domainModel = RootProject(file("../DomainModel"))
lazy val bll = RootProject(file("../BLL"))
lazy val bllImpl = RootProject(file("../BLLImpl"))
lazy val util = RootProject(file("../Util"))
val main = Project(id = "DependencyInjection", base = file(".")).dependsOn(domainModel, dal, dalOrientDB, bll, bllImpl, util)

