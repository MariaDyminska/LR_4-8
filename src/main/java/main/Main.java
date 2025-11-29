package main;

import java.util.Scanner;
import menu.*;
import system.CreditSystem;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CreditSystem system = new CreditSystem();

        boolean testMode = System.getProperty("testMode") != null;

        while (true) {
            System.out.println("\nГОЛОВНЕ МЕНЮ");
            System.out.println("1. Меню менеджера");
            System.out.println("2. Меню клієнта");
            System.out.println("3. Вихід");
            System.out.print("Оберіть: ");

            String opt;
            if (sc.hasNextLine()) {
                opt = sc.nextLine().trim();
            } else {
                break; // якщо немає рядка (для тестів)
            }

            switch (opt) {
                case "1": new ManagerMenu(system).show(); break;
                case "2": new ClientMenu(system).show(); break;
                case "3": System.out.println("До побачення"); return;

                default: System.out.println("Невірний пункт");
            }

            if (testMode) {
                return; // зупинка після першого циклу для тестів
            }
        }
    }
}
