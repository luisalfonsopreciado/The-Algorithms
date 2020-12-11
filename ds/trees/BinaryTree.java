package ds.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    /**
     * Inner TreeNode class, defines one node in the tree.
     * 
     * @param node
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * The root of the Binary Tree
     */
    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    /**
     * Perform an Inorder Traversal given the root of a Binary Tree
     * 
     * @param node
     */
    public static void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    /**
     * 
     * Converts an array of Integers into a tree
     * 
     * @param arr
     */
    public static TreeNode arrayToTree(Integer[] arr) {
        if (arr.length == 0)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.add(root);

        for (int i = 1; i < arr.length; i++) {
            TreeNode curr = queue.remove();
            if (arr[i] != null) {
                TreeNode left = new TreeNode(arr[i]);
                curr.left = left;
                queue.add(left);
            }

            if (i + 1 < arr.length && arr[i + 1] != null) {
                TreeNode right = new TreeNode(arr[i + 1]);
                curr.right = right;
                queue.add(right);
            }

            if (i + 1 < arr.length)
                i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[13];
        arr[0] = 5;
        arr[1] = 3;
        arr[2] = 6;
        arr[3] = 2;
        arr[4] = 4;
        arr[5] = null;
        arr[6] = 8;
        arr[7] = 1;
        arr[8] = null;
        arr[9] = null;
        arr[10] = null;
        arr[11] = 7;
        arr[12] = 9;

        TreeNode root = arrayToTree(arr);

        inorder(root);
        System.out.println();

        arr = new Integer[3];
        arr[0] = 5;
        arr[1] = 1;
        arr[2] = 7;

        root = arrayToTree(arr);

        inorder(root);
        System.out.println();

    }
}
