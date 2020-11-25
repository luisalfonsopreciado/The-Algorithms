package ds.trees.traversal;

import java.util.Stack;

public class Preorder {
    TreeNode root;

    public Preorder() {
        root = new TreeNode(-1);
    }

    public void recursiveTraversal() {
        recursiveUtil(root);
        System.out.println();
    }

    public void recursiveUtil(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.val + " ");
        recursiveUtil(node.left);
        recursiveUtil(node.right);
    }

    public void iterativeTraversal() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.add(curr);
                System.out.print(curr.val + " ");
                curr = curr.left;
            }
            curr = stack.pop();
            curr = curr.right;
        }

        System.out.println();
    }

    public void morrisTraversal() {
        TreeNode curr = root;

        while(curr != null){
            if(curr.left == null){
                System.out.print(curr.val + " ");
                curr = curr.right;
            }else{
                TreeNode pred = curr.left;
                while(pred.right != null && pred.right != curr){
                    pred = pred.right;
                }

                if(pred.right == null){
                    System.out.print(curr.val + " ");
                    pred.right = curr;
                    curr = curr.left;
                }else{
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Preorder pre = new Preorder();
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
