package pl.retsuz.shell.variations.grep;

import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Grep_Def extends CommandVariation {

    public Grep_Def(ICommandVariation next, ICommand parent) {
        super(next, parent, "[\"a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {
        System.out.println("Zbyt mała liczba parametrów!");
    }
}
