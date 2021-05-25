# AVL Tree

AVL is a type of balanced binary search trees

Insert Search Delete Space

## AVL Insertion

4 General Cases:

1. Left left heavy: Perform right rotation.

2. Left right heavy: Perform left rotation on left child followed by a right rotation on root node.

3. Right right heavy: Perform left rotation.

4. Right left heavy: Perform right rotation on right child followed by a left rotation on root node.

## AVL Removal

Similar to Insertion, there exist 4 General Cases for removal for an AVL tree:

1. Node to remove is a leaf: The simplest case, we can simply remove this node without any side effects.

2. Node to remove has a right subtree but no left: have the predecessor left pointer point to the right subtree root.

3. Node to remove has a left subtree but not right: have the predecessor right pointer point to the left subtree root.

4. Node to remmove has both a left and right subtree: The successor can either be the largest value in the left subtree or the smallest value in the right subtree.
