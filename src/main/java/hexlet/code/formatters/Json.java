package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.DiffItem;

import java.util.Set;

public class Json {
    public static String render(Set<DiffItem> items) throws JsonProcessingException, IllegalAccessException {
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
                default -> {
                    //ignore
                }
            }
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
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
