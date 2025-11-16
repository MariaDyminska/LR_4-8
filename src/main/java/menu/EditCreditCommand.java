package menu;
import system.CreditSystem;
import java.util.Scanner;
public class EditCreditCommand implements Command {
    private CreditSystem system; public EditCreditCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.print("id кредиту для редагування: "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Нова назва (пусто для пропуску): "); String name = sc.nextLine();
            System.out.print("Нова сума (пусто): "); String sum = sc.nextLine();
            System.out.print("Новий термін (пусто): "); String term = sc.nextLine();
            System.out.print("Нова ставка (пусто): "); String rate = sc.nextLine();
            system.updatePartial(id, name, sum, term, rate);
            System.out.println("Оновлено.");
        }catch(Exception e){ System.out.println("Помилка: " + e.getMessage()); }
    }
}
