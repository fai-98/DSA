public class LCSVariations {


	// 1458. Max Dot Product of Two Subsequences*****************************************************
	public int maxDotProduct(int[] nums1, int[] nums2) {
		int n = nums1.length, m = nums2.length;
		int[][] dp = new int[n + 1][m + 1];
		for (int[] row : dp) Arrays.fill(row, -(int) 1e9);
		return maxProd(nums1, n, nums2, m, dp);
	}

	public int maxProd(int[] nums1, int n, int[] nums2, int m, int[][] dp) {
		if (n == 0 || m == 0) {
			return dp[n][m] = -(int) 1e8; //no ans so store invalid val (to indicate we solved thisstate)
		}

		if (dp[n][m] != -(int) 1e9) {
			return dp[n][m];
		}

		int val = nums1[n - 1] * nums2[m - 1]; //ignore previous F(.., ..) because it might be better
		// to not add it at all (i.e. if it is negative).

		int acceptBoth = maxProd(nums1, n - 1, nums2, m - 1, dp) + (nums1[n - 1] * nums2[m - 1]); //2                                                                                               nums + recur
		int yesNo = maxProd(nums1, n - 1, nums2, m, dp);
		int noYes = maxProd(nums1, n, nums2, m - 1, dp);

		return dp[n][m] = Math.max(Math.max(val, acceptBoth), Math.max(noYes, yesNo));
	}

	public int maxProd_Tab(int[] nums1, int N, int[] nums2, int M, int[][] dp) {
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0 || m == 0) {   //if arr1 is null or arr2 is null max prod not pos for same
					dp[n][m] = -(int) 1e8; //len arr , so store invalid very less val , so it's does
					continue;  //not affet ans
				}

				int val = nums1[n - 1] * nums2[m - 1];

				int acceptBoth = dp[n - 1][m - 1] + (nums1[n - 1] * nums2[m - 1]);
				int yesNo = dp[n - 1][m];
				int noYes = dp[n][m - 1];
				dp[n][m] = Math.max(Math.max(val, acceptBoth), Math.max(noYes, yesNo));
			}
		}

		return dp[N][M];
	}

	// 1035. Uncrossed Lines**************************************************************************
	public int maxUncrossedLines(int[] nums1, int[] nums2) {
		int n = nums1.length, m = nums2.length;
		int[][] dp = new int[n + 1][m + 1];
		return maxLines(nums1, n, nums2, m, dp);
	}

	public int maxLines(int[] nums1, int N, int[] nums2, int M, int[][] dp) {
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0 || m == 0) {
					dp[n][m] = 0;
					continue;
				}

				if (nums1[n - 1] == nums2[m - 1]) {
					dp[n][m] = 1 + dp[n - 1][m - 1];
				} else {
					dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
				}
			}
		}
		return dp[N][M];
	}
	// 72. Edit Distance******************************************************************************
	public int minDistance(String word1, String word2) {
		int n = word1.length(), m = word2.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int[] row : dp) Arrays.fill(row, -1);
		return dist_memo(word1, n, word2, m, dp);
		// return editDist_Tab(word1, n, word2, m, dp);
	}

	public int dist_memo(String s, int n, String p, int m, int[][] dp) {
		if (n == 0) {
			return dp[n][m] = m; //n->m , so all insert in n (insert m chars)
		}
		if (m == 0) {
			return dp[n][m] = n; // delete all chars of n
		}

		if (dp[n][m] != -1) {
			return dp[n][m];
		}

		if (s.charAt(n - 1) == p.charAt(m - 1))
			return dp[n][m] = dist_memo(s, n - 1, p, m - 1, dp);

		int insert = dist_memo(s, n, p, m - 1, dp);
		int delete = dist_memo(s, n - 1, p, m, dp);
		int replace = dist_memo(s, n - 1, p, m - 1, dp);

		//cheapest among ins/del/rep +1 for 1 operation performed
		return dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;

	}

	public int editDist_Tab(String s, int N, String p, int M, int[][] dp) {
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0) {
					dp[n][m] = m;
					continue;
				} else if (m == 0) {
					dp[n][m] = n;
					continue;
				} else if (s.charAt(n - 1) == p.charAt(m - 1)) {
					dp[n][m] = dp[n - 1][m - 1];
				} else {
					dp[n][m] = Math.min(dp[n - 1][m - 1], Math.min(dp[n - 1][m], dp[n][m - 1])) + 1;
				}
			}
		}
		return dp[N][M];
	}

	// 115. Distinct Subsequences*********************************************************************
	public int numDistinct(String s, String t) {
		int n = s.length(), m = t.length();
		if (m > n) return 0;
		int[][] dp = new int[n + 1][m + 1];
		for (int[] r : dp) Arrays.fill(r, -1);
		return nums(s, n, t, m, dp);
	}

	public int nums(String s, int n, String t, int m, int[][] dp) {
		if (m == 0) {
			return dp[n][m] = 1;
		}

		if (n == 0 || m > n) {
			return dp[n][m] = 0;
		}

		//look-up
		if (dp[n][m] != -1) {
			return dp[n][m];
		}

		int count = 0;
		if (s.charAt(n - 1) == t.charAt(m - 1)) {
			count += nums(s, n - 1, t, m - 1, dp); //chars eq recur for rest string
			count += nums(s, n - 1, t, m, dp); // look for another match in the string
		} else {
			count += nums(s, n - 1, t, m, dp); //look for String t , int String s with len-1
		}
		return dp[n][m] = count;
	}

	public int numDistinct_Tab(String s, int N, String t, int M, int[][] dp) {
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (m == 0) {
					dp[n][m] = 1;
					continue;
				} else if (n == 0 || m > n) {
					dp[n][m] = 0;
					continue;
				} else {
					if (s.charAt(n - 1) == t.charAt(m - 1)) { //ros = rest of string
						dp[n][m] += dp[n - 1][m - 1]; //chars match look for ros
						dp[n][m] += dp[n - 1][m];     //look for t in ros(s)
					} else {
						dp[n][m] += dp[n - 1][m];     //not match , find t in ros of s
					}
				}
			}
		}
		return dp[N][M];
	}

}