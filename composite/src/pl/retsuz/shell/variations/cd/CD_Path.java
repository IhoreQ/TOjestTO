package pl.retsuz.shell.variations.cd;


import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class CD_Path extends CommandVariation {
    public CD_Path(ICommandVariation next, ICommand parent) {
        super(next,parent,"[a-zA-Z0-9.l\\/_]*");
    }
    @Override
    public void make(String params) {

        Composite c= (Composite) (this.getParent().getContext().getCurrent());
        try {
            IComposite elem = c.findElementByPath(params);
            if(Composite.class.isInstance(elem)){
                this.getParent().getContext().setCurrent(elem);
                System.out.println("Ustawiono na "+this.getParent().getContext().getCurrent().getPath());

            }else System.out.println("Nie ustawiono. Żądany element nie jest katalogiem.");

        }catch(Exception e){
            System.out.println("Docelowy element nie jest katalogiem lub obecny katalog nie zawiera elementu.");
        }

    }
}