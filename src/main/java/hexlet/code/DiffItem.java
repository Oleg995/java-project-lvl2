package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public record DiffItem(DiffType type, String name, JsonNode oldValue, JsonNode newValue) {
}
