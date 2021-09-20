public class four_sum {

	// pick a val  (left != left-1) avoid dups
	// find all 3Sum pairs , append val to pairs

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

	public List < List < Integer >> threeSum_(int[] nums, int targ , int si) {
		int n = nums.length;
		List < List < Integer >> res = new ArrayList < > ();
		// Arrays.sort(nums);  already sorted in 4Sum func

		int l = si;
		while (l <= n - 3) {
			if (l != si && nums[l] == nums[l - 1]) {
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

	public List<List<Integer>> fourSum(int[] nums, int target) {
		int n = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);

		int l = 0;
		while (l <= n - 4) {
			if (l != 0 && nums[l] == nums[l - 1]) {
				l++;
				continue;
			}

			int val1 = nums[l];
			int tar = target - val1;
			List<List<Integer>> base = threeSum_(nums, tar, l + 1);

			for (List list : base) {
				list.add(val1);
				res.add(list);
			}

			l++;
		}

		return res;
	}

	public static void main(String[] args) {

	}
}