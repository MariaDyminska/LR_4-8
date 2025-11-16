package menu;
import system.CreditSystem;
import java.util.Scanner;
public class RefinanceCommand implements Command {
    private CreditSystem system; public RefinanceCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){ Scanner sc=new Scanner(System.in); try{ System.out.print("id: "); int id=Integer.parseInt(sc.nextLine().trim()); System.out.print("Нова сума (0=skip): "); double ns=Double.parseDouble(sc.nextLine().trim()); System.out.print("Нова ставка (-1=skip): "); double nr=Double.parseDouble(sc.nextLine().trim()); System.out.print("Новий термін (0=skip): "); int nt=Integer.parseInt(sc.nextLine().trim()); system.refinance(id, ns, nr, nt); }catch(Exception e){ System.out.println("Помилка: " + e.getMessage()); } }
}
