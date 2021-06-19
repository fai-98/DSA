import java.io.*;
import java.util.*;

public class CombinationSum {

	public static void backtrack(int[]arr, int ci, int ti, int tar, String asf ) {


		if (tar < 0) {
			return;
		}
		if (ci == ti) {
			if (tar == 0) {
				System.out.println(asf);
			}
			return;
		}

		backtrack(arr, ci, ti, tar - arr[ci], asf + arr[ci]);
		backtrack(arr, ci + 1, ti, tar, asf);


	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] nums = {1, 2, 5 };
		int target = 7;

		backtrack(nums, 0, nums.length , target , "");
	}

}