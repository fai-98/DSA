import java.util.Arrays;
public class boolean_parenthesization {

	// Boolean Parenthesization Problem | DP-37 - Evaluate exp to true
	// GFG
	public static class bpair {
		int tCount = 0;
		int fCount = 0;

		bpair() {}

		bpair(int t , int f) {
			this.tCount = tCount;
			this.fCount = fCount;
		}

	}

	//ans+=  ... add to ans - the total count/ways to make T/F exp from all possible cuts
	public static void eval(bpair lp , bpair rp , char opr , bpair ans) {
		int totalTF = (lp.tCount + lp.fCount) * (rp.tCount + rp.fCount);
		if (opr == '|') {
			int fCount = (lp.fCount * rp.fCount);
			ans.tCount += totalTF - fCount;
			ans.fCount += fCount;
		} else if (opr == '&') {
			int tCount = lp.tCount * rp.tCount;
			ans.tCount += tCount;
			ans.fCount += totalTF - tCount;
		} else { //XOR ^
			int tCount = (lp.tCount * rp.fCount) + (lp.fCount * rp.tCount);
			ans.tCount += tCount ;
			ans.fCount += totalTF - tCount;
		}

		// System.out.println(ans.tCount + " - " + ans.fCount);
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

	public static void main(String[] args) {
		// String exp = "T^F|F";
		String exp = "T|T&F^T";
		int n = exp.length();
		bpair[][] dp = new bpair[n][n];
		bpair ans = countWays(exp, 0, n - 1, dp);
		System.out.println(ans.tCount + " ---- " + ans.fCount);
	}

}