package menu;

import system.CreditSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class EarlyPayoffCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EarlyPayoffCommand.class);
    private final CreditSystem system;

    public EarlyPayoffCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        logger.info("Почато дострокове погашення кредиту.");

        try {
            System.out.print("id: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введено id: " + id);

            System.out.print("Сума: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            logger.info("Введена сума дострокового погашення: " + amt);

            system.earlyPayoff(id, amt);
            System.out.println("✔ Дострокове погашення виконано.");
            logger.info("Дострокове погашення виконано для id=" + id);

        } catch (NumberFormatException nfe) {
            logger.error("Некоректний числовий формат: " + nfe.getMessage());
            System.out.println("Помилка: неправильний формат числа");

        } catch (Exception e) {
            logger.error("Помилка дострокового погашення", e);
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
