package menu;

import system.CreditSystem;
import model.*;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCreditCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddCreditCommand.class);
    private CreditSystem system;

    public AddCreditCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        logger.info("Почато створення нового кредиту.");

        try {
            System.out.print("Виберіть тип (1-Споживчий,2-Іпотека,3-Авто,4-Бізнес): ");
            String t = sc.nextLine().trim();
            logger.info("Вибрано тип кредиту: {}", t);

            System.out.print("id: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введено id: {}", id);

            System.out.print("Назва: ");
            String name = sc.nextLine();
            logger.info("Назва кредиту: {}", name);

            System.out.print("Сума: ");
            double sum = Double.parseDouble(sc.nextLine());
            logger.info("Сума: {}", sum);

            System.out.print("Термін (міс): ");
            int term = Integer.parseInt(sc.nextLine());
            logger.info("Термін: {}", term);

            System.out.print("Ставка (%): ");
            double rate = Double.parseDouble(sc.nextLine());
            logger.info("Ставка: {}", rate);

            switch (t) {
                case "1":
                    System.out.print("Страхування (true/false): ");
                    boolean ins = Boolean.parseBoolean(sc.nextLine());
                    logger.info("Страхування: {}", ins);
                    system.addCredit(new ConsumerCredit(id, name, sum, term, rate, ins));
                    break;

                case "2":
                    System.out.print("Вартість майна: ");
                    double pv = Double.parseDouble(sc.nextLine());
                    logger.info("Вартість майна: {}", pv);
                    system.addCredit(new MortgageCredit(id, name, sum, term, rate, pv));
                    break;

                case "3":
                    System.out.print("Марка авто: ");
                    String b = sc.nextLine();
                    logger.info("Марка авто: {}", b);
                    system.addCredit(new AutoCredit(id, name, sum, term, rate, b));
                    break;

                case "4":
                    System.out.print("Тип бізнесу: ");
                    String bt = sc.nextLine();
                    logger.info("Тип бізнесу: {}", bt);
                    system.addCredit(new BusinessCredit(id, name, sum, term, rate, bt));
                    break;

                default:
                    logger.warn("Користувач ввів неправильний тип кредиту: {}", t);
                    System.out.println("Невірний тип");
                    return;
            }

            System.out.println("Кредит додано.");
            logger.info("Кредит успішно додано. ID = {}", id);

        } catch (NumberFormatException nfe) {
            logger.warn("Користувач ввів неправильний числовий формат: {}", nfe.getMessage());
            System.out.println("Помилка: введено неправильний формат числа");

        } catch (Exception e) {
            logger.error("Помилка під час додавання кредиту", e);
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
