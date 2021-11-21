import scala.io.Source
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.net.InetAddress
import scala.util.matching.Regex
import java.nio.charset.StandardCharsets

class PipeWithEither:

  def runPipe() =
    val success = catResourcesFile("config.properties")
      .flatMap(lines => grepLineWithPrefix("host", Source.fromString(lines)))
      .flatMap(hostLine => valueOfProp(hostLine))
      .flatMap(host => ping(host))
    println(s"pinging successful: $success")

  def ping(url: String): Option[Boolean] =
    val host = InetAddress.getByName(url)
    if (host.isReachable(5000)) Right(true) else Left(s"unable to reach $url")

  def valueOfProp(propLine: String): Either[String, String] =
    val lines = propLine.split("=")
    if (lines.length == 2) Right(lines(1)) else Left(s"no value in propLine $propLine")

  def grepLineWithPrefix(searchString: String, content: Source): Either[String, String] =
    val regex: Regex = searchString.concat(".*").r
    content
      .getLines()
      .filter(line => regex.findFirstIn(line).isDefined)
      .nextOption match 
        case Some(x) => Right(x)
        case None => s"regex $regex not found in $content"

  def catResourcesFile(fileName: String): Either[String, String] =
    try
      val resourcesDirectory = File("src/main/resources")
      val path = Paths.get(resourcesDirectory.getAbsolutePath() + "/" + fileName)
      Right(Files.readString(path, StandardCharsets.UTF_8))
    catch case e: Exception => Lift(e)

    def runPipeElegant() =
      val success = for 
        lines <- catResourcesFile("config.properties")
        hostLine <- grepLineWithPrefix("host", Source.fromString(lines))
        host <- valueOfProp(hostLine)
      yield ping(host)
    println(s"pinging successful: $success")
