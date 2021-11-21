import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Pattern;

public class Second {

    public static void main(String[] args) {

        boolean success = false;

        Optional<String> props = catResourcesFile("config.properties");
        if (props.isPresent()) {
            Optional<String> hostLine = grepLineWithPrefix("host", props.get());
            if (hostLine.isPresent()) {
                Optional<String> host = valueOfProp(hostLine.get());
                if (host.isPresent()){
                    Optional<Boolean> maybeSuccess = ping(host.get());
                    if (maybeSuccess.isPresent()){
                        success = maybeSuccess.get();
                    }
                }
            }
        }
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
