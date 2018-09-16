package readwrite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

    private static final Logger LOGGER = Logger.getLogger(ReadFile.class.getName());

    public static ArrayList<String> readFile(String fileNameToRead) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileNameToRead))) {
            //1. filter lines starting with #
            //3. convert it into a List
            list = stream
                    .filter(line -> !line.startsWith("#"))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, String.format("Wrong filename or corrupted file:%s or not correct values causing  error reading the file: %s:", fileNameToRead, fileNameToRead), e);
            throw new IOException(String.format("Wrong filename file:%s, or corrupted data causing  error reading the file: %s:", fileNameToRead, fileNameToRead));
        }
        LOGGER.log(Level.INFO, String.format("Successfully reed from file:%s", fileNameToRead));
        return list;
    }


}