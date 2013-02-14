// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.1.0")


resolvers := Seq(
  "sumito3478 Maven Repository (pull)" at "http://maven.sumito3478.info/",
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots",
  "Maven Repository Mirror" at "http://uk.maven.org/maven2")

externalResolvers <<= resolvers map {
  rs =>
    Resolver.withDefaultResolvers(rs, mavenCentral = false)
}

addSbtPlugin("info.sumito3478" %% "aprikot-sbt" % "0.0.8")
