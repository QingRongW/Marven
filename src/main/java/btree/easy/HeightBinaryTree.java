import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;
/*

class NodeB {
    Node left;
    Node right;
    int data;

    NodeB(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class SolutionHBT {


    public static int height(Node root) {
        // Write your code here.

        // height by Node Data
        HashMap<Integer, Integer> heightByNodeData = new HashMap<Integer, Integer>();
        // queue of the remaining nodes
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(root);
        heightByNodeData.put(root.data, 0);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            Node leftChild = cur.left;
            Node rightChild = cur.right;
            Integer height = heightByNodeData.get(cur.data);
            if (leftChild != null && rightChild != null) {
                queue.add(rightChild);
                heightByNodeData.put(leftChild.data, height + 1);
                heightByNodeData.put(rightChild.data, height + 1);
                cur = leftChild;
                queue.add(cur);
            } else if (leftChild == null && rightChild != null) {
                heightByNodeData.put(rightChild.data, height + 1);
                cur = rightChild;
                queue.add(cur);
            } else if (leftChild != null && rightChild == null) {
                heightByNodeData.put(leftChild.data, height + 1);
                cur = leftChild;
                queue.add(cur);
            }
        }
        Integer heightMax = -1;
        for (Entry node : heightByNodeData.entrySet()) {
            if ((Integer) node.getValue() > heightMax) {
                heightMax = (Integer) node.getValue();
            }
        }
        return heightMax;
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
        System.out.println("node number");
        int t = scan.nextInt();
        Node root = null;
        while (t > 0) {
            System.out.println("node data");

            int data = scan.nextInt();
            root = insert(root, data);
            t--;
        }
        scan.close();
        int height = height(root);
        System.out.println("height is " + height);
    }
}



*/