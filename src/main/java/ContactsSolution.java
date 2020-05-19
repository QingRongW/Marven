import java.util.*;

class NodeContact {
    int count;
    NodeContact[] children;

    NodeContact() {
        this.count = 0;
        this.children = new NodeContact[26];
        Arrays.fill(children, null);
    }

    public void insert(NodeContact current, String value) {

        for (char c : value.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new NodeContact();
            }

            current.children[index].count++;
            current = current.children[index];
        }
    }
}

class SolutionFinal {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        NodeContact trie = new NodeContact();
        int n = scan.nextInt();

        while (n-- > 0) {

            String operation = scan.next();
            String value = scan.next();

            // Insertion Operation
            if (operation.equals("add")) {
                trie.insert(trie, value);
            } else { // Search Operation
                NodeContact currentNode = trie;

                // Traverse through each level
                for (char c : value.toCharArray()) {
                    // Maintain a reference to the Node matching the char for that level
                    currentNode = currentNode.children[c - 'a'];

                    if (currentNode == null) {
                        break;
                    }
                }

                // Print the number of results
                System.out.println((currentNode != null) ? currentNode.count : 0);
            }
        }
        scan.close();
    }
}