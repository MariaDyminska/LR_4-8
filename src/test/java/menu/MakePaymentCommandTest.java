package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class MakePaymentCommandTest {

    @Test
    void testMakePayment() {

        // Готуємо систему
        CreditSystem system = new CreditSystem();

        // Додаємо кредит вручну
        Credit c = new Credit(
                1,                 // id
                "Test",            // name
                1000.0,            // sum
                12,                // term
                10.0,              // rate
                CreditType.CONSUMER
        );

        system.addCredit(c);

        // Імітуємо введення:
        // id = 1
        // amount = 200
        String input = "1\n200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Виконуємо команду
        MakePaymentCommand cmd = new MakePaymentCommand(system);
        cmd.execute();

        // Тепер перевіряємо стан кредиту
        Credit updated = system.getCreditById(1);

        assertNotNull(updated, "Кредит повинен існувати");
        assertEquals(800.0, updated.getSum(), 0.0001,
                "Після оплати сума повинна зменшитися на 200");
    }
}
