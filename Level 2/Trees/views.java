public class views {

	/*
	ALL VIEW TYPES OF QUESTIONS --------------------------------------------------------------------------
	*/

	// imp ques for solving all these level order

	public List < List < Integer >> levelOrder(TreeNode root) {

		List < List < Integer >> res = new ArrayList < > ();
		List < Integer > level = new ArrayList < > ();
		Queue < TreeNode > q = new ArrayDeque < > ();
		if (root == null) return res;
		q.add(root);

		while (q.size() > 0) {
			int size = q.size();

			for (int i = 0; i < size; i++) {

				TreeNode rem = q.poll();
				level.add(rem.val);

				if (rem.left != null) q.add(rem.left);
				if (rem.right != null) q.add(rem.right);
			}

			res.add(level);
			level = new ArrayList < > ();
		}

		return res;
	}

	//LEFT VIEW - solved using level order , all first nodes of each lvl added to ans
	public List < Integer >  leftSideView(TreeNode root) {
		Queue < TreeNode > q = new ArrayDeque < > ();
		List < Integer > res = new ArrayList < > ();
		if (root == null) return res;

		q.add(root);

		while (q.size() > 0) {
			res.add(q.peek().val);
			int size = q.size();

			for (int i = 0; i < size; i++) {
				TreeNode rem = q.poll();

				if (rem.left != null) q.add(rem.left);
				if (rem.right != null) q.add(rem.right);
			}
		}

		return res;
	}

	// LeetCode #199  Right View - add right child first

	public List < Integer >  rightSideView(TreeNode root) {
		Queue < TreeNode > q = new ArrayDeque < > ();
		List < Integer > res = new ArrayList < > ();
		if (root == null) return res;

		q.add(root);

		while (q.size() > 0) {
			res.add(q.peek().val);
			int size = q.size();

			for (int i = 0; i < size; i++) {
				TreeNode rem = q.poll();

				if (rem.right != null) q.add(rem.right);
				if (rem.left != null) q.add(rem.left);
			}
		}

		return res;
	}


	/*Vertical Order Traversal of a Binary Tree
	Prerequisites - Pair Class{vertical level , TreeNode } , Width of Shadow - for no. of vertical levels
	*/

	public static void widthOfShadow(TreeNode node , int hl , int[] minMax) {
		if (node == null)return;
		minMax[0] = Math.min(minMax[0], hl);
		minMax[1] = Math.max(minMax[1], hl);
		widthOfShadow(node.left, hl - 1, minMax);
		widthOfShadow(node.right, hl + 1, minMax);

	}

	public static class vPair {
		int vl = 0;
		TreeNode node = null;

		vPair() {};

		vPair(TreeNode node , int vl) {
			this.node = node;
			this.vl = vl;
		}
	}
	/*
	make all idx positive using adding abs(min) to all
	now use BFS , all vlevel of one horiz level first , OR all x of y first
	then move to nxt level
	idx of list map to vlevel
	*/
	public static List<List<Integer>> verticalOrder(TreeNode root) {
		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int vLevels = minMax[1] - minMax[0] + 1;

		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < vLevels; i++) {
			res.add(new ArrayList<>());
		}

		Queue < vPair > q = new ArrayDeque < > ();
		//idx of root = Math.abs(leftmost)
		q.add(new vPair(root, Math.abs(minMax[0])));

		while (q.size() > 0) {

			int size = q.size();

			for (int i = 0; i < size; i++) {
				vPair rem = q.poll();
				TreeNode node = rem.node;
				int level = rem.vl;

				res.get(level).add(node.val);

				if (node.left != null) q.add(new vPair(node.left, level - 1));
				if (node.right != null) q.add(new vPair(node.right, level + 1));

			}
		}

		return res;

	}

	//Vertical Order Recursive ********************************************************************

	public static void helper(Node root, int idx, int hl, ArrayList<Integer> res) {
		if (root == null) return;
		if (idx == hl) {
			System.out.print(root.data + " ");
		}

		helper(root.left, idx, hl - 1, res);
		helper(root.right, idx, hl + 1, res);
	}

	//Function to find the vertical order traversal of Binary Tree.
	public static void verticalOrder(Node root) {
		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int width = minMax[1] - minMax[0] + 1;
		int rootPos = Math.abs(minMax[0]);

		for (int i = 0; i < width; i++) {
			helper(root, i, rootPos, list);
			System.out.println();
		}
	}


	//Vertical Order Sum Using DFS ********************************************************************

	public ArrayList <Integer> verticalSum(Node root) {
		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int hLevels = minMax[1] - minMax[0] + 1;

		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < hLevels; i++) {
			res.add(null);
		}

		int rIdx = Math.abs(minMax[0]);
		dfs(root, res, rIdx);
		return res;
	}

	//Note : nodes at the same vertical line are at the same horiz.. dist from the root
	public void dfs(Node root, ArrayList<Integer> res, int hd) {
		if (root == null)return;  //base case

		//add vals at same vertical line
		if (res.get(hd) == null) {
			res.set(hd, root.data);
		} else {
			int sum = res.get(hd);
			res.set(hd, sum + root.data);
		}

		dfs(root.left, res, hd - 1);
		dfs(root.right, res, hd + 1);
	}

	//Vertical Order Sum Using View ********************************************************************

	public ArrayList <Integer> verticalSum(Node root) {

		ArrayList<Integer> res = new ArrayList<>();

		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int vLevels = minMax[1] - minMax[0] + 1;

		for (int i = 0; i < vLevels; i++) {
			res.add(null);
		}

		Queue < vPair > q = new ArrayDeque < > ();
		//idx of root = Math.abs(leftmost)
		q.add(new vPair(root, Math.abs(minMax[0])));

		while (q.size() > 0) {

			int size = q.size();

			for (int i = 0; i < size; i++) {
				vPair rem = q.poll();
				Node node = rem.node;
				int level = rem.vl;

				if (res.get(level) == null) {
					res.set(level, node.data);
				} else {
					res.set(level, res.get(level) + node.data);
				}

				if (node.left != null) q.add(new vPair(node.left, level - 1));
				if (node.right != null) q.add(new vPair(node.right, level + 1));

			}
		}

		return res;
	}



	/*Bottom View
		  in vertical order the last val of each vertical level is the bottom node ,
		  i.e the last val at each list idx, update/override when a new node comes at same idx
		*/

	public static List<Integer> bottomView(TreeNode root) {
		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int vLevels = minMax[1] - minMax[0] + 1;

		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < vLevels; i++) {
			res.add(null);
		}

		Queue < vPair > q = new ArrayDeque < > ();
		//idx of root = Math.abs(leftmost)
		q.add(new vPair(root, Math.abs(minMax[0])));

		while (q.size() > 0) {

			int size = q.size();

			for (int i = 0; i < size; i++) {
				vPair rem = q.poll();
				TreeNode node = rem.node;
				int level = rem.vl;

				res.set(level, node.val); //One line diff from Vertical Order / Overwrite

				if (node.left != null) q.add(new vPair(node.left, level - 1));
				if (node.right != null) q.add(new vPair(node.right, level + 1));

			}
		}

		return res;

	}

	//Top View - first node of Vertical Order****************************************************************

	public static List<Integer> topView(TreeNode root) {
		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int vLevels = minMax[1] - minMax[0] + 1;

		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < vLevels; i++) {
			res.add(null);
		}

		Queue < vPair > q = new ArrayDeque < > ();
		//idx of root = Math.abs(leftmost)
		q.add(new vPair(root, Math.abs(minMax[0])));

		while (q.size() > 0) {

			int size = q.size();

			for (int i = 0; i < size; i++) {
				vPair rem = q.poll();
				TreeNode node = rem.node;
				int level = rem.vl;

				//only set first time
				if (res.get(level) == null)res.set(level, node.val); //One line diff from Vertical Order

				if (node.left != null) q.add(new vPair(node.left, level - 1));
				if (node.right != null) q.add(new vPair(node.right, level + 1));

			}
		}

		return res;

	}

	//Top View Using Map -- do again !!!*****************************************************************
	public static void topViewHashing(TreeNode root) {

	}



	/*Diagonal Order traversal  4
								 \
								  6
								   \
								    7
								       Primary Diagonal
	*/

	public static List<List<Integer>> diagonalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);

		while (q.size() > 0) {
			int size = q.size();
			List<Integer> temp = new ArrayList<>();

			while (size-- > 0) {
				TreeNode rem = q.remove();
				while (rem != null) { //clusters of diagonal
					temp.add(rem.val);
					if (rem.left != null) {
						q.add(rem.left);
					}
					rem = rem.right;  //goes to rightmost
				}
			}
			res.add(temp); //
		}

		return res;
	}

	//Anti Diagonal Order********************************************************************


	//Diagonal Order Sum Using View (check again******)**************************************

	public static List<List<Integer>> diagonalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);

		while (q.size() > 0) {
			int size = q.size();
			List<Integer> temp = new ArrayList<>();

			while (size-- > 0) {
				TreeNode rem = q.remove();
				while (rem != null) { //clusters of diagonal
					if (temp.size() == 0) {
						temp.add(rem.val);
					} else {
						int val = temp.get(0);
						val += rem.val;
						temp.set(0, val);
					}
					if (rem.left != null) {
						q.add(rem.left);
					}
					rem = rem.right;  //goes to rightmost
				}
			}
			res.add(temp); //
		}

		return res;
	}

	//Vertical Order Sum Using Shadow Technique*************************************************************

	public class ListNode {
		ListNode prev = null;
		ListNode next = null;
		int val;

		public ListNode(int val) {}

		public ListNode(ListNode prev, ListNode next, int val) {
			this.prev = prev;
			this.next = next;
			this.val = val;
		}
	}

	public void verticalOrderSum(TreeNode root , ListNode node ) {
		if (root == null || node == null)return;

		//add vals of same vertical line
		node.val += root.val;

		//make nodes , attach
		if (root.left != null) {
			if (node.prev == null) {
				ListNode temp = new Node(0);
				node.prev = temp;
				temp.next = node;
			}
		}

		if (root.right != null) {
			if (node.next == null) {
				ListNode temp = new Node(0);
				node.next = temp;
				temp.prev = node;
			}
		}

		//move
		verticalOrderSum(root.left, node.prev);
		verticalOrderSum(root.right, node.next);


	}

	public static List<Integer> verticalOrderDriver(TreeNode root) {
		List<Integer> res = new ArrayList<>();

		ListNode node = new ListNode(0);
		verticalOrderSum(root, node);


		while (node.prev != null)node = node.prev;
		while (node != null) {
			res.add(node.val);
			node = node.next;
		}
		return null;
	}

	/*
	Diagonal Order Sum Using Shadow - prev = left , next = right
	in Diagonal only left/prev , in Anti Diag only right/next
	*/

	/*




	#987. Vertical Order Traversal of a Binary Tree
	a. Use two priority q , parent and child
	b. if vLevel equal smaller val pops , otherwise smaller vLevel comes out
	c. if parent pq exhaust , swap parent and child q
	   can also use 1 priority queue with vlevel , hlevel , val
	*/

	//Using 1 PQ - priority hlevel>vlevel>val
	public List < List < Integer >> verticalTraversal_01(TreeNode root) {
		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int vLevels = minMax[1] - minMax[0] + 1;
		List < List < Integer >> res = new ArrayList < > ();
		for (int i = 0; i < vLevels; i++) {
			res.add(new ArrayList < > ());
		}

		// using Lambda operator
		PriorityQueue < vPair > pq = new PriorityQueue < > ((a, b) -> {
			if (a.hl != b.hl) return a.hl - b.hl;
			else if (a.vl != b.vl) return a.vl - b.vl;
			else return a.node.val - b.node.val;
		});

		pq.add(new vPair(root, Math.abs(minMax[0]), 0));

		while (pq.size() > 0) {
			int size = pq.size();
			while (size-- > 0) {
				vPair rem = pq.remove();
				TreeNode node = rem.node;
				int vl = rem.vl;
				int hl = rem.hl;

				res.get(vl).add(node.val);

				if (node.left != null) pq.add(new vPair(node.left, vl - 1, hl + 1));
				if (node.right != null) pq.add(new vPair(node.right, vl + 1, hl + 1));
			}
		}

		return res;
	}

	//Using 2 PQ Parent and Child - segregates horiz levels , we only need to worry abt vLvl
	public List < List < Integer >> verticalTraversal_02(TreeNode root) {
		int[] minMax = new int[2];
		widthOfShadow(root, 0, minMax);

		int vLevels = minMax[1] - minMax[0] + 1;
		List < List < Integer >> res = new ArrayList < > ();
		for (int i = 0; i < vLevels; i++) {
			res.add(new ArrayList < > ());
		}

		// using Lambda operator
		PriorityQueue < vPair > pq = new PriorityQueue < > ((a, b) -> {
			if (a.vl != b.vl) return a.vl - b.vl;
			else return a.node.val - b.node.val;
		});

		PriorityQueue < vPair > childQ = new PriorityQueue < > ((a, b) -> {
			if (a.vl != b.vl) return a.vl - b.vl;
			else return a.node.val - b.node.val;
		});



		pq.add(new vPair(root, Math.abs(minMax[0])));

		while (pq.size() > 0) {
			int size = pq.size();
			while (size-- > 0) {
				vPair rem = pq.remove();
				TreeNode node = rem.node;
				int vl = rem.vl;

				res.get(vl).add(node.val);

				if (node.left != null) childQ.add(new vPair(node.left, vl - 1));
				if (node.right != null) childQ.add(new vPair(node.right, vl + 1));
			}

			PriorityQueue temp = childQ;
			childQ = pq;
			pq = temp;
		}

		return res;
	}
}