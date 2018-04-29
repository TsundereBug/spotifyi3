lazy val root = (project in file(".")).settings(
  name := "spotifyi3",
  version := "0.1",
  scalaVersion := "2.12.6",
  libraryDependencies ++= Seq(
    "net.liftweb" %% "lift-json" % "3.2.0",
  ),
  mainClass in assembly := Some("com.tsunderebug.spotifyi3.Bar")
)