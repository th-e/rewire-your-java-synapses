import scala.io.Source
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.net.InetAddress
import scala.util.matching.Regex
import java.nio.charset.StandardCharsets

class PipeWithOption:

  def runPipe() =
    val success = catResourcesFile("config.properties")
      .flatMap(lines => grepLineWithPrefix("host", Source.fromString(lines)))
      .flatMap(hostLine => valueOfProp(hostLine))
      .flatMap(host => ping(host))
    println(s"pinging successful: $success")

  def ping(url: String): Option[Boolean] =
    val host = InetAddress.getByName(url)
    Option.apply(host.isReachable(5000))

  def valueOfProp(propLine: String): Option[String] =
    val lines = propLine.split("=")
    if (lines.length == 2) Option.apply(lines(1)) else Option.empty

  def grepLineWithPrefix(searchString: String, content: Source): Option[String] =
    val regex: Regex = searchString.concat(".*").r
    content
      .getLines()
      .filter(line => regex.findFirstIn(line).isDefined)
      .nextOption

  // be similar to java
  def catResourcesFile(fileName: String): Option[String] =
    try
      val resourcesDirectory = File("src/main/resources")
      val path = Paths.get(resourcesDirectory.getAbsolutePath() + "/" + fileName)
      Some(Files.readString(path, StandardCharsets.UTF_8))
    catch case e: Exception => None
