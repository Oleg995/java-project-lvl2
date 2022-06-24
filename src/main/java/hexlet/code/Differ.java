package hexlet.code;


import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException, IllegalAccessException {
        var map1 = Parser.getYamlFromFile(readingOfTheFile(filepath1));
        var map2 = Parser.getYamlFromFile(readingOfTheFile(filepath2));
        return switch (format) {
            case "stylish" -> Stylish.stylish(Tree.build(map1, map2));
            case "plain" -> Plain.plain(Tree.build(map1, map2));
            case "json" -> Json.json(Tree.build(map1, map2));
            default -> "invalid format";
        };
    }

    public static String generate(String filepath1, String filepath2) throws IOException, IllegalAccessException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String readingOfTheFile(String path) throws IOException {
        return Files.readString(Paths.get(path)).toString();
    }

}

