lazy val scalatest =  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
lazy val root = (project in file(".")).
settings(
	name := "highlight2pdf",
	version := "0.1",
	scalaVersion := "2.11.6",
	libraryDependencies +=  scalatest
)