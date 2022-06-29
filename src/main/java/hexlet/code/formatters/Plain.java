package hexlet.code.formatters;

import hexlet.code.DiffItem;

import java.util.Objects;
import java.util.Set;

public class Plain {
    public static String render(Set<DiffItem> s) {
        StringBuilder result = new StringBuilder();
        for (DiffItem item : s) {
            boolean oldValueComplex = item.oldValue() != null && item.oldValue().isContainerNode();
            boolean newValueComplex = item.newValue() != null && item.newValue().isContainerNode();
            switch (item.type()) {
                case ADD -> result.append("Property ").append("'").append(item.name())
                        .append("' was added with value: ").append(newValueComplex ? "[complex value]"
                                : Objects.requireNonNull(item.newValue())
                                .isTextual() ? "'" + item.newValue().asText() + "'" : item.newValue()).append("\n");
                case REMOVE -> result.append("Property ").append("'").append(item.name()).append("' was removed\n");
                case CHANGE -> result.append("Property ").append("'").append(item.name()).
                        append("' was updated. From ").
                        append(oldValueComplex ? "[complex value]" : Objects.requireNonNull(item.oldValue())
                                .isTextual() ? "'" + item.oldValue().asText() + "'" : item.oldValue()).append(" to ").
                        append(newValueComplex ? "[complex value]" : Objects.requireNonNull(item.newValue())
                                .isTextual() ? "'" + item.newValue().asText() + "'" : item.newValue()).append("\n");
                default -> {
                    //ignore
                }
            }
        }
        return result.toString().trim();
    }
}
