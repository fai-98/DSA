public class pre_processing {
	// Lintcode 903 · Range Addition

	//apply impact only on si , all are intial.. 0 , and to cancel out
	//apply -ve impact on end+1 , i.e out of range

	public int[] getModifiedArray(int length, int[][] updates) {
		int[] res = new int[length];

		// travel on query and mark impact
		for (int[] query : updates) {
			int si = query[0];
			int ei = query[1];
			int val = query[2];

			res[si] += val;
			if (ei != n - 1) {
				res[ei + 1] -= val; // to cancel out coz we sum the whole arr
			}
		}

		// travel on array and make impact visible using prefix sum
		for (int i = 1; i < length; i++) {
			res[i] += res[i - 1];
		}

		return res;
	}

	// 238. Product of Array Except Self
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] pre = new int[n];
		int[] suf = new int[n];
		int[] res = new int[n];
		int prod = 1;
		for (int i = 0; i < n; i++) {
			if (i == 0) pre[i] = 1;
			else prod *= nums[i - 1];
			pre[i] = prod;
		}
		prod = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (i == n - 1) suf[i] = 1;
			else prod *= nums[i + 1];
			suf[i] = prod;
		}

		for (int i = 0; i < n; i++) {
			res[i] = pre[i] * suf[i];
		}

		return res;
	}

	//A2. only 1 array
	// or,
	// maintain left product in running time and solve
	//     int leftprod = 1;
	//     for(int i = 0; i < n; i++) {
	//         res[i] = leftprod * rightProduct[i + 1];
	//         leftprod *= nums[i];
	//     }

	//A3. O(1) space
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		int prod = 1;
		//need pre[i] * suf[i]

		// store all prefix prod in res array itself pre[i]
		for (int i = 0; i < n; i++) {
			res[i] = prod;  //when i == 0 , prod is 1 coz no ele at left
			prod *= nums[i];
		}

		// multiply all suf[i] on stored pre[i] , while looping
		prod = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] *= prod;  //n-1 prod is 1 , no ele on right
			prod *= nums[i];
		}

		return res;

		//what we did with pre[] prod and suf[] pro can be easily done on the res array itself
	}


	// leetcode 849. https://leetcode.com/problems/maximize-distance-to-closest-person/
	//O(n) Space
	public int maxDistToClosest(int[] seats) {
		int n = seats.length;
		int[] left = new int[n];
		int[] right = new int[n];
		Arrays.fill(left, n);
		Arrays.fill(right, n);

		//populate dist to left 1
		for (int i = 0; i < n; i++) {
			if (seats[i] == 1) {
				left[i] = 0;
			} else if (i > 0) {
				left[i] = 1 + left[i - 1];
			}
		}

		//populate dist to right 1
		for (int i = n - 1; i >= 0; i--) {
			if (seats[i] == 1) {
				right[i] = 0;
			} else if (i < n - 1) {
				right[i] = right[i + 1] + 1;
			}
		}

		int max_dist = 0;
		for (int i = 0; i < n; i++) {
			if (seats[i] == 0)
				max_dist = Math.max(max_dist, Math.min(left[i], right[i]));
		}

		return max_dist;
	}

	// O(1) space
	public int maxDistToClosest(int[] seats) {
		int max_dist = 0, prev = -1, n = seats.length;

		for (int i = 0; i < n; i++) {
			if (seats[i] == 0) {
				continue;
			}

			if (prev == -1) {   //edge case 1 - arr[0] = 0;
				max_dist = i;
			} else {
				max_dist = Math.max(max_dist, (i - prev) / 2);
			}
			prev = i;
		}

		//edge case 2 - arr[n-1] = 0 , [1,1,0,0,0]
		if (seats[n - 1] == 0) {
			max_dist = Math.max(max_dist, n - 1 - prev);
		}

		return max_dist;
	}


	// Leetcode 670. Maximum Swap
	public int maximumSwap(int num) {
		String s = String.valueOf(num);
		char[] arr = s.toCharArray();
		int n = arr.length;

		int[] lastIdx = new int[10];

		//Step 1. Prepare last idx of every digit
		for (int i = 0; i < n; i++) {
			int dig = arr[i] - '0';
			lastIdx[dig] = i;
		}

		//Step 2. Figure out position for swapping
		//can swap largest digit with idx>self
		boolean flag = false;
		for (int i = 0; i < n; i++) {
			int dig = arr[i] - '0';
			for (int j = 9; j > dig; j--) {
				if (lastIdx[j] > i) {
					swap(arr, i, lastIdx[j]);
					flag = true;
					break;
				}
			}
			if (flag) break;
		}

		return Integer.parseInt(String.valueOf(arr));
	}

	public void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}