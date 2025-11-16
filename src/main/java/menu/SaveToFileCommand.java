package menu;
import system.CreditSystem;
import java.util.Scanner;
public class
SaveToFileCommand implements Command {
    private CreditSystem system; public SaveToFileCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){ Scanner sc = new Scanner(System.in); try{ System.out.print("Ім'я файлу: "); String f = sc.nextLine().trim(); system.save(f); System.out.println("Збережено"); } catch(Exception e){ System.out.println("Помилка збереження: " + e.getMessage()); } }
}
