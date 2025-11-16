package menu;

import system.CreditSystem;
import model.ConsumerCredit;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AddCreditCommandTest {

    @Test
    void testAddConsumerCredit() {
        // ---------- підміна System.in ----------
        String input =
                "1\n" +        // тип = ConsumerCredit
                        "10\n" +       // id
                        "Test Credit\n" + // Назва
                        "50000\n" +    // Сума
                        "12\n" +       // Термін
                        "7.5\n" +      // Ставка
                        "true\n";      // Страхування

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // ---------- підміна System.out ----------
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // ---------- тест ----------
        CreditSystem system = new CreditSystem();
        AddCreditCommand cmd = new AddCreditCommand(system);

        cmd.execute();

        // ---------- перевірка ----------

        assertEquals(1, system.getCredits().size(), "Кредит не додався");

        assertTrue(system.getCredits().get(0) instanceof ConsumerCredit);

        ConsumerCredit c = (ConsumerCredit) system.getCredits().get(0);

        assertEquals(10, c.getId());
        assertEquals("Test Credit", c.getName());
        assertEquals(50000, c.getSum());
        assertEquals(12, c.getTerm());
        assertEquals(7.5, c.getRate());
        assertTrue(c.isInsurance());
    }
}

