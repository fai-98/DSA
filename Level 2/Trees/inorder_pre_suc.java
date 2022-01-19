import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class inorder_pre_suc {
	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		TreeNode(int val) {
			this.val = val;
		}
	}

	/*
	 * Find Inorder Predecessor and Successor of node in Binary Tree - T:O(n) ,
	 * S:O(N) Binary Saerch Tree - T:O(logn) , S:O(1)
	 */
	public static class allSolPair {
		TreeNode pred = null;
		TreeNode suc = null;
		TreeNode prev = null;

		int ceil = (int) 1e9;
		int floor = -(int) 1e9;
	}

	// for pred suc , intially pred=suc=pre=null , find ceil and floor
	public static void allSolution(TreeNode node, int data, allSolPair pair) {
		if (node == null)
			return;

		if (node.val < data) {
			pair.floor = Math.max(pair.floor, node.val);
		}

		if (node.val > data) {
			pair.ceil = Math.min(pair.ceil, node.val);
		}

		allSolution(node.left, data, pair);

		if (node.val == data) {
			pair.pred = pair.prev;
		}
		if (pair.pred != null && pair.prev.val == data) {
			pair.suc = node;
		}

		pair.prev = node;

		allSolution(node.right, data, pair);
	}

	/*
	 * find inorder pred successor of BST if data lies to left - this node is
	 * potential successor data found!! data.right exits - Suc = leftmost of right
	 * if data lies to right - this node is potential predecessor data found!!
	 * data.left exits - Pred = rightmost of left
	 */

	public TreeNode getLeftMost(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	public TreeNode getRightMost(TreeNode root) {
		while (root.right != null) {
			root = root.right;
		}
		return root;
	}

	// T: O(logN) binary search like - we directly(iteratively) reach the target ,
	// S: O(1);
	public void getPS(TreeNode root, int data) {

		TreeNode curr = root, pred = null, succ = null;
		while (curr != null) {

			if (curr.val == data) {
				if (curr.right != null) {
					TreeNode node = getLeftMost(curr.right);
					succ = node;
				}
				if (curr.left != null) {
					TreeNode node = getRightMost(curr.left);
					pred = node;
				}
				break;
			} else if (curr.val > data) {
				succ = curr;
				curr = curr.left;
			} else {
				pred = curr;
				curr = curr.right;
			}

		}
		System.out.println("PRED-> " + pred.val + "  " + "SUCC-> " + succ.val);

	}
}