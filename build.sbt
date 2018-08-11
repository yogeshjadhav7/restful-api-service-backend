name := """restful-api-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,JDebPackaging,PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.37"

libraryDependencies += "javax.persistence" % "persistence-api" % "1.0"

libraryDependencies += "org.apache.ftpserver" % "ftpserver-core" % "1.0.6"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"

libraryDependencies += "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13"

libraryDependencies += "com.amazonaws" % "aws-java-sdk-ses" % "1.11.384"

libraryDependencies += "javax.mail" % "mail" % "[1.0.0,)"

// https://mvnrepository.com/artifact/com.opencsv/opencsv
libraryDependencies += "com.opencsv" % "opencsv" % "3.3"

// https://mvnrepository.com/artifact/org.igniterealtime.smack/smack-tcp
libraryDependencies += "org.igniterealtime.smack" % "smack-tcp" % "4.1.9"

// https://mvnrepository.com/artifact/org.igniterealtime.smack/smack-java7
libraryDependencies += "org.igniterealtime.smack" % "smack-java7" % "4.1.9"

// https://mvnrepository.com/artifact/com.google.code.gson/gson
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"

libraryDependencies += "com.google.api-client" % "google-api-client" % "1.24.1"

libraryDependencies += filters




// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

maintainer := "Yogesh Jadhav <yogse7en@gmail.com>"

packageSummary := "restful-api-app"

packageDescription := "restful-api application"

javaOptions in Universal ++= Seq(
  // JVM memory tuning
  "-J-Xms64m",

  // Since play uses separate pidfile we have to provide it with a proper path
  s"-Dpidfile.path=/home/yogesh/apps/play-apps/restful-api-app/target/RUNNING_PID/play.pid",

  // Use separate configuration file for production environment
  s"-Dconfig.file=/home/yogesh/apps/play-apps/restful-api-app/conf/prod.conf",

  // Use separate logger configuration file for production environment
  s"-Dlogger.file=/home/yogesh/apps/play-apps/restful-api-app/conf/prod-logger.xml"
)