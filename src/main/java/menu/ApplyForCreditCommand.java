package menu;

import system.CreditSystem;
import model.Credit;
import java.util.Scanner;

public class ApplyForCreditCommand implements  menu.Command {

    private final CreditSystem system;

    public ApplyForCreditCommand(CreditSystem system) {
        this.system = system;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Введіть ID кредиту для оформлення: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            Credit c = system.findById(id);
            if (c == null) {
                System.out.println("❌ Кредит не знайдено.");
                return;
            }

            System.out.println("\n=== Оформлення кредиту ===");
            System.out.println(c);

            System.out.print("Підтвердити оформлення? (так/ні): ");
            String ans = sc.nextLine().trim();

            if (ans.equalsIgnoreCase("так")) {
                system.getHistory(id).add("Кредит оформлено клієнтом");
                System.out.println("✔ Кредит успішно оформлено!");
            } else {
                System.out.println("Операцію скасовано.");
            }

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
