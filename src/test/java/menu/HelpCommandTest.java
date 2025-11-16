package menu;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HelpCommandTest {

    @Test
    void testHelpCommandOutput() {
        // Перехоплюємо вивід у консоль
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        HelpCommand cmd = new HelpCommand();
        cmd.execute();

        String output = out.toString().trim();

        assertEquals("Доступні команди: help, exit", output);
    }
}
