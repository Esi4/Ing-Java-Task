import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.ing.ReadInputFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReadInputFileTest {
    private static final String ING_FILE_PATH = "C:\\Users\\kot8565\\Desktop\\Idea Projects\\Ing-java-task\\src\\main\\resources\\lng.txt";

    private List<String> lng;
    private ReadInputFile readInputFile;

    @BeforeEach
    public void setUp() {
        lng = new ArrayList<>();
        readInputFile = new ReadInputFile(ING_FILE_PATH);

        readFile(lng, ING_FILE_PATH);
    }

    public void readFile(List<String> arr, String path) {
        try (BufferedReader inputReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = inputReader.readLine()) != null) {
                arr.add(line);
            }
        } catch (IOException e) {
            fail("Error read file" + e.getMessage());
        }
    }

    @Test
    public void testIsValidStringLngFile() {
        for (int i = 0; i < lng.size(); i++) {
            String input = lng.get(i);
            boolean result = readInputFile.isValidString(input);

            assertTrue(result, String.format("String number %d is not valid", i + 1));
        }
    }

    @AfterAll
    public static void afterAllMessage() {
        System.out.println("All tests complete");
    }
}
