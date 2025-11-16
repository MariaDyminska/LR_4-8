package menu;

import system.CreditSystem;
import java.util.Scanner;
import model.Credit;
import java.text.DecimalFormat;

public class OptimalCreditCommand implements Command {

    private CreditSystem system;

    public OptimalCreditCommand(CreditSystem s) {
        this.system = s;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Бажана сума (0=пропуск): ");
            double sum = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Бажаний термін (0=пропуск): ");
            int term = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Макс ставка або пусто: ");
            String r = sc.nextLine().trim();

            // Заміна isBlank() на trim().isEmpty()
            Double max = r.isEmpty() ? null : Double.parseDouble(r);

            Credit best = system.findOptimal(sum, term, max);

            if (best == null) {
                System.out.println("Не знайдено");
            } else {
                DecimalFormat df = new DecimalFormat("#.##");
                System.out.println("Рекомендовано: " + best);
            }

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
