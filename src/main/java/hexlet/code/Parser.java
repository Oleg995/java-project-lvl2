package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class Parser {

    /*YAMLFactory парсит как json так и yaml формат, по этому не вижу смысла добавлять аргумент format в метод.
     */
    public static Map<String, JsonNode> getParseJsonAndYaml(String content, String typeData) throws IOException {

        if (typeData.contains("yaml")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(content, new TypeReference<>() {
            });
        } else if (typeData.contains("json")) {
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            return mapper.readValue(content, new TypeReference<>() {
            });
        }
        throw new IOException();
    }
}
