package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditTest {

    @Test
    void testCalculateMonthlyPayment_Annuity() {
        Credit c = new Credit(1, "Test", 10000, 12, 12, CreditType.CONSUMER);

        double payment = c.calculateMonthlyPayment();

        assertEquals(888.49, payment, 0.01); // очікуване значення
    }

    @Test
    void testCalculateMonthlyPayment_ZeroRate() {
        Credit c = new Credit(2, "ZeroRate", 12000, 12, 0, CreditType.CONSUMER);

        assertEquals(1000, c.calculateMonthlyPayment(), 0.01);
    }

    @Test
    void testTotalPayment() {
        Credit c = new Credit(1, "Test", 12000, 12, 0, CreditType.CONSUMER);

        assertEquals(12000, c.totalPayment(), 0.001);
    }

    @Test
    void testOverpay() {
        Credit c = new Credit(1, "Test", 12000, 12, 0, CreditType.CONSUMER);

        assertEquals(0, c.overpay(), 0.001);
    }

    @Test
    void testToString() {
        Credit c = new Credit(1, "Loan", 10000, 12, 5, CreditType.CONSUMER);

        String str = c.toString();

        assertTrue(str.contains("Loan"));
        assertTrue(str.contains("sum=10000"));
        assertTrue(str.contains("rate=5"));
        assertTrue(str.contains("type=CONSUMER"));
    }
}
