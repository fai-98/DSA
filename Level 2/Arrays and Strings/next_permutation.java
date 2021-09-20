public class next_permutation {

	// 556. Next Greater Element III
	public int nextGreaterElement(int n) {
		String s = String.valueOf(n);
		char[] num = s.toCharArray();

		if (n < 10) return -1;
		int dipIdx = findDip(num);
		if (dipIdx == -1) return -1;

		int ceilIdx = findCeil(num[dipIdx], num);
		swap(dipIdx, ceilIdx, num);
		reverse(num, dipIdx + 1, num.length - 1);

		String res = String.valueOf(num);
		long ans = Long.parseLong(res);

		if (ans <= Integer.MAX_VALUE) {
			return (int) ans;
		} else {
			return -1;
		}
	}

	public int findDip(char[] num) {
		int idx = -1;
		for (int i = num.length - 2; i >= 0; i--) {
			if (num[i] < num[i + 1]) {
				idx = i;
				break;
			}
		}
		return idx;
	}

	public int findCeil(int dip, char[] num) {
		int i = num.length - 1;
		while (dip >= num[i]) {
			i--;
		}
		return i;
	}

	public void swap(int dipIdx, int ceilIdx, char[] num) {
		char temp = num[dipIdx];
		num[dipIdx] = num[ceilIdx];
		num[ceilIdx] = temp;
	}

	public void reverse(char[] num, int l, int r) {
		while (l < r) {
			swap(l, r, num);
			l++;
			r--;
		}
	}


	// 31. Next Permutation
	public void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i + 1] <= nums[i]) {
			i--;
		}
		if (i >= 0) {
			int j = nums.length - 1;
			while (nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	private void reverse(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}