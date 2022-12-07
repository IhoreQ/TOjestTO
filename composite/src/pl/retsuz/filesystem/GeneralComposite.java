package pl.retsuz.filesystem;

public abstract class GeneralComposite implements IComposite{
    protected String name;
    protected IComposite parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IComposite getParent() {
        return parent;
    }

    public void setParent(IComposite parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !IComposite.class.isInstance(o)) return false;
        IComposite that = (IComposite) o;
        return name.equals(that.getName());
    }

    public String getPath(){
        if(this.parent==null)return this.name;
        else return this.parent.getPath()+"/"+this.getName();
    }

}
