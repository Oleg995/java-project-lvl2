package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public final class DiffItem {
    private final DiffType type;
    @AddingToJson
    private final String name;
    @AddingToJson
    private final JsonNode oldValue;
    @AddingToJson
    private final JsonNode newValue;

    public DiffItem(DiffType type, String name, JsonNode oldValue,
                    JsonNode newValue) {
        this.type = type;
        this.name = name;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DiffItem) obj;
        return Objects.equals(this.type, that.type) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.oldValue, that.oldValue) &&
                Objects.equals(this.newValue, that.newValue);
    }

    @Override
    public String toString() {
        return "DiffItem[" +
                "type=" + type + ", " +
                "name=" + name + ", " +
                "oldValue=" + oldValue + ", " +
                "newValue=" + newValue + ']';
    }

    public DiffType type() {
        return type;
    }

    public String name() {
        return name;
    }

    public JsonNode oldValue() {
        return oldValue;
    }

    public JsonNode newValue() {
        return newValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, oldValue, newValue);
    }


}
