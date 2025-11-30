package menu;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddCreditCommandTest {

    private CreditSystem system;
    private AddCreditCommand command;

    @BeforeEach
    void setup() {

        system = new CreditSystem();
        command = new AddCreditCommand(system);
    }

    private void mockInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testAddConsumerCredit() {
        mockInput("1\n101\nСпоживчий кредит\n10000\n12\n5\ntrue\n");
        command.execute();

        List<Credit> credits = system.getCredits();
        assertEquals(1, credits.size());
        assertTrue(credits.get(0) instanceof ConsumerCredit);

        ConsumerCredit cc = (ConsumerCredit) credits.get(0);
        assertEquals(101, cc.getId());
        assertEquals("Споживчий кредит", cc.getName());
        assertEquals(10000, cc.getSum());
        assertEquals(12, cc.getTerm());
        assertEquals(5, cc.getRate());
        assertTrue(cc.isInsurance());
    }

    @Test
    void testAddMortgageCredit() {
        mockInput("2\n102\nІпотека\n50000\n24\n6\n100000\n");
        command.execute();

        List<Credit> credits = system.getCredits();
        assertEquals(1, credits.size());
        assertTrue(credits.get(0) instanceof MortgageCredit);

        MortgageCredit mc = (MortgageCredit) credits.get(0);
        assertEquals(102, mc.getId());
        assertEquals("Іпотека", mc.getName());
        assertEquals(50000, mc.getSum());
        assertEquals(24, mc.getTerm());
        assertEquals(6, mc.getRate());
        assertEquals(100000, mc.getPropertyValue());
    }



    @Test
    void testAddBusinessCredit() {
        mockInput("4\n104\nБізнес кредит\n100000\n36\n8\nIT\n");
        command.execute();

        List<Credit> credits = system.getCredits();
        assertEquals(1, credits.size());
        assertTrue(credits.get(0) instanceof BusinessCredit);

        BusinessCredit bc = (BusinessCredit) credits.get(0);
        assertEquals(104, bc.getId());
        assertEquals("Бізнес кредит", bc.getName());
        assertEquals(100000, bc.getSum());
        assertEquals(36, bc.getTerm());
        assertEquals(8, bc.getRate());
        assertEquals("IT", bc.getBusinessType());
    }

    @Test
    void testInvalidType() {
        mockInput("9\n");
        command.execute();
        assertEquals(0, system.getCredits().size());
    }

    @Test
    void testInvalidNumberFormat() {
        mockInput("1\nabc\n"); // id не число
        command.execute();
        assertEquals(0, system.getCredits().size());
    }
}
