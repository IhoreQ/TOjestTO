package pl.retsuz.shell.variations.cd;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class CD_def extends CommandVariation {
    public CD_def(ICommandVariation next, ICommand parent) {
        super(next,parent,"");
    }
    @Override
    public void make(String params) {
        System.out.println("Zbyt mała liczba parametrów!");
    }
}

