name := "hello-finagle-thrift-java"
version := "0.1"
organization := "zdavep"
scalaVersion := "2.11.7"

resolvers += "twitter" at "https://maven.twttr.com/"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-thrift" % "6.28.0",
  "com.twitter" %% "scrooge-core" % "4.0.0"
)

mainClass in Global := Some("zdavep.hello.HelloServiceServer")

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"

addCommandAlias("dist", ";clean;compile;assembly")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "com/twitter/common/args/apt/cmdline.arg.info.txt.1" => MergeStrategy.first
    case "org/slf4j/impl/StaticLoggerBinder.class" => MergeStrategy.first
    case "org/slf4j/impl/StaticMDCBinder.class" => MergeStrategy.first
    case "org/slf4j/impl/StaticMarkerBinder.class" => MergeStrategy.first
    case x => old(x)
  }
}

com.twitter.scrooge.ScroogeSBT.newSettings
