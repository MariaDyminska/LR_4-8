package main;

import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    void testMainMenuExit() throws Exception {
        String input = "3\n";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(outContent));
        System.setProperty("testMode", "true");

        try {
            Main.main(new String[0]);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
            System.clearProperty("testMode");
        }

        String output = outContent.toString();

        assertTrue(output.contains("ГОЛОВНЕ МЕНЮ"));
        assertTrue(output.contains("До побачення"));
    }
}
