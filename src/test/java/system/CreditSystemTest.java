package system;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.*;
import java.util.stream.Stream;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CreditSystemTest {

    // -------------------------------------------------------------
    // ✔ ТЕСТ 1: Додавання кредитів
    // -------------------------------------------------------------
    @Test
    void testAddCredit() {
        CreditSystem system = new CreditSystem();

        Credit c1 = new Credit(1, "A", 10000, 12, 10, CreditType.CONSUMER);
        system.addCredit(c1);

        assertEquals(1, system.getAllCredits().size());
        assertEquals("A", system.getAllCredits().get(0).getName());
    }

    // -------------------------------------------------------------
    // ✔ ТЕСТ 2: Отримання всіх кредитів
    // -------------------------------------------------------------
    @Test
    void testGetAllCredits() {
        CreditSystem system = new CreditSystem();

        system.addCredit(new Credit(1, "A", 10000, 12, 10, CreditType.CONSUMER));
        system.addCredit(new Credit(2, "B", 5000, 6, 15, CreditType.CONSUMER));

        List<Credit> all = system.getAllCredits();

        assertEquals(2, all.size());
        assertEquals("B", all.get(1).getName());
    }

    // -------------------------------------------------------------
    // ✔ ТЕСТ 3: findOptimal()
    // -------------------------------------------------------------
    @Test
    void testFindOptimal() {
        CreditSystem system = new CreditSystem();

        Credit c1 = new Credit(1, "A", 10000, 12, 10, CreditType.CONSUMER);
        Credit c2 = new Credit(2, "B", 15000, 24, 15, CreditType.CONSUMER);
        Credit c3 = new Credit(3, "C", 20000, 36, 5, CreditType.CONSUMER);

        system.addCredit(c1);
        system.addCredit(c2);
        system.addCredit(c3);

        // Тест сам обчислює правильну відповідь
        Credit expected =
                Stream.of(c1, c2, c3)
                        .min(Comparator.comparingDouble(Credit::overpay))
                        .orElse(null);

        Credit result = system.findOptimal(0, 0, null);

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
    }

    // -------------------------------------------------------------
    // ✔ ТЕСТ 4: smartHelper()
    // -------------------------------------------------------------
    @Test
    void testSmartHelper() {
        CreditSystem system = new CreditSystem();

        Credit c1 = new Credit(1, "A", 10000, 12, 10, CreditType.CONSUMER);
        Credit c2 = new Credit(2, "B", 15000, 24, 15, CreditType.CONSUMER);
        Credit c3 = new Credit(3, "C", 20000, 36, 5, CreditType.CONSUMER);

        system.addCredit(c1);
        system.addCredit(c2);
        system.addCredit(c3);

        // Логіка 100% ідентична твоїй
        Credit expected =
                Stream.of(c1, c2, c3)
                        .min(Comparator.comparingDouble(c ->
                                0.5 * c.calculateMonthlyPayment() +
                                        0.4 * c.overpay() +
                                        0.1 * c.getInterestRate()
                        ))
                        .orElse(null);

        Credit result = system.smartHelper();

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
    }

    // -------------------------------------------------------------
    // ✔ ТЕСТ 5: DataFileManager — збереження та зчитування
    // -------------------------------------------------------------
    @Test
    void testSaveLoadObject() throws Exception {
        String filename = "test_credit_data.bin";

        Credit original = new Credit(10, "Test", 5000, 12, 7, CreditType.CONSUMER);
        DataFileManager.saveObject(original, filename);

        Object loaded = DataFileManager.loadObject(filename);

        assertTrue(loaded instanceof Credit);

        Credit c = (Credit) loaded;
        assertEquals(original.getId(), c.getId());
        assertEquals(original.getName(), c.getName());

        new File(filename).delete();
    }

    // -------------------------------------------------------------
    // ✔ ТЕСТ 6: Інтеграційний — повна симуляція системи
    // -------------------------------------------------------------
    @Test
    void testFullSystemFlow() throws Exception {
        CreditSystem system = new CreditSystem();

        Credit a = new Credit(1, "A", 10000, 12, 10, CreditType.CONSUMER);
        Credit b = new Credit(2, "B", 5000, 6, 20, CreditType.CONSUMER);

        system.addCredit(a);
        system.addCredit(b);

        assertEquals(2, system.getAllCredits().size());

        Credit optimal = system.findOptimal(0, 0, null);
        assertNotNull(optimal);

        // Збереження
        String file = "system_save_test.bin";
        DataFileManager.saveObject(system.getAllCredits(), file);

        // Завантаження
        Object loaded = DataFileManager.loadObject(file);
        assertTrue(loaded instanceof List);

        @SuppressWarnings("unchecked")
        List<Credit> list = (List<Credit>) loaded;

        assertEquals(2, list.size());
        assertEquals("B", list.get(1).getName());

        new File(file).delete();
    }
}
