
lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "rna.passthrough",
  name := "passthrough-service",
  scalaVersion := "2.10.4",
  libraryDependencies ++= Seq(
    "com.twitter.finatra" %% "finatra-http" % "2.0.+",
    "org.apache.kafka" %% "kafka" % "0.8.2.1",
    "com.typesafe" % "config" % "1.3.0"
    ),
  resolvers ++= Seq(
    "Twitter" at "http://maven.twttr.com",
    "Finatra Repo" at "http://twitter.github.com/finatra"
  )
)

lazy val finaglepassthrough = project
  .in(file("."))
  .settings(commonSettings: _*)
  .settings(
    publishLocal := {},
    publish := {},
    assemblyMergeStrategy in assembly := {
      case "BUILD" => MergeStrategy.discard
      case other => MergeStrategy.defaultMergeStrategy(other)
    }
  )


//assemblyJarName in assembly := "rna-passthrough-service.jar"
//
//mainClass in assembly := Some("rna.passthrough.service.KafkaRestService")
