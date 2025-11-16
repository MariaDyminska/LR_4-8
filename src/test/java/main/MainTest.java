package main;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testMainStartsAndPrintsMenu() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Порожній ввід
            ByteArrayInputStream testIn = new ByteArrayInputStream(new byte[0]);
            System.setIn(testIn);

            ByteArrayOutputStream testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));

            // Очікуємо NoSuchElementException через кінець вводу
            assertThrows(NoSuchElementException.class, () -> {
                Main.main(new String[]{});
            });

            // Читаємо текст без try/catch — тепер дозволено
            String output = testOut.toString("UTF-8");

            assertTrue(
                    output.contains("ГОЛОВНЕ МЕНЮ"),
                    "main() повинен друкувати головне меню при запуску"
            );

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
