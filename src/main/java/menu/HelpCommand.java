package menu;
public class HelpCommand implements Command {
    @Override public void execute(){ System.out.println("Доступні команди: help, exit"); }
}
