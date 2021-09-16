import java.util.Arrays;
public class catalan_number {


	// C0 = 1 , Cn = summation i = (0 to n-1) Ci*Cn-i  n>=0

	public static int nth_catalan(int n) {
		int[] dp = new int[n + 2]; //catalan 0...n , n+2 size to avoid idx out of bounds
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 10; i++)
			System.out.println(nth_catalan(i));
	}
}