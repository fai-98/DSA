public class max_chunks {
	// 769. Max Chunks To Make Sorted

	//chaining tachnique
	// imp - given nums - 0 to n-1
	// look at the index till where impact of number is visible
	public int maxChunksToSorted(int[] arr) {
		int max = -(int) 1e9, chunks = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}

			if (i == max) {
				chunks++;
				max = arr[i];
			}
		}

		return chunks;
	}

	// 768. Max Chunks To Make Sorted II
	// chunks I - impact by index
	// chunks -II - impact by value

	public int maxChunksToSorted(int[] arr) {
		int n = arr.length, chunks = 1;
		int[] leftMax = new int[n]; //left max
		int[] righttMin = new int[n]; //right min

		//the max of left chunk will always be <= min of right chunk

		leftMax[0] = arr[0];
		righttMin[n - 1] = arr[n - 1];

		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
		}

		for (int i = n - 2; i >= 0; i--) {
			righttMin[i] = Math.min(righttMin[i + 1], arr[i]);
		}

		for (int i = 0; i < n - 1; i++) {
			if (leftMax[i] <= righttMin[i + 1]) {
				chunks++;
			}
		}

		return chunks;
	}
}