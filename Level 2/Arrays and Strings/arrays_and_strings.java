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
		// first positive missing starting from 1
		//A1. search 1 , 2... n O(n^2)
		//A2. sort and start checking from first +ve number
		//A3. imp observation ideally for n size 1..n must exist
		// if any num is missing it is ans , else n+1 is ans

		//Step 1 . Mark elements which are out of range and check is 1 exists
		boolean isOnePresent = false;
		int n = nums.length;

		for (int i = 0; i < n; i++) {
			if (nums[i] == 1) {
				isOnePresent = true;
			}
			if (nums[i] < 1 || nums[i] > n) {
				nums[i] = 1; //mark with 1
			}
		}

		//Step 2 . if 1 absent , it is ans
		if (isOnePresent == false) {
			return 1;
		}

		//Step 3. Mark corresponding indexes
		for (int i = 0; i < n; i++) {
			int idx = Math.abs(nums[i]) - 1;
			nums[idx] = -Math.abs(nums[idx]);
		}

		//Step 4. Now first +ve/unmarked/vis idx is missing
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


	// Leetcode 204. Count Primes

	// Sieve of Eratosthenes
	public int countPrimes(int n) {
		boolean[] dp = new boolean[n + 1];

		for (int i = 2; i * i < n; i++) {
			for (int j = i * i; j < n; j += i) {
				dp[i] = true;
			}
		}
	}

	// 763. Partition Labels
	public List < Integer > partitionLabels(String s) {
		Map < Character, Integer > map = new HashMap < > ();
		//map of ending indexes of each char
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			map.put(ch, i);
		}

		List < Integer > res = new ArrayList < > ();
		//the char with the farthest effect will decide the chunk size
		//merge all other chars will less effect range
		int max = -(int) 1e9;
		int prev = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			max = Math.max(max, map.get(ch));
			//if reached end of chunk
			if (max == i) {
				res.add(i - prev + 1);
				prev = i + 1; //si of next chunk
			}
		}

		return res;
	}

	// 754. Reach a Number
	public int reachNumber(int target) {
		target = Math.abs(target);
		int sum = 0, jumps = 0;
		while (sum < target) {
			jumps++;
			sum += jumps;
		}

		if (sum == target) {
			return jumps;
		} else if ((sum - target) % 2 == 0) {
			return jumps;
		} else if ((sum + jumps + 1 - target) % 2 == 0) {
			return jumps + 1;
		} else {
			return jumps + 2;
		}
	}

	// 867. Transpose Matrix (M*N)
	public int[][] transpose(int[][] A) {

		int r = A.length;
		int c = A[0].length;

		int ans[][] = new int[c][r];

		for (int i = 0 ; i < c ; i++) {
			for (int j = 0 ; j < r ; j++) {
				ans[i][j] = A[j][i];
			}
		}

		return ans;

	}

	//Transpose N*N
	public static void transpose_(int[][] matrix) {
		int n = matrix.length;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < r; c++) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[c][r];
				matrix[c][r] = temp;
			}
		}
	}

	// 48. Rotate Image
	public void rotate(int[][] matrix) {
		//transpose
		int n = matrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}

		//interchange
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][n - 1 - j];
				matrix[i][n - 1 - j] = temp;
			}
		}
	}

	//push dominos
	//reach a number
	// transpose
	//rotate image
	//sieve of eratosthenes
	// segmented sieve
	// boats to save people
	// find pair given difference


	// 829. Consecutive Numbers Sum
	public int consecutiveNumbersSum(int n) {
		int ct = 0;
		for (int k = 1; k * (k - 1) < 2 * n; k++) {
			//find if it is possible to make sum with k numbers;
			int numerator = n - ((k - 1) * k / 2);
			if (numerator % k == 0) {
				ct++;
			}
		}
		return ct;
	}

	//415. Add Strings

	public static String addStrings(String num1, String num2) {
		int n = num1.length() , m = num2.length();
		int i = n - 1, j = m - 1;

		String res = "";
		int carry = 0;
		while (i >= 0 || j >= 0 || carry != 0) {
			int a = 0, b = 0;

			if (i >= 0) a = num1.charAt(i) - '0';
			if (j >= 0) b = num2.charAt(j) - '0';

			int sum = a + b + carry;
			carry = sum / 10;
			int digit = sum % 10;

			i--;
			j--;

			char num = (char)digit;
			res = digit + res;

		}

		return res;
	}

	// 43. Multiply Strings

	public String multiply(String num1, String num2) {
		int n = num1.length(), m = num2.length();
		int[] res = new int[m + n];

		// max possible len = m+n;
		// pos of sum%10 = dig = i+j+1;
		// pos of carry = i+j

		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {

				int val = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

				int pd = i + j + 1; //digit
				int pc = i + j; //carry

				int sum = val + res[pd];

				res[pc] += sum / 10;
				res[pd] = sum % 10;
			}

		}

		StringBuilder sb = new StringBuilder();

		for (int ele : res) {
			if (sb.length() == 0 && ele == 0) {

			} else {
				sb.append(ele);
			}
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}


	// Leetcode 42. Trapping Rain Water

	// O(N)
	public int trap(int[] height) {
		int n = height.length;
		if (n == 0)return 0;

		int[] lMax = new int[n];
		int[] rMax = new int[n];

		int lm = height[0];
		for (int i = 1; i < n; i++) {
			if (height[i] > lm) {
				lm = height[i];
			}
			lMax[i] = lm;
		}

		int rm = height[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			if (height[i] > rm) {
				rm = height[i];
			}
			rMax[i] = rm;
		}

		int trap = 0;
		for (int i = 1; i < n - 1; i++) {
			trap += Math.min(lMax[i], rMax[i]) - height[i];
		}

		return trap;
	}


	//O(1)

	//Brute force = min(lmax,rmax)-arr[i];
	// here only lmax-arr[i] or rmax-arr[i];
	// how we are making sure that it's min??

	// l++ only when a[l]<=a[r] -> so when curr a[l]<lmax , it's guaranteed lmax<=rmax
	// also lmax<=rmax so, min(lmax,rmax) = lmax

	public int trap(int[] arr) {
		int n = arr.length;
		int l = 0, r = n - 1, lmax = 0, rmax = 0, water = 0;

		while (l < r) {
			if (arr[l] <= arr[r]) {
				if (arr[l] > lmax) {
					lmax = arr[l];
				} else {
					water += lmax - arr[l];
				}

				l++;
			} else {
				if (arr[r] > rmax) {
					rmax = arr[r];
				} else {
					water += rmax - arr[r];
				}
				r--;
			}
		}

		return water;
	}

	// Leetcode 239. Sliding Window Maximum

	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		int[] ans = new int[n - k + 1];
		Deque < Integer > dq = new ArrayDeque < > ();
		//1st k elem
		// dq.add(0);
		for (int i = 0; i < k; i++) {
			while (dq.size() > 0 && nums[dq.getLast()] <= nums[i]) {
				dq.removeLast();
			}
			dq.addLast(i);
		}

		ans[0] = nums[dq.peek()];

		//for all others
		for (int i = k; i < nums.length; i++) {
			while (dq.size() > 0 && nums[dq.getLast()] <= nums[i]) {
				dq.removeLast();
			}
			dq.addLast(i);
			//maybe possible that max val is out of win , only one
			// val can be out of win at any time , coz we are removing evry time
			if (dq.peek() <= i - k) {
				dq.removeFirst();
			}
			// System.out.println(dq+" "+i);
			ans[i - k + 1] = nums[dq.peek()];
		}

		return ans;
	}


	//Stack Method
	public int[] maxSlidingWindow(int[] nums, int k) {

		if (nums.length == 1) return nums;
		int n = nums.length;
		int[] ans = new int[n - k + 1];
		int[] nge = NGE(nums);

		int j = 0;

		for (int i = 0; i < ans.length; i++) {
			if (j < i) j = i;

			//while j in range
			while (nge[j] < i + k) {
				j = nge[j];
			}
			ans[i] = nums[j];
		}

		return ans;
	}

	public int[] NGE(int[] nums) {
		Stack < Integer > st = new Stack < > ();
		int n = nums.length;
		int[] nge = new int[n];
		st.push(0);

		for (int i = 1; i < n; i++) {
			if (nums[st.peek()] >= nums[i]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
					nge[st.pop()] = i;
				}
			}
			st.push(i);
		}

		while (!st.isEmpty()) {
			nge[st.pop()] = n;
		}

		return nge;
	}

	// digit multiplier, https://practice.geeksforgeeks.org/problems/digit-multiplier3000/1

	// first negative, https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1

	// Kadanes
	// 1191. K-Concatenation Maximum Sum
	// https://www.codechef.com/problems/KCON


	// 152. Maximum Product Subarray
	public int maxProduct(int[] nums) {
		int max = -11, product = 1, len = nums.length;

		//whn odd -ves either skip 1 -ve at extreme side
		//left prod
		for (int i = 0; i < len; i++) {
			max = Math.max(product *= nums[i], max);
			if (nums[i] == 0) product = 1;
		}

		product = 1;
		//right prod
		for (int i = len - 1; i >= 0; i--) {
			max = Math.max(product *= nums[i], max);
			if (nums[i] == 0) product = 1;
		}

		return max;
	}

	// Max sum in sub-arrays
	// https://practice.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/1
	public static long pairWithMaxSum(long arr[], long N) {
		long res = -(int)1e9;
		for (int i = 1; i < arr.length; i++) {
			res = Math.max(res, arr[i - 1] + arr[i]);
		}
		return res;
	}


	//Gas Station

	// 1007. Minimum Domino Rotations For Equal Row

	public int minDominoRotations(int[] tops, int[] bottoms) {
		int val1 = tops[0];
		int val2 = bottoms[0];

		int count1 = 0; // rotation required to make top as val1
		int count2 = 0; // rotation required to make top as val2
		int count3 = 0; // rotation required to make bottom as val1
		int count4 = 0; // rotation required to make bottom as val2

		for (int i = 0; i < tops.length; i++) {
			if (count1 != Integer.MAX_VALUE) {
				if (tops[i] == val1) {
					// nothing to do
				} else if (bottoms[i] == val1) {
					count1++;
				} else {
					count1 = Integer.MAX_VALUE;
				}
			}

			if (count2 != Integer.MAX_VALUE) {
				if (tops[i] == val2) {
					// nothing to do
				} else if (bottoms[i] == val2) {
					count2++;
				} else {
					count2 = Integer.MAX_VALUE;
				}
			}

			if (count3 != Integer.MAX_VALUE) {
				if (bottoms[i] == val1) {
					// nothing to do
				} else if (tops[i] == val1) {
					count3++;
				} else {
					count3 = Integer.MAX_VALUE;
				}
			}

			if (count4 != Integer.MAX_VALUE) {
				if (bottoms[i] == val2) {
					// nothing to do
				} else if (tops[i] == val2) {
					count4++;
				} else {
					count4 = Integer.MAX_VALUE;
				}
			}
		}
		int res = Math.min(Math.min(count1, count2), Math.min(count3, count4));
		return res == Integer.MAX_VALUE ? -1 : res;
	}


	// 632. Smallest Range Covering Elements from K Lists

	public int[] smallestRange(List < List < Integer >> nums) {
		PriorityQueue < rPair > pq = new PriorityQueue < > ();
		int max = -(int) 1e9;

		//add first 3 vals
		for (int i = 0; i < nums.size(); i++) {
			int val = nums.get(i).get(0);
			pq.offer(new rPair(val, i, 0));
			max = Math.max(val, max);
		}

		int sp = 0;
		int ep = 0;
		int length = (int) 1e9;

		while (true) {
			rPair rem = pq.poll();
			int minV = rem.val;
			int maxV = max;

			if (maxV - minV < length) { //update
				sp = minV;
				ep = maxV;
				length = maxV - minV;
			}

			//add next val from list of removed val
			if (rem.c + 1 < nums.get(rem.r).size()) {
				int val = nums.get(rem.r).get(rem.c + 1);
				pq.offer(new rPair(val, rem.r, rem.c + 1));
				max = Math.max(max, val);
			} else {
				break;
			}
		}

		int[] res = {
			sp,
			ep
		};
		return res;
	}

	private class rPair implements Comparable < rPair > {
		int val;
		int r;
		int c;

		public rPair() {}

		public rPair(int val, int r, int c) {
			this.val = val;
			this.r = r;
			this.c = c;
		}

		public int compareTo(rPair o) {
			return this.val - o.val;
		}
	}

	// 209. Minimum Size Subarray Sum
	public int minSubArrayLen(int target, int[] nums) {
		int left = 0, sum = 0, len = (int)1e9;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= target) {
				return 1;
			}

			sum += nums[i];
			while (sum >= target) {
				len = Math.min(len, i - left + 1);
				sum -= nums[left];
				left++;
			}
		}
		return len == (int)1e9 ? 0 : len;
	}

	// 643. Maximum Average Subarray I
	public double findMaxAverage(int[] nums, int k) {
		double max = -(int)1e9 * 1.0;
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i < k - 1) {
				sum += nums[i];
			} else {
				sum += nums[i];
				double avg = sum * 1.0 / k;
				max = Math.max(max, avg);
				sum -= nums[i - k + 1];
			}

		}

		return max;
	}


	//XOR
	public int missingNumber(int[] nums) {

		int xor = 0, i = 0;
		for (i = 0; i < nums.length; i++) {
			xor = xor ^ i ^ nums[i];
		}

		return xor ^ i;
	}

	//Sum
	public static int missingNumber(int[] nums) {
		int sum = nums.length;
		for (int i = 0; i < nums.length; i++)
			sum += i - nums[i];
		return sum;
	}

	// 125. Valid Palindrome
	public boolean isPalindrome(String s) {
		StringBuilder sb = new StringBuilder();
		for (char ch : s.toCharArray()) {
			if (ch >= 'a' && ch <= 'z') {
				sb.append(ch);
			} else if (ch >= 'A' && ch <= 'Z') {
				char temp = (char)(ch + 32);
				sb.append(temp);
			} else if (Character.isDigit(ch)) {
				sb.append(ch);
			}
		}

		System.out.println(sb);
		return isPal(sb.toString());
	}

	public boolean isPal(String str) {
		int i = 0 , j = str.length() - 1;
		while (i <= j) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	// 680. Valid Palindrome II
	public boolean validPalindrome(String s) {

		int i = 0, j = s.length() - 1;
		if (isPal(s, i, j)) return true;

		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				if (isPal(s, i + 1, j)) return true;
				else if (isPal(s, i, j - 1)) return true;
				else return false;
			}
			i++;
			j--;
		}
		return false;
	}

	public boolean isPal(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	//can also be solved recursively

	//generalise for atmost k chars
	// Valid Palindrome III


	public static void main(String[] args) {

	}

}