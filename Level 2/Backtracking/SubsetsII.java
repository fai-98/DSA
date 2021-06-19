import java.io.*;
import java.util.*;

public class SubsetsII {

	public static void subsets(int[]arr, int ci, int ti, boolean prev , String asf) {

		if (ci == ti) {
			System.out.println(asf);
			return;
		}

		//if curr elem == prev elem and you didnt take the prev elem,
		//then also ignore the curr one , it'll create duplicate subsets

		subsets(arr, ci + 1, ti,  false, asf);
		if (ci > 0 && arr[ci] == arr[ci - 1] && !prev) return;
		subsets(arr, ci + 1, ti,  true, asf + arr[ci]);

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] nums = {1, 2, 2};
		Arrays.sort(nums);



		// Map<Integer, Boolean> map = new HashMap<>();
		// for (int i = 0; i < nums.length; i++) {
		// 	map.put(nums[i] , false);
		// }
		subsets(nums, 0, nums.length , true , "");
	}

}