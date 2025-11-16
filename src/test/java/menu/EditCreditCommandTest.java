package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class EditCreditCommandTest {

    @Test
    void testEditCreditCommand_DoesNotCrash() throws Exception {
        // Зберегти оригінальні потоки
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // ❗ Створюємо тестову систему з кредитом
            CreditSystem system = new CreditSystem();
            system.addCredit(new Credit(1, "TestCredit", 1000, 12, 5.0, CreditType.CONSUMER));

            // ❗ Емуляція введення користувача:
            // ID = 1
            // назва = нова
            // сума = 2000
            // термін = 24
            // ставка = 8
            String input =
                    "1\n" +
                            "нова назва\n" +
                            "2000\n" +
                            "24\n" +
                            "8\n";

            ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(testIn);

            ByteArrayOutputStream testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));

            // ❗ Викликаємо команду
            EditCreditCommand cmd = new EditCreditCommand(system);

            assertDoesNotThrow(cmd::execute);

            // ❗ Перевіряємо, що дані змінені
            Credit c = system.getCreditById(1);
            assertNotNull(c);
            assertEquals("нова назва", c.getName());
            assertEquals(2000, c.getSum());
            assertEquals(24, c.getTermMonths());
            assertEquals(8.0, c.getInterestRate());

            // ❗ Перевіряємо, що вивід містить "Оновлено."
            String output = testOut.toString("UTF-8");
            assertTrue(output.contains("Оновлено."));

        } finally {
            // Відновлюємо системні потоки
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
