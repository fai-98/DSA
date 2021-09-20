import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class k_sum {
	public static List<List<Integer>> kSum(int[] arr, int target, int k) {
		Arrays.sort(arr);
		List<List<Integer>> res = new ArrayList<>();
		backTrack(res , new ArrayList<>() , false , arr , target, 0, k);
		return res;
	}

	public static  void backTrack(List<List<Integer>> res , List<Integer> temp , boolean prev , int[] nums , int tar, int idx , int k) {

		if (idx == nums.length) {
			if (tar == 0 && temp.size() == k) {
				res.add(new ArrayList(temp));
			}
			return;
		}

		backTrack(res, temp, false, nums, tar, idx + 1, k);

		if (idx > 0 && nums[idx] == nums[idx - 1] && !prev)return;


		if (tar - nums[idx] >= 0) {
			temp.add(nums[idx]);
			backTrack(res, temp, true, nums, tar - nums[idx], idx + 1, k);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 3, 6};
		int tar = 9;
		int k = 3;
		List<List<Integer>> res = kSum(arr, tar, k);

		for (var list : res) {
			for (int val : list) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}
}