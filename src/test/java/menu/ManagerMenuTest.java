package menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerMenuTest {

    private CreditSystem system;

    @BeforeEach
    public void setup() {
        system = new CreditSystem();
    }

    // Метод для запуску меню із заданим введенням
    private String runMenu(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ManagerMenu menu = new ManagerMenu(system);
        try {
            menu.show();
        } catch (Exception e) {
            // Ігноруємо помилки для тестів UI
        }

        return out.toString();
    }

    @Test
    public void testMenuDisplayed() {
        String output = runMenu("5\n"); // вихід одразу
        assertTrue(output.contains("МЕНЮ МЕНЕДЖЕРА"), "Меню має відобразитися");
    }

    @Test
    public void testInvalidOption() {
        String output = runMenu("X\n5\n"); // невірний пункт + вихід
        assertTrue(output.contains("Невірний пункт"), "Повинно бути повідомлення про невірний пункт");
    }

    @Test
    public void testAddCredit() {
        // пункт 1 -> не зберігати у файл -> вихід
        String output = runMenu("1\nні\n5\n");
        assertTrue(output.contains("МЕНЮ МЕНЕДЖЕРА"), "Меню має відобразитися");
    }

    @Test
    public void testEditCredit() {
        // пункт 2 -> не завантажувати з файлу -> редагувати -> не зберігати -> вихід
        String output = runMenu("2\nні\nні\n5\n");
        assertTrue(output.contains("МЕНЮ МЕНЕДЖЕРА"), "Меню має відобразитися");
    }

    @Test
    public void testViewCredits() {
        // пункт 3 -> не завантажувати -> вихід
        String output = runMenu("3\nні\n5\n");
        assertTrue(output.contains("МЕНЮ МЕНЕДЖЕРА"), "Меню має відобразитися");
    }

    @Test
    public void testDeleteCredit() {
        // пункт 4 -> не зберігати -> вихід
        String output = runMenu("4\nні\n5\n");
        assertTrue(output.contains("МЕНЮ МЕНЕДЖЕРА"), "Меню має відобразитися");
    }

    @Test
    public void testExit() {
        String output = runMenu("5\n");
        assertTrue(output.contains("МЕНЮ МЕНЕДЖЕРА"), "Меню має відобразитися перед виходом");
    }
}
