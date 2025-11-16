package menu;

import system.CreditSystem;
import model.ConsumerCredit;
import model.CreditStatus;
import org.junit.jupiter.api.*;
import java.io.*;

public class EarlyPayoffCommandTest {

    @Test
    public void testEarlyPayoffCommand_ClosesCreditButDoesNotRemove() {

        // підготовка системи
        CreditSystem system = new CreditSystem();
        system.addCredit(new ConsumerCredit(1, "Test", 1000, 12, 10, false));

        // симуляція введення
        String input = "1\n1000\n"; // id=1, погашення 1000
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // виконання команди
        EarlyPayoffCommand cmd = new EarlyPayoffCommand(system);
        cmd.execute();

        // отримуємо кредит
        var credit = system.getCreditById(1);

        // ✔ Кредит не видалений
        Assertions.assertNotNull(credit, "Кредит НЕ має бути видалений");

        // ✔ Сума стала 0
        Assertions.assertEquals(0.0, credit.getSum(), 0.0001,
                "Сума після повного погашення має бути 0");

        // ✔ Статус CLOSED
        Assertions.assertEquals(CreditStatus.CLOSED, credit.getStatus(),
                "Після повного погашення кредит має бути CLOSED");
    }
}
