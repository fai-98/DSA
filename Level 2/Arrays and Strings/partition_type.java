public class partition_type {

	// VVI - swap the val which we want on left  {i++;j++}
	// - ignore the val which we want on right(it'll move auto.. when swapped) {j++}

	// 905. Sort Array By Parity
	public int[] sortArrayByParity(int[] nums) {
		int i = 0, j = 0;

		//two way partition
		while (j < nums.length) {
			if (nums[j] % 2 == 0) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;

				i++;
				j++;
			} else {
				j++;
			}
		}

		return nums;
	}

	//922. Sort Array By Parity II
	public int[] sortArrayByParityII(int[] nums) {
		//segregate odd-even;
		int n = nums.length, i = 0, j = 0;
		int[] res = new int[n];

		//two way partition   even-odd
		while (j < nums.length) {
			if (nums[j] % 2 == 0) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;

				i++;
				j++;
			} else {
				j++;
			}
		}

		i = 0; //even
		j = n / 2; //odd
		int k = 0;

		while (j < n) {
			res[k] = nums[i]; //idx even - even val
			res[k + 1] = nums[j];
			i++;
			j++;
			k += 2;
		}
		// return nums;
		return res;
	}



	// Leetcode 75. Sort Colors
	//3 way partition - sort 0 , 1 ,2 (colors)

	public void sortColors(int[] a) {
		int n = a.length;
		int lo = 0, mid = 0, hi = n - 1;
		int temp;

		while (mid <= hi) {
			switch (a[mid]) {
			case 0: {
				temp = a[lo];
				a[lo] = a[mid];
				a[mid] = temp;
				lo++;
				mid++;
				break;
			}

			case 1:
				mid++;
				break;

			case 2: {
				temp = a[mid];
				a[mid] = a[hi];
				a[hi] = temp;
				hi--;
				break;
			}

			}
		}
	}


	// Lintcode 508 · Wiggle Sort
	// Leetcode 280. Wiggle Sort
	// https://www.geeksforgeeks.org/sort-array-wave-form-2/


	// <= or >= , so no need to skip duplicates
	// nums[0] <= nums[1] >= nums[2] <= nums[3]....
	public void wiggleSort(int[] nums) {
		// odd < even

		for (int i = 0, j = 1; j < nums.length; i++, j++) {
			if (i % 2 == 0) { //i even , j odd
				if (nums[i] > nums[j]) {
					swap(nums, i, j);
				}
			} else {
				if (nums[i] < nums[j]) {
					swap(nums, i, j);
				}
			}
		}
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


	// Leetcode 324. Wiggle Sort II

	// strictly nums[0] < nums[1] > nums[2] < nums[3]....

	// nlog(n)
	public void wiggleSortII(int[] nums) {
		int n = nums.length;
		int[] arr = new int[n];
		Arrays.sort(nums);

		//odd idx - larger vals
		int j = n - 1, idx = 1;

		while (idx < n) {
			arr[idx] = nums[j];
			idx += 2;
			j--;
		}

		//even idx smaller vals
		idx = 0;
		while (idx < n) {
			arr[idx] = nums[j];
			idx += 2;
			j--;
		}

		for (int i = 0; i < n; i++) {
			nums[i] = arr[i];
		}
	}

	// O(n) soln ?
}