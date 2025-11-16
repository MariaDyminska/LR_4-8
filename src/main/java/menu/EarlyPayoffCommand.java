package menu;
import system.CreditSystem;
import java.util.Scanner;
public class EarlyPayoffCommand implements Command {
    private CreditSystem system; public EarlyPayoffCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){ Scanner sc=new Scanner(System.in); try{ System.out.print("id: "); int id=Integer.parseInt(sc.nextLine().trim()); System.out.print("Сума: "); double amt=Double.parseDouble(sc.nextLine().trim()); system.earlyPayoff(id, amt); }catch(Exception e){ System.out.println("Помилка: " + e.getMessage()); } }
}
