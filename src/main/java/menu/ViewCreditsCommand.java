package menu;
import system.CreditSystem;
public class ViewCreditsCommand implements Command {
    private CreditSystem system; public ViewCreditsCommand(CreditSystem s){ this.system = s; }
    @Override public void execute(){ system.listCredits(); }
}
