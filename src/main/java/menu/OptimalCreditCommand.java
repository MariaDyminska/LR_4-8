package menu;

import system.CreditSystem;
import model.Credit;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OptimalCreditCommand implements Command {

    private static final Logger logger = Logger.getLogger(OptimalCreditCommand.class.getName());
    private CreditSystem system;

    public OptimalCreditCommand(CreditSystem s) {
        this.system = s;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        logger.info("Пошук оптимального кредиту.");

        try {
            System.out.print("Бажана сума (0=пропуск): ");
            double sum = Double.parseDouble(sc.nextLine().trim());
            logger.info("Введена сума: " + sum);

            System.out.print("Бажаний термін (0=пропуск): ");
            int term = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введений термін: " + term);

            System.out.print("Макс ставка або пусто: ");
            String r = sc.nextLine().trim();
            Double max = r.isEmpty() ? null : Double.parseDouble(r);
            logger.info("Максимальна ставка: " + (max == null ? "пропуск" : max));

            Credit best = system.findOptimal(sum, term, max);

            if (best == null) {
                System.out.println("Не знайдено");
                logger.info("Оптимальний кредит не знайдено.");
            } else {
                DecimalFormat df = new DecimalFormat("#.##");
                System.out.println("Рекомендовано: " + best);
                logger.info("Знайдено оптимальний кредит: " + best);
            }

        } catch (NumberFormatException nfe) {
            logger.warning("Некоректний числовий формат: " + nfe.getMessage());
            System.out.println("Помилка: неправильні числові значення");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Помилка при пошуку оптимального кредиту", e);
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
