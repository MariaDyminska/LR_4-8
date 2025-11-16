package menu;

import system.CreditSystem;
import model.Credit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ApplyForCreditCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ApplyForCreditCommand.class);
    private final CreditSystem system;

    public ApplyForCreditCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        logger.info("Почато оформлення кредиту.");

        try {
            System.out.print("Введіть ID кредиту для оформлення: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введено id: {}", id);

            Credit c = system.findById(id);
            if (c == null) {
                System.out.println("❌ Кредит не знайдено.");
                logger.warn("Кредит з id={} не знайдено.", id);
                return;
            }

            logger.info("Знайдено кредит: {}", c);
            System.out.println("\n=== Оформлення кредиту ===");
            System.out.println(c);

            System.out.print("Підтвердити оформлення? (так/ні): ");
            String ans = sc.nextLine().trim();
            logger.info("Відповідь користувача: {}", ans);

            if (ans.equalsIgnoreCase("так")) {
                system.getHistory(id).add("Кредит оформлено клієнтом");
                System.out.println("✔ Кредит успішно оформлено!");
                logger.info("Кредит id={} оформлено.", id);
            } else {
                System.out.println("Операцію скасовано.");
                logger.info("Користувач скасував оформлення кредиту.");
            }

        } catch (NumberFormatException nfe) {
            logger.warn("Некоректний формат числа: {}", nfe.getMessage());
            System.out.println("Помилка: введено неправильний формат числа");

        } catch (Exception e) {
            logger.error("Помилка під час оформлення кредиту", e);
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
