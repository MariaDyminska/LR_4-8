package menu;

import system.CreditSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class LoadFromFileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoadFromFileCommand.class);
    private final CreditSystem system;

    public LoadFromFileCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ім'я файлу: ");
        String fileName = sc.nextLine().trim();
        logger.info("Користувач ініціював завантаження з файлу: {}", fileName);

        try {
            system.load(fileName);
            System.out.println("Файл успішно завантажено");
            logger.info("Файл успішно завантажено: {}", fileName);
        } catch (Exception e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
            logger.error("Помилка під час завантаження файлу: {}", fileName, e);
        }
    }
}
