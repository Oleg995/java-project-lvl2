package hexlet.code;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;


public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<Object, Object> map1 = getJsonFromFile(filePath1);
        Map<Object, Object> map2 = getJsonFromFile(filePath2);
        var s = unionOfKeys(map1, map2).stream().
                map(it -> getResultMap(map1, map2, (String) it)).toList();
        return stringBuilder(s);
    }
    private static Map<Object, Object> getJsonFromFile(final String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    private static SortedSet<String> getResultMap(Map<Object, Object> map1, Map<Object, Object> map2, String key) {
        SortedSet<String> keys = new TreeSet<>(String::compareToIgnoreCase);
        if (map2.containsKey(key) && map1.containsKey(key)) {
            if (Objects.equals(map1.get(key), map2.get(key))) {
                keys.add(" " + key + ":" + map1.get(key) + "\n");
            } else  {
                keys.add("-" + key + ":" + map1.get(key) + "\n");
                keys.add("+" + key + ":" + map2.get(key) + "\n");
            }
        } else if (!map1.containsKey(key)) {
            keys.add("+" + key + ":" + map2.get(key) + "\n");
        } else if (!map2.containsKey(key)) {
            keys.add("-" + key + ":" + map1.get(key) + "\n");
        }
        return keys;
    }

    private  static SortedSet<Object> unionOfKeys(Map<Object, Object> map1, Map<Object, Object> map2) {
        SortedSet<Object> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }

    private static String stringBuilder(List<SortedSet<String>> s) {
        StringBuilder result = new StringBuilder("{\n");
        for (SortedSet<String> line : s) {
            for (String stringLine: line) {
                result.append(stringLine);
            }
        }
        result.append("}");
        return result.toString();
    }
}
//    public static String generate(Map<Object, Object> map, Map<Object, Object> map2) throws IOException {
//        Set<Object> examination = map.keySet();
//        SortedSet<String> list = new TreeSet<>(Comparator.comparing((String o) -> o.toLowerCase().substring(1)));
//        for (Object key : examination) {
//            if (map2.containsKey(key)) {
//                if (Objects.equals(map.get(key), map2.get(key))) {
//                    list.add(" " + key + ":" + map.get(key) + "\n");
//                } else if (!Objects.equals(map.get(key), map2.get(key))) {
//                    list.add("-" + key + ":" + map.get(key) + "\n");
//                }
//            } else {
//                list.add("-" + key + ":" + map.get(key) + "\n");
//            }
//        }
//        Set<Object> examination2 = map2.keySet();
//        for (Object key : examination2) {
//            if (map.containsKey(key)) {
//                if (!Objects.equals(map2.get(key), map.get(key))) {
//                    list.add("+" + key + ":" + map2.get(key) + "\n");
//                }
//            } else {
//                list.add("+" + key + ":" + map2.get(key) + "\n");
//            }
//        }
//        StringBuilder result = new StringBuilder("{\n");
//        for (String line : list) {
//            result.append(line);
//        }
//        result.append("}");
//        return result.toString();
//    }
