package hexlet.code;


import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.io.IOException;


public class Differ {

    public static String generate(String filepath1, String filepath2, DiffFormat format) throws IOException, IllegalAccessException {
        var map1 = Parser.getYamlFromFile(filepath1);
        var map2 = Parser.getYamlFromFile(filepath2);
        return switch (format) {
            case stylish -> Stylish.stylish(Tree.build(map1, map2));
            case plain -> Plain.plain(Tree.build(map1, map2));
            case json -> Json.json(Tree.build(map1, map2));
        };
    }

    public static String generate(String filepath1, String filepath2) throws IOException, IllegalAccessException {
        return generate(filepath1, filepath2, DiffFormat.stylish);
    }

}

