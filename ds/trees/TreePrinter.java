package ds.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree printer
 * 
 * @author MightyPork
 */
public class TreePrinter {
    /** Node that can be printed */
    public interface PrintableBTreeNode {
        /** Get left child */
        PrintableBTreeNode getLeft();

        /** Get right child */
        PrintableBTreeNode getRight();

        /** Get text to be printed */
        String getText();
    }

    /**
     * Print a tree
     * 
     * @param root tree root node
     */
    public static void print(PrintableBTreeNode root) {
        // List that stores all rows containing nodes or null
        List<List<String>> lines = new ArrayList<List<String>>();

        List<PrintableBTreeNode> level = new ArrayList<PrintableBTreeNode>();
        List<PrintableBTreeNode> next = new ArrayList<PrintableBTreeNode>();

        level.add(root);
        int numNodesInCurrLine = 1;

        int widest = 0;

        while (numNodesInCurrLine > 0) {
            List<String> currentLine = new ArrayList<String>();

            // Initially start with zero nodes in the current line
            numNodesInCurrLine = 0;

            for (PrintableBTreeNode currNode : level) {
                if (currNode == null) {
                    currentLine.add(null);
                    next.add(null);
                    next.add(null);
                    continue;
                }

                String nStr = currNode.getText();
                currentLine.add(nStr);

                widest = Math.max(widest, nStr.length());

                next.add(currNode.getLeft());
                next.add(currNode.getRight());

                if (currNode.getLeft() != null)
                    numNodesInCurrLine++;

                if (currNode.getRight() != null)
                    numNodesInCurrLine++;
            }

            if (widest % 2 == 1)
                widest++;

            lines.add(currentLine);

            // Swap level and next references
            List<PrintableBTreeNode> tmp = level;
            level = next;
            next = tmp;

            next.clear();
        }

        int numInBottomRow = lines.get(lines.size() - 1).size();
        int widthPerStr = numInBottomRow * (widest + 4);

        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(widthPerStr / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null)
                                c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < widthPerStr - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null)
                    f = "";
                int gap1 = (int) Math.ceil(widthPerStr / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(widthPerStr / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            widthPerStr /= 2;
        }
    }
}