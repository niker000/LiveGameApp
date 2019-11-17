package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWorker {
    public void writeResultToFile(String path, String content) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            if(!file.createNewFile()){
                throw new IOException("Can`t create file");
            }
        }
        Files.writeString(Paths.get(path),content, StandardCharsets.UTF_8);
    }

    public String readConditionFile(String path) throws IOException {
        return Files.readString(Paths.get(path), StandardCharsets.UTF_8);
    }
}
