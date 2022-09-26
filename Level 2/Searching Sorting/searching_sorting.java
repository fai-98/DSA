public class searching_sorting {

	// Marks Of Pcm ,
	//https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/searching-and-sorting/marks_of_pcm/ojquestion

	public static class Marks implements Comparable<Marks> {
		int phy;
		int chem;
		int maths;

		Marks() {

		}

		Marks(int phy, int chem, int maths) {
			this.phy = phy;
			this.chem = chem;
			this.maths = maths;
		}

		public int compareTo(Marks o) {
			if (this.phy != o.phy) {
				return this.phy - o.phy;
			} else if (this.chem != o.chem) {
				return o.chem - this.chem;
			} else {
				return this.maths - o.maths;
			}
		}
	}

	/*You have to complete the body of customSort function,
	after sorting final changes should be made in given arrays only. */

	public static void customSort(int[]phy, int[]chem, int[]math) {
		int n = phy.length;
		Marks[] arr = new Marks[n];

		for (int i = 0; i < n; i++) {
			arr[i] = new Marks(phy[i], chem[i], math[i]);
		}

		Arrays.sort(arr);

		for (int i = 0; i < n; i++) {
			phy[i] = arr[i].phy;
			chem[i] = arr[i].chem;
			math[i] = arr[i].maths;
		}
	}

	// Union Of Two Sorted Arrays
	public static ArrayList<Integer> union(int[] a, int[] b) {
		ArrayList<Integer> res = new ArrayList<>();

		int i = 0, j = 0, n = a.length, m = b.length;

		while (i < n && j < m) {
			if (a[i] == b[j]) {
				if (res.size() == 0 || res.get(res.size() - 1) != a[i]) {
					res.add(a[i]);
				}
				i++; j++;
			} else if (a[i] < b[j]) {
				if (res.size() == 0 || res.get(res.size() - 1) != a[i]) {
					res.add(a[i]);
				}
				i++;
			} else {
				if (res.size() == 0 || res.get(res.size() - 1) != b[j]) {
					res.add(b[j]);
				}
				j++;
			}
		}

		while (i < n) {
			if (res.size() == 0 || res.get(res.size() - 1) != a[i]) {
				res.add(a[i]);
			}
			i++;
		}

		while (j < m) {
			if (res.size() == 0 || res.get(res.size() - 1) != b[j]) {
				res.add(b[j]);
			}
			j++;
		}

		return res;
	}


	// 74. Search a 2D Matrix
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


	// Search A 2d Matrix - 2


	// 724. Find Pivot Index / Equilibrium Index
	public int pivotIndex(int[] nums) {
		int osum = 0, lsum = 0;
		for (int i = 0; i < nums.length; i++) {
			osum += nums[i];
		}

		for (int i = 0; i < nums.length; i++) {
			if (osum - nums[i] == lsum * 2) {
				return i;
			}
			lsum += nums[i];
		}
		return -1;
	}


	//find pair with given difference
	public static void findPair(int[] arr, int target) {
		Arrays.sort(arr);
		int lo = 0;
		int hi = 1;

		while (hi < arr.length && lo < hi) {
			if (arr[hi] - arr[lo] == target) {
				System.out.println(arr[lo] + " " + arr[hi]);
				return;
			} else if (arr[hi] - arr[lo] > target) {
				lo++;
			} else {
				hi++;
			}
		}
		System.out.println("-1");
	}

	//What if negatives are present ?

	// if diff is -ve , just make it +ve
	// if pair(i,j) is found with diff = d = j-i
	// it means pair i-j has diff = -d
	public int solve(ArrayList<Integer> arr, int target) {
		Collections.sort(arr);
		if (target < 0) target = -target;
		int lo = 0;
		int hi = 1;

		while (hi < arr.size() && lo <= hi ) {
			int diff = arr.get(hi) - arr.get(lo);
			if (diff == target && lo != hi) {
				return 1;
			} else if (diff > target) {
				lo++;
			} else {
				hi++;
			}
		}

		return 0;
	}


	//Roof top , portal , similar to buy sell stock 1
	public static int findMaxSteps(int[]arr) {
		int res = 0, ct = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i - 1]) {
				ct++;
			} else {
				res = Math.max(ct, res);
				ct = 0;
			}
		}
		res = Math.max(ct, res);
		return res;
	}


	// Maximize Sum Of Arr[i]*i Of An Array
	public static int maximise(int[]arr) {
		Arrays.sort(arr);
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			res += arr[i] * i;
		}
		return res;
	}

	// Max Sum In The Configuration , arr[i]*i , rotation allowed
	public static int maximise(int[]arr) {
		int sum = 0, sim1 = 0, n = arr.length;  // sum of i-1

		for (int i = 0; i < n; i++) {
			sim1 += arr[i] * i;
			sum += arr[i];
		}

		int res = sim1;
		for (int i = 1; i < n; i++) {
			int si = sim1 + sum - n * (arr[n - i]);
			res = Math.max(si, res);
			sim1 = si;
		}
		return res;
	}

	// Count Zeros Xor Pairs
	public static int countPairs(int[]arr) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		int res = 0;
		for (int key : map.keySet()) {
			int fq = map.get(key);
			res += (fq * (fq - 1)) / 2;
		}

		return res;
	}

	// Facing The Sun
	public static int countBuildings(int[]ht) {
		int max = ht[0], ct = 1;
		for (int i = 1; i < ht.length; i++) {
			if (ht[i] > max) {
				max = ht[i];
				ct++;
			}
		}
		return ct;
	}

	// Largest Perimeter Triangle
	//a<b<c , so only check is a+b>c
	//if false move all 3 ptrs
	public static int largestPerimeter(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length, a = 0, b = 0, c = 0, perimeter = 0;
		int i = n - 3, j = n - 2, k = n - 1;

		while (i >= 0) {
			a = nums[i];
			b = nums[j];
			c = nums[k];

			if (a + b > c) {
				perimeter = a + b + c;
				break;
			}

			i--; j--; k--;
		}
		return perimeter;
	}

	//Largest Number
	public static String largestNumber(int[]nums) {

		return "";
	}


	//Ishaan and sticks
	//Using Sorting ??
	public static ArrayList<Integer>  solve(int[] arr) {
		Arrays.sort(arr);
		int n = arr.length;
		int i = n - 4, j = n - 3, k = n - 2, l = n - 1, ct = 0, area = 0;
		int a = 0, b = 0, c = 0, d = 0;

		while (i >= 0) {
			int a = arr[i];
			int b = arr[j];
			int c = arr[k];
			int d = arr[l];

			if (a == b && a == c && a == d) {
				area = a * a;
				ct++;
				i -= 4; j -= 4; k -= 4; l -= 4;
			}

			i--; j--; k--; l--;
		}
	}

	//HashMap
	public static ArrayList<Integer>  solve(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int ele : arr) {
			map.put(ele, map.getOrDefault(ele, 0) + 1);
		}

		int maxArea = -(int)1e9, maxSide = -(int)1e9, ct = 0;

		for (int key : map.keySet()) {
			int freq = map.get(key);
			if (key > maxSide && freq >= 4) {
				maxArea = key * key;
				ct = freq / 4;
				maxSide = key;
			}
		}

		ArrayList<Integer> res = new ArrayList<>();
		res.add(maxArea);
		res.add(ct);
		return res;
	}


	// Toppers Of Class (Redo)
	public static class Pair implements Comparable<Pair> {
		int marks;
		int idx;

		public Pair() {}

		public Pair(int marks, int idx) {
			this.marks = marks;
			this.idx = idx;
		}
	}

	public static int[] kToppers(int[] arr, int k) {
		int n = arr.length;
		Pair[] narr = new Pair[n];
		for (int i = 0; i < n; i++) {
			narr[i] = new Pair(arr[i], i);
		}

		Arrays.sort(narr, (a, b)-> {
			if (a.marks != b.marks) {
				return b.marks - a.marks;
			} else {
				return a.idx - b.idx;
			}
		});

		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			Pair rp = pq.poll();
			res[i] = rp.idx;
		}

		return res;
	}

	//Leaders in an Array

}