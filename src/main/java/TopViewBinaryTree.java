import java.util.*;

/**
 * Class used to print the topView of the BinaryTree from:
 * https://www.hackerrank.com/challenges/tree-top-view/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour
 * -campaign
 */
public class TopViewBinaryTree {

    /**
     * Main method of this class to print the top view
     *
     * @param root
     */
    public static void topView(Node root) {


    }

    /**
     * Method to insert a node.
     *
     * @param root
     * @param data
     * @return
     */
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

    /**
     * Main method, entrance of the class.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }
}
