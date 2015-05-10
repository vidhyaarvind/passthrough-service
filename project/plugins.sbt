resolvers ++= Seq(
  Classpaths.sbtPluginSnapshots
)

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.1.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
