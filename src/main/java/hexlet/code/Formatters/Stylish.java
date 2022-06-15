package hexlet.code.Formatters;

import hexlet.code.DiffItem;

import java.util.Set;

public class Stylish {
    public static String stylish(Set<DiffItem> s) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffItem item : s) {
            switch (item.type()) {
                case ADD -> result.append("  + ").append(item.name()).append(": ").append(item.newValue())
                        .append("\n");
                case REMOVE -> result.append("  - ").append(item.name()).append(": ").append(item.oldValue())
                        .append("\n");
                case NOTHING -> result.append("    ").append(item.name()).append(": ").append(item.oldValue())
                        .append("\n");
                case CHANGE -> result.append("  - ").append(item.name()).append(": ").append(item.oldValue())
                        .append("\n")
                        .append("  + ").append(item.name()).append(": ").append(item.newValue()).append("\n");
                default -> {
                    //ignore
                }
            }
        }
        result.append("}");
        String ex = result.toString();
        return ex.replaceAll("\"", "").replaceAll(",", ", ");
    }
}
