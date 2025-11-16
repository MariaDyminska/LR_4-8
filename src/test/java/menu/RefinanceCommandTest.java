package menu;

import model.CreditType;
import org.junit.jupiter.api.*;
import java.io.*;
import system.CreditSystem;
import model.Credit;

public class RefinanceCommandTest {

    private CreditSystem system;
    private ByteArrayOutputStream out;

    @BeforeEach
    void init() {
        system = new CreditSystem();
        system.addCredit(new Credit(1, "A", 5000, 12, 10, CreditType.CONSUMER));
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void cleanup() {
        System.setOut(System.out);
    }

    @Test
    void testRefinance() {
        String input =
                "1\n" +   // id
                        "6000\n" + // нова сума
                        "8\n" +    // нова ставка
                        "10\n";    // новий термін

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        RefinanceCommand cmd = new RefinanceCommand(system);
        cmd.execute();

        // Перевіряємо кредит через findById
        Credit c = system.findById(1);

        Assertions.assertEquals(6000, c.getSum());
        Assertions.assertEquals(8, c.getInterestRate());
        Assertions.assertEquals(10, c.getTermMonths());
    }
}
