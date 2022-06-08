package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command (
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        version = "1.0.0"
)
public final class App implements Callable<Object> {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionInfoRequested;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format", paramLabel = "format")
    private DiffFormat format;
    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    public String fileOne;
    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    public String fileTwo;

    public static void main(String[] args) throws Exception {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
        } else if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
        } else {
            commandLine.execute(args);
        }
    }

    @Override
    public Object call() throws Exception {
        var map1 = Parser.getYamlFromFile(fileOne);
        var map2 = Parser.getYamlFromFile(fileTwo);
        var diffItems = Differ.generate(map1, map2);
        var s = switch (format) {
            case stylish -> Formatters.stylish(diffItems);
            case plain -> Formatters.plain(diffItems);
            case json -> Formatters.json(diffItems);
        };
        System.out.println(s);
        return null;
    }
}
