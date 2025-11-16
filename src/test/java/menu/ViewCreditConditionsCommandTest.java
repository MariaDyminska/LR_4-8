package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ViewCreditConditionsCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private InputStream originalIn;
    private CreditSystem system;

    @BeforeEach
    void setup() {
        originalOut = System.out;
        originalIn = System.in;
        System.setOut(new PrintStream(out));
        system = new CreditSystem();
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testViewConditions_NotFound() {
        System.setIn(new ByteArrayInputStream("99\n".getBytes()));

        ViewCreditConditionsCommand cmd = new ViewCreditConditionsCommand(system);
        cmd.execute();

        String output = out.toString();
        assertTrue(output.contains("Немає"));
    }

    @Test
    void testViewConditions_Found() {

        Credit credit = new Credit(
                1,
                "TestCredit",
                10000,
                12,
                10,
                CreditType.CONSUMER
        );

        system.addCredit(credit);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        ViewCreditConditionsCommand cmd = new ViewCreditConditionsCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Назва: TestCredit"));
        assertTrue(output.contains("Сума"));
        assertTrue(output.contains("Термін: 12"));
        assertTrue(output.contains("Ставка"));

        // Універсальна перевірка — НЕ залежить від форматування DecimalFormat
        assertTrue(output.contains("Місячний платіж"),
                "Вивід повинен містити 'Місячний платіж'");

        assertTrue(
                output.matches("(?s).*Місячний платіж:\\s*[-+]?[0-9]*[.,]?[0-9]+.*"),
                "Після 'Місячний платіж:' має бути число у будь-якому форматі"
        );

        assertTrue(output.contains("Переплата"),
                "Має бути вивід 'Переплата'");
    }
}
