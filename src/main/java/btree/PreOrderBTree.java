package btree;

import common.Node;
import utils.NodeUtils;

import java.util.Scanner;

public class PreOrderBTree {

    public static void preOrder(Node root) {
        if (root == null) {
            return;
        } else {
            System.out.print(root.data + " ");
            if (root.left != null) {
                preOrder(root.left);
            }
            if (root.right != null) {
                preOrder(root.right);
            }
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
        PreOrderBTree.preOrder(root);
    }
}
