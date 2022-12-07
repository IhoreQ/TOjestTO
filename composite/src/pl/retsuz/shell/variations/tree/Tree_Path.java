package pl.retsuz.shell.variations.tree;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Tree_Path extends CommandVariation {
    public Tree_Path(ICommandVariation next, ICommand parent) {
        super(next,parent,"[a-zA-Z0-9.l\\/_]*");
    }
    @Override
    public void make(String params) {

        Composite c= (Composite) (this.getParent().getContext().getCurrent());
        try {
            IComposite elem = c.findElementByPath(params);

            System.out.print(((Composite) elem).tree(" "));
        }catch(Exception e){
            System.out.println("Docelowy element nie jest katalogiem lub obecny katalog nie zawiera elementu.");
        }

    }
}