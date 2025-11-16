package menu;
import system.CreditSystem;
import java.util.Scanner;
import model.Credit;
import java.text.DecimalFormat;
public class ViewCreditConditionsCommand implements Command {
    private CreditSystem system; public ViewCreditConditionsCommand(CreditSystem s){ this.system = s; }
    @Override public void execute() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("id кредиту: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Credit c = system.findById(id);
            if (c == null) {
                System.out.println("Немає");
                return;
            }
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("Назва: " + c.getName());
            System.out.println("Сума: " + df.format(c.getSum()));
            System.out.println("Термін: " + c.getTermMonths());
            System.out.println("Ставка: " + df.format(c.getInterestRate()));
            System.out.println("Місячний платіж: " + df.format(c.calculateMonthlyPayment()));
            System.out.println("Переплата: " + df.format(c.overpay()));
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }}
}