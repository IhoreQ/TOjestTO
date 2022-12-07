package pl.retsuz.shell.variations.gen;

import pl.retsuz.shell.gen.ICommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandVariation implements ICommandVariation {
    ICommandVariation next;
    ICommand parent;
    Pattern generalPattern;

    public CommandVariation(ICommandVariation next, ICommand parent, String pattern){
        this.next=next;
        this.parent = parent;
        this.generalPattern=Pattern.compile(pattern);
    }

    public ICommandVariation getNext() {
        return next;
    }

    public void setNext(ICommandVariation next) {
        this.next = next;
    }

    public ICommand getParent() {
        return parent;
    }

    @Override
    public void setParent(ICommand parent) {
        this.parent = parent;
    }

    protected boolean match(String command){
        Matcher m = generalPattern.matcher(command);
        return m.matches();
    }

    public void processVariation(String params) throws Exception {
        if(!match(params)){

            if(this.next!=null)
                this.next.processVariation(params);
            else throw new Exception("Polecenie nie istnieje.");
        }else{
            this.make(params);
        }
    }

    public abstract void make(String params);
}
