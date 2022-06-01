package hexlet.code;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class DifferTest {

    @Test
    public void test() {
        String path = "src/test/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        assertTrue(absolutePath.endsWith("src/test/resources"));
    }
    @Test
    public void generateTest() throws IOException {
        String file = Files.readString(Paths.get("src/test/resources/example"));
        assertThat(Differ.generate("src/test/resources/fileOne.json", "src/test/resources/fileTwo.json")).
                isEqualTo(file);
    }
    @Test
    public void generateTest2() throws IOException {
        String file = Files.readString(Paths.get("/home/yatorogod/java-project-lvl2/src/test/resources/example"));
        assertThat(Differ.generate("/home/yatorogod/java-project-lvl2/src/test/resources/fileOne.json",
                "/home/yatorogod/java-project-lvl2/src/test/resources/fileTwo.json")).
                isEqualTo(file);
    }


}
