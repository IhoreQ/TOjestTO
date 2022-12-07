package pl.retsuz.filesystem;

public interface IComposite {
    public void setName(String name);
    public String getName();

    public IComposite getParent();
    public void setParent(IComposite param);

    public String getPath();
}
