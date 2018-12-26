/** ********* PROJECT INFO ******************/
name := "api"
version := "1.0"

/** ********* PROJECT SETTINGS ******************/
Configuration.settings

/** ********* PROD DEPENDENCIES *****************/
libraryDependencies ++= Dependencies.production

/** ********* TEST DEPENDENCIES *****************/
libraryDependencies ++= Dependencies.test

/** ********* COMMANDS ALIASES ******************/
addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("tq", "testQuick")
addCommandAlias("tsf", "testShowFailed")

addCommandAlias("c", "compile")
addCommandAlias("tc", "test:compile")

addCommandAlias("f", "scalafmt") // Format all files according to ScalaFmt
addCommandAlias("fc", "scalafmtCheck")
addCommandAlias("tf", "test:scalafmt") // Test if all files are formatted according to ScalaFmt
addCommandAlias("tfc", "test:scalafmtCheck")

addCommandAlias("prep", ";c;tc;fc;tfc") // All the needed tasks before running the test

TaskKey[Unit]("createDbTables") := (runMain in Compile)
  .toTask(" tv.codely.api.entry_point.cli.DbTablesCreator")
  .value
