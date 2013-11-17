import sbt._
import Keys._
import play.Project._

object Build extends Build {
  val appName = "aprikot-cms"
  val appVersion = "0.1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    anorm) ++ Dependencies.libraryDependencies

  import scalariform.formatter.preferences._
  import com.typesafe.sbt.SbtScalariform
  lazy val scalariformSettings = SbtScalariform.scalariformSettings ++ Seq(
    SbtScalariform.ScalariformKeys.preferences := FormattingPreferences()
      .setPreference(DoubleIndentClassDeclaration, true))

  val playSettings = Seq(
    resolvers += Resolver.url(
      "play-plugin-releases",
      new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases"))(
        Resolver.ivyStylePatterns))

  val aprikotSettings = playSettings ++ scalariformSettings ++ Seq(
    scalacOptions ++= Seq(
      "-target:jvm-1.7",
      "-deprecation",
      "-feature",
      "-unchecked"))

  val main = play.Project(appName, appVersion, appDependencies).settings(aprikotSettings: _*)
}
