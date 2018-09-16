package readwrite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile {

    private static final Logger LOGGER = Logger.getLogger(WriteFile.class.getName());

    public static void writeFile(String fileNameToWrite, String textToAdd) {
        try {
            Files.write(Paths.get(fileNameToWrite), textToAdd.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            LOGGER.log(Level.INFO, String.format("Successfully write to file:%s the data:%s", fileNameToWrite, textToAdd));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, String.format("No access writing to the file:%s the data:%s", fileNameToWrite, textToAdd), e);
        }
    }
}
