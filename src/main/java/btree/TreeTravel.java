package btree;

import java.util.LinkedList;
import java.util.Scanner;

class NodeTree {
    NodeTree left;
    NodeTree right;
    int data;

    NodeTree(int data) {
        this.data = data;
        left = null;
        right = null;
        right = null;
    }
}

class SolutionTree {

    /*
    
    class Node 
        int data;
        Node left;
        Node right;
    */
    public static void levelOrder(NodeTree root) {

        LinkedList<NodeTree> queue = new LinkedList<NodeTree>();
        queue.add(root);
        while (!queue.isEmpty()) {
            NodeTree cur = queue.remove();
            System.out.print(cur.data + " ");
            NodeTree left = cur.left;
            NodeTree right = cur.right;
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

    public static NodeTree insert(NodeTree root, int data) {
        if (root == null) {
            return new NodeTree(data);
        } else {
            NodeTree cur;
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
        NodeTree root = null;
        while (t > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
            t--;
        }
        scan.close();
        levelOrder(root);
    }
}