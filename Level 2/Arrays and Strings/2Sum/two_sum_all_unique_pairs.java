public class two_sum_all_unique_pairs {

	// **********************************twoSum**************************************************************

	//Leetcode 2 sum


	// 2 Sum - Target Sum Unique Pairs
	public static List<List<Integer>> twoSum(int[] arr, int target) {
		int n = arr.length;
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(arr);

		int l = 0, r = n - 1;

		while (l < r) {

			//avoid repetition
			if (l != 0 && arr[l] == arr[l - 1]) {
				l++;
				continue;
			}

			int sum = arr[l] + arr[r];
			if (sum == target) {
				List<Integer> temp = new ArrayList<>();
				temp.add(arr[l]);
				temp.add(arr[r]);
				res.add(temp);
				l++;
				r--;
			} else if (sum > target) {
				r--;
			} else {
				l++;
			}
		}

		return res;
	}

	// 4Sum

	//KSum
}