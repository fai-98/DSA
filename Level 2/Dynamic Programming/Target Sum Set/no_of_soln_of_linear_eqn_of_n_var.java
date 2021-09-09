public class no_of_soln_of_linear_eqn_of_n_var {

	// https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/

	//Apply Coin Change Combinations
	public static int countSol(int[] nums , int tar )  {
		int[] dp = new int[tar + 1];
		dp[0] = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = nums[i]; j <= tar; j++) {
				dp[j] += dp[j - nums[i]];
			}
		}
		return dp[tar];
	}


	public static void main (String[] args) {
		int coeff[] = {2, 2, 5};
		int rhs = 4;
		int n = coeff.length;
		System.out.println (countSol(coeff,  rhs));
	}
}