import java.util.Arrays;
public class regex_matching {


	//not Correct
	// how to handle condition like s = "" , p="a*b*c*"
	// Failed Case -
	// "a" = s
	// "ab*a" = p
	public boolean isMatch_memo(String s, int n, String p, int m, Boolean[][] dp) {

		if (n == 0 || m == 0) {
			if (n == 0 && m == 0) return dp[n][m] = true;
			else if (m > 0 && p.charAt(m - 1) == '*') return dp[n][m] = true;
			else return dp[n][m] = false;
		}

		if (dp[n][m] != null) {
			return dp[n][m];
		}

		char ch1 = s.charAt(n - 1);
		char ch2 = p.charAt(m - 1);
		char prev = '$';
		if (ch2 == '*') {
			prev = p.charAt(m - 2);
		}

		boolean res = false;
		if (ch1 == ch2 || ch2 == '.') {
			res = res || isMatch_memo(s, n - 1, p, m - 1, dp);
		} else if (ch2 == '*') {
			boolean mapEmpty = isMatch_memo(s, n, p, m - 2, dp);
			boolean mapChars = isMatch_memo(s, n - 1, p, m, dp) && (prev == ch1 || prev == '.');
			res = res || mapChars || mapEmpty;
		}

		return dp[n][m] = res;
	}


	//Tabulation
	public boolean isMatch_tab(String s, int N, String p, int M, int[][] dp) {
		dp[0][0] = 1;
		for (int j = 2; j <= M; j += 2) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 2] == 1 ? 1 : 0;
			}
		}

		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {

				char ch1 = s.charAt(n - 1);
				char ch2 = p.charAt(m - 1);
				char prev = '$';
				if (ch2 == '*') {
					prev = p.charAt(m - 2);
				}

				if (ch1 == ch2 || ch2 == '.') {
					dp[n][m] = dp[n - 1][m - 1];
				} else if (ch2 == '*') {
					int mapEmpty = dp[n][m - 2];
					int mapChars = (dp[n - 1][m] == 1) && (prev == ch1 || prev == '.') ? 1 : 0;
					dp[n][m] = (mapChars == 1 || mapEmpty == 1) ? 1 : 0;
				}
			}
		}

		return dp[N][M] == 1;
	}

	public static boolean isMatch_Tab(String s, int N, String p, int M, boolean[][] dp) {
		dp[0][0] = true;
		for (int j = 2; j <= M; j += 2) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 2];
			}
		}

		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {

				char ch1 = s.charAt(n - 1);
				char ch2 = p.charAt(m - 1);
				char prev = '$';
				if (ch2 == '*') {
					prev = p.charAt(m - 2);
				}

				if (ch1 == ch2 || ch2 == '.') {
					dp[n][m] = dp[n - 1][m - 1];
				} else if (ch2 == '*') {
					boolean mapEmpty = dp[n][m - 2];
					boolean mapChars = dp[n - 1][m] && (prev == ch1 || prev == '.');
					dp[n][m] = mapChars || mapEmpty;
				}
			}
		}

		return dp[N][M];
	}


	public static void main(String[] args) {
		String s = "mississippi";
		String p = "mis*i.*p*i";
		int n = s.length(), m = p.length();
		boolean[][] dp = new boolean[n + 1][m + 1];
		isMatch_Tab(s, n, p, m, dp);
		display(dp);
	}

	public static void display(boolean[][] arr) {
		for (boolean[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
	}
}