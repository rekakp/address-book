package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReadingService {

    public List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            URL file = this.getClass()
                    .getClassLoader().getResource(fileName);
            if (file != null) {
                lines = Files.readAllLines(Paths.get(file.toURI()));
            } else {
                throw new FileNotFoundException();
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
