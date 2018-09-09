import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadWriteFile {

    private static final Logger LOGGER = Logger.getLogger(ReadWriteFile.class.getName());

    public static ArrayList<String> readFile(String fileNameToRead) {
        ArrayList<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileNameToRead))) {
            //1. filter lines starting with #
            //3. convert it into a List
            list = stream
                    .filter(line -> !line.startsWith("#"))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Wrong filename or corrupted file or not correct values causing  error reading the file");
        }
        LOGGER.log(Level.INFO, "Successfully reed from file");
        return list;
    }

    public static void writeFile(String fileNameToWrite, String textToAdd) {
        try {
            Files.write(Paths.get(fileNameToWrite), textToAdd.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            LOGGER.log(Level.INFO, "Successfully write to file");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No access writing to the file", e);
        }
    }
}