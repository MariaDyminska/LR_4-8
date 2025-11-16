package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessCreditTest {

    @Test
    void testConstructorAndGetters() {
        BusinessCredit credit = new BusinessCredit(
                10, "Biz Loan", 50000, 48, 9.5, "IT"
        );

        assertEquals(10, credit.getId());
        assertEquals("Biz Loan", credit.getName());
        assertEquals(50000, credit.getSum());
        assertEquals(48, credit.getTermMonths());
        assertEquals(9.5, credit.getInterestRate());
        assertEquals(CreditType.BUSINESS, credit.getType());
        assertEquals("IT", credit.getBusinessType());
    }

    @Test
    void testSetter() {
        BusinessCredit credit = new BusinessCredit(1, "Test", 1000, 12, 5, "Trade");

        credit.setBusinessType("Services");

        assertEquals("Services", credit.getBusinessType());
    }

    @Test
    void testToString() {
        BusinessCredit credit = new BusinessCredit(1, "Biz", 10000, 12, 5, "Retail");

        String str = credit.toString();

        assertTrue(str.contains("Retail"));
        assertTrue(str.contains("businessType=Retail"));
    }
}
