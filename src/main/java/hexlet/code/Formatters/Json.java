package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.DiffItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Json {
    public static String json(Set<DiffItem> items) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map <String, Object> map = new HashMap<String, Object>();
        return mapper.writeValueAsString(items);
//        ObjectNode node = mapper.createObjectNode();
//        for (DiffItem item : items) {
//            map.put(item.)
        }
//            switch (item.type()) {
//                case ADD -> put(node, "+" + item.name(), item.newValue());
//                case REMOVE -> put(node, "-" + item.name(), item.oldValue());
//                case NOTHING -> put(node, " " + item.name(), item.oldValue());
//                case CHANGE -> {
//                    put(node, "-" + item.name(), item.oldValue());
//                    put(node, "+" + item.name(), item.newValue());
//                }
//                default -> {
//                    //ignore
//                }
//            }
//        }
//        return mapper.writeValueAsString(node.toString());
//    }

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
