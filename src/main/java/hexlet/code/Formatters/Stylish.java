package hexlet.code.Formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> list) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> map : list) {
            map.forEach((k, v) -> result.append("  ").append(k.contains("-") || k.contains("+") ? k : "  " + k)
                    .append(": ").append(v).append("\n"));
//            for (Map.Entry<String, Object> it : map.entrySet()) {
//                result.append("  ")
//                        .append(it.getKey().contains("-") || it.getKey()
//                                .contains("+") ? it.getKey() : "  " + it.getKey())
//                        .append(": ").append(it.getValue()).append("\n");
//            }
        }
        result.append("}");
        String ex = result.toString();
        return ex.replaceAll("\"", "").replaceAll(",", ", ")
                .replaceAll("-", "- ").replaceAll("[+]", "+ ");
    }
}
