organization := "lt.dvim.msqp"
name := "msqp4s"
description := "Codec for Valve's Master Server Query Protocol"

libraryDependencies ++= Seq(
  "org.scodec"        %% "scodec-bits" % "1.1.5"
)

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/2m/msqp4s"))
scmInfo := Some(ScmInfo(url("https://github.com/2m/msqp4s"), "git@github.com:2m/msqp4s.git"))
developers += Developer("contributors",
                        "Contributors",
                        "https://gitter.im/2m/msqp4s",
                        url("https://github.com/2m/msqp4s/graphs/contributors"))
organizationName := "https://github.com/2m/msqp4s/graphs/contributors"
startYear := Some(2018)

bintrayOrganization := Some("2m")
bintrayRepository := (if (isSnapshot.value) "snapshots" else bintrayRepository.value)

scalafmtOnCompile := true
enablePlugins(AutomateHeaderPlugin)