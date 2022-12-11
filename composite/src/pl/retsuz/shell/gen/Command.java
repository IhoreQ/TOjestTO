package pl.retsuz.shell.gen;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.variations.gen.ICommandVariation;

import java.util.regex.*;

public abstract class Command implements ICommand {
    private String prefix;
    private IContext context;
    private ICommand next;
    private String man;
    Pattern generalPattern;

    public ICommandVariation get_default() {
        return _default;
    }

    public void set_default(ICommandVariation _default) {
        this._default = _default;
    }

    ICommandVariation _default;


    public Command(String prefix, IContext ctx, ICommand next, ICommandVariation def, String info){
        this.context=ctx;
        this.prefix=prefix;
        this.next = next;
        this.generalPattern = Pattern.compile(prefix + " *([\" a-zA-Z0-9.l\\/_]*)");
        this._default=def;
        this.man=info;
    }

    private boolean checkPrefix(String command){
        Matcher m = generalPattern.matcher(command);
        return m.matches();
    }

    public void perform(String command) throws Exception {
        if(!checkPrefix(command)){

            if(this.next!=null)
                this.next.perform(command);
            else throw new Exception("Polecenie nie istnieje.");
        }else{
            Matcher m = generalPattern.matcher(command);
            String params="";

            if(m.find()){
                params=m.group(1);
            }

            this._default.processVariation(params);
        }
    }

    public String man(){
        return this.man;
    }

    public IContext getContext(){
        return this.context;
    }
}
