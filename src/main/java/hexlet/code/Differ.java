package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;

import java.util.*;


public class Differ {

    public static Set<DiffItem> generate(Map<String, JsonNode> map1, Map<String, JsonNode> map2) {
        Set<String> keys = unionOfKeys(map1, map2);
        Set<DiffItem> tree = new TreeSet<>(Comparator.comparing(diffItem1 -> diffItem1.name().toLowerCase()));
        for (String key : keys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                DiffItem diffItem = new DiffItem(DiffType.REMOVE, key, map1.get(key), null);
                tree.add(diffItem);
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                DiffItem diffItem = new DiffItem(DiffType.ADD, key, null, map2.get(key));
                tree.add(diffItem);
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                DiffItem diffItem = new DiffItem(DiffType.NOTHING, key, map1.get(key), map1.get(key));
                tree.add(diffItem);
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                DiffItem diffItem = new DiffItem(DiffType.CHANGE, key, map1.get(key), map2.get(key));
                tree.add(diffItem);
            }
        }
        return tree;
    }

    private  static Set<String> unionOfKeys(Map<String, JsonNode> map1, Map<String, JsonNode> map2) {
        Set<String> keys = new TreeSet<>(String::compareToIgnoreCase);
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }
}

