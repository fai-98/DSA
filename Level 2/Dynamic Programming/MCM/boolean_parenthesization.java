import java.util.Arrays;
public class boolean_parenthesization {

	// Boolean Parenthesization Problem | DP-37 - Evaluate exp to true
	// GFG
	public static class bpair {
		int tCount = 0;
		int fCount = 0;

		bpair() {}

		bpair(int tCount , int fCount) {
			this.tCount = tCount;
			this.fCount = fCount;
		}

	}

	//ans+=  ... add to ans - the total count/ways to make T/F exp from all possible cuts
	public static void eval(bpair left, bpair right, char oper, bpair ans) {
		int mod = 1003;
		int totalTF = ((left.tCount + left.fCount) % mod * (right.tCount + right.fCount) % mod) % mod;
		if (oper == '|') {
			int fCount = (left.fCount % mod * right.fCount % mod) % mod;
			ans.fCount += fCount;
			ans.tCount += (totalTF - fCount + mod) % mod;
		} else if (oper == '&') {
			int tCount = (left.tCount % mod * right.tCount % mod) % mod;
			ans.tCount += tCount;
			ans.fCount += (totalTF - tCount + mod) % mod;
		} else {
			int tCount = ((left.tCount % mod * right.fCount % mod) % mod + (left.fCount % mod * right.tCount % mod) % mod) % mod;
			ans.tCount += tCount;
			ans.fCount += (totalTF - tCount + mod) % mod;
		}
	}

	public static bpair countWays(String s , int si, int ei , bpair[][] dp) {
		if (si == ei) {
			int tc = s.charAt(si) == 'T' ? 1 : 0;
			int fc = s.charAt(si) == 'F' ? 1 : 0; //base res bpair

			bpair baseRes = new bpair(tc, fc);
			return dp[si][ei] = baseRes;
		}

		if (dp[si][ei] != null) {
			return dp[si][ei];
		}

		bpair myRes = new bpair(0, 0);
		//apply cuts from si+1 to ei-1 , cut+=2 coz operators are at a dist of 2 st from idx 1
		for (int cut = si + 1; cut < ei; cut += 2) {
			bpair lres = countWays(s, si, cut - 1, dp);
			bpair rres = countWays(s, cut + 1, ei, dp);

			char operator = s.charAt(cut);
			eval(lres, rres, operator, myRes); // leftRes (operator | or ^ or &) rightRes update myRes
		}

		//in eval func add to ans all ways generated from all cuts
		return dp[si][ei] = myRes;
	}


	//3D DP approach using dp[n][n][2] - [si][ei][t/f count]

	public static void calc(int[] lRes , int[] rRes , char opr , int[] ans) {
		int tf = (lRes[0] + lRes[1]) * (rRes[0] * rRes[1]);

		if (opr == '|') {
			int f = lRes[1] * rRes[1];
			ans[0] += tf - f;
			ans[1] += f;
		} else if (opr == '&') {
			int t = lRes[0] * rRes[0];
			ans[0] += t;
			ans[1] += tf - t;
		} else {
			int t = (lRes[0] * rRes[1]) + (lRes[1] * rRes[0]);
			ans[0] += t;
			ans[1] += tf - t;
		}

	}


	//has errors
	public static int[] countWays_3d(String s , int si, int ei , int[][][] dp) {

		if (si == ei) {
			dp[si][ei][0] = s.charAt(si) == 'T' ? 1 : 0; //true
			dp[si][ei][1] = s.charAt(si) == 'F' ? 1 : 0; //false

			return dp[si][ei];
		}

		// if (dp[si][ei][0] != 0 || dp[si][ei][1] != 0) {
		// 	return dp[si][ei];
		// }

		int[] myRes = new int[2];
		for (int cut = si + 1; cut < ei; cut += 2) {
			int[] lRes = countWays_3d(s, si, cut - 1, dp);
			int[] rRes = countWays_3d(s, cut + 1, ei, dp);

			char opr = s.charAt(cut);
			calc(lRes, rRes, opr, myRes);
		}

		return dp[si][ei] = myRes;
	}

	public static void main(String[] args) {
		// String exp = "T^F|F";
		String exp = "T|T&F^T";
		// String exp = "T^F&T";
		int n = exp.length();
		bpair[][] dp = new bpair[n][n];
		bpair ans = countWays(exp, 0, n - 1, dp);
		int tc = ans.tCount % 1003;

		// int[][][] dp = new int[n][n][2];
		// int[] ans = countWays_3d(exp, 0, n - 1, dp);
		System.out.println(tc);
		// System.out.println(ans[0] + " ---- " + ans[1]);
	}

}