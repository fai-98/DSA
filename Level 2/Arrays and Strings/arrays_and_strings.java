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


	// 795. Number of Subarrays with Bounded Maximum

	// 	subarrays ending at that idx
	// 1. if( valid range ) i-j+1 (all prev items come once with this ele + 1 (self) (init j=0
	// 2. if ele>R , update j = last occurence of break pt ,
	// bcz SArrs before this pt are not allowed to combine coz max changes .  change j=i+1
	// 3. we need subarrays after that pt


	public int numSubarrayBoundedMax(int[] nums, int left, int right) {
		int res = 0, si = 0, ei = 0, prev = 0;
		// prev = no. of subarrays ending here!
		// res = res(prev idx total sub arrs) + ending here
		while (ei < nums.length) {
			if (nums[ei] >= left && nums[ei] <= right) {
				prev = ei - si + 1;
				res = res + prev;
			} else if (nums[ei] < left) {
				res = res + prev; //ignoring 1 size subarray which is <left
			} else {
				prev = 0;
				si = ei + 1;
			}

			ei++;
		}

		return res;
	}




	// leetcode 119. https://leetcode.com/problems/pascals-triangle-ii/



	// Leetcode 41. First Missing Positive********************************************

	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		//Step 1 . mark elements which are out of range and check if 1 exists
		//range shd be 1..n for n size array
		boolean one = false;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 1)
				one = true;
			if (nums[i] < 1 || nums[i] > n)
				nums[i] = 1;
		}

		//if one is not present it's 1st missing pos
		if (!one) return 1;

		//Step 2. Map value with indexes
		for (int i = 0; i < n; i++) {
			int idx = Math.abs(nums[i]) - 1;
			nums[idx] = -Math.abs(nums[idx]);
		}

		//Step 3. find the res , the idx which is still +ve means idx+1 is missing
		for (int i = 0; i < n; i++) {
			if (nums[i] > 0) return i + 1;
		}

		//if still not found -> all 1..n are present
		return n + 1;
	}

	// Lintcode 912 · Best Meeting Point
	public int minTotalDistance(int[][] grid) {
		int n = grid.length , m = grid[0].length;
		List<Integer> x = new ArrayList<>();
		List<Integer> y = new ArrayList<>();

		// instead of sorting after collecting x,y
		// collect x row wise , and y column wise traversal

		//collect x co-ord
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1)
					x.add(i);
			}
		}

		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				if (grid[i][j] == 1)
					y.add(j);
			}
		}

		//the best pt will lie on the median

		int px = x.get(x.size() / 2);   //find median
		int py = y.get(y.size() / 2);

		int dist = 0;
		for (int i = 0; i < x.size(); i++) {
			dist += Math.abs(x.get(i) - px) + Math.abs(y.get(i) - py);
		}

		return dist;
	}



	// 537. Complex Number Multiplication
	public String complexNumberMultiply(String num1, String num2) {
		String[] s = num1.split("\\+|i", 0);
		String[] t = num2.split("\\+|i", 0);
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		int u = Integer.parseInt(t[0]);
		int v = Integer.parseInt(t[1]);

		int real = (x * u - y * v);
		int imaginary = (x * v + y * u);
		return "" + real + "+" + imaginary + "i";
	}



	public static void main(String[] args) {

	}

}