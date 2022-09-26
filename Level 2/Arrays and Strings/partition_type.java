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

	// 0 , lo-1 = zeroes
	// lo , mid-1 = Ones
	// mid , hi-1 = unknowns
	// hi , n-1 = twos

	public void sortColors(int[] arr) {
		int i = 0, j = 0, k = arr.length - 1;

		while (j <= k) {
			if (arr[j] == 0) {
				swap(arr, i, j);
				i++;
				j++;
			} else if (arr[j] == 1) {
				j++;
			} else {
				swap(arr, j, k);
				k--;
				//not j++, coz arr[k] can return 1 also
			}
		}
	}

	// 26. Remove Duplicates from Sorted Array
	public int removeDuplicates(int[] nums) {
		if (nums.length == 0) return 0;
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if (nums[i] != nums[j]) {

				nums[i + 1] = nums[j];
				i++;
			}
		}
		return i + 1;
	}


	// 27. Remove Element
	public int removeElement(int[] nums, int val) {
		int n = nums.length;
		int i = 0, j = 0;

		while (j < n) {
			if (nums[j] != val) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;

				i++;
				j++;
			} else {
				j++;
			}
		}

		return i;
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


	//Partition on Pivot
	public int partition(int[] arr, int pivot, int lo, int hi) {
		//pivot is arr[hi], if not
		//swap(arr,pivotIdx,hi);

		int i = lo, j = lo;
		while (j <= hi) {
			if (arr[j] <= pivot) {
				swap(arr, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}

		return (i - 1);
	}

	//Quick Sort Algorithm
	public int[] sortArray(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	public void quickSort(int[] arr, int lo, int hi) {
		if (lo >= hi) {
			return;
		}

		int pivotIndex = partition(arr, arr[hi] , lo , hi);
		quickSort(arr, lo, pivotIndex - 1);
		quickSort(arr, pivotIndex + 1 , hi);

	}

	public int partition(int[] arr, int pivot, int lo, int hi) {
		int i = lo, j = lo;
		while (j <= hi) {
			if (arr[j] <= pivot) {
				swap(arr, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}

		return (i - 1);
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//Quick Select Algorithm - kth largest/smallest

	//code for kth smallest , for largest find (n-k)th smallest
	//select pivot = arr[hi] , after part it is at correct idx
	//if idx < k , recur right side , else idx>k recur left side
	// idx == k means kth smallest ele was selected as pivot and now it is at correct pos..
	public int findKthLargest(int[] nums, int k) {
		// (n-k)th smallest
		return quickSelect(nums, 0, nums.length - 1, nums.length - k);
	}

	public int quickSelect(int[] arr, int lo, int hi, int k) {

		int pivotIndex = partition(arr, arr[hi] , lo , hi);

		if (pivotIndex > k) {
			return quickSelect(arr, lo, pivotIndex - 1, k);
		} else if (pivotIndex < k) {
			return quickSelect(arr, pivotIndex + 1, hi, k);
		} else {
			return arr[pivotIndex];
		}
	}

	public int partition(int[] arr, int pivot, int lo, int hi) {
		int i = lo, j = lo;
		while (j <= hi) {
			if (arr[j] <= pivot) {
				swap(arr, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}

		return (i - 1);
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}