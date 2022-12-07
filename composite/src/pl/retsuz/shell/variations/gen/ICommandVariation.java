package pl.retsuz.shell.variations.gen;

import pl.retsuz.shell.gen.ICommand;

public interface ICommandVariation {
    public void processVariation(String params) throws Exception;
    public void setParent(ICommand command);
}
