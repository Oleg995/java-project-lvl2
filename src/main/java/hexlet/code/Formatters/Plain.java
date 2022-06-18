package hexlet.code.Formatters;

import hexlet.code.DiffType;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(List<Map<String, Object>> list) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> map  : list) {
            if (map.containsKey(String.valueOf(DiffType.REMOVE))) {
                result.append("Property ").append("'").append(map.get(String.valueOf(DiffType.REMOVE))).append("'")
                        .append(" was removed\n");
            } else if (map.containsKey(String.valueOf(DiffType.ADD))) {
                result.append("Property ").append("'").append(map.get(String.valueOf(DiffType.ADD))).append("'")
                        .append(" was added with value: ")
                        .append(map.get("value")).append("\n");
            } else if (map.containsKey(String.valueOf(DiffType.CHANGE))) {
                result.append("Property ").append("'").append(map.get(String.valueOf(DiffType.CHANGE))).append("'")
                        .append(" was updated. From ")
                        .append(map.get("oldValue")).append(" to ").append(map.get("newValue")).append("\n");
            }
        }
        return result.toString().replaceAll("\"", "'").trim();
    }
}
