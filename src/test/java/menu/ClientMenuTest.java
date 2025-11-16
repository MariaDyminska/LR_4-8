package menu;

import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientMenuTest {

    @Test
    void testClientMenuExit() {

        // Ввід: "10" → вихід
        String input = "10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CreditSystem system = new CreditSystem();
        ClientMenu menu = new ClientMenu(system);

        long start = System.currentTimeMillis();

        // Якщо меню вийде правильно — метод завершиться
        menu.show();

        long end = System.currentTimeMillis();

        // Перевірка: show() не повинен працювати більше певного часу
        assertTrue((end - start) < 1000,
                "Меню повинно завершитися при виборі '10'");
    }
}
