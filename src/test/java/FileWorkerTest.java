import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.FileWorker;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileWorkerTest {
    private final String PATH = "src\\main\\resources\\testFile";
    private File file;
    private FileWorker fileWorker;

    @Before
    public void setUp() throws Exception {
        this.file = new File(PATH);
        fileWorker = new FileWorker();
        Files.writeString(Paths.get(PATH),"Test", StandardCharsets.UTF_8);
    }

    @After
    public void tearDown() throws Exception {
        this.file.delete();
    }

    @Test
    public void writeResultToFile() throws IOException{
        File file = new File(PATH);
        fileWorker.writeResultToFile(PATH,"Test");
        String expected =  Files.readString(Paths.get(PATH), StandardCharsets.UTF_8);

        assertTrue(file.exists());
        assertEquals(expected,"Test");
    }

    @Test
    public void readConditionFile() throws IOException {
        File file = new File(PATH);
        file.createNewFile();

        String currentContent = fileWorker.readConditionFile(PATH);
        String expected = "Test";
        Assert.assertEquals(expected,currentContent);
    }

    @Test(expected = IOException.class)
    public void whenWriteResultToFile_thenException() throws IOException {
        fileWorker.writeResultToFile("", "Test");
    }

    @Test(expected = IOException.class)
    public void whenReadConditionFile_thenException() throws IOException {
        fileWorker.readConditionFile("");
    }
}