package trees.traversal;

import java.util.Stack;

public class PostOrder {
    TreeNode root;

    public PostOrder() {
        root = new TreeNode(-1);
    }

    public void recursiveTraversal() {
        recursiveUtil(root);
        System.out.println();
    }

    public void recursiveUtil(TreeNode node) {
        if (node == null)
            return;
        recursiveUtil(node.left);
        recursiveUtil(node.right);
        System.out.print(node.val + " ");
    }

    public void iterativeTraversal() {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode curr = stack1.pop();
            if (curr.left != null) {
                stack1.add(curr.left);
            }
            if (curr.right != null) {
                stack1.add(curr.right);
            }
            stack2.push(curr);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }

        System.out.println();
    }

    public void morrisTraversal() {
        if (root == null)
            return;

        TreeNode current = new TreeNode(-1), pre = null;
        TreeNode prev = null, succ = null, temp = null;
        current.left = root;

        while (current != null) {

            // Go to the right child if current does not have a left child

            if (current.left == null) {
                current = current.right;
            }

            else {

                // Traverse left child
                pre = current.left;

                // Find the right most child in the left subtree
                while (pre.right != null && pre.right != current)
                    pre = pre.right;

                if (pre.right == null) {

                    // Make current as the right child of the right most node
                    pre.right = current;

                    // Traverse the left child
                    current = current.left;
                }

                else {
                    pre.right = null;
                    succ = current;
                    current = current.left;
                    prev = null;

                    // Traverse along the right subtree to the right-most child

                    while (current != null) {
                        temp = current.right;
                        current.right = prev;
                        prev = current;
                        current = temp;
                    }

                    // Traverse back fromright most child to current's left child node
                    while (prev != null) {

                        System.out.print(prev.val + " ");
                        temp = prev.right;
                        prev.right = current;
                        current = prev;
                        prev = temp;
                    }

                    current = succ;
                    current = current.right;
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        PostOrder pre = new PostOrder();
        pre.root.val = 10;
        pre.root.left = new TreeNode(5);
        pre.root.left.left = new TreeNode(2);
        pre.root.left.right = new TreeNode(7);
        pre.root.right = new TreeNode(15);
        pre.root.right.right = new TreeNode(30);

        pre.recursiveTraversal();
        pre.iterativeTraversal();
        pre.morrisTraversal();
    }
}
