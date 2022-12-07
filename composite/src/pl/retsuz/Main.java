package pl.retsuz;

import pl.retsuz.context.IContext;
import pl.retsuz.context.StdContext;
import pl.retsuz.examples.ExampleDelivery;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.parser.DefParser;
import pl.retsuz.parser.IParser;
import pl.retsuz.shell.DefShell;
import pl.retsuz.shell.gen.ICommand;

public class Main {
	static IComposite root;
	static IContext ctx;
	static ICommand cmdTree;
	static IParser dparser;

    public static void main(String[] args) {

	    root = ExampleDelivery.generateExampleTree();
		ctx = new StdContext();
	    ctx.setCurrent(root);
	    cmdTree = DefShell.construct(ctx);
	    dparser = new DefParser(cmdTree);
	    dparser.doParse();
    }
}
