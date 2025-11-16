package menu;

import system.CreditSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MakePaymentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(MakePaymentCommand.class);
    private final CreditSystem system;

    public MakePaymentCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        logger.info("Почато оплату по кредиту.");

        try {
            System.out.print("id: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введено id: {}", id);

            System.out.print("Сума: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            logger.info("Введена сума: {}", amt);

            system.makePayment(id, amt);
            System.out.println("✔Оплату виконано успішно");
            logger.info("Оплату виконано успішно для id={}", id);

        } catch (NumberFormatException nfe) {
            logger.warn("Некоректний числовий формат: {}", nfe.getMessage());
            System.out.println("Помилка: неправильний формат числа");

        } catch (Exception e) {
            logger.error("Помилка при оплаті кредиту", e);
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
}
