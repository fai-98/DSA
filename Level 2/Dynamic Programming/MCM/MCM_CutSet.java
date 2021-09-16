import java.util.Arrays;

public class MCM_CutSet {


	//Memoization
	public static int mcm_memo(int[] arr , int si , int ei, int[][] dp) {
		if (si + 1 == ei) {
			return dp[si][ei] = 0; //cost of single mat = 0;
		}

		if (dp[si][ei] != -1) {
			return dp[si][ei];
		}

		int minRes = (int)1e8;
		for (int cut = si + 1; cut < ei; cut++) {
			int leftRes = mcm_memo(arr, si, cut, dp);
			int rightRes = mcm_memo(arr, cut, ei, dp);

			int myRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
			minRes = Math.min(minRes, myRes);
		}

		return dp[si][ei] = minRes;
	}

	//Tabulation
	public static int mcm_tab(int[] arr , int SI , int EI , int[][] dp) {
		int n = arr.length;

		for (int gap = 1; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (si + 1 == ei) {
					dp[si][ei] = 0;
					continue;
				}

				int minRes = (int)1e8;
				for (int cut = si + 1; cut < ei; cut++) {
					int leftRes = dp[si][cut];
					int rightRes = dp[cut][ei];

					int myRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
					if (myRes < minRes) {
						minRes = myRes ;
					}
				}

				dp[si][ei] = minRes;
			}
		}

		return dp[SI][EI];
	}

	//print the MCM expression
	public static String mcm_String(int[] arr , int SI , int EI , int[][] dp) {
		int n = arr.length;
		String[][] sdp = new String[n][n];
		for (String[] row : sdp)
			Arrays.fill(row, "");

		for (int gap = 1; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (si + 1 == ei) {
					dp[si][ei] = 0;
					sdp[si][ei] = (char)(si + 'A') + "";
					continue;
				}

				int minRes = (int)1e8;
				String minStr = "";
				for (int cut = si + 1; cut < ei; cut++) {
					int leftRes = dp[si][cut];
					int rightRes = dp[cut][ei];

					int res = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
					if (res < minRes) {
						minRes = res;
						minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
					}
				}

				dp[si][ei] = minRes;
				sdp[si][ei] = minStr;
			}
		}

		// display_2d(sdp);
		return sdp[SI][EI];
	}


	public static void mcmSet() {
		// int arr[] = new int[] { 4, 2, 3, 1, 3 };
		int arr[] = new int[] { 5, 2, 1, 6 };
		int n = arr.length;
		int[][] dp = new int[n][n];
		// System.out.println(mcm_tab(arr, 0, n - 1, dp));
		// System.out.println(mcm_String(arr, 0, n - 1, dp));
		// for (int[] d : dp)Arrays.fill(d, -1);
		// System.out.println(mcm_tab(arr, 0, n - 1, dp));
		// display(dp);
	}

	public static void display(int[][] arr) {
		for (int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
	}

	public static void display_2d(String[][] arr) {
		for (String[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
	}

	public static void main(String[] args) {
		// mcmSet();
	}
}