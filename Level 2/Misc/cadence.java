public class cadence {

	//543. Diameter of Binary Tree
	// using static
	public int diameterOfBinaryTree_03(TreeNode root) {
		int[] dia = new int[] { 0 };
		diameter_03(root, dia);
		return dia[0];
	}

	public int diameter_03(TreeNode root, int[] dia) {
		if (root == null)
			return -1;
		int lh = diameter_03(root.left, dia);
		int rh = diameter_03(root.right, dia);
		int ht = Math.max(lh, rh) + 1;
		dia[0] = Math.max(dia[0], lh + rh + 2);
		return ht;
	}


	// 540. Single Element in a Sorted Array
	public int singleNonDuplicate(int[] arr) {
		int n = arr.length;
		int lo = 0, hi = n - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (mid > 0 && arr[mid] == arr[mid - 1]) {
				if ((mid - 1) % 2 == 1) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else if (mid < n - 1 && arr[mid] == arr[mid + 1]) {
				if ((n - mid) % 2 == 1) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			} else {
				return arr[mid];
			}
		}
		return 0;
	}


	//patches
	public int solution(String S) {
		int[] res = new int[S.length()];

		for (int i = 0; i < 3; i++) {

			if (S.charAt(i) == 'X') {
				res[i] = 1;
			} else {
				res[i] = 0;
			}
		}

		for (int i = 3; i < S.length(); i++) {
			if (S.charAt(i) == 'X') {
				res[i] = res[i - 3] + 1;
			} else {
				res[i] = res[i - 1];
			}
		}

		return res[S.length() - 1];
	}

	//Dp target sum revise
	//Graph revise
	//After Hashedin revise daily - DSA
}