package menu;

import system.CreditSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class SaveToFileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SaveToFileCommand.class);
    private final CreditSystem system;

    public SaveToFileCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ім'я файлу: ");
        String fileName = sc.nextLine().trim();
        logger.info("Користувач ініціював збереження у файл: {}", fileName);

        try {
            system.save(fileName);
            System.out.println(" Збережено успішно");
            logger.info("Файл успішно збережено: {}", fileName);
        } catch (Exception e) {
            System.out.println(" Помилка збереження: " + e.getMessage());
            logger.error("Помилка під час збереження файлу: {}", fileName, e);
        }
    }
}
