import java.util.*;

import javax.print.attribute.standard.OrientationRequested;

public class dia_set {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        public Node(int data) {
            this.data = data;
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    // diameter using O(N^2)
    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int diameter_01(TreeNode root) {
        if (root == null)
            return 0;
        int ld = diameter_01(root.left);
        int rd = diameter_01(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // using two variables in recursion {diameter , height} - O(N)
    public static int[] diameter_02(TreeNode root) {
        if (root == null)
            return new int[] { -1, -1 };

        int[] lans = diameter_02(root.left);
        int[] rans = diameter_02(root.right);

        int[] myAns = new int[2];

        // compare max of dia in left subtree , dia in rt subtree and dia through self
        myAns[0] = Math.max(Math.max(lans[0], rans[0]), lans[1] + rans[1] + 2);
        myAns[1] = lans[1] + rans[1] + 1;

        return myAns;

    }

    // using static
    public int diameterOfBinaryTree_03(TreeNode root) {
        int[] dia = new int[] { 0 };
        diameter_03(root, dia);
        return dia[0];
    }

    public int diameter_03(TreeNode root, int[] dia) {
        if (root == null)
            return -1;
        int lh = diameter_03(root.left, dia);
        int rh = diameter_03(root.right, dia);
        int ht = Math.max(lh, rh) + 1;
        dia[0] = Math.max(dia[0], lh + rh + 2);
        return ht;
    }

    // 112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        if (root.left == null && root.right == null) {
            return targetSum - root.val == 0 ? true : false;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);

    }

    // 113
    public void pathSum(TreeNode root, int targetSum, List<Integer> smallAns, List<List<Integer>> ans) {
        if (root == null)
            return;

        if (root.left == null && root.right == null && targetSum - root.val == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }

        smallAns.add(root.val);

        pathSum(root.left, targetSum - root.val, smallAns, ans);
        pathSum(root.right, targetSum - root.val, smallAns, ans);

        smallAns.remove(smallAns.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();

        pathSum(root, targetSum, smallAns, ans);
        return ans;

    }

    /*
     * Find the maximum path sum between two leaves of a binary tree
     * https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
     */

    // {max , sum}

    public static class lPair {
        int LTLMax = -(int) 1e9; // leaf to leaf max sum
        int NTLMax = -(int) 1e9; // node to leaf max sum
    }

    public static lPair leaf_to_leaf(TreeNode root) {
        if (root == null)
            return new lPair();

        // special case if leaf , node to leafMax is root.val
        if (root.left == null && root.right == null) {
            lPair base = new lPair();
            base.NTLMax = root.val;
            return base;
        }

        lPair lans = leaf_to_leaf(root.left);
        lPair rans = leaf_to_leaf(root.right);

        lPair myAns = new lPair();

        // set LTL max . if it only has one child , ltl max is still possible candidate
        myAns.LTLMax = Math.max(lans.LTLMax, rans.LTLMax);

        // check valid root - 3 candidates of LTL lans.ltl , rans.ltl and ltl passing
        // through me(node) , confirm is leaves are present both sides
        if (root.left != null && root.right != null) {
            myAns.LTLMax = Math.max(myAns.LTLMax, lans.NTLMax + rans.NTLMax + root.val);
        }

        // set ntl
        myAns.NTLMax = Math.max(lans.NTLMax, rans.NTLMax) + root.val;

        return myAns;

    }

    public static int maxLTLPathSum(TreeNode root) {
        lPair res = leaf_to_leaf(root);
        int ans = res.LTLMax;
        int ans2 = res.NTLMax;

        //to pass on GFG , else logical ans is LTL Max only
        return ans != -(int) 1e9 ? ans : Math.max(ans, ans2);
    }

    // 124. Binary Tree Maximum Path Sum (1/July/2021)
    public class NTNPair {
        int NTNMax = 0;
        int overallMax = -(int) 1e9;
    }

    public int getMax(int... arr) {
        int max = arr[0];
        for (int ele : arr) {
            if (ele > max)
                max = ele;
        }
        return max;
    }

    // use lPair for NTNmax , LTLmax , ans is max(NTN,LTL);

    public NTNPair maxPathSum_124(TreeNode root) {
        NTNPair myAns = new NTNPair();
        if (root == null)
            return myAns;

        NTNPair lans = maxPathSum_124(root.left);
        NTNPair rans = maxPathSum_124(root.right);

        int onSideMax = Math.max(lans.NTNMax, rans.NTNMax) + root.val;
        // candidates for overall max , root alone , left omax , right omax , one side
        // max , from left subtree to right subtree path
        myAns.overallMax = getMax(lans.overallMax, rans.overallMax, root.val, onSideMax,
                                  root.val + lans.NTNMax + rans.NTNMax);

        // Node to node max can be root alone , or left ntn + self, or right ntn + self
        myAns.NTNMax = Math.max(onSideMax, root.val);

        return myAns;

    }

    public int maxPathSum_(TreeNode root) {
        NTNPair res = maxPathSum_124(root);
        return res.overallMax;
    }

    // 968. Binary Tree Cameras

    // camera needed = -1 , hasCamera = 1, already covered = 0
    public int minCameraCover(TreeNode root) {
        int[] cameras = new int[1];
        int ans = dfs(root, cameras);
        if (ans == -1)
            cameras[0]++;
        return cameras[0];
    }

    public int dfs(TreeNode root, int[] cameras) {
        if (root == null)
            return 0; // null already covered

        int lans = dfs(root.left, cameras);
        int rans = dfs(root.right, cameras);

        // any one child needs camera , we have to put
        if (lans == -1 || rans == -1) {
            cameras[0]++;
            return 1;
        }

        // if both child covered , it's better to put camera at parent to cover more
        // real estate
        // tell parent i need camera
        if (lans == 0 && rans == 0) {
            return -1;
        }

        // one of my child has camera , so i'm already covered
        return 0;
    }

    // *************************************** 337. House Robber III
    // *******************************************************

    // done using include exclude principle/maxSum non adj elements (also used in
    // house robber I and II)
    public int houserRobberIII(TreeNode root) {
        // {include , exclude}
        int ans[] = rob_(root);
        return Math.max(ans[0], ans[1]);
    }

    public int[] rob_(TreeNode root) {
        int[] myAns = new int[2];
        if (root == null)
            return myAns;

        int[] lans = rob_(root.left);
        int[] rans = rob_(root.right);

        // include
        myAns[0] = lans[1] + rans[1] + root.val;

        // exclude (if im exc curr , then prev i hv 2 opts , either inc prev one
        // or also exclude prev one and look for the ans in the past )
        myAns[1] = Math.max(lans[0], lans[1]) + Math.max(rans[0], rans[1]);

        return myAns;
    }

    // *************************************** 1372. Longest ZigZag Path in a Binary
    // Tree *****************************************

    public int longestZigZag(TreeNode root) {
        int[] ans = ZigZag(root);
        return ans[2];
    }

    // {left,right,overallMax} , myans left = leftAns(R)+1 , myAns(R)=rightAns(L)+1
    public int[] ZigZag(TreeNode root) {
        if (root == null)
            return new int[] { -1, -1, -1 };

        int[] lans = ZigZag(root.left);
        int[] rans = ZigZag(root.right);
        int[] myAns = new int[3];
        // left
        myAns[0] = lans[1] + 1;
        // right
        myAns[1] = rans[0] + 1;
        // overall max either leftMax , rMax or myAns[left] or myAns right
        myAns[2] = Math.max(Math.max(lans[2], rans[2]), Math.max(myAns[0], myAns[1]));

        return myAns;
    }

    //Static
    static boolean res;
    boolean isSumTree(Node root) {
        res = true;
        getSum(root);
        return res;
    }

    public int getSum(Node root) {

        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.data;
        //for leaf node don't recur , just return from here

        int lSum = getSum(root.left);
        int rSum = getSum(root.right);

        if (root.data != lSum + rSum) {
            res = false;
            return 0;
        }

        return lSum + root.data + rSum;

    }


    //1373. Maximum Sum BST in Binary Tree - Amazon (16ms)
    static int max;

    public class Pair {
        int maxL = -(int) 1e9;
        int minR = (int) 1e9;
        int currSum = 0;
        boolean isBst = true;

        Pair() {}

        Pair(int maxL, int minR, int currSum) {
            this.maxL = maxL;
            this.minR = minR;
            this.currSum = currSum;
        }
    }
    public int maxSumBST(TreeNode root) {
        // {maxSum , sum , isBst ?}  if all nodes -ve , sum = 0
        // if isBst , update max sum
        //max in left subtree < node
        //min in right > node
        max = 0;
        solve(root);
        return max;
    }

    public Pair solve(TreeNode root) {
        if (root == null) {
            Pair base = new Pair();
            return base;
        }

        Pair lPair = solve(root.left);
        Pair rPair = solve(root.right);

        int sum = lPair.currSum + root.val + rPair.currSum;
        int lMax = lPair.maxL;
        int rMin = rPair.minR;
        boolean lBst = lPair.isBst;
        boolean rBst = rPair.isBst;

        Pair myAns = new Pair();

        //if self > max in lst , self < min in rst and also lst & rst are itself bst
        if (root.val > lMax && root.val < rMin && lBst && rBst) {
            //update max here
            max = Math.max(max, sum);
            myAns.currSum = sum;
            myAns.maxL = Math.max(Math.max(lPair.maxL, rPair.maxL), root.val);
            myAns.minR = Math.min(Math.min(lPair.minR, rPair.minR), root.val);
            myAns.isBst = true;
            return myAns;
        } else {
            myAns.isBst = false;
            return myAns;
        }

    }

    //Alternate Code ( 6ms )
    static int max;
    class Pair {
        int mini = (int) 1e9;
        int maxi = -(int) 1e9;
        int sum = 0;

        Pair() {}

        Pair(int mini, int maxi, int sum) {
            this.mini = mini;
            this.maxi = maxi;
            this.sum = sum;
        }
    }
    public int maxSumBST(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }

    public Pair dfs(TreeNode root) {
        if (root == null) {
            return new Pair();  // {min , max , sum}
        }

        Pair lPair = dfs(root.left);
        Pair rPair = dfs(root.right);

        // Current node is greater than max in left AND
        // smaller than min in right, it is a BST.
        Pair myPair = new Pair();

        //why only compare node,l.mini or node,r.maxi
        //we already know that it is BST so all at left are smaller
        //so check smallest of all smaller and compare with self ..similar maxi
        if (root.val > lPair.maxi && root.val < rPair.mini) {
            myPair.mini = Math.min(root.val, lPair.mini);
            myPair.maxi = Math.max(root.val, rPair.maxi);
            myPair.sum = lPair.sum + root.val + rPair.sum;
            //Update max here
            max = Math.max(myPair.sum, max);
            return myPair;
        }

        //if it is not BST send [-INF,INF]->[min,max] so that parent can never be bst
        //for BST node < right min (-INF) , node > lmax (INF) , never possible
        myPair.mini = -(int) 1e9;
        myPair.maxi = (int) 1e9;

        return myPair;
    }


    //Largest BST in Binary Tree (by Size) - Leetcode 333.
    class NodeValue {
        public int maxNode, minNode, maxSize;

        NodeValue(int minNode, int maxNode, int maxSize) {
            this.maxNode = maxNode;
            this.minNode = minNode;
            this.maxSize = maxSize;
        }
    };

    private NodeValue largestBSTSubtreeHelper(TreeNode root) {
        // An empty tree is a BST of size 0.
        if (root == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0); // {min,max,maxSize}
        }

        // Get values from left and right subtree of current tree.
        NodeValue left = largestBSTSubtreeHelper(root.left);
        NodeValue right = largestBSTSubtreeHelper(root.right);

        // Current node is greater than max in left AND smaller than min in right, it is a BST.
        if (left.maxNode < root.val && root.val < right.minNode) {
            // It is a BST.
            return new NodeValue(Math.min(root.val, left.minNode), Math.max(root.val, right.maxNode),
                                 left.maxSize + right.maxSize + 1);
        }

        // Otherwise, return [-inf, inf] so that parent can't be valid BST
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE,
                             Math.max(left.maxSize, right.maxSize));
    }

    public int largestBSTSubtree(TreeNode root) {
        return largestBSTSubtreeHelper(root).maxSize;
    }

    //Alternate
    static class Pair {
        int mini = (int) 1e9;
        int maxi = -(int) 1e9;
        int sz = 0;

        Pair() {}

        Pair(int mini, int maxi, int sz) {
            this.mini = mini;
            this.maxi = maxi;
            this.sz = sz;
        }
    }
    // Return the size of the largest sub-tree which is also a BST
    static int largestBst(Node root) {
        Pair res = dfs(root);
        return res.sz;
    }

    public static Pair dfs(Node root) {
        if (root == null) {
            return new Pair();  // {min , max , sum}
        }

        Pair lPair = dfs(root.left);
        Pair rPair = dfs(root.right);

        // Current node is greater than max in left AND
        // smaller than min in right, it is a BST.
        Pair myPair = new Pair();
        //why only compare node,l.mini or node,r.maxi
        //we already know that it is BST so all at left are smaller
        //so check smallest of all smaller and compare with self ..similar maxi
        if (root.data > lPair.maxi && root.data < rPair.mini) {
            myPair.mini = Math.min(root.data, lPair.mini);
            myPair.maxi = Math.max(root.data, rPair.maxi);
            //update size here
            myPair.sz = lPair.sz + 1 + rPair.sz;
            return myPair;
        }

        //if it is not BST send [-INF,INF]->[min,max] so that parent can never be bst
        //for BST node < right min (-INF) , node > lmax (INF) , never possible
        myPair.mini = -(int) 1e9;
        myPair.maxi = (int) 1e9;
        myPair.sz = Math.max(lPair.sz, rPair.sz); //i'm not BST , still i need to carry curr max ans
        return myPair;
    }
}