organization := "lt.dvim.msqp"
name := "msqp4s"
description := "Codec for Valve's Master Server Query Protocol"

scalaVersion := "2.12.6"
libraryDependencies ++= Seq(
  "org.scodec"  %% "scodec-core" % "1.10.3",
  "com.lihaoyi" %% "utest"       % "0.6.3" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/2m/msqp4s"))
scmInfo := Some(ScmInfo(url("https://github.com/2m/msqp4s"), "git@github.com:2m/msqp4s.git"))
developers += Developer(
  "contributors",
  "Contributors",
  "https://gitter.im/2m/msqp4s",
  url("https://github.com/2m/msqp4s/graphs/contributors")
)
organizationName := "https://github.com/2m/msqp4s/graphs/contributors"
startYear := Some(2018)

bintrayOrganization := Some("2m")
bintrayRepository := (if (isSnapshot.value) "snapshots" else bintrayRepository.value)

scalafmtOnCompile := true
enablePlugins(AutomateHeaderPlugin)
