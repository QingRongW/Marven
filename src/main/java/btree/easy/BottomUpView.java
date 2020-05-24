package btree.easy;

import utils.BTreePrinter;
import common.Node;
import utils.NodeUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;

public class BottomUpView {

    public static void heightView(Node root) {
        Map<Integer, Vector<Integer>> treemap = new TreeMap<>(Collections.reverseOrder());
        fillNodeHeights(root, 0, treemap);
        for (java.util.Map.Entry<Integer, Vector<Integer>> entry : treemap.entrySet()) {
            for (Integer value : entry.getValue()) {
                System.out.print(value);
                System.out.print(" ");
            }
        }
    }

    private static void fillNodeHeights(Node root, int height, Map<Integer, Vector<Integer>> treemap) {
        if (root == null) {
            return;
        }
        int data = root.data;
        Vector<Integer> values = treemap.get(height);
        if (values == null) {
            values = new Vector<>();
        }
        values.add(data);
        treemap.put(height, values);
        fillNodeHeights(root.left, height + 1, treemap);
        fillNodeHeights(root.right, height + 1, treemap);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = NodeUtils.insert(root, data);
        }
        scan.close();
        BTreePrinter.printNode(root);
        heightView(root);
    }
}
