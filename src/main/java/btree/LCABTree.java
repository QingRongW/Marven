package btree;

import common.Node;

import java.util.*;

public class LCABTree {

    public static Node lca(Node root, int v1, int v2) {

        int data = root.data;
        int left = v1;
        int right = v2;
        if (v1 > v2) {
            left = v2;
            right = v1;
        }

        // in different directions
        if (data > left && data < right) {
            return root;
        }
        // in left tree
        else if (data > right && root.left != null) {
            return lca(root.left, left, right);
        }
        // in right side
        else if (data < left && root.right != null) {
            return lca(root.right, left, right);
        } else
            return root;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        BTreePrinter.printNode(root);
        Node ans = lca(root, v1, v2);
        System.out.println(ans.data);
    }

}
