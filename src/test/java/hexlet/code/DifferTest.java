package hexlet.code;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public void testParserYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode1 = mapper.readTree("\"Boston Red Sox\"");
        JsonNode jsonNode2 = mapper.readTree("\"New York Mets\"");
        Map<String, JsonNode> expected = Map.of("american", jsonNode1, "national", jsonNode2);
        String path = "src/test/resources/YamlFile/typeData.yaml";
        String typeData = new File(path).getName();
        Map<String, JsonNode> actual = Parser.getParseJsonAndYaml(Differ.readingOfTheFile(path), typeData);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testParserJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode1 = mapper.readTree("\"Boston Red Sox\"");
        JsonNode jsonNode2 = mapper.readTree("\"New York Mets\"");
        Map<String, JsonNode> expected = Map.of("american", jsonNode1, "national", jsonNode2);
        String path = "src/test/resources/typeData.json";
        String typeData = new File(path).getName();
        Map<String, JsonNode> actual = Parser.getParseJsonAndYaml(Differ.readingOfTheFile(path), typeData);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void yamlWithFormatStylish() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/YamlFile/exampleYaml"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/YamlFile/1.yaml", "src/test/resources/YamlFile/2.yaml", "stylish"));
    }

    @Test

    public void yamlWithFormatJson() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/YamlFile/exampleYamlWithJson"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/YamlFile/typeData.yaml",
                "src/test/resources/YamlFile/typeData2.yaml", "json"));
    }

    @Test
    public void yamlWithFormatPlain() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/YamlFile/exampleYamlWithPlain"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/YamlFile/1.yaml",
                "src/test/resources/YamlFile/2.yaml", "plain"));
    }

    @Test
    public void jsonWithFormatStylish() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/exampleJsonWithStylish"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/fileOne.json",
                "src/test/resources/fileTwo.json", "stylish"));
    }

    @Test
    public void jsonWithFormatJson() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/exampleJsonWithJson"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/actualJson1.json",
                "src/test/resources/actualJson2.json", "json"));
    }

    @Test
    public void jsonWithFormatPlain() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/exampleJsonWithPlain"));
        Assertions.assertEquals(file, Differ.generate("src/test/resources/fileOne.json",
                "src/test/resources/fileTwo.json", "plain"));
    }
}
