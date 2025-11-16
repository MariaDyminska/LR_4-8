package menu;

import system.CreditSystem;
import java.util.Scanner;

public class SearchCreditCommand implements Command {

    private CreditSystem system;

    public SearchCreditCommand(CreditSystem s) {
        this.system = s;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Макс ставка (%) або пусто: ");
            String r = sc.nextLine().trim();
            Double max = r.isEmpty() ? null : Double.parseDouble(r);

            System.out.print("Мін сума або пусто: ");
            String m = sc.nextLine().trim();
            Double min = m.isEmpty() ? null : Double.parseDouble(m);

            System.out.print("Термін (міс) або пусто: ");
            String t = sc.nextLine().trim();
            Integer term = t.isEmpty() ? null : Integer.parseInt(t);

            system.searchByParams(max, min, term).forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
