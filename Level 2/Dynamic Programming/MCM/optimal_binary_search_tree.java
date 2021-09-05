import java.util.Arrays;
public class optimal_binary_search_tree {

	// Optimal Binary Search Tree | DP-24
	// https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

	public static int getRangeSum(int l , int r , int[] freq) {
		return freq[r] - freq[l - 1];
	}

	public static int opt_Cost(int[] nodes , int[] freq , int si , int ei , int[][] dp) {  //ei = n-1

		//look-up
		if (dp[si][ei] != -1) {
			return dp[si][ei];
		}

		int myRes = (int)1e9;
		//make all the nodes from 0..n as root and recursively build left and rt sub-tree
		for (int cut = si; cut <= ei; cut++) {

			//handle -ve base case , if si==cut i.e no ele to left so ret 0 , same for rt call
			int lRes = si == cut ? 0 : opt_Cost(nodes , freq , si , cut - 1, dp);
			int rRes = cut == ei ? 0 : opt_Cost(nodes , freq , cut + 1, ei, dp);

			int eval = lRes + getRangeSum(si, ei, freq) + rRes;
			myRes = Math.min(eval, myRes);
		}

		return dp[si][ei] = myRes;
	}

	public static void opt_Cost() {
		int keys[] = {10, 12, 20};
		int freq[] = {34, 8, 50};
		int n = keys.length;

		for (int i = 1; i < n; i++) {
			freq[i] += freq[i - 1];
		}

		int[][] dp = new int[n][n];
		for (int[] row : dp)Arrays.fill(row, -1);

		System.out.println(opt_Cost(keys, freq, 0, n - 1, dp));

	}

	public static void main(String[] args) {
		opt_Cost();
	}
}