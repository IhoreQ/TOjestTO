package pl.retsuz.context;

import pl.retsuz.filesystem.IComposite;

public interface IContext {
    public void setCurrent(IComposite current);
    public IComposite getCurrent();
}
