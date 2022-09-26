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
	public static void last_index(int[] arr , int tar) {
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

	// 74. Search a 2D Matrix O(log(m) + log(n)) find row + find val

	//each row sorted , first val in next row > last val prev row
	public boolean searchMatrix(int[][] matrix, int target) {
		int row = findRow(matrix, target);
		if (row == -1) return false;

		return findVal(matrix, row, target);
	}

	public int findRow(int[][] mat, int val) {
		int lo = 0, hi = mat.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mat[mid][0] <= val && mat[mid][mat[0].length - 1] >= val) {
				return mid;
			} else if (val > mat[mid][mat[0].length - 1]) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return -1;
	}

	public boolean findVal(int[][] mat, int row, int tar) {
		int lo = 0, hi = mat[0].length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mat[row][mid] == tar) {
				return true;
			} else if (mat[row][mid] < tar) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return false;
	}



	// 240. Search a 2D Matrix II  T : O(m + n)
	// row wise - column wise sorted matrix
	public boolean searchMatrix(int[][] matrix, int target) {
		//all L shapes in matrix are sorted
		//bottom left val is largest in 1st col, smallest in last row
		//start at top right or bottom left cell
		//in each decision you can discard a row or col
		//if out of bounds , not found

		int n = matrix.length, m = matrix[0].length;
		int row = n - 1, col = 0;

		while (row >= 0 && col < m) {
			int mid = matrix[row][col];

			if (mid == target) {
				return true;
			} else if (mid < target) {
				col = col + 1;
			} else {
				row = row - 1;
			}
		}

		return false;
	}

	// Maximum no of 1's row  - GFG   T : O(nlog(m)) binary search every row
	// all the rows are sorted
	public static int maxOnes (int Mat[][], int N, int M) {
		int max = 0, res = 0;
		for (int i = 0; i < N; i++) {
			int lb = left_bound(Mat, i);
			if (lb != -1) {
				int ct = M - lb;
				if (ct > max) {
					res = i;
					max = ct;
				}
			}
		}

		return res;
	}

	public static int left_bound(int[][] mat, int r) {
		int lo = 0, hi = mat[0].length - 1, lb = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mat[r][mid] == 1) {
				lb = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return lb;
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

	// Search In Rotated Sorted Array - 1
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

	// 81. Search in Rotated Sorted Array II

	public boolean search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (nums[mid] == target) {
				return true;
			}

			// if mid != tar and mid == start , skip all mid == st , coz we dont need that
			// st value in out search space and want sorted arr

			if (nums[lo] == nums[mid]) { //single line change from SRA - 1
				lo++; //skip dups in p1
				continue;
			}

			if (nums[lo] <= nums[mid]) { //P1 id sorted
				if (nums[lo] <= target && target <= nums[mid]) { //tar in range
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else { //P2. is sorted
				if (target >= nums[mid] && nums[hi] >= target) {
					lo = mid + 1; //val in P2 range
				} else {
					hi = mid - 1;
				}
			}
		}

		return false;
	}

	// 153. Find Minimum in Rotated Sorted Array
	public int findMin(int[] nums) {
		int n = nums.length;
		int lo = 0, hi = n - 1;

		//largest val will be last val of 1st part - only val > right val
		//smallest val , 1st val of part 2 - it will be only val < left val
		//idx[max] + 1 = idx min val

		//Case 1. len = 1
		if (n == 1) {
			return nums[0];
		}
		//Case 2. len >= 2 , sorted arr , min is at 0th
		if (nums[lo] < nums[hi]) {
			return nums[0];
		}
		//Case 3. len >= 2 and pivot lies 1<= pi <= n-1
		//we can easily compare with mid-1 , first mid-1 compare , then mid+1

		//Binary Search
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mid > 0 && nums[mid] < nums[mid - 1]) {
				return nums[mid]; //it is min, 1st element of part 2
			} else if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1]; //it is max, last ele of part 2
			} else if (nums[lo] < nums[mid]) {
				lo = mid + 1; //1st half sorted , search right half
			} else {
				hi = mid - 1;
			}
		}

		return -1;
	}


	//Find Rotation Count

	// =============================important ques(modified Binary Search)============================

	//	BINARY SEARCH ON ANSWER

	// Minimum Number of Days to Make m Bouquets
	// Find the Smallest Divisor Given a Threshold
	// Divide Chocolate
	// Capacity To Ship Packages In N Days
	// Koko Eating Bananas
	// Minimize Max Distance to Gas Station
	// Split Array Largest Sum
	// Allocate Books


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
	public int shipWithinDays(int[] weights, int days) {
		//left val min shd be >max wt in array
		//right bound at max can be sum of all wts if day is 1
		//we Binary Search a possible cap , check daysTaken with that cap
		//if daysTaken>D inc cap , else dec cap , reach min pos cap
		int l = 0, r = 0;
		for (int wt : weights) {
			l = Math.max(l, wt);
			r += wt;
		}

		while (l < r) {
			int mid = l + (r - l) / 2;
			int curr = daysTaken(weights, mid);
			if (curr <= days) r = mid;
			else l = mid + 1;

		}

		return l;
	}

	public int daysTaken(int[] wts, int cap) {
		int sum = 0, days = 1;
		for (int w : wts) {
			sum += w;
			if (sum > cap) {
				days++;
				sum = w;
			}
		}
		return days;
	}

	// 4. Median of Two Sorted Arrays
	// A1. Merge 2 arrs and find median T : O(n+m) , S: O(m+n) new arr
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] arr = mergeTwoSortedArrays(nums1, nums2);

		int n = arr.length;
		double ans = 0;

		int mid = arr.length / 2;

		if (n % 2 == 1) { //odd len
			ans = (double) arr[mid];
		} else {
			ans = ((double) arr[mid - 1] + (double) arr[mid]) / 2.0; //even len
		}

		return ans;
	}

	public int[] mergeTwoSortedArrays(int[] a, int[] b) {
		int i = 0, j = 0, k = 0;
		int[] ans = new int[a.length + b.length];
		while (i < a.length && j < b.length) {
			if (a[i] <= b[j]) {
				ans[k] = a[i];
				i++;
				k++;
			} else {
				ans[k] = b[j];
				j++;
				k++;
			}
		}

		while (i < a.length) {
			ans[k] = a[i];
			k++;
			i++;
		}

		while (j < b.length) {
			ans[k] = b[j];
			k++;
			j++;
		}

		return ans;
	}

	//A2. Binary Search T: O(log(m+n)) , S : O(1)


	//=================================================================================================

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
	public int singleNonDuplicate(int[] arr) {
		int n = arr.length;
		int lo = 0, hi = n - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mid > 0 && arr[mid] == arr[mid - 1]) {
				if ((mid - 1) % 2 == 1) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else if (mid < n - 1 && arr[mid] == arr[mid + 1]) {
				if ((n - mid) % 2 == 1) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			} else {
				return arr[mid];
			}
		}
		return 0;
	}



}