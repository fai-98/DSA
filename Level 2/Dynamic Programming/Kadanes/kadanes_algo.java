class Solution {

	public int maxSubArray(int[] nums) {
		int local = nums[0];
		int global = nums[0];

		for (int i = 1; i < nums.length; i++) {
			if (local + nums[i] > nums[i])
				local = nums[i] + local;
			else local = nums[i];

			if (local >= global)
				global = local;

		}
		return global;

	}

	public int maxSubArray_(int[] nums) {
		int local = nums[0], global = nums[0];
		for (int i = 1; i < nums.length; i++) {
			local = Math.max(local + nums[i], nums[i]);
			global = Math.max(global, local);
		}
		return global;

	}
}