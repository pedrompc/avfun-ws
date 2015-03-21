name := """EventsService"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "net.codingwell" %% "scala-guice" % "4.0.0-beta5",
  "com.github.nscala-time" %% "nscala-time" % "1.8.0",
  jdbc,
  anorm,
  cache,
  ws
)

lazy val dependencyInjection = RootProject(file("../DependencyInjection"))
lazy val domainModel = RootProject(file("../DomainModel"))
lazy val bll = RootProject(file("../BLL"))
val main = Project(id = "EventsService", base = file(".")).enablePlugins(PlayScala).dependsOn(domainModel, dependencyInjection, bll)
