public class zero_sum_subarray_similar {
	//10 similar questions based on this *********************************************************

	// Largest Subarray With Zero Sum
	public static int solution(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		int preSum = 0, len = 0;
		map.put(0, -1); //preSum , index

		for (int i = 0; i < arr.length; i++) {
			preSum += arr[i];
			if (!map.containsKey(preSum)) {
				map.put(preSum, i);
			} else {
				len = Math.max(len, i - map.get(preSum));
			}
		}

		return len;
	}

	// Count Of All Subarrays With Zero Sum
	public static int solution(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		int preSum = 0, ct = 0;
		map.put(0, 1); //preSum , count

		for (int i = 0; i < arr.length; i++) {
			preSum += arr[i];
			if (!map.containsKey(preSum)) {
				map.put(preSum, 1);
			} else {
				ct += map.get(preSum);
				map.put(preSum, map.get(preSum) + 1);
			}
		}

		return ct;
	}

	// Longest Subarray With Equal Number Of Zeroes And Ones
	// 525. Contiguous Array
	public static int solution(int[] arr) {
		// change zero to -1
		// solve longest zero sum subarray

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				arr[i] = -1;
			}
		}

		Map<Integer, Integer> map = new HashMap<>();
		int preSum = 0, len = 0;
		map.put(0, -1); //preSum , index

		for (int i = 0; i < arr.length; i++) {
			preSum += arr[i];
			if (!map.containsKey(preSum)) {
				map.put(preSum, i);
			} else {
				len = Math.max(len, i - map.get(preSum));
			}
		}

		return len;
	}

	// Count Of Subarrays With Equal Number Of Zeroes And Ones
	public static int solution(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				arr[i] = -1;
			}
		}

		Map<Integer, Integer> map = new HashMap<>();
		int preSum = 0, ct = 0;
		map.put(0, 1); //preSum , count

		for (int i = 0; i < arr.length; i++) {
			preSum += arr[i];
			if (!map.containsKey(preSum)) {
				map.put(preSum, 1);
			} else {
				ct += map.get(preSum);
				map.put(preSum, map.get(preSum) + 1);
			}
		}

		return ct;
	}


	// Maximum Size Subarray Sum Equals K
	public static int maxLenSubarray(int[] arr, int k) {
		int pSum = 0, len = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		for (int i = 0; i < arr.length; i++) {
			pSum += arr[i];

			if (map.containsKey(pSum - k)) {
				len = Math.max(len, i - map.get(pSum - k));
			}

			if (!map.containsKey(pSum)) {
				map.put(pSum, i);
			}
		}

		return len;
	}

	// Count Of Subarrays Having Sum Equals To K
	// 560. Subarray Sum Equals K
	public static int solution(int[] arr, int k) {
		int pSum = 0, ct = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		for (int i = 0; i < arr.length; i++) {
			pSum += arr[i];

			if (map.containsKey(pSum - k)) {
				ct += map.get(pSum - k);
			}

			if (!map.containsKey(pSum)) {
				map.put(pSum, 1);
			} else {
				map.put(pSum, map.get(pSum) + 1);
			}
		}

		return ct;
	}

	// Longest Subarray With Sum Divisible By K

	// at idx 1 rem is x , at idx 2 rem is x -> b/w ele
	public static int solution(int[] arr, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int len = 0, preSum = 0;
		map.put(0, -1);

		for (int i = 0; i < arr.length; i++) {
			preSum += arr[i];
			int rem = preSum % k;
			if (rem < 0) rem += k;

			if (!map.containsKey(rem)) {
				map.put(rem, i);
			} else {
				len = Math.max(len, i - map.get(rem));
			}
		}

		return len;
	}

	// Count Of Subarrays With Sum Divisible By K
	public static int solution(int[] arr, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int ct = 0, preSum = 0;
		map.put(0, 1);

		for (int i = 0; i < arr.length; i++) {
			preSum += arr[i];
			int rem = preSum % k;
			if (rem < 0) rem += k;

			if (!map.containsKey(rem)) {
				map.put(rem, 1);
			} else {
				ct += map.get(rem);
				map.put(rem, map.get(rem) + 1);
			}
		}

		return ct;
	}

	// Longest Subarray With Equal Number Of 0s 1s And 2s
	// if 1s 0s and 2s are equal -> ct(2)-ct(1) == ct(1)-ct(0)
	//make key string ct2-ct1#c1-ct0
	//where key is found again -> middle ele has equal no. of 0,1,2
	public static int solution(int[] arr) {
		Map<String, Integer> map = new HashMap<>();
		int len = 0, zero = 0, one = 0, two = 0;
		map.put(0, -1);

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				zero++;
			} else if (arr[i] == 1) {
				one++;
			} else {
				two++;
			}

			String key = (two - one) + "#" + (one - zero);

			if (!map.containsKey(key)) {
				map.put(rem, i);
			} else {
				len = Math.max(len, i - map.get(key));
			}
		}

		return len;
	}


	// 128. Longest Consecutive Sequence - Interesting Concept
	// A1. Brute Force - O(n^3)
	// Select every element one by one , find ele+1 in array while it exists
	// for(select element)
	// 	while(arr contains (ele+1))
	// 		ans += 1 ; ele +=1

	// A2. Sorting n*logn

	// A3. Set O(n)
	public int longestConsecutive(int[] nums) {
		Set < Integer > set = new HashSet < > ();
		for (int num : nums) {
			set.add(num);
		}

		int len = 1;
		int maxLen = 0;

		//find smallest val from which seq can start -> val-1 does not exist
		//start building sequence by finding val+1 , it is surely longest with val as st
		//also remove from set
		//build another sequence when this ends
		for (int num : nums) {
			if (!set.contains(num - 1)) {
				//it shd be smallest in sequence then we look for ..

				while (set.contains(num + 1)) { //build sequence
					len++;
					num += 1;
					set.remove(num); //remove coz it is part of longest possible
				}

				if (len > maxLen) { //update max
					maxLen = len;
				}
			}

			len = 1; //reset len for new seq
		}

		return maxLen;
	}


	// Count Of Subarrays With Equal Number Of 0s 1s And 2s
	public static int solution(int[] arr) {
		Map<String, Integer> map = new HashMap<>();
		int ct = 0, zero = 0, one = 0, two = 0;
		map.put("0#0", 1);

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				zero++;
			} else if (arr[i] == 1) {
				one++;
			} else {
				two++;
			}

			String key = (two - one) + "#" + (one - zero);

			if (!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				ct += map.get(key);
				map.put(key, map.get(key) + 1);
			}
		}

		return ct;
	}

	// Task Completion
	public static void completeTask(int n, int m, int[] arr) {
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < m; i++) {
			set.add(arr[i]);
		}

		boolean flag = false; //if false add l1 , else add l2, make false

		for (int i = 1; i <= n; i++) {
			if (!set.contains(i)) {
				if (!flag) {
					l1.add(i);
					flag = true;
				} else {
					l2.add(i);
					flag = false;
				}
			}
		}

		for (int ele : l1) {
			System.out.print(ele + " ");
		}

		System.out.println();

		for (int ele : l2) {
			System.out.print(ele + " ");
		}
	}

	// 1679. Max Number of K-Sum Pairs
	public int maxOperations(int[] nums, int k) {
		Map < Integer, Integer > map = new HashMap < > ();
		int res = 0;
		for (int ele : nums) {
			int tar = k - ele;
			if (map.containsKey(tar) && map.get(tar) > 0) {
				res++;
				map.put(tar, map.get(tar) - 1);
			} else {
				map.put(ele, map.getOrDefault(ele, 0) + 1);
			}
		}

		return res;
	}


	//Longest Consecutive Sequence of Elements
	public void fun(int[] arr) {
		Map<Integer, Boolean> map = new HashMap<>();
		for (int num : arr) {
			map.put(num, map.getOrDefault(num, true));
		}

		for (int num : arr) {
			if (map.containsKey(num - 1)) {
				map.put(num, false);
			}
		}

		int st = 0;
		int maxLen = 0;

		for (int i = 0; i < n; i++) {
			int len = 0;
			if (map.get(arr[i]) == true) {
				int num = arr[i];
				while (map.containsKey(num)) {
					num += 1;
					len++;
				}
				if (len > maxLen) {
					maxLen = len;
					st = arr[i];
				}
			}
		}

		for (int i = 0; i < maxLen; i++) {
			System.out.println(st++);
		}
	}
}