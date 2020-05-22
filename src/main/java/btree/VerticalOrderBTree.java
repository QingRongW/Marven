package btree;

import common.Node;
import utils.TreeUtils;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Class used to print the Vertical Order of the BinaryTree.
 */
public class VerticalOrderBTree {

    /**
     * Main method of this class to print the top view
     *
     * @param root
     */
    public static void treeView(Node root) {
        TreeMap<Integer, Vector<Integer>> m = new TreeMap<>();
        int hd = 0;
        getVerticalOrder(root, hd, m);
        System.out.println("Vertical Order traversal is:  ");
        printVerticalOrder(m);

    }

    // The main function to print vertical order of a binary tree
    // with the given root
    static void printVerticalOrder(TreeMap<Integer, Vector<Integer>> m) {
        // Traverse the map and print nodes at every horigontal
        // distance (hd)
        for (Entry<Integer, Vector<Integer>> entry : m.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private static void getVerticalOrder(Node root, int hd, TreeMap<Integer, Vector<Integer>> m) {
        if (root == null) {
            return;
        } else {
            Vector<Integer> values = m.get(hd);
            if (values == null) {
                values = new Vector<>();
                values.add(root.data);
                m.put(hd, values);
            } else {
                values.add(root.data);
                m.put(hd, values);
            }
            getVerticalOrder(root.left, hd - 1, m);
            getVerticalOrder(root.right, hd + 1, m);

        }

    }

    /**
     * Main method, entrance of the class.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = TreeUtils.insert(root, data);
        }
        scan.close();
        treeView(root);
    }
}
