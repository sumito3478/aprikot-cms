import sbt._
import Keys._
import play.Project._

object Build extends Build {
  val appName = "aprikot-cms"
  val appVersion = "0.1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    anorm) ++ Dependencies.libraryDependencies

  val main = play.Project(
    appName,
    appVersion,
    appDependencies).settings(StandardProject.newSettings: _*).settings(
      resolvers += Resolver.url(
        "play-plugin-releases",
        new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases"))(
          Resolver.ivyStylePatterns))

}
