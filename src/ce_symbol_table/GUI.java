package ce_symbol_table;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.io.IOException;
import javax.swing.tree.*;

public class GUI extends JFrame implements ActionListener {

    private JPanel principal, arbol, botones, h, pad, n;
    private JButton anadir, eliminar, guardar, imprimir, salir;
    private JFrame addHijo, clearNodo;
    private JButton add, clear;
    private JComboBox padres, nodos;//para crear un desplegable
    private JTextArea area;
    private JTextField hijo, nodo;
    private JLabel lh, lp, ln;
    int res;
    private String nombreH;
    private String nombreP;
    private String nombreN;
    private String command = null;
    private Tree tree;
    private Leaf root;
//constructor por defecto

    public GUI() {
        super("Genealogical Tree ");
        initialize();
    }

    public static void main(String args[]) {
        GUI frame = new GUI();
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    private void initialize() {
// the container associated with the window is obtained
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
// panels are created
        principal = new JPanel();
        arbol = new JPanel();
// the border of the text area is created
        Box cuadro = Box.createHorizontalBox();
        area = new JTextArea(25, 50);
        area.setEditable(false);
        cuadro.add(new JScrollPane(area));
        arbol.add(cuadro);
// the buttons are created and added to the listeners
        botones = new JPanel(new GridLayout(4, 1));
        anadir = new JButton("Add");
        anadir.addActionListener(this);
        eliminar = new JButton("Delete");
        eliminar.addActionListener(this);
        salir = new JButton("Exit");
        salir.addActionListener(this);
        botones.add(anadir);
        botones.add(eliminar);

        botones.add(salir);
        principal.add(arbol);
        principal.add(botones);
        c.add(principal);
        tree = new Tree();
        root = tree.sendRootNode();
    }

    public void anadir() {
        addHijo = new JFrame("Add Child");
        addHijo.setSize(350, 150);
        addHijo.setDefaultCloseOperation(addHijo.EXIT_ON_CLOSE);
        addHijo.setVisible(true);
        addHijo.setVisible(true);
        addHijo.setLayout(new GridLayout(3, 1));
        h = new JPanel();
        lh = new JLabel("Child Name");
        hijo = new JTextField(20);
        h.add(lh);
        h.add(hijo);
// Get existing parents
// take an array with all the nodes hanging from the root
        ArrayList<String> p = root.getChildren2();
// an iterator is created to be able to go through all the
// array elements
        Iterator pi = p.iterator();
        String[] ps = new String[p.size() + 1];
        ps[0] = "Root";
        int i = 1;
        while (pi.hasNext()) {
            ps[i] = ((String) pi.next());
            i++;
        }
        pad = new JPanel();
        padres = new JComboBox(ps);
        lp = new JLabel("Father Name");
        pad.add(lp);
        pad.add(padres);
        addHijo.add(h);
        addHijo.add(pad);
        add = new JButton("Add");
        add.addActionListener(this);
        addHijo.add(add);
    }
// method to delete node

    public void eliminar() {
        clearNodo = new JFrame("Borrar nodo");
        clearNodo.setSize(350, 150);
        clearNodo.setDefaultCloseOperation(clearNodo.EXIT_ON_CLOSE);
        clearNodo.setVisible(true);
        clearNodo.setVisible(true);
        clearNodo.setLayout(new GridLayout(2, 1));
        n = new JPanel();
// existing parents are obtained
// take an array with all the nodes hanging from the root
        ArrayList<String> p = root.getChildren2();
// an iterator is created to be able to go through all the
// array elements
        Iterator pi = p.iterator();
        String[] ps = new String[p.size() + 1];
        ps[0] = "Root";
        int i = 1;

        while (pi.hasNext()) {
            ps[i] = ((String) pi.next());
            i++;
        }
        nodos = new JComboBox(ps);
        ln = new JLabel("Node name");
        n.add(ln);
        n.add(nodos);
        clearNodo.add(n);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        clearNodo.add(clear);
    }
// method to print in the Tree.java class

    public void crearArbol(Leaf root, Boolean command) {
        String tab = "";
        for (int i = 0; i < root.getChildren().size(); i++) {
            tab = "";
// with this loop you can see the amount of level
// of depth there is.
            for (int j = 0; j < root.rowofChild(root); j++) {
// the program adds a new tab for
// each level
                tab += '\t';
            }
            String str = tab
                    + root.getChildren().get(i).getName();
// if I send is  true  it updates the buffer
// is used to print the file out.txt
            if (command) {
// the program adds the lines to the buffer that
// write the tree
                tree.getTextFile().append(str + " "
                        + System.getProperty("line.separator"));
// if the command is  false  the program updates the
// b fer that later shows the tree
            } else {
// to show you the tree without it
// can modify
                area.append(str + "\n");
            }
// if the node has children, the program calls again
// to the recursive method
            if (root.getChildren().get(i).getChildren().size()
                    != 0) {
                crearArbol(root.getChildren().
                        get(i), command);
            } else {
// the program gets the line to add in the
// buffer of the readText.txt file
                tree.getFirstFile().append(tree.setLineOfFirstFile(root.getChildren().get(i))
                        + System.getProperty("line.separator"));
            }
        }//for
    }

    public void guardar() {
        System.out.println("guardando");
        tree.getTextFile().setLength(0);
        tree.getFirstFile().setLength(0);
        tree.printChildren(root, true);
// the tree structure is written in the out.txt file
        tree.saveTreeStructureToFile(root, "out.txt",
                tree.getTextFile());
// you write the tree structure in the file readText.txt
        tree.saveTreeStructureToFile(root, "readText.txt",
                tree.getFirstFile());
    }

    public void salir() {
        res = JOptionPane.showConfirmDialog(null, "Do you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == anadir) {
            anadir();
        }
// this button is the "OK" of the second window
        if (ae.getSource() == add) {
            nombreH = hijo.getText();
            nombreP = padres.getSelectedItem().toString();

            if (nombreP == "Root") {
                root.insertChild(new Leaf(nombreH, root));
            } else {
                root = tree.addNewChild(nombreH, nombreP, root);
            }

            guardar();
            crearArbol(root, false);
// when the "OK" button is pressed, the child is saved
// the selected parent and what is deleted
// there is in the window to be able to enter a new
//son

            addHijo.dispose();
            JOptionPane.showMessageDialog(addHijo, "Element inserted correctly");

        }

        if (ae.getSource() == eliminar) {
            eliminar();
        }
// this button is the "OK" of the second window
        if (ae.getSource() == clear) {

            nombreN = nodos.getSelectedItem().toString();
            tree.removeChild(nombreN, root);
            guardar();
            crearArbol(root, false);
// when the "OK" button is pressed, the node is deleted
// entered and what is in the window is cleared
// to be able to introduce a new node to be deleted
            clearNodo.dispose();
            JOptionPane.showMessageDialog(clearNodo, "Item removed correctly");

        }

        if (ae.getSource() == salir) {
            salir();// this button will be removed from the interface
        }
    }// close actionPerformed method
}// close class
