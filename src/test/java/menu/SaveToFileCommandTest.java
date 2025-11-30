package menu;

import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SaveToFileCommandTest {

    private ByteArrayOutputStream out;
    private PrintStream originalOut;

    @BeforeEach
    void setup() {
        out = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restore() {
        System.setOut(originalOut);
    }

    @Test
    void testSaveSuccess() throws Exception {
        CreditSystem system = new CreditSystem();

        String filename = "test_save.txt";
        System.setIn(new ByteArrayInputStream((filename + "\n").getBytes()));

        SaveToFileCommand cmd = new SaveToFileCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Збережено"));

        File f = new File(filename);
        assertTrue(f.exists());
        f.delete();
    }

    @Test
    void testSaveError() {
        CreditSystem system = new CreditSystem() {
            @Override
            public void save(String f) {
                throw new RuntimeException("файл недоступний");
            }
        };

        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));

        SaveToFileCommand cmd = new SaveToFileCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Помилка збереження"));
    }
}
