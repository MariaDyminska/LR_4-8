package menu;

import system.CreditSystem;
import java.util.List;
import java.util.Scanner;

public class ViewHistoryCommand implements Command {

    private CreditSystem system;

    public ViewHistoryCommand(CreditSystem s) {
        this.system = s;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("id: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            // Замінено var на конкретний тип List<String>
            List<String> h = system.getHistory(id);

            if (h.isEmpty()) {
                System.out.println("Історія пуста");
            } else {
                h.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
