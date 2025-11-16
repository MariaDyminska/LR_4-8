package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class OptimalCreditCommandTest {

    private ByteArrayOutputStream out;
    private PrintStream originalOut;

    @BeforeEach
    void setup() {
        out = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restore() {
        System.setOut(originalOut);
    }

    @Test
    void testOptimalFound() {
        CreditSystem system = new CreditSystem();

        system.addCredit(new Credit(1, "A", 5000, 12, 10, CreditType.CONSUMER));
        system.addCredit(new Credit(2, "B", 5000, 12, 5, CreditType.CONSUMER)); // кращий

        // sum=5000, term=12, maxRate=10
        String input = "5000\n12\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OptimalCreditCommand cmd = new OptimalCreditCommand(system);
        cmd.execute();

        String output = out.toString();
        assertTrue(output.contains("Рекомендовано"));
        assertTrue(output.contains("B")); // повинно вибрати B
    }

    @Test
    void testOptimalNotFound() {
        CreditSystem system = new CreditSystem();

        // немає кредитів
        String input = "5000\n12\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OptimalCreditCommand cmd = new OptimalCreditCommand(system);
        cmd.execute();

        String output = out.toString();
        assertTrue(output.contains("Не знайдено"));
    }

    @Test
    void testOptimalInvalidInput() {
        CreditSystem system = new CreditSystem();

        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));

        OptimalCreditCommand cmd = new OptimalCreditCommand(system);
        cmd.execute();

        String output = out.toString();
        assertTrue(output.contains("Помилка"));
    }
}
