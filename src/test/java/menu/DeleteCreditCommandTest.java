package menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import system.CreditSystem;
import model.ConsumerCredit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCreditCommandTest {

    private CreditSystem system;
    private DeleteCreditCommand command;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setup() {
        system = new CreditSystem();
        system.addCredit(new ConsumerCredit(1, "Test Credit", 1000, 12, 5.0, true));
        command = new DeleteCreditCommand(system);

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testDeleteExistingCredit() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        command.execute();

        String output = outContent.toString();
        assertTrue(output.contains("Кредит видалено"), "Повинно виводити повідомлення про видалення існуючого кредиту");
    }

    @Test
    void testDeleteNonExistingCredit() {
        ByteArrayInputStream in = new ByteArrayInputStream("99\n".getBytes());
        System.setIn(in);

        command.execute();

        String output = outContent.toString();
        assertTrue(output.contains("Помилка: кредит з таким id не знайдено"), "Повинно виводити повідомлення про неіснуючий кредит");
    }

    @Test
    void testDeleteInvalidId() {
        ByteArrayInputStream in = new ByteArrayInputStream("abc\n".getBytes());
        System.setIn(in);

        command.execute();

        String output = outContent.toString();
        assertTrue(output.contains("Помилка: id має бути числом"), "Повинно повідомляти про некоректний id");
    }
}
