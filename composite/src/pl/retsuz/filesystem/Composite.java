package pl.retsuz.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Composite extends GeneralComposite{
    private List<IComposite> children;

    public Composite(){
        this.children=new ArrayList<>();
    }

    public Boolean alreadyExists(IComposite element){
        int index = this.children.indexOf(element);
        if(index!=-1)return true;
        return false;
    }

    public void addElement(IComposite element) throws Exception {
        if(this.alreadyExists(element))
            throw new Exception("Element o nazwie "+element.getName()+ " już istnieje w " + this.getPath());
        element.setParent(this);
        this.children.add(element);
    }

    public void removeElement(IComposite element) throws Exception {
        int index = this.children.indexOf(element);
        if(index==-1)
            throw new Exception("Element o nazwie "+element.getName()+ " nie istnieje "+ this.getPath());
        this.children.get(index).setParent(null);
        this.children.remove(index);
    }

    public IComposite getElement(IComposite element) throws Exception {
        int index = this.children.indexOf(element);
        if(index==-1)
            throw new Exception("Element o nazwie "+element.getName()+ " nie istnieje w " + this.getPath());
        return this.children.get(index);
    }

    public static void moveElement(IComposite src, IComposite dst, IComposite element) throws Exception {
        if(src==null || !Composite.class.isInstance(src))
            throw new Exception("Element źródłowy nie istnieje bądź nie jest katalogiem: "+ src.getPath());
        if(dst==null || !Composite.class.isInstance(src))
            throw new Exception("Element docelowy nie istnieje bądź nie jest katalogiem "+ dst.getPath());

        Composite source = (Composite) src;
        Composite destination = (Composite) dst;

        if(!source.alreadyExists(element))
            throw new Exception("Element o nazwie "+element + "nie istnieje w katalogu źródłowym "+source.getPath());
        if(destination.alreadyExists(element))
            throw new Exception("Element o nazwie "+element + "już istnieje w katalogu docelowym "+destination.getPath());

        IComposite movingElement = source.getElement(element);
        source.removeElement(element);
        destination.addElement(element);
    }

    public List<IComposite> find(IComposite element){
        List<IComposite> results = new ArrayList<>();
        try{
            results.add(this.getElement(element));
        }catch(Exception e){

        }
        int i=0;
        int n = this.children.size();

        for(i=0;i<n;i++) {
            IComposite temp = this.children.get(i);
            if (Composite.class.isInstance(temp)) {
                List<IComposite> tList = ((Composite) temp).find(element);
                if (tList.size() > 0) results.addAll(tList);
            }
        }
        return results;
    }

    public String ls(String offset){
        String toff= "";
        if(offset!=null)toff=offset;

        String results="";
            results+=toff+this.getName()+"/\n";
            int i=0;int n=this.children.size();
            for(i=0;i<n;i++){
                results+=toff+"\t"+this.children.get(i).getName()+"\n";
            }
        return results;
    }

    public String tree(String offset){
        String toff= "";
        if (offset!=null)
            toff = offset;

        String results = "";
        results += toff + this.getName() + "/\n";
        int i = 0;
        int n = this.children.size();

        for(i = 0;i < n; i++){
            IComposite child = this.children.get(i);
            if(!Composite.class.isInstance(child))
                results += toff + toff + " " + child.getName() + "\n";
            else
                results += toff + ((Composite)child).tree(toff + " ");
        }

        return results;
    }

    public IComposite findElementByPath(String path) throws Exception {
        path=path.replaceAll("\\/+","\\/");
        IComposite position=this;
        String [] arr = path.split("\\/");

        boolean brk= false;

        int i=0;int n= arr.length;
        List<String> rest= new ArrayList<>();
        for(i=0;i<n;i++){
            if(arr[i].equals("..")&&brk==false)position=position.getParent();
            else {
                rest.add(arr[i]);
                brk = true;
            }
        }
        if(rest.size()==0)return position;

        IComposite element_ = new Composite();
        element_.setName(rest.get(0));
        rest.remove(0);

        IComposite reselem =((Composite)position).getElement(element_);

        if(rest.size()==0&&reselem!=null)return reselem;

        String restpath="";
        for(int j=0;j<rest.size();j++){
            restpath+=rest.get(j);
            if(j!=rest.size()-1)restpath+="/";
        }

        if(reselem!=null && Composite.class.isInstance(reselem)) return ((Composite)reselem).findElementByPath(restpath);
        return null;
    }

}
