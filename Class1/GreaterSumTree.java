package Class1;

// https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/

// Start: 9:40
// End and pass all LC test case: 9:46

// Definition for a binary tree node.
 class TreeNode {
     int val;
     TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

public class GreaterSumTree {
    private Integer sum;

    public TreeNode bstToGst(TreeNode root) {
        // Traverse the tree from right, mid and left which is the opposite direction of in-order traverse
        // Because all right children are greater than left, when we return from the recursion
        // we return sum from current node to all children node
        // Time complexity(O(n))
        // Space O(1)

        sum = 0;
        bstToGstHelper(root);
        return root;
    }

    private void bstToGstHelper(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            sum += root.val;
            root.val = sum;
            return;
        }

        bstToGstHelper(root.right);
        sum += root.val;
        root.val = sum;
        bstToGstHelper(root.left);
    }
}
