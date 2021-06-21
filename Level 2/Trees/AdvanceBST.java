
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

	//contruction series ``````````````````````````````````````````````````````````````````````````````````````````````

	//construct from inorder L-N-R
	public static TreeNode bstFromInorder(int[] inorder) {
		return bstFromInorder_(inorder, 0, inorder.length - 1);

	}

	public static TreeNode bstFromInorder_(int[] inorder , int st , int end) {
		if (st > end)return null;
		int mid = st + (end - st) / 2;

		TreeNode root = new TreeNode(inorder[mid]);

		root.left = bstFromInorder_(inorder, st, mid - 1);
		root.right = bstFromInorder_(inorder, mid + 1, end);

		return root;
	}

	//construct from preorder N-L-R using ranges
	public static TreeNode bstFromPreorder(int[] preorder) {
		return bstFromPreorder_(preorder, -(int)1e9, (int)1e9);

	}

	static int idx = 0 ;
	public static TreeNode bstFromPreorder_(int[] preorder , int l , int r) {
		if (idx == preorder.length || preorder[idx] < l || preorder[idx] > r)
			return null;

		TreeNode node = new TreeNode(preorder[idx++]);


		node.left = bstFromPreorder_(preorder, l, node.val);
		node.right = bstFromPreorder_(preorder, node.val, r);

		return node;

	}

	// in post-order same technique , idx start from postorder.length-1 to 0
	// call L-R-Root right call first , then left call
	//in traversing from back root comes first -> right -> left
	static int idx;
	public static TreeNode bstFromPostorder(int[] postorder) {
		idx = postorder.length - 1;
		return bstFromPostorder_(postorder, -(int)1e9, (int)1e9);

	}


	public static TreeNode bstFromPostorder_(int[] postorder , int l , int r) {
		if (idx < 0 || postorder[idx] < l || postorder[idx] > r)
			return null;

		TreeNode node = new TreeNode(postorder[idx--]);


		node.right = bstFromPostorder_(postorder, node.val, r);
		node.left = bstFromPostorder_(postorder, l, node.val);

		return node;

	}

	//BST From Level Order

	public static class bstPair{
		TreeNode node = null;
		int lr = 0;
		int rr = 0;

		bstPair() {}

		bstPair(TreeNode node , int lr , int rr ) {
			this.node = node;
			this.lr = lr;
			this.rr = rr;
		}
	}


	static int idx;
	public static TreeNode bstFromLevelOrder(int[] levelorder) {
		idx = 0 ;
		return bstFromPostorder_(levelorder, -(int)1e9, (int)1e9);

	}


	public static TreeNode bstFromLevelOrderr_(int[] levelorder , int l , int r) {
		if (idx == levelorder.length || levelorder[idx] < l || levelorder[idx] > r)
			return null;


		TreeNode node = find(levelorder, idx++, l, r);

		node.right = bstFromLevelOrder_(levelorder, node.val, r);
		node.left = bstFromLevelOrder_(levelorder, l, node.val);

		return node;

	}




}