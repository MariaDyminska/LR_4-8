package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewHistoryCommandTest {

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
    void testViewHistory_InitialHistory() {

        Credit c = new Credit(1, "Test", 1000, 12, 5, CreditType.AUTO);
        system.addCredit(c);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        ViewHistoryCommand cmd = new ViewHistoryCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Додано кредит"),
                "Історія повинна містити запис 'Додано кредит', бо addCredit() завжди додає подію");
    }

    @Test
    void testViewHistory_WithEvents() {
        Credit c = new Credit(2, "Test2", 2000, 10, 4, CreditType.CONSUMER);
        system.addCredit(c);


        system.makePayment(2, 500);

        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        ViewHistoryCommand cmd = new ViewHistoryCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Платіж"),
                "Після оплати має виводитись запис 'Платіж'");
    }


    @Test
    void testViewHistory_InvalidIdInput() {
        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));

        ViewHistoryCommand cmd = new ViewHistoryCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Помилка"),
                "При неправильному вводі повинно вивести 'Помилка'");
    }
}
