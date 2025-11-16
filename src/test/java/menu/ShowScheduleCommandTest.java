package menu;

import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.*;
import system.CreditSystem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShowScheduleCommandTest {

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
    void testShowSchedule_ValidId() {
        CreditSystem system = new CreditSystem();
        system.addCredit(new Credit(1, "Test", 10000, 12, 10, CreditType.CONSUMER));

        // Вхід: "1\n"
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        ShowScheduleCommand cmd = new ShowScheduleCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Місяць"), "Графік повинен містити слово 'Місяць'");
        assertTrue(output.contains("Платіж"), "Графік повинен містити колонку 'Платіж'");
        assertTrue(output.contains("Залишок"), "Графік повинен містити колонку 'Залишок'");
    }

    @Test
    void testShowSchedule_InvalidId() {
        CreditSystem system = new CreditSystem();

        System.setIn(new ByteArrayInputStream("99\n".getBytes()));

        ShowScheduleCommand cmd = new ShowScheduleCommand(system);
        cmd.execute();

        String output = out.toString();

        assertTrue(output.contains("Кредит не знайдено"),
                "При неправильному ID повинно друкуватись 'Кредит не знайдено'");
    }
}
