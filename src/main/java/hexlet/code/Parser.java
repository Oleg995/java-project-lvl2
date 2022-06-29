package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

import java.util.Map;


public class Parser {

    public static Map<String, JsonNode> getParseJsonAndYaml(String content, String typeData) throws IOException {

        if (typeData.contains("yml")) {
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
