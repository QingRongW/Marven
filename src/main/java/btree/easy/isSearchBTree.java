package btree.easy;

import common.Node;

public class isSearchBTree {

    boolean checkBST(Node root) {
        if (root == null) {
            return true;
        } else {
            if (root.left != null && root.left.data > root.data) {
                return false;
            }
            if (root.right != null && root.right.data < root.data) {
                return false;
            }
            return checkBST(root.left) && checkBST(root.right);
        }
    }
}
