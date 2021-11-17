public class Main {

	public static int solution(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int maxLen = 0;

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];

			if (map.containsKey(sum)) {      //don't update , coz this will give larger subarray
				int len = i - map.get(sum);
				maxLen = Math.max(len, maxLen);
			} else {
				map.put(sum, i);
			}
		}

		return maxLen;
	}
}

//elements b/w fisrt occurence of sum and second occurence have zero sum
// -------sum = x-------
//---------------------sum = x ------
// sum = x , ----sum = 0 ------------