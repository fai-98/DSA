import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class tree_construction_set {
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

	// 297. Serialize and Deserialize Binary Tree -> preorder serialize
	public String serialize_bt(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serialize_(root, sb);
		return sb.toString();
	}

	public void serialize_(TreeNode root, StringBuilder sb) {
		if (root == null) {
			sb.append("null ");
			return;
		}
		sb.append(root.val + " ");
		serialize_(root.left, sb);
		serialize_(root.right, sb);
		return;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize_bt(String data) {
		// System.out.println(data);
		String[] pre = data.split(" ");
		return build(pre, new int[] { 0 });

	}

	public TreeNode build(String[] pre, int[] idx) {
		if (idx[0] == pre.length || pre[idx[0]].equals("null")) {
			idx[0]++;
			return null;
		}

		TreeNode root = new TreeNode(Integer.parseInt(pre[idx[0]++]));

		root.left = build(pre, idx);
		root.right = build(pre, idx);

		return root;
	}

	// 297 Queue Implementation
	public TreeNode deserialize_que(String data) {
		String[] pre = data.split(",");
		Queue<String> q = new LinkedList<>();
		q.addAll(Arrays.asList(pre));
		return build(q);
	}

	public TreeNode build(Queue<String> q) {
		if (q.isEmpty())
			return null;
		String ele = q.poll();
		if (ele.equals("#")) {
			return null;
		}

		int val = Integer.parseInt(ele);
		TreeNode node = new TreeNode(val);

		node.left = build(q);
		node.right = build(q);

		return node;
	}

	// 449. Serialize and Deserialize BST
	// dont use inorder serialize , it loses the exact same config
	// use preOrder serialise + BST property (using lower bound and upper b )
	// init.. lb = -INF , up = INF
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serialize_preOrder(sb, root);
		return sb.toString();
	}

	public void serialize_preOrder(StringBuilder sb, TreeNode root) {
		if (root == null)
			return;
		sb.append(root.val + ",");
		serialize_preOrder(sb, root.left);
		serialize_preOrder(sb, root.right);
	}

	// Decodes your encoded data to tree.
	// Simple Construct BST from Pre gives exact config , init.. lb = -INF , ub =
	// INF , use code of construct bst from inorder
	// if use Queue instead of Array , static arr idx is avoided
	public TreeNode deserialize(String data) {
		if (data.isEmpty())
			return null;
		String[] pre = data.split(",");
		int[] idx = new int[1];
		int lb = -(int) 1e9, ub = (int) 1e9;
		return deserialize_(pre, lb, ub, idx);
	}

	public TreeNode deserialize_(String[] pre, int lb, int ub, int[] idx) {
		if (idx[0] == pre.length) {
			return null;
		}

		int val = Integer.parseInt(pre[idx[0]]);

		if (val < lb || val > ub)
			return null;

		idx[0]++;

		TreeNode node = new TreeNode(val);

		node.left = deserialize_(pre, lb, val, idx);
		node.right = deserialize_(pre, val, ub, idx);

		return node;
	}

}