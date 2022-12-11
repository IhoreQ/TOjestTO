package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;

public class Grep extends Command {

    public Grep(IContext ctx, ICommand next) {
        super("grep", ctx, next, null, "Użycie grep \"<wzorzec>\" <sciezka>");
    }
}
