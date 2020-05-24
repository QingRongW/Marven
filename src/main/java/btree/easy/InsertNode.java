package btree.easy;

import common.Node;

import java.util.Scanner;

public class InsertNode {
    public static void preOrder(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    public static Node insert(Node root, int data) {
        Node node = new Node(data);
        if (root == null) {
            return node;
        }

        if (data <= root.data) {
            if (root.left == null) {
                root.left = node;
            } else {
                insert(root.left, data);
            }
        } else {
            if (root.right == null) {
                root.right = node;
            } else {
                insert(root.right, data);
            }
        }

        return root;

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        preOrder(root);
    }
}
