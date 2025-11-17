package menu;

import system.CreditSystem;
import model.Credit;
import java.text.DecimalFormat;

public class SmartHelperCommand implements Command {

    private CreditSystem system;

    public SmartHelperCommand(CreditSystem s) {
        this.system = s;
    }

    @Override
    public void execute() {

        Credit c = system.smartHelper();

        if (c == null) {
            System.out.println("Немає кредитів");
            return;
        }

        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("=== Розумний помічник ===");
        System.out.println("Назва: " + c.getName());
        System.out.println("Сума: " + df.format(c.getSum()));
        System.out.println("Термін: " + c.getTermMonths());
        System.out.println("Ставка: " + df.format(c.getInterestRate()));
        System.out.println("Місячний платіж: " + df.format(c.calculateMonthlyPayment()));
        System.out.println("Переплата: " + df.format(c.overpay()));
    }
}
