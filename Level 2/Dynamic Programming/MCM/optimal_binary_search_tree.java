import java.util.Arrays;
public class optimal_binary_search_tree {

	// Optimal Binary Search Tree | DP-24
	// https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

	public static int getRangeSum(int l , int r , int[] freq) {
		return freq[r] - freq[l - 1];
	}


	//T : O(n^3) , without preSum query , O(n) loop every time for calc the range sum(si,ei)
	// T: O(n^4);
	public static int opt_Cost(int[] nodes , int[] preSum , int si , int ei , int[][] dp) {
		//base case handle in loop

		//look-up
		if (dp[si][ei] != -1) {
			return dp[si][ei];
		}

		int myRes = (int)1e9;
		//make all the nodes from 0..n as root and recursively build left and rt sub-tree
		for (int cut = si; cut <= ei; cut++) {

			//handle -ve base case , if si==cut i.e no ele to left so ret 0 , same for rt call
			int lCost = cut == si  ? 0 : opt_Cost(nodes, preSum, si, cut - 1, dp);
			int rCost = cut == ei  ? 0 : opt_Cost(nodes, preSum, cut + 1, ei, dp);

			int selfCost = si == 0 ? preSum[ei] : preSum[ei] - preSum[si - 1];
			int rec_res = lCost + selfCost + rCost;
			myRes = Math.min(myRes, rec_res);
		}

		return dp[si][ei] = myRes;
	}


	// we can add the cost of nodes * level
	// for tree 10,12,20
	// 	optimal ans is 20 .. 10 .. 12
	// 	50*1 + 34*2 + 8*3;

	// 	or ,

	// 	we return presum si...ei at each level , so nth lvl value is auto.. added n times

	// 	cost from (0,2) - 50+34+8;  - lvl 1
	// 	cost from (0,1) - 34+8;     - lvl 2
	// 	cost from (1,1) - 8         - lvl 3

	//**************************************************************************************************

	public static void opt_Cost() {
		int keys[] = {10, 12, 20};
		int freq[] = {34, 8, 50};

		//ans = 142;

		// int keys[] = {1, 2, 3, 4, 5};
		// int freq[] = {10, 20, 30, 40, 50};

		int n = keys.length;

		for (int i = 1; i < n; i++) {
			freq[i] += freq[i - 1];
		}

		System.out.println(Arrays.toString(freq));

		int[][] dp = new int[n][n];
		for (int[] row : dp)Arrays.fill(row, -1);

		System.out.println(opt_Cost(keys, freq, 0, n - 1, dp));

	}

	public static void main(String[] args) {
		opt_Cost();
	}
}