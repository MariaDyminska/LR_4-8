package menu;

import model.ConsumerCredit;
import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCreditCommandTest {

    @Test
    public void testDeleteCreditCommand_RemovesCredit() {
        CreditSystem system = new CreditSystem();
        system.addCredit(new ConsumerCredit(1, "A", 1000, 12, 10, false));

        String input = "1\n";     // id=1
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DeleteCreditCommand cmd = new DeleteCreditCommand(system);
        cmd.execute();

        assertNull(system.getCreditById(1),
                "Кредит повинен бути видалений");
    }

    @Test
    public void testDeleteCreditCommand_InvalidId_DoesNotRemove() {
        CreditSystem system = new CreditSystem();
        system.addCredit(new ConsumerCredit(1, "A", 1000, 12, 10, false));

        String input = "99\n";     // неіснуючий id
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DeleteCreditCommand cmd = new DeleteCreditCommand(system);
        cmd.execute();

        assertNotNull(system.getCreditById(1),
                "Кредит не повинен бути видалений при неправильному id");
    }
}
