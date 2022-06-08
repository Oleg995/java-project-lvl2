package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.DiffItem;
import java.util.Set;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
//
public class Formatters {
    public static String stylish(Set<DiffItem> s) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffItem item : s) {
            switch (item.type()) {
                case ADD -> result.append("  + ").append(item.name()).append(": ").append(item.newValue()).append("\n");
                case REMOVE -> result.append("  - ").append(item.name()).append(": ").append(item.oldValue()).append("\n");
                case NOTHING -> result.append("    ").append(item.name()).append(": ").append(item.oldValue()).append("\n");
                case CHANGE -> result.append("  - ").append(item.name()).append(": ").append(item.oldValue()).append("\n")
                        .append("  + ").append(item.name()).append(": ").append(item.newValue()).append("\n");
            }
        }
        result.append("}");
        String ex = result.toString();
        return ex.replaceAll("\"", "").replaceAll(",", ", ");
    }

    public static String plain(Set<DiffItem> s) {

        StringBuilder result = new StringBuilder("\n");
        for (DiffItem item : s) {
            boolean oldValueComplex = item.oldValue() != null && item.oldValue().isContainerNode();
            boolean newValueComplex = item.newValue() != null && item.newValue().isContainerNode();
            switch (item.type()) {
                case ADD -> result.append("Property ").append("'").append(item.name())
                        .append("' was added with value: ").append(newValueComplex ? "[complex value]" : Objects.requireNonNull(item.newValue())
                                .isTextual() ? "'" + item.newValue().asText() + "'" : item.newValue()).append("\n");
                case REMOVE -> result.append("Property ").append("'").append(item.name()).append("' was removed\n");
                case CHANGE -> result.append("Property ").append("'").append(item.name()).append("' was updated. From ").
                        append(oldValueComplex ? "[complex value]" : Objects.requireNonNull(item.oldValue())
                                .isTextual() ? "'" + item.oldValue().asText() + "'" : item.oldValue()).append(" to ").
                        append(newValueComplex ? "[complex value]" : Objects.requireNonNull(item.newValue())
                                .isTextual() ? "'" + item.newValue().asText() + "'" : item.newValue()).append("\n");
            }
        }
        return result.toString();
    }

    public static String json(Set<DiffItem> items) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        for (DiffItem item : items) {
            switch (item.type()) {
                case ADD -> put(node, "+" + item.name(), item.newValue());
                case REMOVE -> put(node, "-" + item.name(), item.oldValue());
                case NOTHING -> put(node, " " + item.name(), item.oldValue());
                case CHANGE -> {
                    put(node, "-" + item.name(), item.oldValue());
                    put(node, "+" + item.name(), item.newValue());
                }
            }
        }
        return node.toPrettyString();
    }

    private static void put(ObjectNode node, String name, JsonNode value) {
        if (value == null || !value.isContainerNode()) {
            node.put(name, value);
            return;
        }
        String stringValue = value
                .toString()
                .replaceAll("\"", "")
                .replaceAll(",", ", ")
                .replaceAll(":", ": ");
        node.put(name, stringValue);
    }
}


