import java.util.Arrays;
public class min_swaps_to_sort_array {

	//make another array 2d , {val , idx in orig arr}
	//Sort on basis of vals -
	//now all vals are on correct pos , with orig idx
	//detect cycles in the arr and find cycle lengths
	//num of swaps = cycleLen - 1

	public static int minSwaps(int nums[]) {
		// Code here
		int n = nums.length;
		int[][] arr = new int[n][2];  // num,idx

		for (int i = 0; i < n; i++) {
			arr[i][0] = nums[i];
			arr[i][1] = i;
		}

		Arrays.sort(arr, (a, b)-> {
			return a[0] - b[0];
		});

		boolean[] vis = new boolean[n];

		int swaps = 0;
		for (int i = 0; i < n; i++) {
			int idx = arr[i][1];
			if (idx != i && vis[i] == false) {
				swaps += cycleLen(arr, i, vis) - 1;
			}
		}

		return swaps;

	}

	public static int cycleLen(int[][] nums , int idx , boolean[] vis) {
		int len = 0;

		while (vis[idx] != true) {
			vis[idx] = true;
			idx = nums[idx][1];
			len++;
		}

		return len;
	}

	public static void main(String[] args) {
		int[] arr = {6, 4, 2, 14, 8, 10, 12, 16};
		// int[] arr = {10, 19, 6, 3, 5};
		System.out.println(minSwaps(arr));
	}
}