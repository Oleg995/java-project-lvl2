package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonHandler {

    public static Map<Object, Object> getJsonFromFile(final String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static String generate(Map<Object, Object> map, Map<Object, Object> map2) throws IOException {
        Set<Object> examination = map.keySet().stream().sorted().collect(Collectors.toSet());
        StringBuilder result = null;
        result = new StringBuilder("{\n");
        for (Object key : examination) {
            if (map2.containsKey(key)) {
                if (map.get(key).equals(map2.get(key))) {
                    result.append(" ").append(key).append(":").append(map.get(key)).append("\n");
                } else if (!map.get(key).equals(map2.get(key))) {
                    assert result != null;
                    result.append("-").append(key).append(":").append(map.get(key)).append("\n");
                }
            } else {
                result.append("-").append(key).append(":").append(map.get(key)).append("\n");
            }
        }
        Set<Object> examination2 = map2.keySet().stream()
                .sorted().collect(Collectors.toSet());
        for (Object key : examination2) {
            if (map.containsKey(key)) {
                if (!map2.get(key).equals(map.get(key))) {
                    result.append("+").append(key).append(":").append(map2.get(key)).append("\n");
                }
            } else {
                result.append("+").append(key).append(":").append(map2.get(key)).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}


//    public static String search(Map<Object, Object> map, Map<Object, Object> map2) throws IOException {
//        StringBuilder result = new StringBuilder();
//        result.append("{\n");
//        for (Map.Entry<Object, Object> element: map.entrySet()) {
//            if (map2.containsKey(element.getKey())) {
//                if (element.getValue().equals(map2.get(element.getKey()))) {
//                    result
//                }
//            }
//        return result.toString();
//    }
//}



//        return map.keySet().stream().
//                map(element-> "-" + element).collect(Collectors.toSet());
