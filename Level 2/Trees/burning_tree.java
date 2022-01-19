import java.util.ArrayList;

public class burning_tree {

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


	public static ?? ?? NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {

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

	// print nodes k distance away
	//take 0(N) space worst case , skewed tree
	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		List<TreeNode> paths = NodeToRootPath(root, target.val);
		List<Integer> res = new ArrayList<>();
		TreeNode blocker = null;

		for (int i = 0; i < paths.size(); i++) {
			if (k - i < 0)break;
			kDown(paths.get(i), blocker, k - i, res);
			blocker = paths.get(i);
		}

		for (TreeNode node : paths) {
			System.out.println(node.val);
		}

		return res;
	}

	public static List<TreeNode> NodeToRootPath(TreeNode root, int data) {
		if (root == null) {
			return new ArrayList<>();
		}

		if (root.val == data) {
			List<TreeNode> base = new ArrayList<>();
			base.add(root);
			return base;
		}

		List<TreeNode> left = NodeToRootPath(root.left, data);
		if (left.size() != 0) {
			left.add(root);
			return left;
		}

		List<TreeNode> right = NodeToRootPath(root.right, data);
		if (right.size() != 0) {
			right.add(root);
			return right;
		}

		return new ArrayList<>();
	}


	public static void kDown(TreeNode node , TreeNode blocker , int k , List<Integer> res) {
		if (node == null || node == blocker || k < 0)return;
		if (k == 0) {
			res.add(node.val);
			return;
		}
		kDown(node.left, blocker, k - 1, res);
		kDown(node.right, blocker, k - 1, res);

		return;
	}

	/*
	k distance space optimized
	nodeToRootPath+kDown simultaneously
	Time : O(2N);
	Space : O(1)+O(Recursive);
	*/
	public static List<Integer> distanceK_01(TreeNode root, TreeNode target, int k) {
		List < Integer > res = new ArrayList < > ();
		NodeToRootPath_01(root, target, k, res);
		return res;
	}

	public static int NodeToRootPath_01(TreeNode root, TreeNode target , int k , List<Integer> res) {
		//if(null) identify with -1
		if (root == null)return -1;

		if (root == target) {
			kDown(root, null, k, res);
			return 1;
		}

		int left_distance = NodeToRootPath_01(root.left, target, k);
		if (left_distance != -1) {
			kDown(root, root.left, k - left_distance, res);
			return left_distance + 1;
		}

		int right_distance = NodeToRootPath_01(root.right, target, k);
		if (right_distance != -1) {
			kDown(root, root.right, k - right_distance, res);
			return right_distance + 1;
		}
		return -1;
	}


	/*
	Burn the binary tree
	Time : O(2N);
	Space : O(1)+O(Recursive);
	https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
	*/
	public static List<List<Integer>> burningTreeDriver(TreeNode root, int target ) {
		List<List<Integer>> ans = new ArrayList<>();
		burningTree(root, target.val, ans);
		return ans;
	}

	/*
	this uses node to root path and also calls kDown simultaneously , space optimized;
	-1 means node not found so no call for kDown , if ans not -1 , call kdown with k = 0;
	int this fire starts from the origin and spreads , so k increases;
	*/
	public  int burningTree(TreeNode root, int target , List<List<Integer>> ans) {
		if (root == null) {
			return -1;
		}

		if (root.val == target) {
			kDown2(root, null, 0, ans);
			return 1;
		}

		int ld = burningTree(root.left, target, ans);
		if (ld != -1) {
			kDown2(root, root.left, ld, ans);
			return ld + 1;
		}

		int rd = burningTree(root.right, target, ans);
		if (rd != -1) {
			kDown2(root, root.right, rd, ans);
			return rd + 1;
		}

		return -1;
	}

	/*modified k down , at every node time is present , for origin node time is 0
	  if(time==list.size() -> this node is first one so add emty [] and then add node.val);
	  int list of list every idx represents time , idx==time;

	  ..kdown calls dfs on whole subtree with t++ on child nodes and adds nodes to list at t=idx;
	*/
	public  void kDown2(TreeNode node , TreeNode blocker , int time , List<List<Integer>> res) {
		if (node == null || node == blocker)return;

		if (time == res.size()) {
			res.add(new ArrayList<>());
		}
		res.get(time).add(node.val);

		kDown2(node.left, blocker, time + 1, res);
		kDown2(node.right, blocker, time + 1, res);

		return;
	}



	//burning tree with water

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

	/*
	Burning Tree With water , given arrayList of TreeNodes/Vals(Unique) containing water
	*/

}


