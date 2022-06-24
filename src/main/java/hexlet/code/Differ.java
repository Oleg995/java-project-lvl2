package hexlet.code;


import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = Parser.getParseJsonAndYaml(readingOfTheFile(filepath1));
        var map2 = Parser.getParseJsonAndYaml(readingOfTheFile(filepath2));
        return switch (format) {
            case "stylish" -> Stylish.render(Tree.build(map1, map2));
            case "plain" -> Plain.render(Tree.build(map1, map2));
            case "json" -> Json.render(Tree.build(map1, map2));
            default -> "invalid format";
        };
    }

    public static String generate(String filepath1, String filepath2) throws IOException, IllegalAccessException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String readingOfTheFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

}

