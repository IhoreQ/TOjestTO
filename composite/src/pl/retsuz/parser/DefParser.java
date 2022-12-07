package pl.retsuz.parser;

import pl.retsuz.shell.gen.ICommand;

import java.util.Scanner;

public class DefParser implements IParser{
    ICommand cmdTree;
    Scanner sc;

    public DefParser(ICommand cmdTree) {
        this.cmdTree = cmdTree;
        sc = new Scanner(System.in);
    }

    @Override
    public void doParse() {
        while(true){
            System.out.print(":3 "+this.cmdTree.getContext().getCurrent().getPath()+" ");
            String line= sc.nextLine();
            try {
                cmdTree.perform(line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
