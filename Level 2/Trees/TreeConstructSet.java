import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class TreeConstructSet {
	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		TreeNode(int val) {
			this.val = val;
		}
	}

	/*
	 * 105. Construct Binary Tree from Preorder and Inorder Traversal 8 indexes
	 * PRE(L) st-end , IN(L) st-end , PRE(R) st-end , IN(R) st-end Algo: a. st of
	 * pre is root b. find its inorder idx , all to left of idx are left subtree and
	 * similarly right c. find total_ele in left sub-tree using inorder array , pass
	 * preorder root(idx+1) left_ele in LC d. similarly for right call e. base case
	 * if any st(idx) > ei return null
	 */
	public TreeNode buildTree_01(int[] preorder, int[] inorder) {
		return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
	}

	public TreeNode build_01(int[] pre, int[] in, int psi, int pei, int isi, int iei) {
		if (psi > pei || isi > iei)
			return null;

		TreeNode root = new TreeNode(pre[psi]);

		int idx = find(in, pre[psi]);
		int ele = idx - isi;

		root.left = build_01(pre, in, psi + 1, psi + ele, isi, idx - 1);
		root.right = build_01(pre, in, psi + 1 + ele, pei, idx + 1, iei);

		return root;
	}

	public int find_01(int[] arr, int val) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val)
				return i;
		}
		return -1;
	}

	// Using POST_IN -- submit it , TLE!!
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return build(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
	}

	public TreeNode build(int[] post, int[] in, int psi, int pei, int isi, int iei) {
		if (psi > pei || isi > iei)
			return null;

		TreeNode root = new TreeNode(post[pei]);

		int idx = find(in, post[pei]);
		int ele = pei - idx;

		root.right = build(post, in, pei - ele, pei - 1, idx + 1, iei);
		root.left = build(post, in, psi, pei - ele - 1, isi, idx - 1);

		return root;
	}

	public int find(int[] arr, int val) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val)
				return i;
		}
		return -1;
	}

	// From Preorder and PostOrder
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		return build_03(pre, post, 0, pre.length - 1, 0, post.length - 1);
	}

	public TreeNode build_03(int[] pre, int[] post, int preSt, int preEnd, int postSt, int postEnd) {
		if (preSt > preEnd)
			return null;

		TreeNode root = new TreeNode(pre[preSt]);
		if (preSt == preEnd)
			return root;

		int idx = postSt;
		// find
		while (post[idx] != pre[preSt + 1]) {
			idx++;
		}
		int ele = idx - postSt + 1;

		root.left = build_03(pre, post, preSt + 1, preSt + ele, postSt, idx);
		root.right = build_03(pre, post, preSt + ele + 1, preEnd, idx + 1, postEnd - 1);

		return root;
	}

	// Serialize a Binary Tree -> preorder serialize

}