package menu;

import system.CreditSystem;
import model.*;
import java.util.Scanner;

public class AddCreditCommand implements Command {

    private CreditSystem system;

    public AddCreditCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Виберіть тип (1-Споживчий,2-Іпотека,3-Авто,4-Бізнес): ");
            String t = sc.nextLine().trim();

            System.out.print("id: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Назва: ");
            String name = sc.nextLine();

            System.out.print("Сума: ");
            double sum = Double.parseDouble(sc.nextLine());

            System.out.print("Термін (міс): ");
            int term = Integer.parseInt(sc.nextLine());

            System.out.print("Ставка (%): ");
            double rate = Double.parseDouble(sc.nextLine());

            switch (t) {
                case "1":
                    System.out.print("Страхування (true/false): ");
                    boolean ins = Boolean.parseBoolean(sc.nextLine());
                    system.addCredit(new ConsumerCredit(id, name, sum, term, rate, ins));
                    break;

                case "2":
                    System.out.print("Вартість майна: ");
                    double pv = Double.parseDouble(sc.nextLine());
                    system.addCredit(new MortgageCredit(id, name, sum, term, rate, pv));
                    break;

                case "3":
                    System.out.print("Марка авто: ");
                    String b = sc.nextLine();
                    system.addCredit(new AutoCredit(id, name, sum, term, rate, b));
                    break;

                case "4":
                    System.out.print("Тип бізнесу: ");
                    String bt = sc.nextLine();
                    system.addCredit(new BusinessCredit(id, name, sum, term, rate, bt));
                    break;

                default:
                    System.out.println("Невірний тип");
                    return;
            }

            System.out.println("Кредит додано.");

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
