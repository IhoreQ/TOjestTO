package pl.retsuz.shell.variations.tree;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Tree_Def extends CommandVariation {
    public Tree_Def(ICommandVariation next, ICommand parent) {
        super(next,parent,"");
    }
    @Override
    public void make(String params) {

        Composite c= (Composite) (this.getParent().getContext().getCurrent());

        System.out.print(c.tree(" "));

    }
}