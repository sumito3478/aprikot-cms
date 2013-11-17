import sbt._
import Keys._

object Dependencies {
  val apache_snapshots = "Apache Snapshots" at "https://repository.apache.org/content/groups/snapshots"

  val typesafe_releases = "TypeSafe Releases" at "http://repo.typesafe.com/typesafe/releases"

  val h2 = "com.h2database" % "h2" % "1.3.174"

  val shapeless = "com.chuusai" % "shapeless" % "2.0.0-M1" cross CrossVersion.full

  val commons_io = "commons-io" % "commons-io" % "2.5-SNAPSHOT" changing()

  val commons_collections = "commons-collections" % "commons-collections" % "3.2.1"

  val postgresql = "org.postgresql" % "postgresql" % "9.3-1100-jdbc41"

  val scalalogging = "com.typesafe" %% "scalalogging-slf4j" % "1.0.1"

  val logback = "ch.qos.logback" % "logback-classic" % "1.0.13"

  val scalatest = "org.scalatest" %% "scalatest" % "2.0"

  val Seq(util_collection) = Seq("collection").map(a => "com.twitter" %% s"util-$a" % "6.7.0")

  val Seq(akka_actor, akka_remote, akka_kernel) = Seq("actor", "remote", "kernel").map(a => "com.typesafe.akka" %% s"akka-$a" % "2.2.3")

  val reflections = "org.reflections" % "reflections" % "0.9.8"

  val high_scale_lib = "com.github.stephenc.high-scale-lib" % "high-scale-lib" % "1.1.4"

  val netty = "io.netty" % "netty" % "3.7.0.Final"

  val snappy = "org.iq80.snappy" % "snappy" % "0.3"

  val json4s = "org.json4s" %% "json4s-native" % "3.2.5"

  val Seq(dispatch_core, dispatch_json4s) = Seq("core", "json4s-native").map(a => "net.databinder.dispatch" %% s"dispatch-$a" % "0.11.0")

  val async_http_client = "com.ning" % "async-http-client" % "1.7.21"

  val argonaut = "io.argonaut" %% "argonaut" % "6.0.1"

  val resolvers = Seq(
      typesafe_releases,
      apache_snapshots)

  val libraryDependencies =  Seq(
      async_http_client,
      json4s,
      dispatch_core,
      dispatch_json4s,
      snappy,
      netty,
      high_scale_lib,
      reflections,
      akka_kernel,
      akka_remote,
      akka_actor,
      scalatest % "test",
      util_collection,
      logback,
      scalalogging,
      commons_collections,
      postgresql,
      commons_io,
      shapeless,
      h2)
}
