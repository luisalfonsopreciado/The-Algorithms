package ds.trees.cartesianTree;

import java.util.Stack;

/** Cartesian Tree Implementation */
public class CartesianTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    int[] arr;

    public CartesianTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * Construct a Cartesian Tree representation of an input array
     * 
     * @param arr - input array
     * @return root - TreeNode reference to root of Cartesian Tree
     */
    public static TreeNode constructCartesianTreeNaive(int[] arr) {
        return constructCartesianTreeNaiveUtil(arr, 0, arr.length - 1);
    }

    /**
     * Constructs a Cartesian Tree of an input array betwee the range lo and hi
     * (inclusive)
     * 
     * @param arr - input array
     * @param lo  - low pointer
     * @param hi  - high pointer
     * @return root - TreeNode reference to root of Cartesian Tree
     */
    public static TreeNode constructCartesianTreeNaiveUtil(int[] arr, int lo, int hi) {
        if (lo > hi)
            return null;

        int minIndex = lo, minVal = arr[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (arr[i] < minVal) {
                minIndex = i;
                minVal = arr[i];
            }
        }

        TreeNode root = new TreeNode(minVal);
        root.left = constructCartesianTreeNaiveUtil(arr, lo, minIndex - 1);
        root.right = constructCartesianTreeNaiveUtil(arr, minIndex + 1, hi);
        return root;
    }

    public static void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    /**
     * Constructs a Cartesian Tree given an input array
     * 
     * @param arr - TreeNode reference to root of Cartesian Tree
     */
    public static TreeNode constructCartesianTree(int[] arr) {
        if (arr.length == 0)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(new TreeNode(arr[0]));
        
        for (int i = 1; i < arr.length; i++) {
            TreeNode newNode = new TreeNode(arr[i]);
            TreeNode lastPopped = null;
            while (!stack.isEmpty() && stack.peek().val > arr[i]) {
                lastPopped = stack.pop();
            }

            newNode.left = lastPopped;
            if (!stack.isEmpty()) {
                stack.peek().right = newNode;
            }
            stack.push(newNode);

        }

        // Find the root
        while (stack.size() != 1) {
            stack.pop();
        }

        return stack.pop();
    }

    /**
     * Constructs a Cartesian Tree number given an input array
     * 
     * @param arr - TreeNode reference to root of Cartesian Tree
     * @return String - Bit String representation of cartesian tree
     */
    public static String buildCartesianTreeNumber(int[] arr) {
        StringBuilder str = new StringBuilder();
        if (arr.length == 0)
            return str.toString();
        Stack<Integer> stack = new Stack<>();
        str.append("1");
        stack.push(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];

            while (!stack.isEmpty() && stack.peek() > arr[i]) {
                stack.pop();
                str.append("0");
            }

            str.append("1");
            stack.push(curr);

        }

        while (stack.size() != 1) {
            stack.pop();
            str.append("0");
        }

        str.append("0");

        return str.toString();
    }

    public static void main(String[] args) {
        int[] arr = { 93, 84, 33, 64, 62, 83, 63, 58 };
        TreeNode root = constructCartesianTree(arr);
        inorder(root);
        int[] arr2 = { 66, 13, 59 };
        String num = buildCartesianTreeNumber(arr2);
        System.out.println(num);
    }
}
