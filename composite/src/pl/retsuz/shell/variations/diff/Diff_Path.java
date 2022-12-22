package pl.retsuz.shell.variations.diff;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

import java.util.*;

public class Diff_Path extends CommandVariation {

    public Diff_Path(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l\\/_]* [a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {
        Composite composite = (Composite) (this.getParent().getContext().getCurrent());

        String[] fileNames = params.split(" ");

        try {
            TextFile firstFile = (TextFile) composite.findElementByPath(fileNames[0]);
            TextFile secondFile = (TextFile) composite.findElementByPath(fileNames[1]);

            String[] firstFileLines = firstFile.getContent().split("\n");
            String[] secondFileLines = secondFile.getContent().split("\n");

            int i = firstFileLines.length;
            int j = secondFileLines.length;

            ArrayList<String> elements = new ArrayList<>();
            ArrayList<Character> operations = new ArrayList<>();

            int[][] lcs = longestCommonSubsequence(firstFileLines, secondFileLines);

            while (i != 0 || j != 0) {
                if (i == 0) {
                    elements.add(secondFileLines[j - 1]);
                    operations.add('+');
                    j--;
                }
                else if (j == 0) {
                    elements.add(firstFileLines[i - 1]);
                    operations.add('-');
                    i--;
                }
                else if (firstFileLines[i - 1].equals(secondFileLines[j - 1])) {
                    elements.add(firstFileLines[i - 1]);
                    operations.add('#');
                    i--;
                    j--;
                }
                else if (lcs[i - 1][j] <= lcs[i][j - 1]) {
                    elements.add(secondFileLines[j - 1]);
                    operations.add('+');
                    j--;
                }
                else {
                    elements.add(firstFileLines[i - 1]);
                    operations.add('-');
                    i--;
                }
            }

            Collections.reverse(elements);
            Collections.reverse(operations);

            printDifferences(elements, operations);

        } catch(Exception e) {
            System.out.println("Docelowy element nie jest plikiem lub nie istnieje.");
        }
    }

    private int[][] longestCommonSubsequence(String[] firstText, String[] secondText) {

        int n = firstText.length;
        int m = secondText.length;

        int[][] lcs = new int[n + 1][m + 1];

        for (int i = 0; i < n + 1; ++i) {
            for (int j = 0; j < m + 1; ++j) {
                if (i == 0 || j == 0)
                    lcs[i][j] = 0;
                else if ((firstText[i - 1].equals(secondText[j - 1])))
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                else
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }

        return lcs;
    }

    private void printDifferences(ArrayList<String> elements, ArrayList<Character> operations) {

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";

        for (int k = 0; k < elements.size(); ++k) {
            switch (operations.get(k)) {
                case '+' -> System.out.println(ANSI_GREEN + "[+]" + " " + elements.get(k) + ANSI_RESET);
                case '-' -> System.out.println(ANSI_RED + "[-]" + " " + elements.get(k) + ANSI_RESET);
                case '#' -> System.out.println("[#] " + elements.get(k));
            }
        }
    }
}
