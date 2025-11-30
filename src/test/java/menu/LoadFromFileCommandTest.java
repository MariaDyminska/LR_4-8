package menu;

import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class LoadFromFileCommandTest {

    @Test
    public void testLoadFromFileCommand_LoadsData() throws Exception {

        String filename = "test_load.txt";

        CreditSystem system = new CreditSystem();
        system.addCredit(new model.ConsumerCredit(1, "Test", 1000, 12, 10, false));
        system.save(filename);

        CreditSystem loadSystem = new CreditSystem();

        String input = filename + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LoadFromFileCommand cmd = new LoadFromFileCommand(loadSystem);
        cmd.execute();

        assertNotNull(loadSystem.getCreditById(1), "Кредит повинен бути завантажений");
        assertEquals("Test", loadSystem.getCreditById(1).getName());
    }
}
