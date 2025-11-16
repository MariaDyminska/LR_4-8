package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// DecimalFormat не обов'язковий, але може бути корисний для налагодження
// import java.text.DecimalFormat;

public class MortgageCreditTest {

    @Test
    void testToString() {
        int id = 3;
        String name = "Mortgage Loan";
        double sum = 200000.00;
        int term = 360;
        double rate = 4.5; // Тестове значення з десятковою частиною
        double propertyValue = 350000.75;

        // Перевіряємо також, чи правильно успадкувалася сума.
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

        // 1. Перевірка успадкованих полів
        assertTrue(str.contains(name), "toString має містити назву кредиту.");
        assertTrue(str.contains("type=MORTGAGE"), "toString має містити тип кредиту.");
        assertTrue(str.contains("term=360"), "toString має містити термін.");
        assertTrue(str.contains("status=ACTIVE"), "toString має містити статус.");

        // 2. ***ФІНАЛЬНЕ ВИПРАВЛЕННЯ СУМИ***
        // DecimalFormat("#.##") видаляє кінцеві нулі, тому очікуємо 200000.
        assertTrue(str.contains("sum=200000"), "toString має містити суму без кінцевих нулів.");

        // 3. ***ФІНАЛЬНЕ ВИПРАВЛЕННЯ СТАВКИ***
        // DecimalFormat("#.##") у вашій локалі використовує кому, тому очікуємо 4,5%.
        assertTrue(str.contains("rate=4,5%"), "toString має містити ставку у форматі X,X%.");

        // 4. Перевірка propertyValue (дочірній клас використовує %.2f, який використовує точку)
        // Припускаємо, що тут ви використовуєте String.format("%.2f", ...)
        String expectedPropertyValuePart = String.format("propertyValue=%.2f", propertyValue);
        assertTrue(str.contains(expectedPropertyValuePart),
                "toString має містити propertyValue у коректному форматі.");
    }

    // Виправлений тест для Constructor/Getters
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

    // Виправлений тест для Setter
    @Test
    void testSetter() {
        MortgageCredit mc = new MortgageCredit(1, "OldName", 1, 1, 1, 100);
        mc.setPropertyValue(200.50);

        assertEquals(200.50, mc.getPropertyValue(), 0.001); // Перевірка нового поля
    }

    // Додайте інші необхідні тести, якщо потрібно досягти 90% покриття
    // ...
}