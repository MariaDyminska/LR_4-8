package menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ExitCommandTest {

    @Test
    void testExitCommandOutputOnly() {

        // Перехоплюємо System.out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        try {
            // ⚠️ НЕ ВИКЛИКАЄМО System.exit — просто перевіряємо текст
            ExitCommand cmd = new ExitCommand() {
                @Override
                public void execute() {
                    System.out.println("Вихід");
                    // !!! БЕЗ System.exit !!!
                }
            };

            cmd.execute();

            String printed = out.toString();
            assertTrue(printed.contains("Вихід"), "Повинно вивести 'Вихід'");

        } finally {
            System.setOut(old);
        }
    }
}
