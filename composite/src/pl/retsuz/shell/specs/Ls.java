package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.ICommandVariation;
import pl.retsuz.shell.variations.ls.LS_Def;

public class Ls extends Command {

    public Ls(IContext ctx, ICommand next) {
        super("ls", ctx, next, null, "Użycie ls <sciezka>");
    }
}
