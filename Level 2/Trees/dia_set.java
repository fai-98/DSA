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

    // diameter using O(N^2)
    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

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

    // using two variables in recursion {diameter , height}
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

}