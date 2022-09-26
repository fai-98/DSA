public class binary_tree_basic {

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
		return root == null ? -(int) 1e9 : Math.max(Math.max(Maximum(root.left), Maximum(root.right)), root.val);
	}

	public static boolean find(TreeNode root, int data) {
		if (root == null)
			return false;
		if (root.val == data)
			return true;

		return find(root.left, data) || find(root.right, data);
	}


	// #236 -> LCA Lowest Common Ancestor simple sol
	// T - O(3N) S - O(2N)
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> list1 = nodeToRootPath(root, p);
		List<TreeNode> list2 = nodeToRootPath(root, q);

		int p1 = list1.size() - 1;
		int p2 = list2.size() - 1;

		while (p1 >= 0 && p2 >= 0) {
			if (list1.get(p1) == list2.get(p2)) {
				p1--;
				p2--;
			} else {
				break;
			}
		}

		return list1.get(p1 + 1);

	}

	// Optimized T: O(N) , S: O(1)
	static TreeNode LCA;
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		LCA = null;
		isPresent(root, p, q);
		return LCA;
	}

	public boolean isPresent(TreeNode node , TreeNode p, TreeNode q) {
		if (node == null)return false;

		boolean lans = isPresent(node.left, p, q);
		boolean rans = isPresent(node.right, p, q);
		boolean self = (node == p || node == q);
		if (self && lans || self && rans || lans && rans) {
			LCA = node;
			return true;
		}
		return (node == p || node == q) || lans || rans;

	}


	//LCA try without boolean
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)return null;

		TreeNode L = lowestCommonAncestor(root.left, p, q);
		TreeNode R = lowestCommonAncestor(root.right, p, q);
		TreeNode self = (root == p || root == q) ? root : null;
		if ((L != null && R != null) || (self != null && L != null) || (self != null && R != null)) {
			return root;  //return root , not self coz  in L&&R case self is null
		}

		return L != null ? L : R != null ? R : self != null ? self : null;
	}



	public static void NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {

	}


	public static ArrayList<ArrayList<TreeNode>> nodeToAllLeafPath(TreeNode root) {
		if (root == null)return null;
		ArrayList<ArrayList<TreeNode>> res = new ArrayList<>();
		helper(root, res, new ArrayList<TreeNode>());
		return res;
	}

	public static void helper(TreeNode node , ArrayList<ArrayList<TreeNode>> res , ArrayList<TreeNode> temp ) {
		if (node == null)return;
		if (node.left == null && node.right == null) {
			temp.add(node);
			res.add(new ArrayList(temp));
			temp.remove(temp.size() - 1);
		}

		temp.add(node)
		helper(node.left, res, temp);
		helper(node.right, res, temp);
		temp.remove(temp.size() - 1);
	}

	public static int countExactlyOneChild(TreeNode node) {
		if (node == null)return 0;
		if (node.left == null && node.right == null)return 0;

		int lans = countExactlyOneChild(node.left);
		int rans = countExactlyOneChild(node.right);

		return (node.left == null || node.right == null) ? 1 + lans + rans : lans + rans;
	}

	//Diameter ==================================================================================

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
}