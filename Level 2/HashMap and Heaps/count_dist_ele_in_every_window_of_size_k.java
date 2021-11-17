import java.util.*;

public class Main {

	public static ArrayList<Integer> solution(int[] arr, int k) {
		//acquire and release strategy
		ArrayList<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();

		//add first k-1 ele
		for (int i = 0; i < k - 1; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		for (int j = 0, i = k - 1; i < arr.length; i++, j++) {
			//acquire
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			//answer
			list.add(map.size());
			//release
			int freq = map.get(arr[j]);
			if (freq == 1) {
				map.remove(arr[j]);
			} else {
				map.put(arr[j], map.get(arr[j]) - 1);
			}
		}

		return list;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] arr = new int[scn.nextInt()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		int k = scn.nextInt();
		ArrayList<Integer> ans = solution(arr, k);
		for (int a : ans) {
			System.out.print(a + " ");
		}
	}


}
