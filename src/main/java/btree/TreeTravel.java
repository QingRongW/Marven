package btree;

import common.Node;
import utils.TreeUtils;

import java.util.LinkedList;
import java.util.Scanner;

public class TreeTravel {
    public static void levelOrder(Node root) {

        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.print(cur.data + " ");
            Node left = cur.left;
            Node right = cur.right;
            if (left != null && right != null) {
                cur = left;
                queue.add(cur);
                queue.add(right);
            } else if (left == null && right != null) {
                cur = right;
                queue.add(cur);
            } else if (left != null && right == null) {
                cur = left;
                queue.add(cur);
            }
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t > 0) {
            int data = scan.nextInt();
            root = TreeUtils.insert(root, data);
            t--;
        }
        scan.close();
        levelOrder(root);
    }
}