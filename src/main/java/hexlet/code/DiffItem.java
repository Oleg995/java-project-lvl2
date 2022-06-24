package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

public record DiffItem(DiffType type, String name, JsonNode oldValue, JsonNode newValue) {

}
