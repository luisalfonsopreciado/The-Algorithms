package algorithms.tree;

import java.util.Queue;

import java.util.LinkedList;

/*
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 
Example 1:

Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 
Constraints:

The number of nodes in the given tree is less than 4096.
-1000 <= node.val <= 1000 
 */

public class PopulatingNextRightPointer {
    class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode next;

        TreeNode(int val) {
            this.val = val;
            right = left = next = null;
        }
    }

    public TreeNode connect(TreeNode root) {
        if (root == null)
            return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                curr.next = i == size - 1 ? null : queue.peek();
                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }
        }

        return root;
    }

    /**
     * A (ugly) Recursive Solution
     */
    public TreeNode connectDFS(TreeNode root) {
        traverse(root);
        return root;
    }

    public void traverse(TreeNode node) {
        if (node == null)
            return;
        if (node.left != null) {
            node.left.next = node.right;
            if (node.left.right != null) {
                node.left.right.next = node.right.left;
                setMiddle(node.left.right.right, node.right.left.left);
            }
        }
        traverse(node.left);
        traverse(node.right);
    }

    public void setMiddle(TreeNode left, TreeNode right) {
        if (left == null)
            return;
        left.next = right;
        setMiddle(left.right, right.left);
    }
}
