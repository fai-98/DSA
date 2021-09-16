public class minimum_cost_to_cut_a_stick {
	// Leetcode 1547. Minimum Cost to Cut a Stick

	public int minCost(int n, int[] cuts) {
		Map < String, Integer > memo = new HashMap < > ();
		Arrays.sort(cuts);
		return cost_mcm(cuts, 0, n, memo);
	}


	//1373 ms
	public int cost_mcm(int[] arr, int si, int ei, Map < String, Integer > memo) {

		if (ei - si <= 1) {
			String base = si + "-" + ei;
			memo.put(base, 0);
			return 0;
		}

		String str = si + "-" + ei;

		if (memo.containsKey(str)) {
			return memo.get(str);
		}

		int myRes = (int) 1e9;
		for (int i = 0; i < arr.length; i++) {
			int cut = arr[i];

			//cut is out of stick range
			if (cut <= si || cut >= ei)
				continue;

			int lans = cost_mcm(arr, si, cut, memo);
			int rans = cost_mcm(arr, cut, ei, memo);

			int rres = lans + (ei - si) + rans;
			//lans + rans + self cost
			myRes = Math.min(myRes, rres);

		}
		//maybe possible res is not updated , so return very less value
		myRes = myRes == (int) 1e9 ? 0 : myRes;
		memo.put(str, myRes);
		return myRes;

	}

	//n can be 10^6 , so n^2 dp gives TLE and also lot of space is wasted , so use memo as hashMap

	//faster ?
	// instead of using 0 and n-1 as si,ei and checking if cut is valid or not
	// push 0 at begin and n at end of cuts array , and simply apply mcm
	// cuts[] = [0 .. cut1 cut2...cutn..n]

	public int minCost(int n, int[] cuts) {
		List < Integer > arr = new ArrayList < > ();
		arr.add(0);
		for (int ele : cuts) arr.add(ele);
		arr.add(n);
		Collections.sort(arr);

		int[][] dp = new int[102][102];
		for (int[] r : dp) Arrays.fill(r, -1);
		return cuts_mcm(arr, 0, arr.size() - 1, dp);
	}


	// 75ms
	public int cuts_mcm(List < Integer > arr, int si, int ei, int[][] dp) {
		if (ei - si == 1) {
			return dp[si][ei] = 0;
		}

		if (dp[si][ei] != -1) {
			return dp[si][ei];
		}

		int myRes = (int) 1e9;
		for (int cut = si + 1; cut < ei; cut++) {

			int lans = cuts_mcm(arr, si, cut, dp);
			int rans = cuts_mcm(arr, cut, ei, dp);

			int rres = lans + arr.get(ei) - arr.get(si) + rans;
			myRes = Math.min(myRes, rres);
		}

		myRes = myRes == (int) 1e9 ? 0 : myRes;
		return dp[si][ei] = myRes;
	}
}