package menu;

import system.CreditSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class EditCreditCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditCreditCommand.class);
    private CreditSystem system;

    public EditCreditCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        logger.info("Почато редагування кредиту.");

        try {
            System.out.print("id кредиту для редагування: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введено id: {}", id);

            System.out.print("Нова назва (пусто для пропуску): ");
            String name = sc.nextLine();
            logger.info("Введена нова назва: '{}'", name);

            System.out.print("Нова сума (пусто): ");
            String sum = sc.nextLine();
            logger.info("Введена нова сума: '{}'", sum);

            System.out.print("Новий термін (пусто): ");
            String term = sc.nextLine();
            logger.info("Введений новий термін: '{}'", term);

            System.out.print("Нова ставка (пусто): ");
            String rate = sc.nextLine();
            logger.info("Введена нова ставка: '{}'", rate);

            system.updatePartial(id, name, sum, term, rate);
            System.out.println("Оновлено.");
            logger.info("Кредит id={} успішно оновлено.", id);

        } catch (NumberFormatException nfe) {
            logger.warn("Некоректний числовий формат: {}", nfe.getMessage());
            System.out.println("Помилка: неправильний формат числа");

        } catch (Exception e) {
            logger.error("Помилка редагування кредиту", e);
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
