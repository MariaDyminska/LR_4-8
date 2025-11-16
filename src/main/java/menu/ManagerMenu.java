package menu;
import java.util.Scanner;
import system.CreditSystem;

public class ManagerMenu {
    private CreditSystem system;
    private Scanner sc = new Scanner(System.in);

    public ManagerMenu(CreditSystem system){
        this.system = system;
    }

    public void show(){
        while(true){
            System.out.println("\nМЕНЮ МЕНЕДЖЕРА");
            System.out.println("1. Додати кредит (з можливістю зберегти у файл)");
            System.out.println("2. Редагувати кредит (з можливістю завантажити/зберегти)");
            System.out.println("3. Переглянути кредити (з можливістю завантажити з файлу)");
            System.out.println("4. Видалити кредит (з можливістю зберегти у файл)");
            System.out.println("5. Вихід");
            System.out.print("Оберіть: ");
            String opt = sc.nextLine().trim();

            try {
                switch(opt){
                    case "1":
                        // --- СТАРА ВЕРСІЯ AddCreditCommand (без Scanner)
                        new AddCreditCommand(system).execute();

                        System.out.print("Зберегти у файл? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так"))
                            new SaveToFileCommand(system).execute();
                        break;

                    case "2":
                        System.out.print("Завантажити з файлу перед редагуванням? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так"))
                            new LoadFromFileCommand(system).execute();

                        new EditCreditCommand(system).execute();

                        System.out.print("Зберегти у файл? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так"))
                            new SaveToFileCommand(system).execute();
                        break;

                    case "3":
                        System.out.print("Завантажити з файлу перед переглядом? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так"))
                            new LoadFromFileCommand(system).execute();

                        new ViewCreditsCommand(system).execute();
                        break;

                    case "4":
                        new DeleteCreditCommand(system).execute();

                        System.out.print("Зберегти у файл? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так"))
                            new SaveToFileCommand(system).execute();
                        break;

                    case "5":
                        return;

                    default:
                        System.out.println("Невірний пункт");
                }
            } catch(Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}