public class subset_sum_gfg {
	//Subset Sum - DP 25
	public static Boolean targetSet() {
		int set[] = { 3, 34, 4, 12, 5, 2 };
		int sum = 9;

		return isPos(set, set.length, sum);
	}

	public static boolean isPos(int[] arr , int n , int tar) {

		if (tar == 0 || n == 0) {
			return tar == 0;
		}

		if (tar - arr[n - 1] >= 0) {
			return isPos(arr, n - 1, tar - arr[n - 1]) || isPos(arr, n - 1, tar);
		} else {
			return isPos(arr, n - 1, tar);
		}
	}

	//Tabulation - if ans == 0 false , else true , dp[n+1][tar+1] size
	public static int subsetSum_Tab(int[] arr , int N , int[][] dp , int Tar) {
		for (int n = 0; n <= N; n++) {
			for (int tar = 0; tar <= Tar; tar++) {
				if (tar == 0) {
					dp[n][tar] = 1;
					continue;
				} else if (n == 0) {
					dp[n][tar] = 0;
					continue;
				}

				if (tar - arr[n - 1] >= 0) {
					dp[n][tar] = dp[n - 1][tar - arr[n - 1]] + dp[n - 1][tar];
				} else {
					dp[n][tar] = dp[n - 1][tar];
				}
			}
		}

		return dp[N][Tar];
	}

	//Subset Sum Count
	// Returns true if there is a subset of set[] with sun equal to given sum
	public static int isSubsetSum(int set[], int n, int sum) {
		// The value of subset[i][j] will be true if there is a
		// subset of set[0..j-1] with sum equal to i
		int[][] subset = new int[n + 1][sum + 1];

		// If sum is 0, then answer is true
		for (int i = 0; i <= n; i++)
			subset[i][0] = 1;

		// If sum is not 0 and set is empty, then answer is false
		for (int i = 1; i <= sum; i++)
			subset[0][i] = 0;

		// Fill the subset table in botton up manner
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j < set[i - 1])
					subset[i][j] = subset[i - 1][j];
				if (j >= set[i - 1])
					subset[i][j] = subset[i - 1][j] +
					               subset[i - 1][j - set[i - 1]];
			}
		}

		/* // uncomment this code to print table
		for (int i = 0; i <= n; i++)
		{
		for (int j = 0; j <= sum; j++)
			printf ("%4d", subset[i][j]);
		printf("\n");
		}*/

		return subset[n][sum];
	}


	//Minimum Subset Sum Difference

	// Returns the minimum value of the difference of the two sets.
	public static int findMin(int arr[], int n) {
		int sum = 0;
		for (int i = 0 ; i < n ; i++ ) {
			sum += arr[i];
		}

		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0 ; i < n + 1 ; i++) {
			for (int j = 0; j < sum + 1 ; j++) {
				if (i == 0 && j == 0)dp[i][j] = true;
				else if (j == 0)dp[i][j] = true;
				else if (i == 0)dp[i][j] = false;
				else {
					if (dp[i - 1][j] == true)dp[i][j] = true;
					else if (arr[i - 1] <= j) {
						dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
					}
				}
			}
		}

		int diff = Integer.MAX_VALUE;;

		// Find the largest j such that dp[n][j]
		// is true where j loops from sum/2 t0 0  ,
		// sum - 2*j shd be min coz susbet shd be closest to sum/2
		for (int j = sum / 2; j >= 0; j--) {
			if (dp[n][j] == true) {
				if (diff > sum - (2 * j))diff = sum - 2 * j;

			}
		}
		return diff;

	}

	// Last Stone Weight II - just get min subset sum diff (above code)

	//Optimized Version
	public int lastStoneWeightII(int[] arr) {
		int sum = 0, set1 = 0, n = arr.length;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}

		int[] prev = new int[sum / 2 + 1];
		prev[0] = 1;

		for (int i = 1; i < n + 1; i++) {
			int[] dp = new int[sum + 1];
			for (int j = 0; j < sum / 2 + 1; j++) {
				if (prev[j] == 1) dp[j] = 1;
				else if (arr[i - 1] <= j) {
					dp[j] = prev[j] + prev[j - arr[i - 1]];
				}
				//this sum is possible , make set1 as max as pos under 0<= set1 <= sum/2
				if (dp[j] != 0) set1 = Math.max(set1, j);
			}

			prev = dp;
		}


		return sum - 2 * set1; //diff b/w set1-set2 -> set1 <= set2 (larger set)
	}

	// 	Basically for the given stones we can create two
	// sets,the sum of second set of stones to be subtracted from sum of first one.
	// ideally we want sum of each set to be sum(stones)/2 so that they cancel each other out.

	//ex Input: a, b, c, d

	// Smash them, answer: (a - b) - (c - d) => (a + d) - (b + c)
	// The same property holds true if the elements are re-ordered to [c, a, d, b].
	// Answer: (c - a) - (d - b) => (c + b) - (a + d)
}