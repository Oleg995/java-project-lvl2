package hexlet.code;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DifferTest {
//    public static void main(String[] args) {
//        File file = new File("src/test/resources/1.yaml");
//        System.out.println(file.getAbsolutePath());
//    }

    @Test
    public void test() {
        String path = "src/test/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        assertTrue(absolutePath.endsWith("src/test/resources"));
    }
    @Test
    public void generateTestFromYaml() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/exampleYaml"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/1.yaml", "src/test/resources/2.yaml"));
    }

    @Test
    public void generateTestFromStylish() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/exampleYaml"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/fileOne.json",
                "src/test/resources/fileTwo.json"));
    }

    @Test
    public void formatOfPlain() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/examplePlain"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/1.yaml",
                "src/test/resources/2.yaml", "plain"));
    }

    @Test
    public void formatOfJson() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/exampleJson"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/actualPlain1.yaml",
                "src/test/resources/actualPlain2.yaml", "json"));
    }
}
