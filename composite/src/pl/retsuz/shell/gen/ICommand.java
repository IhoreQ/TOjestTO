package pl.retsuz.shell.gen;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public interface ICommand {
    void perform(String command) throws Exception;
    String man();
    public IContext getContext();
    public ICommandVariation get_default();

    public void set_default(ICommandVariation _default);
}
