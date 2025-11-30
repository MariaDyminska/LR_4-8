package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewCreditsCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut;

    private CreditSystem system;

    @BeforeEach
    void setUp() {
        system = new CreditSystem();
        originalOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        out.reset();
    }

    @Test
    void testViewCredits_EmptyList() {
        ViewCreditsCommand cmd = new ViewCreditsCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Немає кредитів"),
                "При порожньому списку має бути 'Немає кредитів'");
    }


    @Test
    void testViewCredits_WithCredits() {

        Credit c = new Credit(1, "Test", 1000, 12, 5, CreditType.AUTO);
        system.addCredit(c);

        ViewCreditsCommand cmd = new ViewCreditsCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Test"),
                "Повинен виводитися кредит");
        assertTrue(output.contains("sum=1000"),
                "Сума має бути у виводі");
        assertTrue(output.contains("rate=5%"),
                "Має бути виведена ставка");
    }
}
