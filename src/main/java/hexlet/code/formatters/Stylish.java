package hexlet.code.formatters;

import hexlet.code.DiffItem;

import java.util.Set;

public class Stylish {
    public static String render(Set<DiffItem> s) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffItem item : s) {
            switch (item.type()) {
                case ADD -> result.append("  + ").append(item.name()).append(": ").append(item.newValue().toString().
                                replaceAll(":", "=")).append("\n");
                case REMOVE -> result.append("  - ").append(item.name()).append(": ").append(item.oldValue().toString().
                                replaceAll(":", "=")).append("\n");
                case NOTHING -> result.append("    ").append(item.name()).append(": ").append(item.oldValue().toString().
                        replaceAll(":", "=")).append("\n");
                case CHANGE -> result.append("  - ").append(item.name()).append(": ").append(item.oldValue().toString().
                        replaceAll(":", "=")).append("\n")
                        .append("  + ").append(item.name()).append(": ").append(item.newValue().toString().
                                replaceAll(":", "=")).append("\n");
                default -> {
                    //ignore
                }
            }
        }
        result.append("}");

        return result.toString().replaceAll("\"", "").replaceAll(",", ", ").trim();
    }
}
