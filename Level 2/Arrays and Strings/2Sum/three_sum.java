public class three_sum {


	public List < List < Integer >> threeSum(int[] nums) {
		return threeSum_(nums, 0);
	}


	// 3 Sum - Target Sum Unique Triplet
	// follow up
	// pick a val  (left != left-1) avoid dups
	// find all 2sum pairs , append val to pairs
	public List < List < Integer >> two_sum(int[] arr, int target, int si) {
		int n = arr.length;
		List < List < Integer >> res = new ArrayList < > ();

		int l = si, r = n - 1;

		while (l < r) {
			if (l != si && arr[l] == arr[l - 1]) {
				l++;
				continue;
			}

			if (arr[l] + arr[r] == target) {
				List < Integer > temp = new ArrayList < > ();
				temp.add(arr[l]);
				temp.add(arr[r]);
				res.add(temp);
				l++;
				r--;
			} else if (arr[l] + arr[r] > target) {
				r--;
			} else {
				l++;
			}
		}

		return res;
	}

	public List < List < Integer >> threeSum_(int[] nums, int targ) {
		int n = nums.length;
		List < List < Integer >> res = new ArrayList < > ();
		Arrays.sort(nums);

		int l = 0;
		while (l <= n - 3) {
			if (l != 0 && nums[l] == nums[l - 1]) {
				l++;
				continue;
			}

			int val1 = nums[l];
			int tar = targ - val1;
			List < List < Integer >> two_sum_pairs = two_sum(nums, tar, l + 1);

			for (List list : two_sum_pairs) {
				list.add(val1);
				res.add(list);
			}

			l++;
		}

		return res;
	}


	// ***********************************another_method**********************

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				int lo = i + 1 , hi = nums.length - 1 , sum = 0 - nums[i];

				while (lo < hi) {
					if (nums[lo] + nums[hi] == sum) {
						res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

						while (lo < hi && nums[lo] == nums[lo + 1])lo++;
						while (lo < hi && nums[hi] == nums[hi - 1])hi--;

						lo++; hi--;
					} else if (nums[lo] + nums[hi] < sum)lo++;
					else hi--;
				}
			}
		}
		return res;
	}
}