import java.util.Arrays;
public class LIS {


	// T: O(n ^ 2) , S: O(N)
	public static int[] lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, 1);
		int max = 1;

		for (int i = 1; i < dp.length; i++) {
			int currMax = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					currMax = Math.max(currMax, dp[j]);
				}
			}
			dp[i] += currMax;

			max = Math.max(max, dp[i]);
		}
		return dp;
	}


	// LIS recursive
	public static int LIS_rec(int[] arr) {
		int n = arr.length , maxLen = 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			maxLen = Math.max(maxLen, LIS_rec(arr, i, dp));
		}

		return maxLen;
	}

	public static int LIS_rec(int[] arr , int ei , int[] dp) {
		if (dp[ei] != 0) {
			return dp[i];
		}

		int maxLen = 1;
		for (int i = ei - 1; i >= 0; i--) {
			if (arr[i] < arr[ei]) {
				maxLen = Math.max(maxLen, LIS_rec(arr, i, dp) + 1);
			}
		}
		return dp[ei] = maxLen;
	}


	// https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
	// Dynamic Programming | Building Bridges
	// points{x1 north , x2 south }
	public static int building_Bridges(int[][] points) {
		Arrays.sort(points, (a, b)-> {
			return a[0] - b[0];
		});


		//sort on basis of x1 co-ord , LIS on x2
		// x is increasing coz sorted , x2 is inc coz LIS
		// we build max bridges because of LIS

		int[] dp = new int[points.length];
		Arrays.fill(dp, 1);
		int max = 1;

		for (int i = 1; i < dp.length; i++) {
			int currMax = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (points[j][1] < points[i][1]) {
					currMax = Math.max(currMax, dp[j]);
				}
			}
			dp[i] += currMax;

			max = Math.max(max, dp[i]);
		}
		return max;

	}


	//min number of deletions to be performed to make array sorted
	// -1e7 <= ele <= 1e7 , given arr will not be sorted

	//use arr[j]<=arr[i] , coz duplicates shd be part of ans
	public static int min_del(int[] arr) {
		int n = arr.length , maxLen = 0;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		for (int i = 1; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] <= arr[i]) {  //use equality for duplicates
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}

		int min_deletions = n - maxLen;
		return min_deletions;
	}

	//Longest Decreasing SSQ
	public static int[] lengthOfLDS(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, 1);
		int max = 1;

		for (int i = 1; i < dp.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] > nums[i]) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return dp;
	}

	//LIS from n-1 to i == LDS from i to n-1
	public static int[] LDS_(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int max = 1;

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
			max = Math.max(max, dp[i]);
		}

		return dp;
	}



	//Maximum Sum inc SSQ
	// imp dont add LIS vals ,
	// 1,2,4,500,5,6,7,600 , LIS Sum = ... , MaxSum Inc SSQ = 1100

	public static int maxSum_LIS(int[] nums) {
		int n = nums.length , sum = 0;
		int[] dp = new int[n];

		for (int i = 0; i < n; i++) {
			dp[i] = nums[i];
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + nums[i]); //re-check this condition
				}
			}
			sum = Math.max(sum, dp[i]);
		}
		return sum;
	}


	// Longest Bitonic Subsequence | DP-15
	public static int LBS(int[] nums) {
		int n = nums.length , lbs = 0;

		int[] LIS = lengthOfLIS(nums);  // lis ending at i
		int[] LDS = LDS_(nums);         // lds starting from i

		for (int i = 0; i < n; i++) {
			lbs = Math.max(lbs, LIS[i] + LDS[i] - 1);  //subtract double count of ith ele
		}

		return lbs;
	}


	// Maximum sum Bi-tonic Sub-sequence

	// maxSum LIS , maxSum LDS

	//reverse bitonic ssq , dec...inc

	// 354. Russian Doll Envelopes

	public int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, (a, b) -> {
			return a[0] - b[0];
		});

		int[] dp = new int[envelopes.length];
		Arrays.fill(dp, 1);
		int max = 1;

		for (int i = 1; i < dp.length; i++) {
			int currMax = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
					currMax = Math.max(currMax, dp[j]);
				}
			}
			dp[i] += currMax;

			max = Math.max(max, dp[i]);
		}
		return max;
	}

	//Get LIS String using reverse-engineering -> Recursive

	// printLIS(nums,(int)1e9,dp.length-1,dp,maxLen,""); call
	public void printLIS(int[] arr , int nxt , int idx , int[] dp , int len , String lis) {
		if (dp[idx] == 1 || idx < 0) {
			System.out.println(arr[idx] + ", " + lis);
			return;
		}

		if (dp[idx] == len && arr[idx] < nxt) {
			printLIS(arr, arr[idx], idx - 1, dp, len - 1, arr[idx] + ", " + lis);
		} else {
			printLIS(arr, nxt, idx - 1, dp, len, lis);
		}

	}

	public static void main(String[] args) {
		// int[][] cities = {{8, 1}, {1, 2}, {4, 3}, {3, 4}, {5, 5}, {2, 6}, {6, 7}, {7, 8}};
		// System.out.println(building_Bridges(cities));

		// int[] arr = {1, 2, 4, 500, 5, 6, 7, 600};
		int[] arr = {1, 11, 2, 10, 4, 5, 2, 1} ;
		// System.out.println(maxSum_LIS(arr));
		// System.out.println(Arrays.toString(LDS_(arr)));
		// System.out.println(Arrays.toString(lengthOfLIS(arr)));
		// System.out.println(LBS(arr));

	}

}