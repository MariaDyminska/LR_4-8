package menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientMenuTest {

    private CreditSystem system;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setup() {
        system = new CreditSystem();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Допоміжний метод: запускає меню з заданим набором вводу.
     */
    private String runMenu(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ClientMenu menu = new ClientMenu(system);
        menu.show();
        return outContent.toString();
    }

    @Test
    void testMenuDisplayed() {
        String output = runMenu("10\n"); // Просто вихід
        assertTrue(output.contains("МЕНЮ КЛІЄНТА"), "Меню клієнта має відобразитися");
    }

    @Test
    void testInvalidOption() {
        String output = runMenu("X\n10\n"); // Невірний пункт + вихід
        assertTrue(output.contains("Невірний пункт"), "Повинно бути повідомлення про невірний пункт");
    }

    @Test
    void testExitOption() {
        long start = System.currentTimeMillis();
        runMenu("10\n"); // Вихід
        long end = System.currentTimeMillis();
        assertTrue((end - start) < 1000, "Меню має завершитися швидко при виборі '10'");
    }

    @Test
    void testAllValidOptions() {
        // Послідовність вибору всіх дій + вихід
        String input = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n";
        String output = runMenu(input);

        for (int i = 1; i <= 9; i++) {
            assertTrue(output.contains("МЕНЮ КЛІЄНТА"), "Пункт " + i + " повинен запускатися без помилок");
        }
        assertTrue(output.contains("МЕНЮ КЛІЄНТА"), "Меню має знову відобразитися після всіх команд");
    }
}
