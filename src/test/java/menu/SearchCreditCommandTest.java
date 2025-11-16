package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchCreditCommandTest {

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
    void testSearchCredit_Found() {
        CreditSystem system = new CreditSystem();

        system.addCredit(new Credit(1, "A", 5000, 12, 10, CreditType.CONSUMER));
        system.addCredit(new Credit(2, "B", 15000, 24, 5, CreditType.AUTO));

        // maxRate=10, minSum=4000, term=12
        String input = "10\n4000\n12\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        SearchCreditCommand cmd = new SearchCreditCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("A"),
                "Пошук повинен знайти кредит A");
        assertFalse(output.contains("B"),
                "Кредит B не повинен підходити під умови");
    }

    @Test
    void testSearchCredit_EmptyParams() {
        CreditSystem system = new CreditSystem();

        system.addCredit(new Credit(1, "A", 5000, 12, 10, CreditType.CONSUMER));

        // всі порожні — "\n\n\n"
        System.setIn(new ByteArrayInputStream("\n\n\n".getBytes()));

        SearchCreditCommand cmd = new SearchCreditCommand(system);
        cmd.execute();

        String output = out.toString();

        // Має відобразити всі кредити
        assertTrue(output.contains("A"));
    }

    @Test
    void testSearchCredit_InvalidNumber() {
        CreditSystem system = new CreditSystem();

        // некоректне введення: "abc"
        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));

        SearchCreditCommand cmd = new SearchCreditCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Помилка"),
                "Некоректний ввід повинен викликати повідомлення 'Помилка'");
    }
}
