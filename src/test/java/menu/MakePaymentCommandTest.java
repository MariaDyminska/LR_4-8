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


        CreditSystem system = new CreditSystem();

        Credit c = new Credit(
                1,
                "Test",
                1000.0,
                12,
                10.0,
                CreditType.CONSUMER
        );

        system.addCredit(c);

        String input = "1\n200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));


        MakePaymentCommand cmd = new MakePaymentCommand(system);
        cmd.execute();


        Credit updated = system.getCreditById(1);

        assertNotNull(updated, "Кредит повинен існувати");
        assertEquals(800.0, updated.getSum(), 0.0001,
                "Після оплати сума повинна зменшитися на 200");
    }
}
