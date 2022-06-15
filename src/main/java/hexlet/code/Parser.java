package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, JsonNode> getYamlFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        String content = Files.readString(Paths.get(file.getAbsolutePath()));
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
