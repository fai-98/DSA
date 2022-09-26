public class two_sum_all_unique_pairs {

	// **********************************twoSum**************************************************************

	//Leetcode 2 sum
	public int[] twoSum(int[] nums, int target) {
		int[] ans = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				ans[0] = map.get(target - nums[i]);
				ans[1] = i;
				return ans;
			} else map.put(nums[i], i);
		}

		return ans;
	}

	//167. Two Sum II - Input Array Is Sorted / Pair With Given Sum
	public int[] twoSum(int[] arr, int tar) {
		int i = 0, j = arr.length - 1;
		int[] ans = new int[2];
		//find pair with given diff (interviewbit) - similar
		while (i < j) {
			int sum = arr[i] + arr[j];
			if (sum == tar ) { //i!=j if sum = 2*arr[i] // avoid same ele
				ans[0] = i + 1;
				ans[1] = j + 1;
				return ans;
			} else if (sum > tar) {
				j--;
			} else {
				i++;
			}
		}

		return ans;
	}

	// 2 Sum - Target Sum All Unique Pairs

	//O(n)  2 SET 1 PASS
	public static int uniquePairs(int[] nums, int target) {
		Set<Integer> set = new HashSet<Integer>();
		Set<Integer> seen = new HashSet<Integer>();
		int count = 0;
		for (int num : nums) {
			if (set.contains(target - num) && !seen.contains(num)) {
				count++;
				seen.add(target - num);
				seen.add(num);
			} else if (!set.contains(num)) {
				set.add(num);
			}

		}

		return count;
	}

	// Time: O (n), Space O(n) - USE SINGLE SET but two pass
	private static int getCountTwoSumUnique(int[] nums, int target) {
		Set<Integer> unique = new HashSet<>();

		int ans = 0;

		// add all nums to the the set
		for (int num : nums) {
			// if both num and compliment is in set, (skip this)
			unique.add(num);
		}

		for (int num : nums) {
			int comp = target - num;
			if (unique.contains(comp)) {
				ans++;
				unique.remove(num); // If comp is in it, then num is as well since we put all of nums in the set.
				unique.remove(comp);
			}
		}

		return ans;
	}

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