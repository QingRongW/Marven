package btree;

import common.Node;
import utils.NodeUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class used to print the topView of the BinaryTree from:
 * https://www.hackerrank.com/challenges/tree-top-view/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour
 * -campaign
 */
public class TopViewBTree {

    public static void topView(Node root) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> nodeByItsHD = new HashMap<>();
        int hd = 0;
        queue.add(root);
        updateTreeMap(m, root, hd);
        nodeByItsHD.put(root, hd);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            hd = nodeByItsHD.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                nodeByItsHD.put(cur.left, hd - 1);
                updateTreeMap(m, cur.left, hd - 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nodeByItsHD.put(cur.right, hd + 1);
                updateTreeMap(m, cur.right, hd + 1);
            }
        }
        for (java.util.Map.Entry<Integer, Integer> entry : m.entrySet()) {
            System.out.print(entry.getValue());
            System.out.print(" ");
        }
    }

    private static void updateTreeMap(TreeMap<Integer, Integer> m, Node node, int hd) {
        if (m.get(hd) == null) {
            m.put(hd, node.data);
        }
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
        /*BTreePrinter.printNode(root);*/
        topView(root);
    }

}