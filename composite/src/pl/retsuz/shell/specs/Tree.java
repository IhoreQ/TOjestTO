package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.ICommandVariation;
import pl.retsuz.shell.variations.ls.LS_Def;

public class Tree extends Command {

    public Tree(IContext ctx, ICommand next) {
        super("tree", ctx, next, null, "Użycie tree <sciezka>");
    }
}
