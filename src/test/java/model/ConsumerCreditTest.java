package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumerCreditTest {

    @Test
    void testConstructorAndGetters() {
        ConsumerCredit credit = new ConsumerCredit(
                20, "Cons Loan", 15000, 24, 8.0, true
        );

        assertEquals(20, credit.getId());
        assertEquals("Cons Loan", credit.getName());
        assertEquals(15000, credit.getSum());
        assertEquals(24, credit.getTermMonths());
        assertEquals(8.0, credit.getInterestRate());
        assertEquals(CreditType.CONSUMER, credit.getType());
        assertTrue(credit.isInsuranceIncluded());
    }

    @Test
    void testSetter() {
        ConsumerCredit credit = new ConsumerCredit(1, "Test", 1000, 12, 5, false);

        credit.setInsuranceIncluded(true);

        assertTrue(credit.isInsuranceIncluded());
    }

    @Test
    void testToString() {
        ConsumerCredit credit = new ConsumerCredit(1, "Cons", 10000, 12, 5, true);

        String str = credit.toString();

        assertTrue(str.contains("insurance=true"));
    }
}
