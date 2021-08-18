import java.util.Arrays;
public class LRS {

	//exactly same as LCS , apply LCS on same str LCS(str,str);
	//if chars are equal they shd not be on same index (else it's not repetition)
	//if diff idx chars are equal -> there is repetition
	public static int LRS(String s , int N , String p , int M, int[][] dp) {
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0 || m == 0) {
					dp[n][m] = 0;
					continue;
				}
				// If characters match and indexes are not same
				if (s.charAt(n - 1) == p.charAt(m - 1) && n != m) {
					dp[n][m] = 1 + dp[n - 1][m - 1];
				} else {
					dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
				}
			}
		}

		return dp[N][M];
	}

	public static void main(String[] args) {
		String s = "AABEBCDD";
		// String p = "ACDGHR";
		int n = s.length();
		// int m = p.length();

		int[][] dp = new int[n + 1][n + 1];
		System.out.println(LRS(s, n, s, n, dp));
	}

	public static void display2D(int[][] arr) {
		for (int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}

	}
}