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


	// Search A 2d Matrix


	// Search A 2d Matrix - 2


	// Find Pivot Index / Equilibrium Index
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

	// Search In Rotated Sorted Array
}