import com.rreeves.Tree;

import java.util.Random;

//Test driver..
public class MainProgram {
    public static void main(String []args) {
        Random rand = new Random();
        Tree tree = new Tree();

        tree.insert(50);
        tree.insert(20);
        tree.insert(25);
        tree.insert(35);
        tree.insert(70);
        tree.insert(65);
        tree.insert(75);
        tree.insert(80);

        System.out.println("Max depth is " + tree.getMaxDepth());
        tree.printInOrder();

        if (tree.isBst())
            System.out.println("Tree is BST");
        else
            System.out.println("Tree isn't BST");

        tree.printLevelOrder();
    }
}