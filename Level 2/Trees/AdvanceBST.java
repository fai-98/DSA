
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class AdvanceBST {
	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static int size(TreeNode root) {
		return root == null ? 0 : size(root.left) + size(root.right) + 1;
	}

	public static int height(TreeNode root) {
		return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
	}

	public static int Maximum(TreeNode root) {
		TreeNode curr = root;
		while (curr.right != null) { // rightMost
			curr = curr.right;
		}
		return curr.val;
	}

	public static int Minimum(TreeNode root) {
		TreeNode curr = root;
		while (curr.left != null) { // leftMost
			curr = curr.left;
		}
		return curr.val;
	}

	public static boolean find(TreeNode root, int data) {
		TreeNode curr = root;
		while (curr != null) {
			if (curr.val == data)
				return true;
			else if (curr.val < data)
				curr = curr.right;
			else
				curr = curr.left;
		}

		return false;
	}

	//iterative
	public static ArrayList<TreeNode> rootToNodePath(TreeNode node, int data) {
		ArrayList<TreeNode> res = new ArrayList<>();
		TreeNode curr = node;
		while (node != null) {
			res.add(node);
			if (node.val < data) {
				node = node.right;
			} else if (node.val > data) {
				node = node.left;
			} else break;
		}
		//if ans not exits , clear res.clear()
		return res;
	}

	//iterative , the node where the paths of both nodes splits is the LCA
	public TreeNode lowestCommonAncestor(TreeNode node, int p, int q) {
		TreeNode curr = node;
		while (curr != null) {
			if (p.val < curr.val && q.val < curr.val)
				curr = curr.left;
			else if (p.val > curr.val && q.val > curr.val)
				curr = curr.right;
			else return curr;
		}
	}


//Leetcode 173 , #push all left , #add, pop->push right then all left of right node
	class BSTIterator {
		//addFirst , removeFirst , getFirst
		private LinkedList<TreeNode> st = new LinkedList<>();

		public BSTIterator(TreeNode root) {
			addAllLeft(root);
		}

		private void addAllLeft(TreeNode node) {
			while (node != null) {
				this.st.addFirst(node);
				node = node.left;
			}
		}

		public int next() {
			TreeNode rNode = this.st.getFirst();
			addAllLeft(rNode.right);
			return rNode.val;
		}

		public boolean hasNext() {
			return this.st.size() != 0;
		}
	}

}