package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class Tree {
    public static List<Map<String, Object>> build(Map<String, JsonNode> map1, Map<String, JsonNode> map2)
            throws IOException {
        Set<String> keys = unionOfKeys(map1, map2);
        List<Map<String, Object>> list = new LinkedList<>();
        for (String key : keys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                Map<String, Object> map = new HashMap<>();
                map.put(String.valueOf(DiffType.REMOVE), key);
                map.put("value", map1.get(key).isContainerNode() ? "[complex value]" : map1.get(key));
                list.add(map);
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                Map<String, Object> map = new HashMap<>();
                map.put(String.valueOf(DiffType.ADD), key);
                map.put("value", map2.get(key).isContainerNode() ? "[complex value]" : map2.get(key));
                list.add(map);
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                Map<String, Object> map = new HashMap<>();
                map.put(String.valueOf(DiffType.NOTHING), key);
                map.put("value", map1.get(key).isContainerNode() ? "[complex value]" : map1.get(key));
                list.add(map);
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                Map<String, Object> map = new HashMap<>();
                map.put("newValue", map2.get(key).isContainerNode() ? "[complex value]" : map2.get(key));
                map.put("oldValue", map1.get(key).isContainerNode() ? "[complex value]" : map1.get(key));
                map.put(String.valueOf(DiffType.CHANGE), key);
                list.add(map);
            }
        }
        return list;
    }
    public static List<Map<String, Object>> buildJson(Map<String, JsonNode> map1, Map<String, JsonNode> map2)
            throws IOException {
        Set<String> keys = unionOfKeys(map1, map2);
        List<Map<String, Object>> list = new LinkedList<>();
        for (String key : keys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                Map<String, Object> map = new HashMap<>();
                map.put("-" + key, map1.get(key));
                list.add(map);
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                Map<String, Object> map = new HashMap<>();
                map.put("+" + key, map2.get(key));
                list.add(map);
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                Map<String, Object> map = new HashMap<>();
                map.put(key, map1.get(key));
                list.add(map);
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                Map<String, Object> map = new TreeMap<>(Collections.reverseOrder());
                map.put("+" + key, map2.get(key));
                map.put("-" + key, map1.get(key));
                list.add(map);
            }
        }
        return list;
    }

    private static Set<String> unionOfKeys(Map<String, JsonNode> map1, Map<String, JsonNode> map2) {
        Set<String> keys = new TreeSet<>(String::compareToIgnoreCase);
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }
}
