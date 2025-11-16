package menu;
import system.CreditSystem;
import java.util.Scanner;
public class ShowScheduleCommand implements Command {
    private CreditSystem system; public ShowScheduleCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){ try{ java.util.Scanner sc=new java.util.Scanner(System.in); System.out.print("id для графіку: "); int id=Integer.parseInt(sc.nextLine().trim()); system.printSchedule(id); }catch(Exception e){ System.out.println("Помилка: " + e.getMessage()); } }
}
