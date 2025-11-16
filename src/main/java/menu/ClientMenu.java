package menu;

import java.util.Scanner;
import system.CreditSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientMenu {
    private static final Logger logger = LogManager.getLogger(ClientMenu.class);
    private CreditSystem system;
    private Scanner sc = new Scanner(System.in);

    public ClientMenu(CreditSystem system) {
        this.system = system;
    }

    public void show() {
        while (true) {
            // Логування меню
            logger.info("Відкрито меню клієнта");

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

            String opt = sc.nextLine().trim();
            logger.info("Користувач обрав пункт меню: {}", opt);

            try {
                switch (opt) {
                    case "1":
                        new menu.SearchCreditCommand(system).execute();
                        logger.info("Виконано пошук кредиту за параметрами");
                        break;
                    case "2":
                        new menu.OptimalCreditCommand(system).execute();
                        logger.info("Підібрано оптимальний кредит");
                        break;
                    case "3":
                        new menu.ViewCreditConditionsCommand(system).execute();
                        logger.info("Переглянуто умови кредиту");
                        break;
                    case "4":
                        new menu.ShowScheduleCommand(system).execute();
                        logger.info("Розраховано кредит");
                        break;
                    case "5":
                        new ApplyForCreditCommand(system).execute();
                        logger.info("Оформлено кредит");
                        break;
                    case "6":
                        new menu.SmartHelperCommand(system).execute();
                        logger.info("Використано розумного помічника");
                        break;
                    case "7":
                        new menu.MakePaymentCommand(system).execute();
                        logger.info("Зроблено внесок по кредиту");
                        break;
                    case "8":
                        new menu.EarlyPayoffCommand(system).execute();
                        logger.info("Достроково погашено кредит");
                        break;
                    case "9":
                        new menu.ViewHistoryCommand(system).execute();
                        logger.info("Переглянуто історію кредитів");
                        break;
                    case "10":
                        logger.info("Користувач вийшов з меню клієнта");
                        return;
                    default:
                        logger.warn("Користувач обрав невірний пункт меню: {}", opt);
                        System.out.println("Невірний пункт");
                }
            } catch (Exception e) {
                logger.error("Помилка при обробці дії користувача", e);
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}
