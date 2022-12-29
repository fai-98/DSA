/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max;
    public int maxAncestorDiff(TreeNode root) {
        max = -(int)1e9;
        fun(root,root.val,root.val);
        return max;
    }

    public void fun(TreeNode root, int oMin, int oMax){
        if(root == null) return;
        fun(root.left, Math.min(oMin,root.val), Math.max(oMax,root.val));
        fun(root.right, Math.min(oMin,root.val), Math.max(oMax,root.val));
        max = Math.max(max,Math.max(Math.abs(root.val-oMin),Math.abs(root.val-oMax)));
        return;
    }
}