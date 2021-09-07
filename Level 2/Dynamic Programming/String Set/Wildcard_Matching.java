import java.util.Arrays;
public class Wildcard_Matching {


	//Driver
	public static boolean isMatch(String s, String p) {
		p = compress(p);
		int n = s.length(), m = p.length();
		int[][] dp = new int[n + 1][m + 1];
		// for (int[] d : dp) Arrays.fill(d, -1);

		// boolean res  = isMatch_memo(s, n, p, m, dp) == 1;
		boolean res = isMatch_tab(s, n, p, m, dp);
		display(dp);
		return res;
	}

	// * can match any no. of chars - ***** == * , compress string p stars to single *
	public static String compress(String str) {
		if (str.length() == 0)
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(0));

		int i = 1;
		while (i < str.length()) {
			while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
				i++;

			if (i < str.length())
				sb.append(str.charAt(i));
			i++;
		}

		return sb.toString();
	}

	// Recursion
	public static  boolean is_match_recursive(String s, int i, String p, int j) {

		if (i == s.length() || j == p.length()) {
			if (i == s.length() && j == p.length()) return true;
			else if (j == p.length() - 1 && p.charAt(j) == '*') return true;
			else return false;
		}

		boolean res = false;
		if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
			res = res || is_match_recursive(s, i + 1, p, j + 1);
		} else if (p.charAt(j) == '*') {
			boolean mapEmpty = is_match_recursive(s, i, p, j + 1);
			boolean mapChars = is_match_recursive(s, i + 1, p, j);
			res = res || mapEmpty || mapChars;
		}

		return res;
	}

	//Memoization + int[][] dp + boolean
	//mapping (n-1) , (m-1) ,   n,m are len
	public static int isMatch_memo(String s , int n , String p , int m , int[][] dp) {

		if (n == 0 || m == 0) {
			if (n == 0 && m == 0)      //both become ""  -> matched!!
				return dp[n][m] = 1;
			else if (m == 1 && p.charAt(m - 1) == '*')  //one char left in p i.e * -> it matches ""
				return dp[n][m] = 1 ;
			else return
				    dp[n][m] = 0;
		}

		if (dp[n][m] != -1) {
			return dp[n][m];
		}

		char ch1 = s.charAt(n - 1);
		char ch2 = p.charAt(m - 1);

		if (ch1 == ch2 || ch2 == '?') {
			return dp[n][m] = isMatch_memo(s, n - 1, p, m - 1, dp);
		} else if (ch2 == '*') {
			boolean res = false;
			res = res || isMatch_memo(s, n - 1, p, m, dp) == 1; // * match withs char and survives
			res = res || isMatch_memo(s, n, p, m - 1, dp) == 1; // * match with "" empty str and vanish
			return dp[n][m] = res ? 1 : 0;  //res = true - 1 , else 0
		} else {
			return dp[n][m] = 0;
		}
	}

	public static boolean isMatch_tab(String s , int N , String p , int M , int[][] dp) {
		// dp[n+1][m+1]
		for (int n = 0; n <= N; n++) {
			for (int m = 0; m <= M; m++) {
				if (n == 0 || m == 0) {
					if (n == 0 && m == 0) {
						dp[n][m] = 1;
						continue;
					} else if (m == 1 && p.charAt(m - 1) == '*') {
						dp[n][m] = 1 ;
						continue;
					} else {
						dp[n][m] = 0;
						continue;
					}
				}

				char ch1 = s.charAt(n - 1);
				char ch2 = p.charAt(m - 1);

				if (ch1 == ch2 || ch2 == '?') {
					dp[n][m] = dp[n - 1][m - 1];
				} else if (ch2 == '*') {
					dp[n][m] = (dp[n - 1][m] == 1 || dp[n][m - 1] == 1) ? 1 : 0;
				} else {
					dp[n][m] = 0;
				}

			}
		}

		return dp[N][M] == 1;
	}

	public static void main(String[] args) {
		// String s =  "*abd***ahdh**dj************************";
		// System.out.println(compress(s));
		isMatch("adceb", "*a*b");
	}

	public static void display(int[][] arr) {
		for (int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
	}

}


// dp for s = "adceb" , p = "*a*b" ;

// [-1, 1, -1, 0, -1]
// [-1, -1, 1, 1, -1]
// [-1, -1, -1, 1, -1]
// [-1, -1, -1, 1, -1]
// [-1, -1, -1, 1, -1]
// [-1, -1, -1, -1, 1]
