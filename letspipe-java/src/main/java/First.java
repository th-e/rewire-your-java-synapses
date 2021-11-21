import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class First {


    // cat config.properties | grep "host"  | cut -d = -f 2 | xargs -I {} ping {}
    // intentionally we take a very low level path

    public static void main(String[] args) throws IOException {

        String props = catResourcesFile("config.properties");
        String hostLine = grepLineWithPrefix("host", props);
        String host = valueOfProp(hostLine);
        boolean success = ping(host);
        System.out.println("pinging " + host + " successful: "+ success);
    }

    static boolean ping(String url) throws IOException {
        InetAddress host = InetAddress.getByName(url);
        return (host.isReachable(5000));
    }

     static String valueOfProp(String propLine){
        return propLine.split("=")[1];
    }

    static String grepLineWithPrefix(String searchString, String content){
        // intentionally bad style, improve!
        String result = null;
        List<String> lines = content.lines().collect(Collectors.toList());
        for (String line: lines) {
            if (Pattern.matches(searchString + ".*", line)) result = line;
        }
        return result;
    }

    static String catResourcesFile(String fileName) throws IOException {
        File resourcesDirectory = new File("src/main/resources");
        Path path = Paths.get(resourcesDirectory.getAbsolutePath()+ "/" + fileName);
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}
