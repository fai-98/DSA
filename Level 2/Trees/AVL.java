public class AVL {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // =====================================================================================================================//

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        add(root, val);
        return root;
    }

    public void add(TreeNode root, int val) {
        if (val <= root.val) {
            if (root.left == null) {
                TreeNode node = new TreeNode(val);
                root.left = node;
                return;
            } else {
                add(root.left, val);
            }
        } else {
            if (root.right == null) {
                TreeNode node = new TreeNode(val);
                root.right = node;
                return;
            } else {
                add(root.right, val);
            }
        }
        return;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        // find code
        if (root.val > key)
            root.left = deleteNode(root.left, key);
        else if (root.val < key)
            root.right = deleteNode(root.right, key);
        // now val found
        else {
            if (root.left == null && root.right == null)
                return null;
            // if only single child
            else if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // both children
            else {
                int lMax = max(root.left);
                root.val = lMax;
                root.left = deleteNode(root.left, lMax);
                return root;
            }
        }

        return root;
    }

    public int max(TreeNode node) {
        if (node.right != null)
            return max(node.right);
        return node.val;
    }

    // =====================================================================================================================//
}
