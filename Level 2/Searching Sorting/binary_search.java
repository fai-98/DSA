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

	// 153. Find Minimum in Rotated Sorted Array\




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

}