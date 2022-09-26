import java.util.*;

public class morris_traversal {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public static List<Integer> morris_Inorder(TreeNode root) {
        TreeNode curr = root;
        List<Integer> res = new ArrayList<>();

        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) { // thread creation area
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread destroy area (left->rightmost.right = curr)
                    rightMost.right = null;
                    res.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return res;
    }

    public static List<Integer> morris_Preorder(TreeNode root) {
        TreeNode curr = root;
        List<Integer> res = new ArrayList<>();

        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) { // thread creation area
                    rightMost.right = curr;
                    res.add(curr.val);
                    curr = curr.left;
                } else { // thread destroy area (left->rightmost.right = curr)
                    rightMost.right = null;

                    curr = curr.right;
                }
            }
        }

        return res;
    }

    // validate BST (changes made in array refllects everywhere)
    // T:O(N) , S:O(h)
    public static boolean validate(TreeNode root, TreeNode[] prev) {
        if (root == null)
            return true;

        boolean lans = validate(root.left, prev);
        if (!lans)
            return lans;

        if (prev[0] != null && prev[0].val >= root.val)
            return false;

        boolean rans = validate(root.right, prev);
        if (!rans)
            return rans;

        return true;
    }

    //Iterative Inorder
    public boolean isValidBST(TreeNode root) {
        TreeNode prev = null;
        TreeNode curr = root;

        Stack<TreeNode> st = new Stack<>();

        while (curr != null || st.size() > 0) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            if (prev != null && curr.val <= prev.val) return false;
            prev = curr;
            curr = curr.right;
        }

        return true;
    }


    // #98 validate BST using Morris T:O(3N) , S:O(1)
    public static boolean validateMorris(TreeNode root) {
        TreeNode prev = null;
        TreeNode curr = root;

        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (prev != null && prev.val >= curr.val)
                    return false;
                prev = curr;
                curr = curr.right;
            } else { // left !null , get rMost of left
                TreeNode rMost = getRightMostNode(left, curr);
                // 2 cases ->
                if (rMost.right == null) { // make thread
                    rMost.right = curr;
                    curr = curr.left;
                } else {
                    rMost.right = null;
                    if (prev != null && prev.val >= curr.val)
                        return false;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        return true;
    }

    // #230 kth Smallest iterative
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            if (k-- == 1)
                return curr.val;
            curr = curr.right;
        }

        return 0;
    }

    /*
     * kth largest using Morris kth from reverse inorder is kth largest reverse
     * inorde usiong morris
     */

    public TreeNode getLeftMost(TreeNode node, TreeNode curr) {
        while (node.left != null && node.left != curr) {
            node = node.left;
        }
        return node;
    }

    public int kthLargest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode right = curr.right;
            if (right == null) {
                if (k-- == 1)
                    return curr.val;
                curr = curr.left;
            } else {
                TreeNode lMost = getLeftMost(right, curr);
                if (lMost.left == null) {
                    lMost.left = curr;
                    curr = curr.right;
                } else {
                    lMost.left = null;
                    if (k-- == 1)
                        return curr.val;
                    curr = curr.left;
                }
            }
        }
        return -1;
    }

    // BST to DLL
    public static TreeNode bToDLL(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                prev.right = curr;
                curr.left = prev;
                prev = curr;
                curr = curr.right; // imp
            } else {
                TreeNode rMost = getRightMostNode(left, curr);
                if (rMost.right == null) {
                    rMost.right = curr;
                    curr = curr.left; // imp

                } else { // thread break
                    rMost.right = null;
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        TreeNode head = dummy.right;
        dummy.right = head.left = null;
        head.left = prev;
        prev.right = head;
        return head;
    }

    // BST Iterator using Morris
    class BSTIterator {
        TreeNode curr = null;

        public BSTIterator(TreeNode root) {
            curr = root;
        }

        private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        public int next() {
            int rv = -1;
            while (this.curr != null) {
                TreeNode left = this.curr.left;
                if (left == null) {
                    // work - inorder of leaf node
                    rv = this.curr.val;
                    // go right
                    this.curr = this.curr.right;
                    break;

                } else {
                    TreeNode rMost = getRightMostNode(left, this.curr);
                    if (rMost.right == null) {
                        rMost.right = this.curr;
                        this.curr = this.curr.left;
                    } else {
                        rMost.right = null;
                        // i'm at inorder of curr
                        rv = this.curr.val;
                        this.curr = this.curr.right;
                        break;
                    }
                }
            }
            return rv;
        }

        public boolean hasNext() {
            return this.curr != null;
        }
    }

}