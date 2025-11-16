package menu;

import system.CreditSystem;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RefinanceCommand implements Command {

    private static final Logger logger = Logger.getLogger(RefinanceCommand.class.getName());
    private CreditSystem system;

    public RefinanceCommand(CreditSystem s) {
        this.system = s;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        logger.info("Почато рефінансування кредиту.");

        try {
            System.out.print("id: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введено id: " + id);

            System.out.print("Нова сума (0=skip): ");
            double ns = Double.parseDouble(sc.nextLine().trim());
            logger.info("Введена нова сума: " + ns);

            System.out.print("Нова ставка (-1=skip): ");
            double nr = Double.parseDouble(sc.nextLine().trim());
            logger.info("Введена нова ставка: " + nr);

            System.out.print("Новий термін (0=skip): ");
            int nt = Integer.parseInt(sc.nextLine().trim());
            logger.info("Введений новий термін: " + nt);

            system.refinance(id, ns, nr, nt);

            logger.info("Рефінансування виконано успішно для id=" + id);

        } catch (NumberFormatException nfe) {
            logger.warning("Некоректний числовий формат: " + nfe.getMessage());
            System.out.println("Помилка: неправильні числові значення");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Помилка рефінансування", e);
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
