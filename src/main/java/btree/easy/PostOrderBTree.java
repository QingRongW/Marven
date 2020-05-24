package btree.easy;

import common.Node;
import utils.NodeUtils;

import java.util.Scanner;

public class PostOrderBTree {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = NodeUtils.insert(root, data);
        }
        scan.close();
        postOrder(root);
    }

    public static void postOrder(Node root) {
        if (root == null) {
            return;
        } else {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

}
