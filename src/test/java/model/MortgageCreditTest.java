package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MortgageCreditTest {

    @Test
    void testToString() {
        int id = 3;
        String name = "Mortgage Loan";
        double sum = 200000.00;
        int term = 360;
        double rate = 4.5;
        double propertyValue = 350000.75;


        double testOverpaySum = 10000;
        int testOverpayTerm = 12;
        double testOverpayRate = 0;

        MortgageCredit mc = new MortgageCredit(
                id,
                name,
                sum,
                term,
                rate,
                propertyValue
        );

        String str = mc.toString();


        assertTrue(str.contains(name), "toString має містити назву кредиту.");
        assertTrue(str.contains("type=MORTGAGE"), "toString має містити тип кредиту.");
        assertTrue(str.contains("term=360"), "toString має містити термін.");
        assertTrue(str.contains("status=ACTIVE"), "toString має містити статус.");


        assertTrue(str.contains("sum=200000"), "toString має містити суму без кінцевих нулів.");


        assertTrue(str.contains("rate=4,5%"), "toString має містити ставку у форматі X,X%.");


        String expectedPropertyValuePart = String.format("propertyValue=%.2f", propertyValue);
        assertTrue(str.contains(expectedPropertyValuePart),
                "toString має містити propertyValue у коректному форматі.");
    }

    @Test
    void testConstructorAndGetters() {
        MortgageCredit mc = new MortgageCredit(10, "TestMort", 50000, 180, 7.5, 120000.0);

        assertEquals(10, mc.getId());
        assertEquals("TestMort", mc.getName());
        assertEquals(50000, mc.getSum(), 0.001);
        assertEquals(180, mc.getTermMonths());
        assertEquals(7.5, mc.getInterestRate(), 0.001);
        assertEquals(CreditType.MORTGAGE, mc.getType());
        assertEquals(120000.0, mc.getPropertyValue(), 0.001); // Перевірка нового поля
    }


    @Test
    void testSetter() {
        MortgageCredit mc = new MortgageCredit(1, "OldName", 1, 1, 1, 100);
        mc.setPropertyValue(200.50);

        assertEquals(200.50, mc.getPropertyValue(), 0.001); // Перевірка нового поля
    }

}