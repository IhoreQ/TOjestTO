package pl.retsuz.shell;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.specs.*;
import pl.retsuz.shell.variations.cd.CD_Path;
import pl.retsuz.shell.variations.cd.CD_ddot;
import pl.retsuz.shell.variations.cd.CD_def;
import pl.retsuz.shell.variations.diff.Diff_Def;
import pl.retsuz.shell.variations.diff.Diff_Path;
import pl.retsuz.shell.variations.gen.ICommandVariation;
import pl.retsuz.shell.variations.grep.Grep_Def;
import pl.retsuz.shell.variations.grep.Grep_Path;
import pl.retsuz.shell.variations.ls.LS_Def;
import pl.retsuz.shell.variations.ls.LS_Path;
import pl.retsuz.shell.variations.ls.LS_ddot;
import pl.retsuz.shell.variations.more.More_Def;
import pl.retsuz.shell.variations.tree.Tree_Def;
import pl.retsuz.shell.variations.tree.Tree_Path;
import pl.retsuz.shell.variations.tree.Tree_ddot;

public abstract class DefShell {
    public static ICommand construct(IContext ctx){
        ICommand more = new More(ctx, null);
        ICommandVariation more_def= new More_Def(null, more);
        more.set_default(more_def);

        ICommand tree = new Tree(ctx, more);

        ICommandVariation tree_path= new Tree_Path(null, tree);
        ICommandVariation tree_ddot= new Tree_ddot(tree_path, tree);
        ICommandVariation tree_def= new Tree_Def(tree_ddot, tree);
        tree.set_default(tree_def);

        ICommand cd = new Cd(ctx, tree);

        ICommandVariation cd_path= new CD_Path(null, cd);
        ICommandVariation cd_ddot= new CD_ddot(cd_path, cd);
        ICommandVariation cd_def= new CD_def(cd_ddot, cd);
        cd.set_default(cd_def);

        ICommand ls = new Ls(ctx, cd);
        ICommandVariation ls_path= new LS_Path(null, ls);
        ICommandVariation ls_ddot= new LS_ddot(ls_path, ls);
        ICommandVariation ls_def= new LS_Def(ls_ddot, ls);
        ls.set_default(ls_def);

        ICommand grep = new Grep(ctx, ls);
        ICommandVariation grep_path = new Grep_Path(null, grep);
        ICommandVariation grep_def = new Grep_Def(grep_path, grep);
        grep.set_default(grep_def);

        ICommand diff = new Diff(ctx, grep);
        ICommandVariation diff_path = new Diff_Path(null, diff);
        ICommandVariation diff_def = new Diff_Def(diff_path, diff);
        diff.set_default(diff_def);

        return diff;
    }
}
