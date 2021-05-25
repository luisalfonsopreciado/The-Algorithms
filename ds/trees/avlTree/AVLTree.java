package ds.trees.avlTree;

import ds.trees.TreePrinter;
import ds.trees.TreePrinter.PrintableBTreeNode;

public class AVLTree {
    public static class TreeNode implements PrintableBTreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int height;
        int bf; // Balance factor

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public PrintableBTreeNode getLeft() {
            return left;
        }

        @Override
        public PrintableBTreeNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return Integer.toString(val);
        }
    }

    TreeNode root;
    int nodeCount;

    public AVLTree() {
        root = null;
        nodeCount = 0;
    }

    public void insert(int val) {
        // Only insert new values
        if (!contains(root, val)) {
            root = insert(root, val);
            nodeCount++;
        }
        // Value is already in the tree
    }

    public void remove(int val) {
        // Only remove existing vaues
        if (contains(root, val)) {
            root = remove(root, val);
            nodeCount--;
        }
        // Value is not in tree
    }

    private TreeNode remove(TreeNode node, int val) {
        if (node == null)
            return null;

        if (node.val > val) {
            node.left = remove(node.left, val);
        } else if (node.val < val) {
            node.right = remove(node.right, val);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // Choose to remove from left subtree
                if (node.left.height > node.right.height) {

                    // Swap the value of the successor into the node.
                    int successorValue = findMax(node.left);
                    node.val = successorValue;

                    // Find the largest node in the left subtree.
                    node.left = remove(node.left, successorValue);

                } else {

                    // Swap the value of the successor into the node.
                    int successorValue = findMin(node.right);
                    node.val = successorValue;

                    // Go into the right subtree and remove the leftmost node we
                    // found and swapped data with. This prevents us from having
                    // two nodes in our tree with the same value.
                    node.right = remove(node.right, successorValue);
                }
            }
        }

        update(node);

        return balance(node);
    }

    // Helper method to find the leftmost node (which has the smallest value)
    private int findMin(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node.val;
    }

    // Helper method to find the rightmost node (which has the largest value)
    private int findMax(TreeNode node) {
        while (node.right != null)
            node = node.right;
        return node.val;
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null)
            return new TreeNode(val);

        if (node.val > val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }

        // Update balance factor
        update(node);

        // Rebalance the tree
        return balance(node);
    }

    private void update(TreeNode node) {
        int lh = -1, rh = -1; // Left and Right height

        if (node.left != null) {
            lh = node.left.height;
        }
        if (node.right != null) {
            rh = node.right.height;
        }

        // Update height
        node.height = 1 + Math.max(lh, rh);
        // Update balance factor
        node.bf = rh - lh;
    }

    private TreeNode balance(TreeNode root) {
        // Left Heavy
        if (root.bf == -2) {
            // left left case
            if (root.left.bf <= 0) {
                return leftLeftCase(root);
            } else {
                // left right case
                return leftRightCase(root);
            }
        }

        // Right Heavy
        if (root.bf == 2) {
            // right right case
            if (root.right.bf >= 0) {
                return rightRightCase(root);
            } else {
                // right left case
                return rightLeftCase(root);
            }
        }

        // Tree is already balanced, bf is {-1, 0, +1}
        return root;
    }

    private TreeNode rightLeftCase(TreeNode root) {
        root.right = rightRotation(root.right);
        return rightRightCase(root);
    }

    private TreeNode rightRightCase(TreeNode root) {
        return leftRotation(root);
    }

    private TreeNode leftRightCase(TreeNode root) {
        root.left = leftRotation(root.left);
        return rightRightCase(root);
    }

    private TreeNode leftLeftCase(TreeNode root) {
        return rightRotation(root);
    }

    private TreeNode rightRotation(TreeNode root) {
        TreeNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        // Update height and balance factors
        update(root);
        update(newRoot);

        return newRoot;
    }

    private TreeNode leftRotation(TreeNode root) {
        TreeNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;

        // Update height and balance factors
        update(root);
        update(newRoot);

        return newRoot;
    }

    public boolean contains(TreeNode node, int val) {
        if (node == null)
            return false;

        if (node.val == val)
            return true;

        if (node.val > val) {
            return contains(node.left, val);
        }

        return contains(node.right, val);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        for (int i = 0; i < 9; i++) {
            tree.insert(i);
        }
        TreePrinter.print(tree.root);
        tree.remove(3);
        TreePrinter.print(tree.root);
    }
}