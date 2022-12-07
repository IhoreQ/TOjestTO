package pl.retsuz.examples;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.filesystem.TextFile;

public abstract class ExampleDelivery {
    public static IComposite generateExampleTree(){
        IComposite root= new Composite();
        root.setName("root");

        IComposite _null = new Composite();
        _null.setName("null");

        IComposite usr = new Composite();
        usr.setName("users");

        IComposite _default = new Composite();
        _default.setName("default");

        IComposite docs = new Composite();
        docs.setName("documents");

        IComposite szwester = new Composite();
        szwester.setName("szwester");

        IComposite sdocs = new Composite();
        sdocs.setName("documents");

        IComposite secretdoc=new TextFile();
        secretdoc.setName("tajna_wiadomosc.txt");
        ((TextFile)secretdoc).setContent("( ͡° ͜ʖ ͡°)");

        IComposite pomarancza=new TextFile();
        pomarancza.setName("pomarancza.txt");
        ((TextFile)pomarancza).setContent("Masz, poczęstuj się.");

        try {
            ((Composite) root).addElement(_null);

            ((Composite) _default).addElement(docs);
            ((Composite) usr).addElement(_default);
            ((Composite) root).addElement(usr);

            ((Composite) szwester).addElement(sdocs);
            ((Composite) usr).addElement(szwester);

            ((Composite) sdocs).addElement(secretdoc);
            ((Composite) docs).addElement(pomarancza);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}
