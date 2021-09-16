public class arrays_and_strings {

	// 925. Long Pressed Name
	public boolean isLongPressedName(String name, String typed) {
		int i = 0; //name
		int j = 0; //typed

		while (i < name.length() && j < typed.length()) {
			if (name.charAt(i) == typed.charAt(j)) {
				i++;
				j++;
			} else {
				if (i > 0 && name.charAt(i - 1) == typed.charAt(j)) {//typed char can be more
					j++;
				} else {
					return false; //typed char is diff than curr and also prev name char
				}
			}
		}

		//last char of name maybe left in typed or not
		while (j < typed.length()) {
			if (typed.charAt(j) != name.charAt(i - 1)) {
				return false;
			}
			j++;
		}

		// ex aaabb , aaaaaa , i stops moving and j is left
		return i == name.length();
	}


	// 11. Container With Most Water
	public int maxArea(int[] height) {
		int n = height.length;
		int l = 0, r = n - 1, maxArea = 0;

		while (l < r) {
			int area = Math.min(height[l], height[r]) * (r - l);
			maxArea = Math.max(area, maxArea);
			if (height[l] < height[r]) {
				l++;
			} else r--;
		}
		return maxArea;
	}
	//if hi<hj , j-- will always give less area if hj inc or dec , coz len=len-1 , and height is
	//decided by the lesser one
	//i++ maybe give better ans or not , if not we already store max at every pt;




	//O(n^2) , maximizie area b/w all i,j pairs n(n-1)/2 pairs
	public int solve(int[] height) {
		int n = height.length;
		int maxArea = 0;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int area = Math.min(height[i], height[j]) * (j - i);
				maxArea = Math.max(area, maxArea);
			}
		}

		return maxArea;
	}


	// 977. Squares of a Sorted Array
	public int[] sortedSquares(int[] nums) {
		int n = nums.length;
		int i = 0 , j = n - 1 , k = n - 1;
		int[] res = new int[n];

		while (k >= 0) {
			int l = nums[i] * nums[i];
			int r = nums[j] * nums[j];

			if (r > l) {
				res[k] = r;
				j--;
			} else {
				res[k] = l;
				i++;
			}
			k--;
		}

		return res;
	}

	// k is at n-1 (largest val) , largest val will be given by least(-ve) val or most +ve val



	// ... Moore's Voting Algorithm ***********************************************


	// 169. Majority Element  ( >N/2 times)
	public int majorityElement(int[] nums) {
		int cand = nums[0], ct = 1;
		for (int i = 1; i < nums.length; i++) {
			int ele = nums[i];

			if (ele == cand) {
				ct++;
			} else {
				if (ct > 0) {
					//pair up with the cand
					ct--;
				} else { //all prev ele are paired up
					//start a new candidate
					cand = ele;
					ct = 1;
				}
			}
		}

		//if majority element exists , its definitely = cand
		//follow up if no maj ele exists , return -1;
		int count = 0;
		for (int ele : nums) {
			if (ele == cand) count++;
		}

		return count > nums.length / 2 ? cand : -1;
	}

	//alternative
	public int majorityElement(int[] nums) {
		int candidate = 0;
		int votes = 0;
		for (int num : nums) {
			if (votes == 0)candidate = num;
			if (candidate == num)votes++;
			else votes--;
		}
		return candidate;
	}


	// 229. Majority Element II  (  >N/3 times )
	public List<Integer> majorityElement(int[] nums) {
		//make pairs of 3 ele together
		int val1 = nums[0] , count1 = 1, val2 = 1, count2 = 0 , n = nums.length;

		for (int i = 1; i < n; i++) {
			if (nums[i] == val1) {
				count1++;
			} else if (nums[i] == val2) {
				count2++;
			} else {
				if (count1 == 0) { //set new candidate
					val1 = nums[i];
					count1 = 1;
				} else if (count2 == 0) { //set new candidate
					val2 = nums[i];
					count2 = 1;
				} else {    //pair up
					count1--;
					count2--;
				}
			}
		}

		//check validity
		int freq1 = 0 , freq2 = 0;
		for (int ele : nums) {
			if (ele == val1)
				freq1++;
			else if (ele == val2)
				freq2++;
		}

		List<Integer> res = new ArrayList<>();
		if (freq1 > n / 3) res.add(val1);
		if (freq2 > n / 3) res.add(val2);

		return res;

	}


	//General >n/k times
	// 1. using HashMap
	// 2. using pairing of k items , using k-1 vals and counts
	// use k-1 size arr vals and count

	public List<Integer> majorityK(int[] nums , int k) {
		int n = nums.length;
		int[] val = new int[k - 1];
		int[] count = new int[k - 1];


	}



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


	// 628. Maximum Product of Three Numbers

	// (-)*(-)*(+) = +;
	// (+)*(+)*(+) = +;

	public int maximumProduct(int[] nums) {
		int max1 = -(int) 1e9;
		int max2 = -(int) 1e9;
		int max3 = -(int) 1e9;

		int min1 = (int) 1e9;
		int min2 = (int) 1e9;

		for (int val : nums) {
			if (val > max1) {
				max3 = max2;
				max2 = max1;
				max1 = val;
			} else if (val > max2) {
				max3 = max2;
				max2 = val;
			} else if (val > max3) {
				max3 = val;
			}

			if (val < min1) {
				min2 = min1;
				min1 = val;
			} else if (val < min2) {
				min2 = val;
			}
		}

		int candidate1 = max1 * max2 * max3;
		int candidate2 = max1 * min1 * min2;

		return Math.max(candidate1, candidate2);
	}


	// 769. Max Chunks To Make Sorted

	//chaining tachnique
	// imp - given nums - 0 to n-1
	// look at the index till where impact of number is visible
	public int maxChunksToSorted(int[] arr) {
		int max = -(int) 1e9, chunks = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}

			if (i == max) {
				chunks++;
				max = arr[i];
			}
		}

		return chunks;
	}

	// 768. Max Chunks To Make Sorted II
	// chunks I - impact by index
	// chunks -II - impact by value

	public int maxChunksToSorted(int[] arr) {
		int n = arr.length, chunks = 1;
		int[] leftMax = new int[n]; //left max
		int[] righttMin = new int[n]; //right min

		//the max of left chunk will always be <= min of right chunk

		leftMax[0] = arr[0];
		righttMin[n - 1] = arr[n - 1];

		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
		}

		for (int i = n - 2; i >= 0; i--) {
			righttMin[i] = Math.min(righttMin[i + 1], arr[i]);
		}

		for (int i = 0; i < n - 1; i++) {
			if (leftMax[i] <= righttMin[i + 1]) {
				chunks++;
			}
		}

		return chunks;
	}


	// 747. Largest Number At Least Twice of Others
	public int dominantIndex(int[] nums) {
		int n = nums.length, max1 = -(int) 1e9, max2 = -(int) 1e9, idx = -1;

		if (n == 1) return 0;

		for (int i = 0; i < n; i++) {
			int ele = nums[i];
			if (ele > max1) {
				max2 = max1;
				max1 = ele;
				idx = i;
			} else if (ele > max2) {
				max2 = ele;
			}
		}


		return max1 >= 2 * max2 ? idx : -1;
	}


	// 345. Reverse Vowels of a String
	public String reverseVowels(String s) {
		int n = s.length();
		char[] str = s.toCharArray();
		int i = 0, j = n - 1;

		while (i < j) {
			if (isVowel(str[i]) && isVowel(str[j])) {
				char temp = str[i];
				str[i] = str[j];
				str[j] = temp;

				i++;
				j--;

			} else if (isVowel(str[i])) {
				j--;
			} else if (isVowel(str[j])) {
				i++;
			} else {
				i++;
				j--;
			}

		}

		String res = String.valueOf(str);
		return res;
	}

	private boolean isVowel(char ch) {
		String s = "AEIOUaeiou";
		return s.contains(ch + "");
	}






	public static void main(String[] args) {

	}

}