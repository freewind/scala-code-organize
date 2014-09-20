organization := "scala-code-organize"

name := "default"

version := "0.1-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.0"

resolvers in ThisBuild ++= Seq(
  "ibiblio" at "http://mirrors.ibiblio.org/pub/mirrors/maven2",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies in ThisBuild ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "org.jsoup" % "jsoup" % "1.7.3",
  "org.specs2" %% "specs2" % "2.4.2" % "test"
)

lazy val noDi = project in file("no-di")

lazy val ctorParams = project in file("ctor-params")

lazy val funcImplicitParams = project in file("func-implicit-params")

lazy val objectAsFunc = project in file("object-as-func")

lazy val reader = project in file("reader")

lazy val simpleCake = project in file("simple-cake")
