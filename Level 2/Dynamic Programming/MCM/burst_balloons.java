import java.util.Arrays;

public class burst_balloons {


	public static  int maxCoins_memo(int[] nums, int si, int ei, int[][] dp) {
		if (dp[si][ei] != 0) {
			return dp[si][ei];
		}

		//left alive balloon and right alive balloon
		int lval = si == 0 ? 1 : nums[si - 1]; //if first bal.. then asssume 1 on left
		int rval = ei == nums.length - 1 ? 1 : nums[ei + 1];

		int myRes = -(int) 1e9;
		for (int cut = si; cut <= ei; cut++) {
			int lRes = cut == si ? 0 : maxCoins_memo(nums, si, cut - 1, dp);
			int rRes = cut == ei ? 0 : maxCoins_memo(nums, cut + 1, ei, dp);

			int recRes = lRes + lval * nums[cut] * rval + rRes;
			myRes = Math.max(recRes, myRes);
		}

		return dp[si][ei] = myRes;
	}

	public static  int maxCoins_tab(int[] nums, int SI, int EI, int[][] dp) {
		int n = nums.length;

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; ei++, si++) {
				//left alive balloon and right alive balloon
				int lval = si == 0 ? 1 : nums[si - 1];
				int rval = ei == nums.length - 1 ? 1 : nums[ei + 1];

				int myRes = -(int) 1e9;
				for (int cut = si; cut <= ei; cut++) {
					int lRes = cut == si ? 0 : dp[si][cut - 1];
					int rRes = cut == ei ? 0 : dp[cut + 1][ei];

					int recRes = lRes + lval * nums[cut] * rval + rRes;
					myRes = Math.max(recRes, myRes);
				}

				dp[si][ei] = myRes;

			}
		}

		return dp[SI][EI];
	}

	public static void main(String[] args) {
		int[] nums = {3, 1, 5, 8};
		int n = nums.length;
		int[][] dp = new int[n][n];
		maxCoins_tab(nums, 0, n - 1, dp);
		display(dp);
	}

	public static void display(int[][] arr) {
		for (int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
	}

}