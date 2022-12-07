package pl.retsuz.shell.variations.ls;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class LS_Def extends CommandVariation {
    public LS_Def(ICommandVariation next, ICommand parent) {
        super(next,parent,"");
    }
    @Override
    public void make(String params) {

        Composite c= (Composite) (this.getParent().getContext().getCurrent());

        System.out.print(c.ls(" "));

    }
}
