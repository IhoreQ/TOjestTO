package pl.retsuz.shell.variations.more;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class More_Def extends CommandVariation {
    public More_Def(ICommandVariation next, ICommand parent) {
        super(next,parent,"[a-zA-Z0-9.l\\/_]*");
    }
    @Override
    public void make(String params) {

        Composite c= (Composite) (this.getParent().getContext().getCurrent());
        try {
            IComposite elem = c.findElementByPath(params);

            TextFile tf = ((TextFile) elem);
            System.out.println(tf.getContent());
        }catch(Exception e){
            System.out.println("Docelowy element nie jest plikiem lub nie istnieje.");
        }

    }
}