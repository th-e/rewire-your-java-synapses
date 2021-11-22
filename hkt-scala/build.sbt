ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "3.1.0"

lazy val root = (project in file(".")).settings(
  name := "hkt-scala",
  libraryDependencies ++= Seq(
    // "core" module - IO, IOApp, schedulers
    // This pulls in the kernel and std modules automatically.
    "org.typelevel" %% "cats-effect" % "3.2.9",
    // concurrency abstractions and primitives (Concurrent, Sync, Async etc.)
    "org.typelevel" %% "cats-effect-kernel" % "3.2.9",
    // standard "effect" library (Queues, Console, Random etc.)
    "org.typelevel" %% "cats-effect-std" % "3.2.9",
    "org.typelevel" %% "cats-effect-testing-specs2" % "1.3.0" % Test
  )
)
