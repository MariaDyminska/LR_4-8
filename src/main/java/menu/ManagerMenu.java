package menu;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import system.CreditSystem;

public class ManagerMenu {

    private static final Logger logger = LogManager.getLogger(ManagerMenu.class);

    private CreditSystem system;
    private Scanner sc = new Scanner(System.in);

    public ManagerMenu(CreditSystem system){
        this.system = system;
    }

    public void show(){
        while(true){
            logger.info("Відкрито меню менеджера");
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
                        logger.info("Вибрано додати кредит");
                        new AddCreditCommand(system).execute();

                        System.out.print("Зберегти у файл? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так")) {
                            logger.info("Збереження кредитів у файл");
                            new SaveToFileCommand(system).execute();
                        }
                        break;

                    case "2":
                        System.out.print("Завантажити з файлу перед редагуванням? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так")) {
                            logger.info("Завантаження кредитів з файлу перед редагуванням");
                            new LoadFromFileCommand(system).execute();
                        }

                        logger.info("Редагування кредиту");
                        new EditCreditCommand(system).execute();

                        System.out.print("Зберегти у файл? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так")) {
                            logger.info("Збереження кредитів у файл після редагування");
                            new SaveToFileCommand(system).execute();
                        }
                        break;

                    case "3":
                        System.out.print("Завантажити з файлу перед переглядом? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так")) {
                            logger.info("Завантаження кредитів з файлу перед переглядом");
                            new LoadFromFileCommand(system).execute();
                        }

                        logger.info("Перегляд кредитів");
                        new ViewCreditsCommand(system).execute();
                        break;

                    case "4":
                        logger.info("Видалення кредиту");
                        new DeleteCreditCommand(system).execute();

                        System.out.print("Зберегти у файл? (так/ні): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("так")) {
                            logger.info("Збереження кредитів у файл після видалення");
                            new SaveToFileCommand(system).execute();
                        }
                        break;

                    case "5":
                        logger.info("Вихід з меню менеджера");
                        return;

                    default:
                        logger.warn("Вибрано невірний пункт меню: {}", opt);
                        System.out.println("Невірний пункт");
                }
            } catch(Exception e) {
                logger.error("Помилка в меню менеджера: ", e);
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}
