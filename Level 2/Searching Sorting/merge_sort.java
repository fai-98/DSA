public class merge_sort {
	//Divide and Conquer
	//ENHANCED MERGE SORT QUESTIONS

	//Merge Sort
	public int[] sortArray(int[] nums) {
		return mergeSort(nums, 0, nums.length - 1);
	}

	public int[] mergeSort(int[] arr , int lo , int hi ) {
		if (lo == hi) {
			int baseArray[] = new int[1];
			baseArray[0] = arr[lo];
			return baseArray;
		}

		int mid = lo + (hi - lo) / 2;
		int firstSortedHalf[] = mergeSort(arr, lo, mid);
		int secondSortedHalf[] = mergeSort(arr, mid + 1, hi);
		int sortedArray[] = mergeTwoSortedArrays(firstSortedHalf, secondSortedHalf);
		return sortedArray;
	}


	public int[] mergeTwoSortedArrays(int[] a , int[] b) {
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

	//Quick Sort
	public int[] sortArray(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	public void quickSort(int[] arr , int st , int end) {
		if (st > end) {
			return;
		}

		int pi = st + (end - st) / 2;
		pi = partition(arr, pi, st, end);

		quickSort(arr, st, pi - 1);
		quickSort(arr, pi + 1, end);

	}
	public int partition(int[] arr , int pi , int st , int end) {
		swap(arr, pi, end);

		int i = st, j = st;
		while (j <= end) {
			if (arr[j] <= arr[end]) {
				swap(arr, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}

		return i - 1;
	}

	public void swap(int[]arr , int i , int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//Bubble Sort
	public int[] sortArray(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j + 1] < nums[j]) {
					swap(nums, j + 1, j);
				} else break;
			}
		}

		return nums;
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// Count Inversions in an array | Set 1 (Using Merge Sort) Hard.

	//Only one line change from merge sort
	//while merging 2 arrs , we have a chance to calc inv, we have 2 sorted arrs left and right
	//if right arr ele comes before left one , this means all ele at ith idx of left arr are > than right ele
	//so we have inversion pairs = left.length - i;

	static long inversions;
	static long inversionCount(long nums[], long N) {
		inversions = 0;
		mergeSort(nums, 0, nums.length - 1);
		return inversions;
	}

	public static long[] mergeSort(long[] arr , int lo , int hi ) {
		if (lo == hi) {
			long baseArray[] = new long[1];
			baseArray[0] = arr[lo];
			return baseArray;
		}

		int mid = lo + (hi - lo) / 2;
		long firstSortedHalf[] = mergeSort(arr, lo, mid);
		long secondSortedHalf[] = mergeSort(arr, mid + 1, hi);
		long sortedArray[] = mergeTwoSortedArrays(firstSortedHalf, secondSortedHalf);
		return sortedArray;
	}

	public static long[] mergeTwoSortedArrays(long[] a , long[] b) {
		int i = 0, j = 0, k = 0;
		long[] ans = new long[a.length + b.length];
		while (i < a.length && j < b.length) {
			if (a[i] <= b[j]) {
				ans[k] = a[i];
				i++;
				k++;
			} else {
				inversions += a.length - i;   //imp**
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



	// 493. Reverse Pairs (LC Hard)
	static int pairs;
	public int reversePairs(int[] nums) {
		pairs = 0;
		mergeSort(nums, 0, nums.length - 1);
		return pairs;
	}

	public int[] mergeSort(int[] arr , int lo , int hi ) {
		if (lo == hi) {
			int baseArray[] = new int[1];
			baseArray[0] = arr[lo];
			return baseArray;
		}

		int mid = lo + (hi - lo) / 2;
		int firstSortedHalf[] = mergeSort(arr, lo, mid);
		int secondSortedHalf[] = mergeSort(arr, mid + 1, hi);
		int sortedArray[] = mergeTwoSortedArrays(firstSortedHalf, secondSortedHalf);
		return sortedArray;
	}


	public int[] mergeTwoSortedArrays(int[] a , int[] b) {
		int i = 0, j = 0, k = 0;
		int[] ans = new int[a.length + b.length];

		for (i = 0; i < a.length; i++) {
			while (j < b.length && a[i] > (long)b[j] * 2) {
				j++;
			}
			pairs += j;
		}

		i = 0 ; j = 0;

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


	//Some optimizations in count inv and rev pairs
	//using global arr , without static var

	//23. Merge k Sorted Lists (LC Hard)
	public ListNode mergeKLists(ListNode[] lists) {
		return mergeK(lists, 0, lists.length - 1);
	}

	public ListNode mergeK(ListNode[] arr, int si, int ei) {
		if (si == ei) {
			return arr[si];
		}

		int mid = si + (ei - si) / 2;
		ListNode leftList = mergeK(arr, si, mid);
		ListNode rightList = mergeK(arr, mid + 1, ei);
		return mergeTwoLists(leftList, rightList);
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		ListNode p1 = l1;
		ListNode p2 = l2;

		while (p1 != null && p2 != null) {
			if (p1.val <= p2.val) {
				p.next = p1;
				p1 = p1.next;
			} else {
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}

		p.next = p1 != null ? p1 : p2;
		return dummy.next;
	}
}