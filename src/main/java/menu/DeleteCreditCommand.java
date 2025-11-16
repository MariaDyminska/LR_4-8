package menu;
import system.CreditSystem;
import java.util.Scanner;
public class DeleteCreditCommand implements Command {
    private CreditSystem system; public DeleteCreditCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){
        Scanner sc=new Scanner(System.in);
        try{ System.out.print("id для видалення: "); int id=Integer.parseInt(sc.nextLine().trim()); system.removeCredit(id); System.out.println("Видалено"); }catch(Exception e){ System.out.println("Помилка: " + e.getMessage()); }
    }
}
