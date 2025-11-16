package menu;
import system.CreditSystem;
import java.util.Scanner;
public class LoadFromFileCommand implements Command {
    private CreditSystem system; public LoadFromFileCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){ Scanner sc = new Scanner(System.in); try{ System.out.print("Ім'я файлу: "); String f = sc.nextLine().trim(); system.load(f); System.out.println("Завантажено"); } catch(Exception e){ System.out.println("Помилка завантаження: " + e.getMessage()); } }
}
