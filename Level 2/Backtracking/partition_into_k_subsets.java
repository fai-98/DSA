public class partition_into_k_subsets {

	// Partition in K Subsets

	//levels = numbers
	//options = subsets


	// 698. Partition to K Equal Sum Subsets

	//when kth set satisfies sum = sum/k , then only we recur for k-1 sets

	//without sorting TLE

	//2727 ms
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		Arrays.sort(nums);

		if (k <= 0 || k > nums.length || sum % k != 0) return false;

		boolean[] vis = new boolean[nums.length];

		return backTrack(0, k, 0, sum / k, vis, nums);
	}

	public boolean backTrack(int idx, int k, int currSum, int reqSum, boolean[] vis, int[] arr) {
		if (currSum > reqSum) return false;
		if (k == 1) return true; //if k-1 sets have eq sum = sum/k , kth set will definitely have sum/k

		//start from 0 idx bcz ele inc in prev set already marked vis
		if (currSum == reqSum) return backTrack(0, k - 1, 0, reqSum, vis, arr);

		for (int i = idx; i < arr.length; i++) {
			if (vis[i] == false) {
				vis[i] = true;
				if (backTrack(i + 1, k, currSum + arr[i], reqSum, vis, arr) == true) {
					return true;
				}
				vis[i] = false;
			}
		}

		return false;
	}

	//1998 ms
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		Arrays.sort(nums); //without sorting TLE !!

		if (k <= 0 || k > nums.length || sum % k != 0) return false;

		boolean[] vis = new boolean[nums.length];

		return backTrack(nums.length - 1, k, 0, sum / k, vis, nums);
	}

	public boolean backTrack(int idx, int k, int currSum, int reqSum, boolean[] vis, int[] arr) {
		if (k == 1) return true;

		//start from 0 idx bcz ele inc in prev set already marked vis
		if (currSum == reqSum) return backTrack(arr.length - 1, k - 1, 0, reqSum, vis, arr);

		for (int i = idx; i >= 0; i--) {
			if (vis[i] == false) {
				if (currSum + arr[i] > reqSum) continue;
				vis[i] = true;

				if (backTrack(i - 1, k, currSum + arr[i], reqSum, vis, arr) == true) {
					return true;
				}
				vis[i] = false;
			}
		}

		return false;
	}
}