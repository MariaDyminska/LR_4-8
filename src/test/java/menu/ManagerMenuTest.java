package menu;
import org.junit.jupiter.api.Test;
import system.CreditSystem;
import model.Credit;
import model.CreditType;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerMenuTest {

    @Test
    public void testAddCredit() {
        CreditSystem system = new CreditSystem();
        int before = system.getCredits().size();

        // Створюємо об'єкт Credit
        Credit credit = new Credit(1, "TestCredit", 5000, 12, 10, CreditType.CONSUMER);

        system.addCredit(credit);  // додаємо в систему

        int after = system.getCredits().size();
        assertEquals(before + 1, after);
    }

    @Test
    public void testDeleteCredit() {
        CreditSystem system = new CreditSystem();

        Credit credit = new Credit(1, "DeleteMe", 3000, 6, 15, CreditType.CONSUMER);
        system.addCredit(credit);


    }

    @Test
    public void testEditCredit() {
        CreditSystem system = new CreditSystem();
        Credit credit = new Credit(1, "EditMe", 4000, 12, 5, CreditType.CONSUMER);
        system.addCredit(credit);

        // Емуляція EditCreditCommand
        credit.setInterestRate(20);
        assertEquals(20, credit.getInterestRate());
    }

    @Test
    public void testViewCredits() {
        CreditSystem system = new CreditSystem();
        assertDoesNotThrow(system::getCredits);
    }
}
