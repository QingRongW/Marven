package btree.easy;

import common.Node;
import utils.NodeUtils;

import java.util.Scanner;

public class InOrderBTree {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = NodeUtils.insert(root, data);
        }
        scan.close();
        inOrder(root);
    }

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
}
