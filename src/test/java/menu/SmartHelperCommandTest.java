package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SmartHelperCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setup() {
        originalOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
    }

    @Test
    void testSmartHelper_NoCredits() {
        CreditSystem system = new CreditSystem(); // порожня система

        SmartHelperCommand cmd = new SmartHelperCommand(system);
        cmd.execute();

        String output = out.toString();
        assertTrue(output.contains("Немає кредитів"),
                "Для порожньої системи має виводитись повідомлення 'Немає кредитів'");
    }

    @Test
    void testSmartHelper_WithCredits() {
        CreditSystem system = new CreditSystem();

        // Додаємо кілька кредитів
        Credit c1 = new Credit(1, "A", 10000, 12, 10, CreditType.CONSUMER);
        Credit c2 = new Credit(2, "B", 5000, 6, 5, CreditType.AUTO);   // найвигідніший
        Credit c3 = new Credit(3, "C", 20000, 24, 20, CreditType.MORTGAGE);

        system.addCredit(c1);
        system.addCredit(c2);
        system.addCredit(c3);

        SmartHelperCommand cmd = new SmartHelperCommand(system);
        cmd.execute();

        String output = out.toString();

        // Має вибрати найкращий кредит — B
        assertTrue(output.contains("Розумний помічник"), "Має бути заголовок");
        assertTrue(output.contains("Назва: B"), "SmartHelper повинен вибрати кредит B");

        // Універсальні перевірки чисел
        assertTrue(output.matches("(?s).*Сума:\\s*[-+]?[0-9]*[.,]?[0-9]+.*"));
        assertTrue(output.contains("Термін: 6"));
        assertTrue(output.matches("(?s).*Ставка:\\s*[-+]?[0-9]*[.,]?[0-9]+.*"));
        assertTrue(output.matches("(?s).*Місячний платіж:\\s*[-+]?[0-9]*[.,]?[0-9]+.*"));
        assertTrue(output.matches("(?s).*Переплата:\\s*[-+]?[0-9]*[.,]?[0-9]+.*"));
    }
}
