import java.io.*;
import java.util.*;

public class CombinationSumII {

	public static void backtrack(int[]arr, int ci, int ti, int tar, String asf, Set<String> set ) {


		if (tar < 0) {
			return;
		}
		if (ci == ti) {
			if (tar == 0) {
				if (!set.contains(asf)) {
					System.out.println(asf);
					set.add(asf);
				}
			}
			return;
		}

		backtrack(arr, ci + 1, ti, tar - arr[ci], asf + arr[ci], set);

		backtrack(arr, ci + 1, ti, tar, asf, set);


	}

	//derived , npr/r! , item chooses type
	public static void backtrack2(int prev, int idx, int tar, int[] arr, String asf) {

		if (tar < 0) {
			return;
		}

		if (tar == 0) {
			System.out.println(asf);
			return;
		}

		for (int i = prev + 1 ; i < arr.length; i++) {
			if (i > prev + 1 && arr[i] == arr[i - 1])continue;
			if (tar - arr[i] < 0)break;
			backtrack2(i, idx + 1, tar - arr[i], arr, asf + arr[i]);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] nums = {10, 1, 2, 7, 6, 1, 5 };
		int target = 8;
		Arrays.sort(nums);

		Set<String> set = new HashSet<>();
		// backtrack(nums, 0, nums.length , target , "",  set);

		backtrack2(-1, 1, target, nums, "");
	}

}