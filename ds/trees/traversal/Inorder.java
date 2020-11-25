package ds.trees.traversal;

import java.util.Stack;

public class Inorder {

    TreeNode root;

    public Inorder() {
        root = new TreeNode(-1);
    }

    public void recursiveTraversal(){
        recursiveUtil(root);
        System.out.println();
    }

    public void recursiveUtil(TreeNode node){
        if(node == null) return;

        recursiveUtil(node.left);
        System.out.print(node.val + " ");
        recursiveUtil(node.right);
    }

    public void iterativeTraversal() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.print(curr.val + " ");
            curr = curr.right;
        }

        System.out.println();
    }

    public void morrisTraversal() {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
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
                    System.out.print(curr.val + " ");
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Inorder in = new Inorder();
        in.root.val = 10;
        in.root.left = new TreeNode(5);
        in.root.left.left = new TreeNode(2);
        in.root.left.right = new TreeNode(7);
        in.root.right = new TreeNode(15);
        in.root.right.right = new TreeNode(30);

        in.morrisTraversal();
        in.iterativeTraversal();
        in.recursiveTraversal();
    }
}
