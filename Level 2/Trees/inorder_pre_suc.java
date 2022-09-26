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


	//kth Largest in BST

	public int kthLargest(TreeNode root, int k) {
		TreeNode curr = root;
		while (curr != null) {
			TreeNode right = curr.right;
			if (right == null) {
				if (k-- == 1)
					return curr.data;
				curr = curr.left;
			} else {
				TreeNode lMost = getLeftMost(right, curr);
				if (lMost.left == null) {
					lMost.left = curr;
					curr = curr.right;
				} else {
					lMost.left = null;
					if (k-- == 1)
						return curr.data;
					curr = curr.left;
				}
			}
		}
		return -1;
	}

	// 98. Validate Binary Search Tree
	boolean checkBST(Node root) {
		//MORRIS TRAVERSAL S: O(1) , T:O(3N)
		Node prev = null;
		Node curr = root;

		while (curr != null) {
			Node left = curr.left;
			if (left == null) {
				if (prev != null && prev.data >= curr.data)
					return false;
				prev = curr;
				curr = curr.right;
			} else { // left !null , get rMost of left
				Node rMost = getRightMostNode(left, curr);
				// 2 cases ->
				if (rMost.right == null) { // make thread
					rMost.right = curr;
					curr = curr.left;
				} else {
					rMost.right = null;
					if (prev != null && prev.data >= curr.data)
						return false;
					prev = curr;
					curr = curr.right;
				}
			}
		}

		return true;
	}

	Node getRightMostNode(Node node, Node curr) {
		while (node.right != null && node.right != curr) {
			node = node.right;
		}
		return node;
	}
}