package ds.trees.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrder {

    TreeNode root;

    public LevelOrder() {
        root = new TreeNode(-1);
    }

    public void iterativeTraversal(){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            System.out.print(curr.val + " ");
            if(curr.left != null){
                queue.add(curr.left);
            }
            if(curr.right != null){
                queue.add(curr.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LevelOrder in = new LevelOrder();
        in.root.val = 10;
        in.root.left = new TreeNode(5);
        in.root.left.left = new TreeNode(2);
        in.root.left.right = new TreeNode(7);
        in.root.right = new TreeNode(15);
        in.root.right.right = new TreeNode(30);

        in.iterativeTraversal();
    }
}
