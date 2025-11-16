package menu;
import java.util.Scanner;
import system.CreditSystem;
public class ClientMenu {
    private CreditSystem system; private Scanner sc = new Scanner(System.in);
    public ClientMenu(CreditSystem system){ this.system = system; }
    public void show(){
        while(true){
            System.out.println("\nМЕНЮ КЛІЄНТА");
            System.out.println("1. Пошук кредиту за параметрами");
            System.out.println("2. Підібрати оптимальний кредит");
            System.out.println("3. Переглянути умови кредиту");
            System.out.println("4. Розрахувати кредит");
            System.out.println("5. Оформити кредит");
            System.out.println("6. Розумний помічник (найкращий кредит)");
            System.out.println("7. Зробити внесок по кредиту");
            System.out.println("8. Достроково погасити кредит");
            System.out.println("9. Переглянути історію кредитів");
            System.out.println("10. Вихід");
            System.out.print("Оберіть: ");
            String opt=sc.nextLine().trim();
            try{
                switch(opt){
                    case "1": new menu.SearchCreditCommand(system).execute(); break;
                    case "2": new menu.OptimalCreditCommand(system).execute(); break;
                    case "3": new menu.ViewCreditConditionsCommand(system).execute(); break;
                    case "4": new menu.ShowScheduleCommand(system).execute(); break;
                    case "5": new ApplyForCreditCommand(system).execute(); break;
                    case "6": new menu.SmartHelperCommand(system).execute(); break;
                    case "7": new menu.MakePaymentCommand(system).execute(); break;
                    case "8": new menu.EarlyPayoffCommand(system).execute(); break;
                    case "9": new menu.ViewHistoryCommand(system).execute(); break;
                    case "10": return;
                    default: System.out.println("Невірний пункт");
                }
            }catch(Exception e){ System.out.println("Помилка: " + e.getMessage()); }
        }
    }
}
