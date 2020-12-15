package ds.trees.bst;

import java.util.Queue;
import java.util.LinkedList;

/*
Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the
tree is now the root of the tree, and every node has no left child and only one right child.
*/

public class ConvertBSTLinkedList {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Iterative Solution
     */
    public static TreeNode increasingBST(TreeNode root) {
        if (root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                queue.add(curr);
                curr = curr.right;
            } else {
                TreeNode pred = curr.left;

                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    queue.add(curr);
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }

        TreeNode out = queue.peek();

        while (!queue.isEmpty()) {
            TreeNode t = queue.remove();
            t.right = queue.isEmpty() ? null : queue.peek();
            t.left = null;
        }

        return out;
    }

    static TreeNode curr;

    /**
     * Recursive Solution
     */
    public static TreeNode increasingBSTRecursive(TreeNode root) {
        TreeNode ans = new TreeNode(0);
        curr = ans;
        inorder(root);
        return ans.right;
    }

    /**
     * Private helper function traversing the tree inorder
     * 
     * @param node
     */
    private static void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        // Nodes are visited in inorder fashion, which is what we want so simply append
        // to curr and update curr
        node.left = null;
        curr.right = node;
        curr = node;
        inorder(node.right);
    }
}