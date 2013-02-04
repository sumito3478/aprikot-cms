import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "aprikot-cms"
  val appVersion      = "0.0.1-SNAPSHOT"

  val appDependencies = Seq(
    "com.micronautics" % "securesocial" % "2.1-RC4a" withSources(),
    jdbc,
    anorm
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.url(
      "play-plugin-releases",
      new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases"))(
      Resolver.ivyStylePatterns)
  )

}
