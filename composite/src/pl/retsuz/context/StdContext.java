package pl.retsuz.context;

import pl.retsuz.filesystem.IComposite;

public class StdContext implements IContext{
    private IComposite current;

    public IComposite getCurrent() {
        return current;
    }

    public void setCurrent(IComposite current) {
        this.current = current;
    }
}
