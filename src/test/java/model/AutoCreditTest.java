package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutoCreditTest {

    @Test
    void testConstructorAndGetters() {
        AutoCredit credit = new AutoCredit(
                1,
                "Car Loan",
                20000.0,
                36,
                7.5,
                "Toyota"
        );

        assertEquals(1, credit.getId());
        assertEquals("Car Loan", credit.getName());
        assertEquals(20000.0, credit.getSum());
        assertEquals(36, credit.getTermMonths());
        assertEquals(7.5, credit.getInterestRate());
        assertEquals(CreditType.AUTO, credit.getType());
        assertEquals("Toyota", credit.getCarBrand());
    }

    @Test
    void testSetCarBrand() {
        AutoCredit credit = new AutoCredit(2, "Test", 10000, 12, 5.0, "BMW");

        assertEquals("BMW", credit.getCarBrand());

        credit.setCarBrand("Audi");

        assertEquals("Audi", credit.getCarBrand());
    }

    @Test
    void testToString() {
        AutoCredit credit = new AutoCredit(3, "Auto", 15000, 24, 6.0, "Honda");

        String result = credit.toString();

        assertTrue(result.contains("Honda"));
        assertTrue(result.contains("Auto"));
        assertTrue(result.contains("15000"));
        assertTrue(result.contains("carBrand=Honda"));
    }
}
