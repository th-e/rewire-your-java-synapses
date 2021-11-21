import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Pattern;

public class Third {

    public static void main(String[] args)  {

        var success = catResourcesFile("config.properties")
                .flatMap(lines -> grepLineWithPrefix("host", lines))
                .flatMap(hostLine -> valueOfProp(hostLine))
                .flatMap(host -> ping(host));
        System.out.println("pinging successful: " + success);
    }

    static Optional<Boolean> ping(String url)  {
        try {
            InetAddress host = InetAddress.getByName(url);
            return Optional.of(host.isReachable(5000));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    static Optional<String> valueOfProp(String propLine) {
        String[] lines = propLine.split("=");
        if (lines.length == 2)
            return Optional.of(lines[1]);
        else
            return Optional.empty();
    }

    static Optional<String> grepLineWithPrefix(String searchString, String content) {
        return content.lines().filter(line -> Pattern.matches(searchString + ".*", line)).findFirst();
    }

    static Optional<String> catResourcesFile(String fileName) {
        File resourcesDirectory = new File("src/main/resources");
        Path path = Paths.get(resourcesDirectory.getAbsolutePath() + "/" + fileName);
        try {
            return Optional.of(Files.readString(path, StandardCharsets.UTF_8));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
