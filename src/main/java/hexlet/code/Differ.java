package hexlet.code;


import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String content = readingAFile(filepath1);
        String content1 = readingAFile(filepath2);
        var map1 = Parser.parseJsonAndYaml(content); //
        var map2 = Parser.parseJsonAndYaml(content1);
        return switch (format) {
            case "stylish" -> Stylish.stylish(Tree.buildJson(map1, map2));
            case "plain" -> Plain.plain(Tree.build(map1, map2));
            case "json" -> Json.json(Tree.buildJson(map1, map2));
            default -> "format entered incorrectly";
        };
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
    private static String readingAFile(String filePath)throws IOException {
        return Files.readString(Paths.get(filePath));
    }

}

