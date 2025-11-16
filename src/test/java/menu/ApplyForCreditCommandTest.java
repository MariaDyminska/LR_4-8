package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplyForCreditCommandTest {

    private CreditSystem system;

    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        system = new CreditSystem();

        // Додаємо кредити (історія автоматично міститиме по 1 запису!)
        system.addCredit(new Credit(1, "Credit A", 1000, 12, 10, CreditType.CONSUMER));
        system.addCredit(new Credit(2, "Credit B", 2000, 24, 12, CreditType.CONSUMER));

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    // Симуляція вводу користувача
    private void setInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void testApplyForCreditSuccessfully() {
        // Користувач вводить ID = 1 та підтвердження "так"
        setInput("1\nтак\n");

        ApplyForCreditCommand cmd = new ApplyForCreditCommand(system);
        cmd.execute();

        List<String> hist = system.getHistory(1);

        // У історії вже був 1 запис ("Додано кредит")
        // після оформлення стає 2
        assertEquals(2, hist.size(), "Повинно бути 2 записи після оформлення");

        assertTrue(hist.get(1).contains("оформлено"), "Останній запис повинен містити слово 'оформлено'");
    }

    @Test
    void testApplyForCredit_NotFound() {
        // Вибираємо неіснуючий ID
        setInput("999\n");

        ApplyForCreditCommand cmd = new ApplyForCreditCommand(system);
        cmd.execute();

        // Ніякі історії НЕ повинні змінитися
        assertEquals(1, system.getHistory(1).size(), "Історія кредиту 1 не повинна змінитися");
        assertEquals(1, system.getHistory(2).size(), "Історія кредиту 2 не повинна змінитися");

        String output = out.toString();
        assertTrue(output.contains("не знайдено"), "Повідомлення повинно містити 'не знайдено'");
    }

    @Test
    void testApplyForCredit_NotConfirmed() {
        // Користувач вибрав кредит, але не підтвердив
        setInput("1\nні\n");

        ApplyForCreditCommand cmd = new ApplyForCreditCommand(system);
        cmd.execute();

        // Було 1 запис (додавання кредиту)
        // Користувач скасував → має лишитись 1
        assertEquals(1, system.getHistory(1).size(),
                "Якщо користувач скасував — історія не повинна змінитися");

        String output = out.toString();
        assertTrue(output.contains("скасовано"), "Повинно бути повідомлення про скасування");
    }
}
