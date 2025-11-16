package menu;

import org.junit.jupiter.api.Test;
import system.CreditSystem;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class LoadFromFileCommandTest {

    @Test
    public void testLoadFromFileCommand_LoadsData() throws Exception {
        // Готуємо тестовий файл
        String filename = "test_load.txt";

        // Створюємо систему і зберігаємо в файл 1 кредит
        CreditSystem system = new CreditSystem();
        system.addCredit(new model.ConsumerCredit(1, "Test", 1000, 12, 10, false));
        system.save(filename);

        // Робимо систему, яку будемо завантажувати (порожню)
        CreditSystem loadSystem = new CreditSystem();

        // Імітуємо введення: назва файлу
        String input = filename + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Виконуємо команду
        LoadFromFileCommand cmd = new LoadFromFileCommand(loadSystem);
        cmd.execute();

        // Перевірка — кредит має завантажитися
        assertNotNull(loadSystem.getCreditById(1), "Кредит повинен бути завантажений");
        assertEquals("Test", loadSystem.getCreditById(1).getName());
    }
}
