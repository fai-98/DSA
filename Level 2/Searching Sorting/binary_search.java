public class binary_search {

	//Binary Search
	public static void binary_search(int[] arr , int tar) {
		int lo = 0;
		int hi = arr.length - 1;
		int idx = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (arr[mid] == tar) {
				idx = mid;
				break;
			} else if (arr[mid] > tar) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return idx;
	}


	//First index
	public static void first_index(int[] arr , int tar) {
		int lo = 0;
		int hi = arr.length - 1;
		int idx = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (arr[mid] == tar) {
				idx = mid;
				hi = mid - 1;
			} else if (arr[mid] > tar) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return idx;
	}

	//Last Index
	public static void first_index(int[] arr , int tar) {
		int lo = 0;
		int hi = arr.length - 1;
		int idx = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (arr[mid] == tar) {
				idx = mid;
				lo = mid + 1;
			} else if (arr[mid] > tar) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return idx;
	}

	//Find Transition point  , portal
	public static int findTransition(int[]arr) {
		int lo = 0;
		int hi = arr.length - 1;
		int idx = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (arr[mid] == 1) {
				idx = mid;
				hi = mid - 1;
			} else if (arr[mid] > 1) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return idx;
	}

	//LeetCode 278. First Bad Version
	public int firstBadVersion(int n) {
		int lo = 1;
		int hi = n;
		int res = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (isBadVersion(mid)) {
				res = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return res;
	}

	// Guess Number Higher Or Lower , portal
	public static int guessNumber(int n) {
		int lo = 1;
		int hi = n , res = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (guess(mid) == 0) {
				res = mid;
				break;
			} else if (guess(mid) == -1) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return res;
	}


	// ============================ 3 Similar ques ===================================

	// Search In Rotated Sorted Array
	public int search(int[] nums, int target) {
		int n = nums.length;
		int lo = 0, hi = n - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] >= nums[lo]) {
				if (nums[lo] <= target && nums[mid] >= target) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else {
				if (nums[mid] <= target && nums[hi] >= target) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}

		return -1;
	}

	// 153. Find Minimum in Rotated Sorted Array


	//Find Rotation Count

	// =================================important ques============================================


	// 875. Koko Eating Bananas
	public int minEatingSpeed(int[] piles, int h) {
		int lo = 1, hi = -(int) 1e9;
		for (int num : piles) {
			hi = Math.max(hi, num);
		}

		int k = (int) 1e9;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isPos(piles, mid, h)) {
				k = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return k;
	}

	public boolean isPos(int[] arr, int speed, int h) {
		int time = 0;
		for (int i = 0; i < arr.length; i++) {
			time += (int) Math.ceil((double) arr[i] / speed);
		}
		return time <= h;
	}


	// 1283. Find the Smallest Divisor Given a Threshold
	// (exactly same code as above)


	public int smallestDivisor(int[] nums, int threshold) {
		int lo = 1, hi = -(int) 1e9;
		for (int num : nums) {
			hi = Math.max(hi, num);
		}

		int k = (int) 1e9;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isPos(nums, mid, threshold)) {
				k = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return k;
	}

	public boolean isPos(int[] arr, int divisor, int threshold) {
		int time = 0;
		for (int i = 0; i < arr.length; i++) {
			time += (int) Math.ceil((double) arr[i] / divisor);
		}
		return time <= threshold;
	}


	// Allocate Minimum Number Of Pages , portal
	//https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1

	public static boolean isBurdenPossible(int[] arr, int burden, int m) {
		int sum = 0, students = 1;

		for (int i = 0; i < arr.length; i++) {
			if (sum + arr[i] <= burden) {
				sum += arr[i];
			} else {
				students++;
				sum = arr[i];
			}
		}

		return students <= m;
	}

	//burden = max no. of pages alloted to any student
	//min possible burden = max(arr) , when 1 book/student -> m = size of arr
	//max burden = sum(arr) when student = 1
	//search space =  max(arr)--------sum(arr)


	public static int minPages(int[] arr, int m) {
		if (m > arr.length) {
			return -1;      //less than 1 book/student
		}

		int lo = 0, hi = 0, burden = (int)1e9;
		for (int val : arr) {
			lo = Math.max(lo, val);
			hi += val;
		}

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (isBurdenPossible(arr, mid, m)) {
				burden = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return burden;
	}


	// 410. Split Array Largest Sum


	// 1011. Capacity To Ship Packages Within D Days


	// Count Zeros In A Sorted Matrix
	public static int countZeros(int[][]mat) {
		int n = mat.length, m = mat[0].length;
		int i = n - 1, j = 0, ct = 0;

		while (i >= 0 && j < m) {
			if (mat[i][j] == 1) {
				i--;
			} else {
				ct += i + 1;
				j++;
			}
		}

		return ct;
	}


	// Counting Elements In Two Arrays
	public static int[] find(int[]arr1, int[]arr2) {
		int n = arr1.length;
		Arrays.sort(arr2);
		int[] res = new int[n];

		for (int i = 0; i < n; i++) {
			int ceil = getCeil(arr2, arr1[i], arr2.length);
			res[i] = ceil;
		}

		return res;
	}

	public static int getCeil(int[] arr, int val, int n) {
		int lo = 0, hi = n - 1, ceil = -1;

		while (lo <= hi) {

			int mid = lo + (hi - lo) / 2;

			if (arr[mid] <= val) {
				lo = mid + 1;
			} else {
				ceil = mid;
				hi = mid - 1;
			}
		}

		return ceil == -1 ? n : ceil;
	}



	// Distinct Absolute Array Elements
	public static int count(int[]arr) {
		int n = arr.length;
		int i = 0, j = n - 1, ct = 0, prev = -(int)1e9, next = (int)1e9;

		while (i <= j) {
			int l = Math.abs(arr[i]);
			int r = Math.abs(arr[j]);

			if (l == r) {
				if (l != prev && r != next) {
					ct++;
				}
				prev = l;
				next = r;
				i++;
				j--;
			} else if (l < r) {
				if (r != next) {
					ct++;
				}
				next = r;
				j--;
			} else {
				if (l != prev) {
					ct++;
				}
				prev = l;
				i++;
			}
		}
		return ct;
	}


	// 540. Single Element in a Sorted Array
	public int singleNonDuplicate(int[] nums) {

	}




}