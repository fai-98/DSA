
import java.util.Arrays;

public class Insertion_Deletion {
	// All Ques are straight-forward applications of LCS

	// 583. Delete Operation for Two Strings
	public int minDistance(String word1, String word2) {
		int n = word1.length(), m = word2.length();
		int[][] dp = new int[n + 1][m + 1];
		int lcs_len = LCS(word1, n, word2, m, dp);
		return n + m - 2 * lcs_len;
	}

	// Minimum number of deletions and insertions to convert String a to String b
	// https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/

	// Approach String a (del some chars) ---> LCS (insert some chars) ------>
	// String b
	public int minOperations(String word1, String word2) {
		int n = word1.length(), m = word2.length();
		int[][] dp = new int[n + 1][m + 1];
		int lcs_len = LCS(word1, n, word2, m, dp);

		int deletions = n - lcs_len;
		int insertions = m - lcs_len;
		return deletions + insertions;
	}

	// Find length of longest subsequence of one string which is substring of
	// another string
	// https://www.geeksforgeeks.org/find-length-longest-subsequence-one-string-substring-another-string/
	public static int maxSubsequenceSubstring(String X, String Y, int N, int M) {
		int[][] dp = new int[N + 1][M + 1];
		return LCS_(X, N, Y, M, dp);
	}

	// we cant skip char in String 2 (coz substring needed) , we can onlt skip char
	// in String 1
	public static int LCS_(String s, int N, String p, int M, int[][] dp) {
		int max = 0;
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0 || m == 0) {
					dp[n][m] = 0;
					continue;
				}

				if (s.charAt(n - 1) == p.charAt(m - 1)) {
					dp[n][m] = 1 + dp[n - 1][m - 1];
				} else {
					dp[n][m] = dp[n - 1][m];
				}

				max = Math.max(max, dp[n][m]);
			}
		}
		return max;
	}

	// 712. Minimum ASCII Delete Sum for Two
	// Strings************************************************

	public int minimumDeleteSum(String s1, String s2) {
		int n = s1.length(), m = s2.length();
		int[][] dp = new int[n + 1][m + 1];
		int sum1 = 0, sum2 = 0;

		for (char ch : s1.toCharArray())
			sum1 += ch;

		for (char ch : s2.toCharArray())
			sum2 += ch;

		int lcsSum = lcs_sum(s1, n, s2, m, dp);
		System.out.println(lcsSum);
		return sum1 + sum2 - 2 * lcsSum;
	}

	// gets the max ASCII sum of lcs possible , if multiple LCS exist , we get the
	// max ASCII one
	// dp[i][j] = max ASCII sum of LCS
	public int lcs_sum(String s, int N, String p, int M, int[][] dp) {
		int lcs = 0;
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0 || m == 0) {
					dp[n][m] = 0;
					continue;
				}

				if (s.charAt(n - 1) == p.charAt(m - 1)) {
					dp[n][m] = s.charAt(n - 1) + dp[n - 1][m - 1]; // **
				} else {
					dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
				}
			}
		}

		return dp[N][M];
	}

	// Minimum number of deletions to make a string
	// palindrome**************************************
	// https://www.geeksforgeeks.org/minimum-number-deletions-make-string-palindrome/

	// -- Min no. of deletions = Length of String - Length of
	// longestPalindromeSubseq (LPSq);

	// 1312. Minimum Insertion Steps to Make a String
	// Palindrome********************************
	// no of insertions = no. of deletions (exactly same ques)
	// -> we delete unpaired chars
	// -> or we can add same no. of chars to pair them up and make palindrome

	// Recursion (min insertion)
	public static int min_Pal(char[] str, int si, int ei) {
		if (si >= ei) {
			return 0;
		}

		if (str[si] == str[ei]) {
			return min_Pal(str, si + 1, ei - 1);
		} else {
			return Math.min(min_Pal(str, si + 1, ei), min_Pal(str, si, ei - 1)) + 1;
		}
	}

	// LCS**********************************************************************
	public int LCS(String s, int N, String p, int M, int[][] dp) {
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0 || m == 0) {
					dp[n][m] = 0;
					continue;
				}

				if (s.charAt(n - 1) == p.charAt(m - 1)) {
					dp[n][m] = 1 + dp[n - 1][m - 1];
				} else {
					dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
				}
			}
		}
		return dp[N][M];
	}

	public static void main(String[] args) {
		// String s = "AABEBCDD";
		String s = "abcde";
		char[] str = s.toCharArray();
		int n = s.length();
		System.out.println(min_Pal(str, 0, n - 1));

		// int m = p.length();

		// int[][] dp = new int[n + 1][n + 1];
		// System.out.println(LRS(s, n, s, n, dp));
	}
}