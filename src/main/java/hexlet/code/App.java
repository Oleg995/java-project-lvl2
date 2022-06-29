package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        version = "1.0.0"
)
public final class App implements Callable<Object> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionInfoRequested;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format", paramLabel = "format")
    private String format;
    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String fileOne;
    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String fileTwo;

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.execute(args);
    }

    @Override
    public Integer call() {
        String diff;
        try {
            diff = Differ.generate(fileOne, fileTwo, format);
        } catch (Exception exception) {
            return ERROR_EXIT_CODE;
        }
        System.out.println(diff);
        return SUCCESS_EXIT_CODE;
    }
}
