import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class k_sum_general_template {
	//find all unique pairs of k-1 size and tar = tar-val
	//append val (also skip duplicate vals)
	//base case if (k==2)	return 2Sum(nums,tar,index);

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

	public List<List<Integer>> kSum_(int[] nums, int target , int si , int k) {

		if (k == 2) {
			return two_sum(nums, target, si);
		}

		int n = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		int l = si;
		while (l <= n - k) {
			if (l != si && nums[l] == nums[l - 1]) {
				l++;
				continue;
			}

			int val1 = nums[l];
			int tar = target - val1;
			List<List<Integer>> base = kSum_(nums, tar, l + 1 , k - 1);

			for (List list : base) {
				list.add(val1);
				res.add(list);
			}

			l++;
		}

		return res;
	}

	public List<List<Integer>> kSum(int[] nums, int target , int k) {
		Arrays.sort(nums);
		List<List<Integer>> res = kSum_(nums, target, 0 , k);
		return res;
	}
}