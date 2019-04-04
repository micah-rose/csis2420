package ce_symbol_table;

import java.util.Scanner;

public class Test {

    public static void main(String args[]) {
        String child = null;
        String parent = null;
        String node = null;
//Options can be added, saved, printed and output
        String command = null;
        System.out.println("Welcome\n\n");
        Tree tree = new Tree();
        Leaf root = tree.sendRootNode();
        @SuppressWarnings("resource")
		Scanner Teclado = new Scanner(System.in);
        do {
            System.out.println("Enter the command (add, delete, print, save, exit): ");
            command = Teclado.nextLine();
            if (command.equals("add")) {
                System.out.println("Insert the name of the child: ");
                child = Teclado.nextLine();
                System.out.println("Insert the father's name: ");
                parent = Teclado.nextLine();
//if the file readText.txt is empty
                if (parent.equals("null")) {
//new son is inserted
                    root.insertChild(new Leaf(child, root));
                    tree.setControl(true);
// if the readText.txt file is not empty
// program has to find the father and insert the son
                } else {
                    root = tree.addNewChild(child, parent, root);
                }
                if (tree.isControl()) {
                    System.out.println("The node has been added successfully...");
                    tree.setControl(false);
                } else {
                    System.out.println("The father has not been found!!!");
                }
            } else if (command.equals("print")) {
// goes to the first line
                tree.getTextFile().setLength(0);
                tree.getFirstFile().setLength(0);
                tree.printChildren(root, false);
            } else if (command.equals("save")) {
                tree.getTextFile().setLength(0);
                tree.getFirstFile().setLength(0);
                tree.printChildren(root, true);
// the tree structure is written in the file
//out.txt
                tree.saveTreeStructureToFile(root, "out.txt",
                        tree.getTextFile());
// the tree structure is written in the file
//readText.txt
                tree.saveTreeStructureToFile(root, "readText.txt",
                        tree.getFirstFile());
            } else if (command.equals("delete")) {
                System.out.println("Insert the name of the node to be deleted: ");
                node = Teclado.nextLine();
                tree.removeChild(node, root);
                if (tree.isControl()) {
                    System.out.println("The node has been removed...");
                    tree.setControl(false);
                } else {
                    System.out.println("The node has not been found!!!");
                }
            }
        } while ((!command.equals("exit")) && (!command.equals("" + "")));
        System.out.println("The program has been closed. Run it again");
    }//close do
}//close clase test
