import java.util.Arrays;
public class Josh {

	public static void partition(int[] arr , int k) {
		int lo = 0 , mid = 0 , hi = arr.length - 1;
		// 3 way partition/Dutch National Flag Algo
		// 0...lo-1 less
		//lo..mid-1 more
		//mid...hi-1 equal
		//hi...end unknown

		while (mid <= hi) {
			if (arr[mid] < k) {
				int temp = arr[lo];
				arr[lo] = arr[mid];
				arr[mid] = temp;
				lo++;
			} else if (arr[mid] > k) {
				mid++;
			} else {
				int temp = arr[hi];
				arr[hi] = arr[mid];
				arr[mid] = temp;
				hi--;
			}
		}
	}

	//longest even odd subarray length
	public static int longestEvenOddSum(int[] arr , int n) {
		int ct = 1 , max = 0;
		for (int i = 1; i < n; i++) {
			if ((arr[i - 1] + arr[i]) % 2 == 1) {
				ct++;
			} else {
				max = Math.max(max, ct);
				ct = 1;
			}
		}
		return max;
	}

	// Stock Buy Sell to Maximize Profit
	public int maxProfit_(int[] prices) {
		int n = prices.length;
		int[] dp = new int[n];
		dp[0] = 0;

		int prev = prices[0];
		for (int i = 1; i < n; i++) {
			if (prices[i] > prev) {
				dp[i] += prices[i] - prev + dp[i - 1];
			} else {
				dp[i] = dp[i - 1];
			}
			prev = prices[i];
		}

		return dp[n - 1];
	}

	//Balanced Binary Tree
	public boolean isBalanced(TreeNode root) {
		// |lh-rh|<=1;
		if (root == null) return true;
		int lh = height(root.left);
		int rh = height(root.right);
		if (Math.abs(lh - rh) > 1) return false;
		return isBalanced(root.left) && isBalanced(root.right);
	}

	public int height(TreeNode node) {
		if (node == null) return 0;
		int lh = height(node.left);
		int rh = height(node.right);
		return Math.max(lh, rh) + 1;
	}

	// Remove BST keys outside the given range - GFG


	public static void main(String[] args) {
		// I/P- [6, 7, 2, 5, 4, 9, 8, 5], k=5
		// <k , > k , ==k
		// O/P- [2, 4, 6, 7, 9, 8, 5, 5]

		int[] arr = {6, 7, 2, 5, 4, 9, 8, 5};
		// partition(arr, 0, arr.length - 1, 5);
		int a[] = { 1, 2, 3, 4, 5, 7, 8 };

		System.out.println(longestEvenOddSum(a, 7));


	}
}