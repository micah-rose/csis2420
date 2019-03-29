package ce_symbol_table;

import java.util.ArrayList;
import java.util.Iterator;

public class Leaf {

    private String name;
    private Leaf parent;
// array of children
    public ArrayList<Leaf> children = null;
// default constructor
// a son has a name and a father

    public Leaf(String name, Leaf parent) {
        this.name = name;
        this.parent = parent;
        children = new ArrayList<Leaf>();
    }
// the name is given

    public String getName() {
        return name;
    }
//change the name

    public void setName(String name) {
        this.name = name;
    }
// the father is given

    public Leaf getParent() {
        return parent;
    }
// the father's name is changed

    public void setParent(Leaf parent) {
        this.parent = parent;
    }
// the list of the names of the children is given

    public ArrayList<String> getChildren2() {
// a new list with the names of the children is created

        ArrayList<String> aux = new ArrayList<String>();
        if (!name.equals("RootNode")) {
            aux.add((String) name);
        }
        if (children == null) {
            return aux;
        } else {
// an iterator is created to be able to go through all the
// array elements
            Iterator pi = children.iterator();
// as long as that node has children, it is passed to the next
            while (pi.hasNext()) {
// go through the children you have
                ArrayList<String> aux2 = ((Leaf) pi.next()).getChildren2();
// that list is retraced
                Iterator pi2 = aux2.iterator();
                while (pi2.hasNext()) {
                    aux.add((String) pi2.next());
                }
            }
            return aux;
        }
    }
// the list of children is given

    public ArrayList<Leaf> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Leaf> children) {
        this.children = children;
    }
// a child is inserted into the node that is passed to it

    public void insertChild(Leaf node) {
// if this child does not contain the node that is being passed to it, it is
// add on the next line
        if (!this.children.contains(node)) {
            this.children.add(node);
        }
    }
//loop into files son to son 

    public int rowofChild(Leaf node) {
        int row = 0;
        while (node.name != "RootNode") {
            row += 1;
            node = node.parent;
        }
        return row;
    }
// the number of children that a node has is given

    public int ContainsNode(Leaf node) {
        for (int i = 0; i < this.children.size(); i++) {
// is compared if the name of that child is equal to the name
// of the node that happens to you
            if (this.children.get(i).getName().equals(node.getName())) {
                return i;
            }
        }
        return -1;
    }
}//close class Leaf

