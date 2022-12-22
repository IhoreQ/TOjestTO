package pl.retsuz.shell.variations.grep;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep_Path extends CommandVariation {

    public Grep_Path(ICommandVariation next, ICommand parent) {
        super(next, parent, "\"(.*?)\" [a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {

        Composite composite = (Composite) (this.getParent().getContext().getCurrent());
        String pattern = "";

        Pattern p = Pattern.compile("\"(.*?)\" ");
        Matcher m = p.matcher(params);

        while (m.find()) {
            pattern = m.group(1);
        }

        String fileName = params.substring(pattern.length() + 3);
        int beginIndex;
        int endIndex;
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";

        try {
            TextFile textFile = (TextFile) composite.findElementByPath(fileName);
            String[] lines = textFile.getContent().split("\n");

            for (String line : lines) {
                if (line.contains(pattern)) {
                    beginIndex = line.indexOf(pattern);
                    endIndex = beginIndex + pattern.length();
                    System.out.println(line.substring(0, beginIndex) +
                            ANSI_RED + line.substring(beginIndex, endIndex) + ANSI_RESET +
                            line.substring(endIndex));
                }
            }

        } catch (Exception e) {
            System.out.println("Docelowy element nie jest plikiem lub nie istnieje.");
        }
    }
}
