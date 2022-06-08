package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;



public class DifferTest {

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
        var map1 = Parser.getYamlFromFile("src/test/resources/1.yaml");
        var map2 = Parser.getYamlFromFile("src/test/resources/2.yaml");
        String file = Files.readString(Paths.get("src/test/resources/exampleYaml"));
        Assertions.assertEquals(file, Formatters.stylish(Differ.generate(map1, map2)));
    }

    @Test
    public void generateTestFromJson() throws IOException {
        var map1 = Parser.getYamlFromFile("src/test/resources/fileOne.json");
        var map2 = Parser.getYamlFromFile("src/test/resources/fileTwo.json");
        String file = Files.readString(Paths.get("src/test/resources/exampleYaml"));
        Assertions.assertEquals(file, Formatters.stylish(Differ.generate(map1, map2)));
    }

    @Test
    public void formatOfPlain() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/examplePlain"));
        var map1 = Parser.getYamlFromFile("src/test/resources/1.yaml");
        var map2 = Parser.getYamlFromFile("src/test/resources/2.yaml");
        Assertions.assertEquals(file, Formatters.plain(Differ.generate(map1, map2)));
    }

    @Test
    public void formatOfJson() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/exampleJson"));
        var map1 = Parser.getYamlFromFile("src/test/resources/actualPlain1.yaml");
        var map2 = Parser.getYamlFromFile("src/test/resources/actualPlain2.yaml");
        Assertions.assertEquals(file, Formatters.json(Differ.generate(map1, map2)));
    }
}
