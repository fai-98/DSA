public class intervals_type {
	// Minimum Number of Platforms Required for a Railway/Bus Station
	// Max number of trains at a particular timw

	//find max num of overlapping intervals
	public static int findPlatform(int arr[], int dep[], int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		// arr[curr] <= dep[prev];
		//prev ke jane s phle hi or just at that moment agli train agayi , we need another platform
		int platform = 0, oMax = -(int)1e9;
		int i = 0; //arrival
		int j = 0; //departure

		while (i < n) {
			if (arr[i] <= dep[j]) {
				platform++;
				i++;
			} else {
				platform--;
				j++;
			}

			oMax = Math.max(oMax , platform);
		}
		return oMax;

	}
}