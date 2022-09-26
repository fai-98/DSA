public class amazon_march {
	//1. diameter of binary tree
	//2. number of islands

	//3. The celebrity problem

	//using graph
	// T : n^2
	// S: O(N)
	static int findCelebrity(int n) {

		// the graph needs not be constructed
		// as the edges can be found by
		// using knows function

		// degree array;
		int[] indegree = new int[n];
		int[] outdegree = new int[n];

		// query for all edges
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int x = knows(i, j);

				// set the degrees
				outdegree[i] += x;
				indegree[j] += x;
			}
		}

		// find a person with indegree n-1
		// and out degree 0
		for (int i = 0; i < n; i++)
			if (indegree[i] == n - 1 && outdegree[i] == 0)
				return i;

		return -1;
	}


	//Using Stack
	public static int findCelebrity(int n) {
		int mat[][] = { { 0, 0, 1, 0 },
			{ 0, 0, 1, 0 },
			{ 0, 0, 0, 0 },
			{ 0, 0, 1, 0 }
		};

		Stack<Integer> st = new Stack<>();

		//push all ids in stack
		for (int i = 0; i < n; i++) {
			st.push(i);
		}

		while (st.size() > 1) {
			int a = st.pop();
			int b = st.pop();

			if (mat[a][b] == 1) {
				// if a knows b , a is not celeb
				st.push(b);
			} else {
				st.push(a);  //a can be a celeb
			}
		}

		//we have potential celeb , if it knows 0 people and known by n-1 , it is celeb
		int pot = st.pop();

		for (int i = 0; i < n; i++) {
			if (i != pot) {
				if (mat[i][pot] == 0 || mat[pot][i] == 1) {
					return -1;
				}
			}
		}

		return pot;

	}


	//4.min no. of platforms
	//Brute
	// The idea is to take every interval one by one and
	//find the number of intervals that overlap with it.
	//Keep track of the maximum number of intervals that overlap with an interval.
	//Finally, return the maximum value.

	//Optimize nlogn
	//find max num of overlapping intervals
	public static int findPlatform(int arr[], int dep[], int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		// arr[curr] <= dep[prev];
		//prev ke jane s phle hi or just at that moment agli train agayi , we need another platform
		// min platform = max no. of trains at a particular time
		int maxTrain = 0, platform = -(int)1e9;
		int i = 0; //arrival
		int j = 0; //departure

		while (i < n && j < n) {
			if (arr[i] <= dep[j]) {
				maxTrain++;
				i++;
			} else {
				maxTrain--;
				j++;
			}

			platform = Math.max(platform , maxTrain);
		}
		return platform;

	}

	//5. kth largest/smalles ele

	// A1 - sorting nlogn
	// A2 - heap of size n O(n+klogn)
	// A3 - use set if all are distinct
	// A4 - heap of size k



	//O(n+(n-k)logk)  n for heap build , n-k elements loop , logk heapify
	public static void kLargestElements(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		//add first k elem
		for (int i = 0; i < k; i++)pq.add(arr[i]);

		//if elem > pq.peek() , rem peek , add elem, size always k
		for (int i = k; i < n; i++)
			if (pq.peek() < arr[i]) {
				pq.poll();
				pq.add(arr[i]);
			}


		while (pq.size() > 0) {
			System.out.println(pq.poll());
		}
	}

	//similarly for kth smallest
	public static int kthSmallest(int[] arr, int l, int r, int k) {
		//use max heap
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < k; i++) {
			pq.add(arr[i]);
		}

		for (int i = k; i < arr.length; i++) {
			if (pq.peek() > arr[i]) {
				pq.poll();
				pq.offer(arr[i]);
			}
		}

		return pq.peek();
	}

	// A5 - quick select
	//Quick Select Algorithm - kth largest/smallest

	//code for kth smallest , for largest find (n-k)th smallest
	//select pivot = arr[hi] , after partition pivot is at correct idx
	//if idx < k , recur right side , else idx>k recur left side
	// idx == k means kth smallest ele was selected as pivot and now it is at correct pos..
	public int findKthLargest(int[] nums, int k) {
		// (n-k)th smallest
		return quickSelect(nums, 0, nums.length - 1, nums.length - k);
	}

	public int quickSelect(int[] arr, int lo, int hi, int k) {

		int pivotIndex = partition(arr, arr[hi] , lo , hi);

		if (pivotIndex > k) {
			return quickSelect(arr, lo, pivotIndex - 1, k);
		} else if (pivotIndex < k) {
			return quickSelect(arr, pivotIndex + 1, hi, k);
		} else {
			return arr[pivotIndex];
		}
	}

	public int partition(int[] arr, int pivot, int lo, int hi) {
		int i = lo, j = lo;
		while (j <= hi) {
			if (arr[j] <= pivot) {  //swap the val which shd be on left
				swap(arr, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}

		return (i - 1);
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}




	//6. Quick Sort Algorithm
	public int[] sortArray(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	public void quickSort(int[] arr, int lo, int hi) {
		if (lo >= hi) {
			return;
		}

		int pivotIndex = partition(arr, arr[hi] , lo , hi);
		quickSort(arr, lo, pivotIndex - 1);
		quickSort(arr, pivotIndex + 1 , hi);

	}

	public int partition(int[] arr, int pivot, int lo, int hi) {
		int i = lo, j = lo;
		while (j <= hi) {
			if (arr[j] <= pivot) {
				swap(arr, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}

		return (i - 1);
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


	//https://www.geeksforgeeks.org/amazon-interview-experience-on-campus-10/

	//7.https://www.geeksforgeeks.org/find-height-of-a-special-binary-tree-whose-leaf-nodes-are-connected/

	// leaves are connected as circular DLL
	public static int findTreeHeight(Node root) {
		if (root == null)return 0;

		//check for leaf , if it's a leaf return from here with ht = 1
		if (root.left != null && root.left.right == root &&
		        root.right != null && root.right.left == root) {
			return 1;
		}

		int lh = findTreeHeight(root.left);
		int rh = findTreeHeight(root.right);

		return Math.max(lh, rh) + 1;
	}

	// 8.1953. Maximum Number of Weeks for Which You Can Work
	// 	We can complete all milestones for all projects, unless one project has
	// more milestones then all other projects combined.

	// For the latter case:

	// We can complete all milestones for other projects,
	// plus same number of milestones for the largest project,
	// plus one more milestone for the largest project.

	// After that point, we will not be able to alternate projects anymore.

	public long numberOfWeeks(int[] arr) {
		long sum = 0;
		int max = -(int)1e9;

		for (int ele : arr) {
			sum += ele;
			max = Math.max(max, ele);
		}

		return Math.min(sum, 2 * (sum - max) + 1);
	}

	//9.   (134. Gas Station)
	// Circular Tour Problem
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int n = gas.length, sum = 0;
		// check if circular tour possible
		for (int i = 0; i < n; i++) {
			sum += gas[i] - cost[i];
		}

		if (sum < 0) return -1;

		//now it is surely possible
		//brute force = take every idx as st point and check
		//if at any pt , balance is -ve , take nxt st point

		//optimize , if at any pt bal is -ve , all pts before it are invalid
		int balance = 0, start = 0;
		for (int i = 0; i < n; i++) {
			balance += gas[i] - cost[i];

			if (balance < 0) {
				start = i + 1;
				balance = 0;
			}
		}

		return start;
	}

	// 10. 116. Populating Next Right Pointers in Each Node

	//Brute
	public Node connect(Node root) {
		if (root == null) return null;
		Queue < Node > q = new ArrayDeque < > ();
		q.add(root);

		while (q.size() > 0) {
			int size = q.size();
			for (int i = 0; i < size; i++) {

				Node node = q.remove();

				if (i < size - 1) {
					node.next = q.peek();
				}

				if (node.left != null) q.add(node.left);
				if (node.right != null) q.add(node.right);
			}
		}

		return root;
	}

	//O(1) space
	//2 ptrs = lroot - st node of each level , curr traverses the curr level
	public Node connect(Node root) {
		if (root == null)return null;
		Node lroot = root;
		while (lroot.left != null) {
			Node curr = lroot;
			while (curr != null) {
				curr.left.next = curr.right;
				if (curr.next != null) {
					curr.right.next = curr.next.left;
				}
				curr = curr.next;
			}
			lroot = lroot.left;
		}
		return root;
	}

	//11. Zig Zag Traversal
	public static ArrayList<Integer> zigZagTraversal(Node root) {
		//Add your code here.
		Deque<Node> dq = new ArrayDeque<>();
		dq.add(root);

		int level = 1;
		ArrayList<Integer> res = new ArrayList<>();

		while (dq.size() > 0) {
			int size = dq.size();
			while (size-- > 0) {
				//order in dq is same as level order always
				//we remove from front for odd level and from back for even lvl
				if (level % 2 == 1) {
					Node rem = dq.remove();
					res.add(rem.data);
					if (rem.left != null)dq.add(rem.left);
					if (rem.right != null)dq.add(rem.right);
				} else {
					Node rem = dq.removeLast();
					res.add(rem.data);
					if (rem.right != null)dq.addFirst(rem.right);
					if (rem.left != null)dq.addFirst(rem.left);
				}
			}
			level++;
		}
		return res;
	}

	// 12. Remove k consecutive chars
	public static class Pair {
		char ch;
		int ct;

		Pair() {}

		Pair(char ch, int ct) {
			this.ch = ch;
			this.ct = ct;
		}
	}

	public static String removeKConsecutive(String s, int k) {
		Stack<Pair> st = new Stack<>();

		for (char c : s.toCharArray()) {
			if (st.size() > 0) {
				if (st.peek().ch == c) {
					if (st.peek().ct == k - 1) {
						st.pop();
					} else {
						st.peek().ct += 1;
					}
				} else {
					Pair np = new Pair(c, 1);
					st.push(np);
				}
			} else {
				Pair np = new Pair(c, 1);
				st.push(np);
			}
		}

		String ans = "";
		while (st.size() > 0) {
			char c = st.peek().ch;
			int cnt = st.peek().ct;
			while (cnt-- > 0)
				ans = c + ans;
			st.pop();
		}
		return ans;

	}

	// 13. Max Path Sum in Binary Tree
	public int maxPathSum(TreeNode root) {
		int[] maxi = new int[1];
		maxi[0] = Integer.MIN_VALUE;
		maxPathDown(root, maxi);
		return maxi[0];
	}

	public int maxPathDown(TreeNode root, int[] maxi) {
		if (root == null) return 0;
		int leftSideMax = Math.max(0, maxPathDown(root.left, maxi));
		int rightSideMax = Math.max(0, maxPathDown(root.right, maxi));
		maxi[0] = Math.max(maxi[0], leftSideMax + root.val + rightSideMax);
		return Math.max(leftSideMax, rightSideMax) + root.val;
		//return either left or right , best path for parent node
	}

	// case 1. when root alone is max , l and r are -ve , we return 0
	// ignore -ve sums , they will only reduce the ans
	// L+self+R is calc , if L -ve return 0 , R + Self , similarly for R
	// if root is very less and it decreases out maxi , then best ans is already stored in
	// static variable


	//14. Car Pooling
	//calc net increase at every point
	public boolean carPooling(int[][] trips, int capacity) {
		int[] net_increase = new int[1001];

		for (int i = 0; i < trips.length; i++) {
			int p = trips[i][0], si = trips[i][1], ei = trips[i][2];

			net_increase[si] += p; //p pass come at si
			net_increase[ei] -= p; //p pass leave at ei
		}

		int cap = 0;
		for (int i = 0; i < 1001; i++) {
			cap += net_increase[i];
			if (cap > capacity)return false;
		}

		return true;
	}

	// 15.Count pairs in array whose sum is divisible by K

	//if rem = 0, pairs = n(n-1)/2
	//else pairs = ct rem[i] * ct rem[k-i] ..till i<=k/2 i!=k-i !!

	//in pairs type question
	//pair with given diff , given sum , think about sorting
	//with sum div by , think about hashing remainder


	//16. https://www.interviewbit.com/problems/pair-with-given-difference/


	//17. Count freq in sorted array nlogn
	int count(int[] arr, int n, int x) {
		int fi = -1, li = -1, lo = 0, hi = n - 1;

		// first idx
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (arr[mid] < x) {
				lo = mid + 1;
			} else if (arr[mid] > x) {
				hi = mid - 1;
			} else {
				fi = mid;
				hi = mid - 1;
			}
		}
		if (fi == -1)return 0; //val not present

		lo = 0; hi = n - 1;
		//last idx
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (arr[mid] < x) {
				lo = mid + 1;
			} else if (arr[mid] > x) {
				hi = mid - 1;
			} else {
				li = mid;
				lo = mid + 1;
			}
		}

		return (li - fi) + 1;
	}

	// 18. Max Product Subarray
	public int maxProduct(int[] nums) {
		int max = -11, product = 1, len = nums.length;

		//whn odd -ves either skip 1 -ve at extreme side
		//left prod
		for (int i = 0; i < len; i++) {
			max = Math.max(product *= nums[i], max);
			if (nums[i] == 0) product = 1;
		}

		product = 1;
		//right prod
		for (int i = len - 1; i >= 0; i--) {
			max = Math.max(product *= nums[i], max);
			if (nums[i] == 0) product = 1;
		}

		return max;
	}

	// if there are no zeros , the subarr must start from left or start from right side
	// the prod must be some prefix or suffix product
	// case 1  -ve.....+ves.....-ve , best prod inc both -ves
	// case 2  -ve .....+ves ......+ve , best prod from rt side
	// case 3  +ve .....+ve prod .... -ve , best prod from left side
	// case 4  +ve ......+ve.....+ve , whole arr
	// case 5 with 0s , ....0000.... , in middle , we reset prod with 1 and calc running max
	// case 6 -ve....-ve...-ve , either first 2 -ve or last 2 -ve inc
	// case 7 0s on extremes , same case

	// 19. Container with most water

	// 20. Trapping Rainwater
	public int trap(int[] arr) {
		int n = arr.length;
		int l = 0, r = n - 1, lmax = 0, rmax = 0, water = 0;

		// Brute force = min(lmax,rmax)-arr[i];
		// here only lmax-arr[i] or rmax-arr[i];
		// how we are making sure that it's min??

		// l++ only when a[l]<=a[r] -> so when curr a[l]<lmax ,
		// it's guaranteed lmax<=rmax
		// also lmax<=rmax so, min(lmax,rmax) = lmax

		while (l < r) {
			if (arr[l] <= arr[r]) {
				if (arr[l] > lmax) {
					lmax = arr[l];
				} else {
					water += lmax - arr[l];
				}

				l++;
			} else {
				if (arr[r] > rmax) {
					rmax = arr[r];
				} else {
					water += rmax - arr[r];
				}
				r--;
			}
		}

		return water;
	}

	// 21. Subarray Prod less than k

	//each valid step introduces x new subarr where x = size of win
	//ex [1,2,3] now acquire [4]
	// new subarr added are [1,2,3,4] , [2,3,4] , [3,4] , [4] = size of win = 4
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		int acq = -1, rel = -1, prod = 1, count = 0;

		if (k <= 1)return 0;
		while (acq < nums.length - 1) {
			acq++;
			prod *= nums[acq];

			while (prod >= k) {
				rel++;
				prod /= nums[rel];
			}

			count += acq - rel;
		}
		return count;
	}

	//Array pivot , binary search all variations (most imp) sorted rotated array variations

	// 22. Find Minimum in Rotated Sorted Array
	public int findMin(int[] nums) {
		int n = nums.length;
		int lo = 0, hi = n - 1;

		//largest val will be last val of 1st part - only val > right val
		//smallest val , 1st val of part 2 - it will be only val < left val
		//idx[max] + 1 = idx min val

		//Case 1. len = 1
		if (n == 1) {
			return nums[0];
		}
		//Case 2. len >= 2 , sorted arr , min is at 0th
		if (nums[lo] < nums[hi]) {
			return nums[0];
		}
		//Case 3. len >= 2 and pivot lies 1<= pi <= n-1
		//we can easily compare with mid-1 , first mid-1 compare , then mid+1

		//Binary Search
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mid > 0 && nums[mid] < nums[mid - 1]) {
				return nums[mid]; //it is min, 1st element of part 2
			} else if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1]; //it is max, last ele of part 2
			} else if (nums[lo] < nums[mid]) {
				lo = mid + 1; //1st half sorted , search right half
			} else {
				hi = mid - 1;
			}
		}

		return -1;
	}

	// 23. Search in SRA
	public int search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (nums[mid] == target) {
				return mid;
			} else if (nums[lo] <= nums[mid]) { //P1 id sorted
				if (nums[lo] <= target && target <= nums[mid]) { //tar in range
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else { //P2. is sorted
				if (target >= nums[mid] && nums[hi] >= target) {
					lo = mid + 1; //val in P2 range
				} else {
					hi = mid - 1;
				}
			}
		}

		return -1;
	}

	// 24. Search in SRA - 2 (dups allowed)

	// 25. Check if array is SRA
	public boolean check(int[] nums) {
		int n = nums.length, pivot = 0;

		for (int i = 0; i < n; i++) {
			if (nums[i] > nums[(i + 1) % n]) {
				pivot++;
			}
			if (pivot > 1)return false;
		}
		return true;
	}

	// 26. Count Rotations


	// 27. Recover BST vvi
	private TreeNode first;
	private TreeNode middle;
	private TreeNode second;
	private TreeNode prev;

	public void inorder(TreeNode node) {
		if (node == null)return;

		inorder(node.left);

		if (prev != null && prev.val > node.val) {

			if (first == null) { //violation 1
				first = prev;
				middle = node;
			} else { //violation 2
				second = node;
			}

		}
		prev = node;
		inorder(node.right);
	}
	public void recoverTree(TreeNode root) {
		//in inorder 2 cases
		//Adjacent nodes are swapped - only 1 violation
		//non-adj , 2 violations
		first = middle = second = null;
		// prev = new TreeNode(Integer.MIN_VALUE);
		inorder(root);

		if (second == null) { //adjacent nodes
			int temp = first.val;
			first.val = middle.val;
			middle.val = temp;
		} else { //non-adj nodes
			int temp = first.val;
			first.val = second.val;
			second.val = temp;
		}

		return;
	}

	// 28. Subtree of Another Tree

	// For each node during pre-order traversal of s,
	// use a recursive function isSame to validate if sub-tree started
	// with this node is the same with t.
	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if (root == null)return false;
		boolean self = find(root, subRoot);
		boolean L = isSubtree(root.left, subRoot);
		boolean R = isSubtree(root.right, subRoot);

		return self || L || R;
	}

	public boolean find(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null)return true;
		else if (node1 == null || node2 == null)return false;
		else if (node1.val != node2.val)return false;

		boolean L = find(node1.left, node2.left);
		boolean R = find(node1.right, node2.right);

		return L && R;
	}

	// 29. Kadanes + print array elements


	// 30. Count And Say

	// 31. Sum Tree - node.val = leftSubtree sum + rt sbtree sum
	boolean isSumTree(Node root) {
		// Your code here
		int[] arr = getSum(root);

		return arr[1] == 1 ? true : false;
	}

	public int[] getSum(Node node) {
		if (node == null) {
			return new int[] {0, 1}; //1 = true , 0 = false;
		}

		int[] lans = getSum(node.left);
		int[] rans = getSum(node.right);

		int[] myAns = new int[2];

		int mySum = lans[0] + node.data + rans[0];
		if (mySum == 2 * node.data || mySum == node.data && lans[1] == 1 && rans[1] == 1) {
			myAns[1] = 1;
		} else {
			return myAns;  //subtree is not valid so just return from here
		}

		myAns[0] = mySum;

		return myAns;
	}

	// 32. 1373. Maximum Sum BST in Binary Tree
	static int max;

	public class Pair {
		int maxL = -(int) 1e9;
		int minR = (int) 1e9;
		int currSum = 0;
		boolean isBst = true;

		Pair() {}

		Pair(int maxL, int minR, int currSum) {
			this.maxL = maxL;
			this.minR = minR;
			this.currSum = currSum;
		}
	}
	public int maxSumBST(TreeNode root) {
		// {maxSum , sum , isBst ?}  if all nodes -ve , sum = 0
		// if isBst , update max sum
		//max in left subtree < node
		//min in right > node
		max = 0;
		solve(root);
		return max;
	}

	public Pair solve(TreeNode root) {
		if (root == null) {
			Pair base = new Pair();
			return base;
		}

		Pair lPair = solve(root.left);
		Pair rPair = solve(root.right);

		int sum = lPair.currSum + root.val + rPair.currSum;
		int lMax = lPair.maxL;
		int rMin = rPair.minR;
		boolean lBst = lPair.isBst;
		boolean rBst = rPair.isBst;

		Pair myAns = new Pair();

		//if self > max in lst , self < min in rst and also lst & rst are itself bst
		if (root.val > lMax && root.val < rMin && lBst && rBst) {
			//update max here
			max = Math.max(max, sum);
			myAns.currSum = sum;
			myAns.maxL = Math.max(Math.max(lPair.maxL, rPair.maxL), root.val);
			myAns.minR = Math.min(Math.min(lPair.minR, rPair.minR), root.val);
			myAns.isBst = true;
			return myAns;
		} else {
			myAns.isBst = false;
			return myAns;
		}

	}

	//333. Leetcode Largest BST Subtree
	static class Pair {
		int mini = (int) 1e9;
		int maxi = -(int) 1e9;
		int sz = 0;

		Pair() {}

		Pair(int mini, int maxi, int sz) {
			this.mini = mini;
			this.maxi = maxi;
			this.sz = sz;
		}
	}
	// Return the size of the largest sub-tree which is also a BST
	static int largestBst(Node root) {
		Pair res = dfs(root);
		return res.sz;
	}

	public static Pair dfs(Node root) {
		if (root == null) {
			return new Pair();  // {min , max , sum}
		}

		Pair lPair = dfs(root.left);
		Pair rPair = dfs(root.right);

		// Current node is greater than max in left AND
		// smaller than min in right, it is a BST.
		Pair myPair = new Pair();
		//why only compare node,l.mini or node,r.maxi
		//we already know that it is BST so all at left are smaller
		//so check smallest of all smaller and compare with self ..similar maxi
		if (root.data > lPair.maxi && root.data < rPair.mini) {
			myPair.mini = Math.min(root.data, lPair.mini);
			myPair.maxi = Math.max(root.data, rPair.maxi);
			//update size here
			myPair.sz = lPair.sz + 1 + rPair.sz;
			return myPair;
		}

		//if it is not BST send [-INF,INF]->[min,max] so that parent can never be bst
		//for BST node < right min (-INF) , node > lmax (INF) , never possible
		myPair.mini = -(int) 1e9;
		myPair.maxi = (int) 1e9;
		myPair.sz = Math.max(lPair.sz, rPair.sz); //i'm not BST , still i need to carry curr max ans
		return myPair;
	}

	//34. Nuts and Bolts , Lock and Keys  - Sort/HashMap/QuickSort
	private static void matchPairs(char[] nuts, char[] bolts, int low,
	                               int high) {
		if (low < high) {
			// Choose last character of bolts array for nuts partition.
			int pivot = partition(nuts, low, high, bolts[high]);

			// Now using the partition of nuts choose that for bolts
			// partition.
			partition(bolts, low, high, nuts[pivot]);

			// Recur for [low...pivot-1] & [pivot+1...high] for nuts and
			// bolts array.
			matchPairs(nuts, bolts, low, pivot - 1);
			matchPairs(nuts, bolts, pivot + 1, high);
		}
	}

	// Similar to standard partition method. Here we pass the pivot element
	// too instead of choosing it inside the method.
	private static int partition(char[] arr, int low, int high, char pivot) {
		int i = low;
		char temp1, temp2;
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				temp1 = arr[i];
				arr[i] = arr[j];
				arr[j] = temp1;
				i++;
			} else if (arr[j] == pivot) {
				temp1 = arr[j];
				arr[j] = arr[high];
				arr[high] = temp1;
				j--;
			}
		}
		temp2 = arr[i];
		arr[i] = arr[high];
		arr[high] = temp2;

		// Return the partition index of an array based on the pivot
		// element of other array.
		return i;
	}

	//35. LRU Cache Revision
	// done

	//36. 973. K Closest Points to Origin
	public class Pair {
		long dist;
		int idx;

		Pair() {}

		Pair(long dist, int idx) {
			this.dist = dist;
			this.idx = idx;
		}
	}
	public int[][] kClosest(int[][] points, int k) {
		// (x1-x2)^2 + (y1-y2)^2 = dist^2  //x2 and y2 are 0
		// x^2 + y^2 = dist^2
		// make pq of dist^2 , idx

		PriorityQueue < Pair > pq = new PriorityQueue < > ((a, b) -> {
			return (int)(b.dist - a.dist);
		});

		for (int i = 0; i < points.length; i++) {
			int x = points[i][0];
			int y = points[i][1];

			long dis = x * x + y * y;

			pq.add(new Pair(dis, i));

			if (pq.size() > k) {
				pq.poll();
			}
		}

		int[][] res = new int[k][2];
		while (k-- > 0) {
			Pair rem = pq.poll();
			res[k] = points[rem.idx];
		}

		return res;
	}
	//37. BST Iterator (almost same as iterative inorder traversal)
	// next() = O(1) , hasNext() Amortized O(1) - sometimes called , S : O(h) stack space
	class BSTIterator {
		private static Stack<TreeNode> st;

		public BSTIterator(TreeNode root) {
			st = new Stack<>();
			addAllLeft(root);
		}

		public void addAllLeft(TreeNode node) {
			while (node != null) {
				this.st.push(node);
				node = node.left;
			}
		}

		public int next() {
			TreeNode rem = this.st.pop(); //i'm at inorder
			addAllLeft(rem.right);  //if right null , add func will handle
			return rem.val;
		}

		public boolean hasNext() {
			return !st.isEmpty();
		}
	}

	//38. 138. Copy List with Random Pointer
	public void copyNodes(Node head) {
		Node curr = head;
		while (curr != null) {
			Node next = curr.next;
			Node temp = new Node(curr.val);
			curr.next = temp;
			temp.next = next;
			curr = next;
		}
	}

	public void setRandom(Node head) {
		Node curr = head;
		Node copy = head.next;
		while (curr != null) {
			if (curr.random != null) {
				copy.random = curr.random.next;
			}
			curr = copy.next;
			if (curr != null)copy = curr.next;
		}
	}
	public Node extractList(Node head) {
		Node dummy = new Node(-1);
		Node curr = head;
		Node copy = head.next;
		dummy.next = copy;

		while (curr != null) {
			curr.next = copy.next;
			curr = curr.next;
			if (curr != null) copy.next = curr.next;
			copy = copy.next;
		}
		return dummy.next;
	}
	public Node copyRandomList(Node head) {
		if (head == null) return null;
		copyNodes(head);
		setRandom(head);
		return extractList(head);
	}

	// 39. 25. Reverse Nodes in k-Group
	private static ListNode tHead;
	private static ListNode tTail;

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k == 1) return head;
		int len = size(head);
		tHead = null; tTail = null;
		ListNode pHead = null, pTail = null, curr = head; //prev head tail

		while (curr != null && len >= k) {

			for (int i = 0; i < k && curr != null; i++) {
				ListNode next = curr.next; //catch next
				curr.next = null; //break link
				addFirst(curr); //add first
				curr = next;
			}

			if (pHead == null) { //first list
				pHead = tHead;
				pTail = tTail;
			} else {
				pTail.next = tHead;
				pTail = tTail;
			}

			tHead = null;
			tTail = null;
			len = len - k;
		}

		pTail.next = curr;
		return pHead;
	}

	public int size(ListNode node) {
		int sz = 0;
		ListNode curr = node;

		while (curr != null) {
			sz++;
			curr = curr.next;
		}

		return sz;
	}

	public void addFirst(ListNode node) {
		if (tHead == null) {
			tHead = node;
			tTail = node;
		} else {
			node.next = tHead;
			tHead = node;
		}
	}

	//40. 108. Convert Sorted Array to Binary Search Tree
	public TreeNode sortedArrayToBST(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;
		return construct(nums, lo, hi);
	}


	public TreeNode construct(int[]nums , int lo , int hi) {
		if (lo > hi)return null;

		int mid = lo + (hi - lo) / 2;
		TreeNode node = new TreeNode(nums[mid]);

		node.left = construct(nums, lo, mid - 1);
		node.right = construct(nums, mid + 1, hi);

		return node;

	}

	//41. Construct from unsorted array

	//42. 724. Find Pivot Index / Equilibrium Index

	//A1. Brute - 2 prefix sums lsum and rsum - ret first idx with lsum = rsum
	//A2.
	public int pivotIndex(int[] nums) {
		int sum = 0; //whole arr sum
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		int preSum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (preSum * 2 == sum - nums[i]) { //if we had sum-a[i]/2 at left
				return i;
			}
			preSum += nums[i];
		}

		return -1;
	}

	//43. Pacific Atlantic Water Flow - done
	//Use 2 dfs

	//43. Boundary Traversal
	static ArrayList<Integer> list;
	static ArrayList<Integer> rtSide;

	ArrayList <Integer> boundary(Node root) {
		list = new ArrayList<>();
		rtSide = new ArrayList<>();

		if (root == null) return list;
		list.add(root.data);

		if (isLeaf(root)) return list;

		getLeftSide(root.left);
		getLeaves(root);
		getRightSide(root.right);

		for (int i = rtSide.size() - 1; i >= 0; i--) {
			list.add(rtSide.get(i));
		}
		return list;
	}

	public static boolean isLeaf(Node root) {
		if (root.left == null && root.right == null) {
			return true;
		}
		return false;
	}

	public static void getLeftSide(Node root) {
		Node curr = root;

		while (curr != null) {

			if (isLeaf(curr)) {
				return;
			}

			list.add(curr.data);
			if (curr.left != null) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
	}

	public static  void getLeaves(Node root) {
		if (root == null) return;

		if (isLeaf(root)) {
			list.add(root.data);
		}

		getLeaves(root.left);
		getLeaves(root.right);
	}

	public static void getRightSide(Node root) {
		Node curr = root;

		while (curr != null) {

			if (isLeaf(curr)) {
				return;
			}

			rtSide.add(curr.data);

			if (curr.right != null) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}
	}

	// 44. Rotten Oranges

	//45. Longest Palindromic Substring

	//46. Minimum subset sum difference - done KnapSack dp
	// Returns the minimum value of the difference of the two sets.
	public static int findMin(int arr[], int n) {
		int sum = 0;
		for (int i = 0 ; i < n ; i++ ) {
			sum += arr[i];
		}

		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0 ; i < n + 1 ; i++) {
			for (int j = 0; j < sum + 1 ; j++) {
				if (i == 0 && j == 0)dp[i][j] = true;
				else if (j == 0)dp[i][j] = true;
				else if (i == 0)dp[i][j] = false;
				else {
					if (dp[i - 1][j] == true)dp[i][j] = true;
					else if (arr[i - 1] <= j) {
						dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
					}
				}
			}
		}

		int diff = Integer.MAX_VALUE;;

		// Find the largest j such that dp[n][j]
		// is true where j loops from sum/2 t0 0  ,
		// sum - 2*j shd be min coz susbet shd be closest to sum/2
		for (int j = sum / 2; j >= 0; j--) {
			if (dp[n][j] == true) {
				if (diff > sum - (2 * j))diff = sum - 2 * j;

			}
		}
		return diff;
	}

	//47. Longest Valid Parentheses



	//48. 2096. Step-By-Step Directions From a Binary Tree Node to Another (VVVVI ques)

	//get path to st , path to end , remove common prefix (same as LCA)

	// 3 tree ques - LCA + find root to node / node to root path + get depth etc.
	public String getDirections(TreeNode root, int startValue, int destValue) {
		StringBuilder s = new StringBuilder(), d = new StringBuilder();
		find(root, startValue, s);
		find(root, destValue, d);
		int i = 0, max_i = Math.min(d.length(), s.length());
		while (i < max_i && s.charAt(s.length() - i - 1) == d.charAt(d.length() - i - 1))
			++i;
		return "U".repeat(s.length() - i) + d.reverse().toString().substring(i);
	}

	private boolean find(TreeNode n, int val, StringBuilder sb) {
		if (n.val == val)
			return true;
		if (n.left != null && find(n.left, val, sb))
			sb.append("L");
		else if (n.right != null && find(n.right, val, sb))
			sb.append("R");
		return sb.length() > 0;
	}


	// 49. 698. Partition to K Equal Sum Subsets
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		Arrays.sort(nums); //without sorting TLE !!

		if (k <= 0 || k > nums.length || sum % k != 0) return false;

		boolean[] vis = new boolean[nums.length];

		return backTrack(nums.length - 1, k, 0, sum / k, vis, nums);
	}

	public boolean backTrack(int idx, int k, int currSum, int reqSum, boolean[] vis, int[] arr) {
		if (k == 1) return true;

		//start from 0 idx bcz ele inc in prev set already marked vis
		if (currSum == reqSum) return backTrack(arr.length - 1, k - 1, 0, reqSum, vis, arr);

		for (int i = idx; i >= 0; i--) {
			if (vis[i] == false) {
				if (currSum + arr[i] > reqSum) continue;
				vis[i] = true;

				if (backTrack(i - 1, k, currSum + arr[i], reqSum, vis, arr) == true) {
					return true;
				}
				vis[i] = false;
			}
		}

		return false;
	}


	// 50. Smallest range k lists
	public int[] smallestRange(List < List < Integer >> nums) {
		PriorityQueue < rPair > pq = new PriorityQueue < > ();
		int max = -(int) 1e9;

		//add first 3 vals
		for (int i = 0; i < nums.size(); i++) {
			int val = nums.get(i).get(0);
			pq.offer(new rPair(val, i, 0));
			max = Math.max(val, max);
		}

		int sp = 0;
		int ep = 0;
		int length = (int) 1e9;

		while (true) {
			rPair rem = pq.poll();
			int minV = rem.val;
			int maxV = max;

			if (maxV - minV < length) { //update
				sp = minV;
				ep = maxV;
				length = maxV - minV;
			}

			//add next val from list of removed val
			if (rem.c + 1 < nums.get(rem.r).size()) {
				int val = nums.get(rem.r).get(rem.c + 1);
				pq.offer(new rPair(val, rem.r, rem.c + 1));
				max = Math.max(max, val);
			} else {
				break;
			}
		}

		int[] res = {
			sp,
			ep
		};
		return res;
	}

	private class rPair implements Comparable < rPair > {
		int val;
		int r;
		int c;

		public rPair() {}

		public rPair(int val, int r, int c) {
			this.val = val;
			this.r = r;
			this.c = c;
		}

		public int compareTo(rPair o) {
			return this.val - o.val;
		}
	}

	// 51.
	public class Pair {
		char ch;
		int fq;

		Pair() {}

		Pair(char ch, int fq) {
			this.ch = ch;
			this.fq = fq;
		}
	}

	public String reorganizeString(String s) {
		// 1953. Maximum Number of Weeks for Which You Can Work - Greedy
		// Leetcode 358
		int max = 0;
		int all = s.length();

		Map < Character, Integer > map = new HashMap < > ();

		PriorityQueue < Pair > pq = new PriorityQueue < > ((a, b) -> { //char , freq
			return b.fq - a.fq; //max heap based on freq
		});

		for (char ch : s.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
			if (map.get(ch) > max) max = map.get(ch);
		}

		int remain = all - max;
		if (max > remain + 1) return ""; //pair = max -1 with all rem and in the end 1 more ch

		for (char key : map.keySet()) {
			pq.offer(new Pair(key, map.get(key)));
		}

		//now ans making is possible
		StringBuilder sb = new StringBuilder();

		//greedily choose max freq ch to add to ans (max ch shd be curr max not overall max)
		//ex : a-5 , b-4 , c-3 , after aba  now a - 4 , b-3 , c-3 so max is a
		while (pq.size() > 0) {
			Pair rem = pq.poll();

			if (sb.length() == 0 || sb.charAt(sb.length() - 1) != rem.ch) { //prev ch not same
				sb.append(rem.ch);
				rem.fq = rem.fq - 1;
			} else {         //if sb tail is same as first , pop again before push f1 back
				Pair rem2 = pq.poll();
				sb.append(rem2.ch);
				rem2.fq = rem2.fq - 1;

				if (rem2.fq != 0) pq.offer(rem2);
			}

			if (rem.fq != 0) pq.offer(rem);

		}
		return sb.toString();
	}

	// 52. Sort a k sorted array





































	//26 Feb 2022 =================================================================================

	//InterviewBit Trees - 34Q Amazon

	//balanced O(n) sol using ht
	public boolean isBalanced(TreeNode root) {
		// |lh-rh|<=1;
		if (root == null) return true;
		boolean[] ans = new boolean[1];
		ans[0] = true;
		height(root, ans);
		return ans[0];
	}

	public int height(TreeNode node, boolean[] ans) {
		if (node == null) return 0;
		int lh = height(node.left, ans);
		int rh = height(node.right, ans);
		if (Math.abs(lh - rh) > 1) ans[0] = false;
		return Math.max(lh, rh) + 1;
	}

	//symmetric - mirror of itself
	// cousins
	// identical

	//InterviewBit Two Pointers - Amazon 8Q

	//27 Feb 2022

	//InterviewBit Graphs - Algos  - Amazon 13Q

	// Cycle Undirected
	// https://www.interviewbit.com/problems/cycle-in-undirected-graph/

	public int solve(int A, int[][] B) {
		int[] par = new int[A];
		for (int i = 0; i < A; i++) {
			par[i] = i;
		}

		//Union Find
		for (int[] edge : B) {
			int u = edge[0] - 1;
			int v = edge[1] - 1;

			int p1 = findPar(u, par);
			int p2 = findPar(v, par);

			if (p1 != p2) {  //no cycle
				par[p2] = p1;
			} else {         // a , b have edge with c , now they also have edge -> cycle = true
				return 1;
			}
		}

		return 0;
	}

	public int findPar(int u, int[] par) {
		if (par[u] == u)return u;
		return par[u] = findPar(par[u], par);
	}

	//Course Schedule 1 + Topological Sort + Cycle in Directed

	public boolean canFinish(int N, int[][] edges) {
		ArrayList < Integer > [] graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList < > ();
		}
		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
		}

		return !topSort(graph);
	}

	//dfs cycle code - for every unvis* vtx apply dfs
	public boolean topSort(ArrayList < Integer > [] graph) {
		int[] vis = new int[graph.length];
		boolean isCycle = false;

		for (int i = 0; i < graph.length; i++) {
			if (vis[i] == -1) {
				isCycle = isCycle || dfsTopo(graph, i, vis);
			}
		}

		return isCycle;
	}

	//fun() dfs topo , -1 = unvis , 0 = curr , 1 = backtrack
	public boolean dfsTopo(ArrayList < Integer > [] graph, int src, int[] vis) {
		vis[src] = 0; //curr path
		boolean cycle = false;

		for (int nbr : graph[src]) {
			if (vis[nbr] == -1) {
				cycle = cycle || dfsTopo(graph, nbr, vis);
			} else if (vis[nbr] == 0) { //part of curr path vis again
				return true;
			}
		}

		vis[src] = 1; //backtrack
		return cycle;
	}

	//Number of Islands - DFS
	//Black Shapes - NOI same
	// Commutable Islands - MST Prims/Kruskals

	//Valid Path - Circles within rectangles

	//Knight on Chess Board

	//Path in Matrix - Walls and Gates Type ques



	//InterviewBit Heaps  - Amazon 6Q


	//Stacks Queue

	//Implement queue using stacks****************************************************

	//A1. stack s1 , s2   , T : O(N) , S : O(2N)
	// push - s1->s2 , push in s1 , s2 -> s1

	//A2. Amortized O(1) - sometimes O(n) mostly O(1)

	//Two Stacks input , output
	// push - push in input
	// pop() / peek()
	// if output not empty - output.pop()/top() O(1) ,
	// else input->output , output.pop()/top() O(n)

	private final Stack < Integer > input;
	private final Stack < Integer > output;
	/** Initialize your data structure here. */
	public MyQueue() {
		input = new Stack();
		output = new Stack();
	}

	/** Push element x to the back of queue. */
	public void push(int x) {
		input.push(x); //O(1)
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
		if (output.size() > 0) {
			return output.pop();  //O(1)
		} else {
			while (input.size() > 0) {    //O(n)
				output.push(input.pop());
			}
			return output.pop();
		}
	}

	/** Get the front element. */
	public int peek() {
		if (output.size() > 0) {
			return output.peek();
		} else {
			while (input.size() > 0) {
				output.push(input.pop());
			}
			return output.peek();
		}
	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
		return input.isEmpty() && output.isEmpty();
	}


	//Min - Stack  O(N) Space****************************************************
	long min;
	Stack < Long > st = new Stack < > ();

	/** initialize your data structure here. */
	public MinStack() {
		st = new Stack();

	}

	public void push(int val) {
		if (st.size() == 0) {
			st.push((long) val);
			min = val;
		} else {
			if (val >= min) {
				st.push((long) val);
			} else {
				long nVal = val + (val - min); //val is new min
				min = val;
				st.push(nVal);
			}
		}
	}

	public void pop() {
		if (st.peek() >= min)
			st.pop();
		else { //top < currMin -> val is a modified val -> at this pt min was updated
			long rv = min; //rollback to prev min
			min = 2 * min - st.peek(); //2*currMin - (2*currMin - prevMin) = prevMin
			st.pop();
		}
	}

	public int top() {
		long top = st.peek();
		if (top >= min)
			return (int) top;
		else {
			return (int) min;
		}
	}

	public int getMin() {
		return (int) min;
	}

	//https://www.geeksforgeeks.org/expression-contains-redundant-bracket-not/**********

	//1. if ch = ) and top = ( , unwanted braces found
	//2. else while(peek()!='(')  pop() , but if no operator found , bracket is useless
	//  ex.  (abc) + b;

	//387. First Unique Character in a String****************************************************

	//use map/arr , when ch is got , update its idx in map
	// after map filling - all ch,last ocuurence idx is stored
	//iterate on str if(i == map.get(ch)) -> first idx = last idx - it is ans

	//Single Iteration + Count Array

	//Balanced Parenthesis ****************************************************
	//A1. if closing then check
	//A2. at every opening , push closing
	// if closing  -> if(st.isEmpty() || st.pop() != ch) {return false};


	//Sliding Window Max - A1. using stacks********************************************************
	// find nge , and go to next nge until it is inside the window

	//A2. Deque
	//always peek of dq will have curr win max , if max out of win , pop once
	//add first k elements , add first max
	//loop from i=k to end and add rem ans

	//Trapping Rainwater************************************************************************

	//A1. Using 2 arr , lmax and rmax O(N) space
	//A2. O(1) Space


	//Largest Rectangle in Histogram******************************************


	// Two Pointers***************************************************************************

	//Merge 2 sorted arr/lists

	//Remove dups from sorted arr
	public int removeDuplicates(int[] nums) {
		if (nums.length == 0) return 0;
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if (nums[i] != nums[j]) {

				nums[i + 1] = nums[j];
				i++;
			}
		}
		return i + 1;
	}

	//Remove Elements - Leetcode
	public int removeElement(int[] nums, int val) {
		int n = nums.length;
		int i = 0, j = 0;

		while (j < n) {
			if (nums[j] != val) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;

				i++;
				j++;
			} else {
				j++;
			}
		}

		return i;
	}

	//Max Consecutive Ones - 1
	public int findMaxConsecutiveOnes(int[] nums) {
		int max = 0, count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				count++;
				max = Math.max(max, count);
			} else {
				count = 0;
			}
		}
		return max;
	}

	//Sliding Window approach
	// Maximum Consecutive Ones - 1 / Leetcode 487.
	public static int solution(int[] arr) {
		int st = -1, end = -1, len = -(int)1e9, zero = 0;

		while (end < arr.length - 1) {
			end++;
			if (arr[end] == 0) {
				zero++;
			}

			while (zero > 1) {
				st++;
				if (arr[st] == 0) {
					zero--;
				}
			}

			len = Math.max(len, end - st);
		}

		return len;
	}

	// Maximum Consecutive Ones - III - Sliding Window - acquire and release
	public int longestOnes(int[] arr, int k) {
		int st = -1, end = -1, len = -(int)1e9, zero = 0;

		while (end < arr.length - 1) {
			end++;
			if (arr[end] == 0) {
				zero++;
			}

			//window is inalid / max valid again
			while (zero > k) {
				st++;
				if (arr[st] == 0) {
					zero--;
				}
			}

			len = Math.max(len, end - st); //max here
		}

		return len;
	}

	//Pair With Given Diff - (negatives also)
	//What if negatives are present ?

	//many cases - all +ve, +/- , all -ve
	//sum +ve / -ve

	// if diff is -ve , just make it +ve
	// if pair(i,j) is found with diff = d = j-i
	// it means pair i-j has diff = -d
	public int solve(ArrayList<Integer> arr, int target) {
		Collections.sort(arr);
		if (target < 0) target = -target;
		int lo = 0;
		int hi = 1;

		while (hi < arr.size() && lo <= hi ) {
			int diff = arr.get(hi) - arr.get(lo);
			if (diff == target && lo != hi) {
				return 1;
			} else if (diff > target) {
				lo++;
			} else {
				hi++;
			}
		}

		return 0;
	}

	//167. Two Sum II - Input Array Is Sorted / Pair With Given Sum
	public int[] twoSum(int[] arr, int tar) {
		int i = 0, j = arr.length - 1;
		int[] ans = new int[2];
		//find pair with given diff (interviewbit) - similar
		while (j >= 0 && i < j) {
			int sum = arr[i] + arr[j];
			if (sum == tar && i != j) { //i!=j if sum = 2*arr[i] // avoid same ele
				ans[0] = i + 1;
				ans[1] = j + 1;
				return ans;
			} else if (sum > tar) {
				j--;
			} else {
				i++;
			}
		}

		return ans;
	}

	//Container With Most Water - Leetcode 11
	public int maxArea(int[] height) {
		int n = height.length;
		int l = 0, r = n - 1, maxArea = 0;

		while (l < r) {
			int area = Math.min(height[l], height[r]) * (r - l);
			maxArea = Math.max(area, maxArea);
			if (height[l] < height[r]) {
				l++;
			} else r--;
		}
		return maxArea;
	}


	//HashMaps*****************************************************************

	//2 Sum - Map - And Similar

	//2 Sum - single pair (2 ptrs)
	//2 Sum - all Unique Pairs
	public static List<List<Integer>> twoSum(int[] arr, int target) {
		int n = arr.length;
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(arr);

		int l = 0, r = n - 1;

		while (l < r) {

			//avoid repetition
			if (l != 0 && arr[l] == arr[l - 1]) {  //skip dups
				l++;
				continue;
			}

			int sum = arr[l] + arr[r];
			if (sum == target) {
				List<Integer> temp = new ArrayList<>();
				temp.add(arr[l]);
				temp.add(arr[r]);
				res.add(temp);
				l++;
				r--;
			} else if (sum > target) {
				r--;
			} else {
				l++;
			}
		}

		return res;
	}

	//3 Sum - fix one , call 2Sum
	//4 Sum - fix one call 3 Sum
	//K Sum - recursive , fix one , call k-1 Sum ,  base case is 2Sum

	//Longest Consecutive Sequence

	//Brute Force - O(n^3)
	//Select every element one by one , find ele+1 in array while it exists
	//for(select element)
	// 	while(arr contains (ele+1))
	// 		ans += 1 ; ele +=1

	//Sorting n*logn

	//Set O(n)

	public int longestConsecutive(int[] nums) {
		Set < Integer > set = new HashSet < > ();
		for (int num : nums) {
			set.add(num);
		}

		int len = 1;
		int maxLen = 0;

		//find smallest val from which seq can start i.e val-1 does not exist
		//start building sequence by finding val+1 , it is surely longest with val as st
		//also remove from set
		//build another sequence when this ends

		for (int num : nums) {
			if (!set.contains(num - 1)) {
				//it shd be smallest in sequence then we look for ..

				while (set.contains(num + 1)) {
					len++;
					num += 1;
					set.remove(num);
				}

				if (len > maxLen) {
					maxLen = len;
				}
			}

			len = 1;
		}

		return maxLen;
	}

	//Valid Sudoku - Use HashSet

	//Group Anagrams
	//A1. Use Map<Map<Character,Integer>,List<String>> , map of freq map,strings
	//A2. Sort the string ans use it as key Map<String,List>
	//A3. Use Char/Int array of 26 size and convert to string for key
	public List < List < String >> groupAnagrams(String[] strs) {
		Map < String, List < String >> map = new HashMap < > ();

		for (String str : strs) {

			char[] freq = new char[26];
			for (int i = 0; i < str.length(); i++) {
				freq[str.charAt(i) - 'a']++;
			}

			String key = new String(freq);

			if (map.containsKey(key)) {
				List list = map.get(key);
				list.add(str);
				map.put(key, list);
			} else {
				List < String > list = new ArrayList < > ();
				list.add(str);
				map.put(key, list);
			}
		}

		List < List < String >> res = new ArrayList < > ();
		for (List < String > l : map.values()) {
			res.add(l);
		}
		return res;
	}


	//Acquire Release Strategy - (Sliding Window + HashMap + Two Pointers)

	//3. Longest Substring Without Repeating Characters

	//Using Map
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0)return 0;

		int[] map = new int[128];
		int acq = -1, rel = -1, len = 0;

		while (acq < s.length() - 1) {
			acq++;
			char ch = s.charAt(acq);   //acquire the chars
			map[ch]++;                 //store freq

			while (map[ch] > 1) {
				//if any ch has > 1 freq , it is invalid win, make it val
				rel++;
				char rem = s.charAt(rel);
				map[rem]--;
			}
			//if we reach here, it is surely valid win , update max
			len = Math.max(len, acq - rel);
		}

		return len;
	}

	//Using Set
	public int lengthOfLongestSubstring(String s) {
		int i = 0, j = 0, count = 0, max = 0;
		Set<Character> set = new HashSet<>();

		while (j < s.length()) {
			char ch1 = s.charAt(j);
			char ch2 = s.charAt(i);
			if (!set.contains(ch1)) { //not seen
				count++;              //len++
				set.add(ch1);         //store seen
				max = Math.max(count, max);  //update max
				j++;                  //expand window
			} else {
				count--;			 //dec len of window
				set.remove(ch2);     //if seen means win invalid,
				i++;				 // start releasing from back ans validate again
			}
		}

		return max;
	}

	//Zero Sum Subarray - Similar -> (Prefix Sum + HashMap)


	//Linked List ************************************************(no code)

	//Arrays - LeetCode Patterns

	//238. Product of Array Except Self
	//A1. Use two arr pre[] and suf[] products
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		int prod = 1;
		//need pre[i] * suf[i]

		// store all prefix prod in res array itself pre[i]
		for (int i = 0; i < n; i++) {
			res[i] = prod;  //when i == 0 , prod is 1 coz no ele at left
			prod *= nums[i];
		}

		// multiply all suf[i] on stored pre[i] , while looping
		prod = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] *= prod;  //n-1 prod is 1 , no ele on right
			prod *= nums[i];
		}

		return res;

		//what we did with pre[] prod and suf[] pro can be easily done on the res array itself
	}

	//73. Set Matrix Zeroes / https://www.geeksforgeeks.org/a-boolean-matrix-question/
	//Brute use 2 arr row[n] , col[m] size
	// in optimization 0th cell overlaps so use bool var

	// store states of each row in the first of that row, and store states
	// of each column in the first of that column. Because the state of row0
	// and the state of column0 would occupy the same cell,
	// use two bool var , if 0 is at row0 , row = true , same for col
	// fill all matrix except row 0 and col 0 , separately fill r0 and c0

	//268. Missing Number
	//size of arr = n , so only n-1 ele can exist
	//so sum of first 0...n elements - sum(arr) = missing num

	//136. Single Number
	public int singleNumber(int[] nums) {
		int a = 0;
		for (int num : nums) {
			a ^= num;
		}
		return a;
	}

	// Range of 1...n elements , marking the indexex****************************************

	// 287. Find the Duplicate Number (modification not allowed)
	//Linked List Cycle method , also know proof

	//448. Find All Numbers Disappeared in an Array - map indexes
	//mark idx , if already vis idx , -> this nums[idx] is missing

	// 442. Find All Duplicates in an Array
	//mark idx , if already vis idx , -> this nums[i] is repeated

	// 41. First Missing Positive
	public int firstMissingPositive(int[] nums) {
		// first positive missing starting from 1
		//A1. search 1 , 2... n O(n^2)
		//A2. sort and start checking from first +ve number
		//A3. imp observation ideally for n size 1..n must exist
		// if any num is missing it is ans , else n+1 is ans

		//Step 1 . Mark elements which are out of range and check is 1 exists
		boolean isOnePresent = false;
		int n = nums.length;

		for (int i = 0; i < n; i++) {
			if (nums[i] == 1) {
				isOnePresent = true;
			}
			if (nums[i] < 1 || nums[i] > n) {
				nums[i] = 1; //mark with 1
			}
		}

		//Step 2 . if 1 absent , it is ans
		if (isOnePresent == false) {
			return 1;
		}

		//Step 3. Mark corresponding indexes
		for (int i = 0; i < n; i++) {
			int idx = Math.abs(nums[i]) - 1;
			nums[idx] = -Math.abs(nums[idx]);
		}

		//Step 4. Now first +ve/unmarked/vis idx is missing
		for (int i = 0; i < n; i++) {
			if (nums[i] > 0) return i + 1;
		}

		//if still not found -> all 1..n are present
		return n + 1;
	}

	// Maximum no of 1's row  - GFG   T : O(nlog(m)) binary search every row
	// all the rows are sorted
	public static int maxOnes (int Mat[][], int N, int M) {
		int max = 0, res = 0;
		for (int i = 0; i < N; i++) {
			int lb = left_bound(Mat, i);
			if (lb != -1) {
				int ct = M - lb;
				if (ct > max) {
					res = i;
					max = ct;
				}
			}
		}

		return res;
	}

	public static int left_bound(int[][] mat, int r) {
		int lo = 0, hi = mat[0].length - 1, lb = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mat[r][mid] == 1) {
				lb = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return lb;
	}
	//TRIE
	//Implement - done
	//Design add/search with (.) -d
	//Word Search ii  - done
	//Longest word in Dict - done (sort+set)/Trie
	//Concatenated words  - done(dp) sort + set + dp , TRIE Pending

	//Stock Buy Sell
	//1/2/fees/cooldown - done

	//Dynamic Prog revision - (InterviewBit + Leetcode Patterns)


	//Intervals ques

	//Majority Elements



	//String ?

	//LeetCode Hard Tag (Patterns)

	// unique ch in all substr of given str (DP)

	//k reverse (LL)

	//Sudoku solve (Backtracking)
	//N QUeens   (Backtracking)

	//295. Find Median from Data Stream - heap  done**
	// Sliding Win Median  - heap  done**
	// Max Freq Stack - heap
	// Merge k lists - heap
	// Range cover k lists - heap
	// Course Scedule iii - heap

	//meadian of 2 sorted arr - merge 2 sorted O(n+m) + Space(m+n), Binary Search O(log(n+m))

	//Sliding Win Max - Stack

	//Min Window Substr - Map + Sliding + Two Ptrs

	//Backtracking

}
