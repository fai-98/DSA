class Solution {

	// Leetcode 718. Maximum Length of Repeated Subarray
	// This problem is Identical to Longest Common Substring - Parent Problem (Longest Common Subsequence)

	public int findLength(int[] nums1, int[] nums2) {
		int n = nums1.length, m = nums2.length, max = -(int) 1e9;
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		return max == -(int) 1e9 ? 0 : max;
	}

	/*Recursive
	public int lcs(int[] nums1, int[] nums2, int idx1, int idx2, int[] res) {
	    if (idx1 <= 0 || idx2 <= 0) {
	        return 0;
	    }

	    int len = 0;
	    if (nums1[idx1 - 1] == nums2[idx2 - 1]) {
	        len = 1 + lcs(nums1, nums2, idx1 - 1, idx2 - 1, res);
	        res[0] = Math.max(len, res[0]);
	    }

	    //explore all corresponding prefixes for a particular char
	    lcs(nums1, nums2, idx1 - 1, idx2, res);
	    lcs(nums1, nums2, idx1, idx2 - 1, res);

	    return len;

	}
	*/
}