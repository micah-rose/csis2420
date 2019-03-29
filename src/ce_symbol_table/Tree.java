package ce_symbol_table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Tree {
// is controlled if the user uses a parent that already exists

    private boolean control;
// the tree structure is written in the out.txt file
    private StringBuffer textFile = new StringBuffer();
// the tree structure is written in the file readText.txt
    private StringBuffer firstFile = new StringBuffer();
// default constructor

    public Tree() {
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }
// the file is given out.txt

    public StringBuffer getTextFile() {
        return textFile;
    }
// the read.txt file is given

    public StringBuffer getFirstFile() {
        return firstFile;
    }

    public Leaf addNewChild(String childName, String parentName, Leaf rootNode) {
// the number of children is given in that way

        for (int i = 0; i < rootNode.getChildren().size(); i++) {
// all the children of that sheet are traversed and in the position
// where he will be the father at that moment
            Leaf parentNode = rootNode.getChildren().get(i);
// the names are compared
            if (parentNode.getName().equals(parentName)) {
// if it is the name that the program is looking for,
// insert the node
                parentNode.insertChild(new Leaf(childName,
                        parentNode));
// there is a node with the same name as the
// user has entered
                control = true;
// in the case that the user puts more than one node
// with the same name, the program only adds the child
// in the first node. Another possibility is to remove the
// "break" and the program adds the new son to all
// the parents with the same name.
                break;
            }
// if the node has children, the program calls the method
// recursive
            if (parentNode.getChildren().size() != 0) {
                addNewChild(childName, parentName, parentNode);
            }
        }
        return rootNode;
    }
// method that removes the node name that is passed to it

    public void removeChild(String Node, Leaf rootNode) {
// the number of children in this sub tree is given
        for (int i = 0; i < rootNode.getChildren().size(); i++) {
// the tree nodes are traversed one by one
            Leaf parentNode = rootNode.getChildren().get(i);
// se comparan los nombres
            if (parentNode.getName().equals(Node)) {
// if it is the name that the program is looking for,
// delete the node
                rootNode.getChildren().remove(i);
// there is a node with the same name as the
// user has entered
                control = true;
                break;
            }
// call to the recursive method
            removeChild(Node, parentNode);
        }//close for
    }//close removeChild()
//this method displays the tree

    public void printChildren(Leaf rootNode, Boolean command) {
        String tab = "";
        for (int i = 0; i < rootNode.getChildren().size(); i++) {
            tab = "";
// with this loop you can see the amount of level of
// depth there is.
            for (int j = 0; j < rootNode.rowofChild(rootNode); j++) {
// the program adds a new tab for each
//level
                tab += '\t';
            }
            String str = tab + rootNode.getChildren().get(i).getName();
// if command is  true  updates the buffer that is then
// used to print the file out.txt
            if (command) {
// the program a de the lines to the b quefer that writes
// the tree
                textFile.append(str + " "
                        + System.getProperty("line.separator"));
// if command is  false  the program updates the buffer that
// later shows the tree
            } else {
                System.out.println(str);
            }
// if the node has children, the program calls back to
// recursive method
            if (rootNode.getChildren().get(i).getChildren().size() != 0) {

                printChildren(rootNode.getChildren().get(i),
                        command);
            } else {
// the program gets the line to add in the
// b fer of the readText.txt file
                firstFile.append(setLineOfFirstFile(rootNode.
                        getChildren().get(i))
                        + System.getProperty("line.separator"));
            }
        }//close for
    }//close method printChildren()
// method to save the tree in the text file

    public void saveTreeStructureToFile(Leaf rootNode, String filename,
            StringBuffer strBuffer) {
        try {
            //a file is created
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(strBuffer.toString());
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
// you get the line that is added in the readText.txt file with the
// predecessors of the node

    public String setLineOfFirstFile(Leaf node) {
        String line = "";
// an ArrayList object is created
        ArrayList<String> lineList = new ArrayList<String>();
// is continued as long as the loop does not get all the predecessors
        while (node.getName() != "RootNode") {
            lineList.add(node.getName());
            node = node.getParent();
        }
// the loop obtains the names of the nodes from the last
//first. It is necessary to invest it.
        Collections.reverse(lineList);
        for (int i = 0; i < lineList.size(); i++) {
            line += lineList.get(i) + ",";
        }
// take the line without the last comma
        return line.substring(0, line.length() - 1);
    }
// the root sheet is returned

    public Leaf sendRootNode() {
        File file = new File("readText.txt");
        BufferedReader reader = null;
// a new sheet is created, which will be the root
        Leaf root = new Leaf("RootNode", null);

// a copy is made to execute the tree
        Leaf broot = root;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
// it is repeated until all the lines are read
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                for (int i = 0; i < fields.length; i++) {
// a new sheet is created
                    Leaf leaf = new Leaf(fields[i], broot);

                    int containsIndex = broot.ContainsNode(leaf);
// it checks if the father contains this sheet
                    if (containsIndex != -1) {
// if the father contains this child, a
// continuation search the row of that
// child, and temporarily makes it the
// new root
                        broot
                                = broot.getChildren().get(containsIndex);
                    } else {
// if the father does not contain this sheet,
// then this sheet is added as a son
// of this father
                        broot.insertChild(leaf);
// this sheet is the new father
                        broot = leaf;

                    }
                }// close for
// for the new file line, start from
// the principle to do the same process
                broot = root;

            }//close while
        } catch (FileNotFoundException e) {
            System.out.println("The file has not been found");
        } catch (IOException e) {
            System.out.println("An error has occurred");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//close trye
        return root;
    }//close method
}//close class
