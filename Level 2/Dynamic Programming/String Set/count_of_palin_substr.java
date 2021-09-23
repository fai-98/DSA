class Solution {

	// Leetcode 647. Palindromic Substrings

	public int countSubstrings(String s) {
		int n = s.length(), ct = 0;
		boolean[][] dp = new boolean[n][n];

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; ei++, si++) {
				if (gap == 0) {
					dp[si][ei] = true;
					ct++;
				} else if (gap == 1 && s.charAt(si) == s.charAt(ei)) {
					dp[si][ei] = true;
					ct++;
				} else {
					if (s.charAt(si) == s.charAt(ei) && dp[si + 1][ei - 1] == true) {
						dp[si][ei] = true;
						ct++;
					}
				}
			}
		}
		return ct;
	}
}