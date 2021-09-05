public class min_max_val_of_expression {


	// Minimum and Maximum values of an expression with * and + **************************************
	public static class minMaxpair {
		int minVal = (int)1e9;
		int maxVal = -(int)1e9;

		minMaxpair() {}

		minMaxpair(int minVal, int maxVal) {
			this.minVal = minVal;
			this.maxVal = maxVal;
		}
	}

	//if + min = lmin + rmin , .....
	public static minMaxpair evaluate(minMaxpair lRes  , minMaxpair rRes , char opr) {
		if (opr == '+') {
			return new minMaxpair(lRes.minVal + rRes.minVal, lRes.maxVal + rRes.maxVal);
		} else {
			return new minMaxpair(lRes.minVal * rRes.minVal, lRes.maxVal * rRes.maxVal);
		}
	}

	public static minMaxpair minMaxExp(String str , int si , int ei , minMaxpair[][] dp) {
		if (si == ei) {
			int val = str.charAt(si) - '0';
			return dp[si][ei] = new minMaxpair(val, val);   //its single digit number
		}

		if (dp[si][ei] != null) {
			return dp[si][ei];
		}

		minMaxpair myRes = new minMaxpair();

		for (int cut = si + 1; cut < ei; cut += 2) {
			minMaxpair lRes = minMaxExp(str, si, cut - 1, dp);
			minMaxpair rRes = minMaxExp(str, cut + 1, ei, dp);

			minMaxpair eval = evaluate(lRes, rRes, str.charAt(cut));  //lres , cut = operator , rres

			myRes.minVal = Math.min(eval.minVal, myRes.minVal);
			myRes.maxVal = Math.max(eval.maxVal, myRes.maxVal);
		}

		return dp[si][ei] = myRes;
	}

	public static void minMaxExp() {
		String str = "1+2*3+4*5";
		int n = str.length();
		minMaxpair[][] dp = new minMaxpair[n][n];
		minMaxpair res = minMaxExp(str, 0, n - 1, dp);

		System.out.println("Min Res : " + res.minVal);
		System.out.println("Max Res : " + res.maxVal);
	}

	// Follow Up Ques
	// 1. Using 3D DP ??
	// 2. What if oprs are + , * , -

	public static void main(String[] args) {
		// mcmSet();
		minMaxExp();
	}
}