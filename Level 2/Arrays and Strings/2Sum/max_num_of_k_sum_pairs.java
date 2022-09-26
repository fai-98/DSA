public class max_num_of_k_sum_pairs {
	// 1679. Max Number of K-Sum Pairs

	//after sorting this is same as 167. Two Sum II - Input Array Is Sorted / Pair With Given Sum
	public int maxOperations(int[] nums, int k) {
		// nlog(n)
		//find number of disjoint two sum pairs , can be dups
		int n = nums.length;
		Arrays.sort(nums);
		int l = 0, r = n - 1, res = 0;

		while (l < r) {
			int sum = nums[l] + nums[r];

			if (sum == k) {
				res++;
				l++;
				r--;
			} else if (sum < k) {
				l++;
			} else {
				r--;
			}
		}

		return res;
	}

	//O(n) using map
	public int maxOperations(int[] nums, int k) {
		Map < Integer, Integer > map = new HashMap < > ();
		int res = 0;
		for (int ele : nums) {
			int tar = k - ele;
			if (map.containsKey(tar) && map.get(tar) > 0) {
				res++;
				map.put(tar, map.get(tar) - 1);
			} else {
				map.put(ele, map.getOrDefault(ele, 0) + 1);
			}
		}

		return res;
	}
}