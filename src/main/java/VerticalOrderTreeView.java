import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

// Java program for printing vertical order of a given binary tree
public class VerticalOrderTreeView {
    // Utility function to store vertical order in map 'm'
    // 'hd' is horizontal distance of current node from root.
    // 'hd' is initially passed as 0
    static void getVerticalOrder(Node node, int hd,
                                 TreeMap<Integer, Vector<Integer>> m) {
        if (node == null) {
            return;
        }
        Vector values = m.get(hd);
        int data = node.data;
        if (values == null) {
            values = new Vector();
        }
        values.add(data);
        m.put(hd, values);
        getVerticalOrder(node.left, hd - 1, m);
        getVerticalOrder(node.right, hd + 1, m);
    }

    // The main function to print vertical order of a binary tree
    // with the given root
    static void printVerticalOrder(Node root) {
        // Create a map and store vertical order in map using
        // function getVerticalOrder()
        TreeMap<Integer, Vector<Integer>> m = new TreeMap<>();
        int hd = 0;
        getVerticalOrder(root, hd, m);

        // Traverse the map and print nodes at every horigontal
        // distance (hd)
        for (Entry<Integer, Vector<Integer>> entry : m.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    // Driver program to test above functions
    public static void main(String[] args) {

        // TO DO Auto-generated method stub
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        root.right.right.right = new Node(9);
        System.out.println("Vertical Order traversal is");
        printVerticalOrder(root);
    }
}