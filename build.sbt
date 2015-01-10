name := "conecta-sugerencias"

version := "1.0"

lazy val `conecta-sugerencias` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers ++= Seq(
  "anormcypher" at "http://repo.anormcypher.org/"
)

libraryDependencies ++= Seq(
  jdbc ,
  anorm ,
  cache ,
  ws ,
  "org.anormcypher" %% "anormcypher" % "0.5.0"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  