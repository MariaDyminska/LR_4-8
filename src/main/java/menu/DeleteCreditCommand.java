package menu;

import system.CreditSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DeleteCreditCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteCreditCommand.class);
    private final CreditSystem system;

    public DeleteCreditCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        System.out.print("id для видалення: ");
        String input = sc.nextLine().trim();
        logger.info("Отримано запит на видалення кредиту з id = {}", input);

        try {
            int id = Integer.parseInt(input);

            system.removeCredit(id);
            System.out.println(" Кредит видалено");
            logger.info("Кредит успішно видалено. id = {}", id);

        } catch (NumberFormatException ex) {
            System.out.println("Помилка: id має бути числом");
            logger.warn("Користувач ввів некоректний id (не число): {}", input);

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
            logger.error("Помилка під час видалення кредиту: id = {}", input, e);
        }
    }
}
