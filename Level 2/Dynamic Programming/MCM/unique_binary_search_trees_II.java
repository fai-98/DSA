import java.util.Arrays;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
	public List < TreeNode > generateTrees(int n) {
		return build(1, n);
	}

	public List < TreeNode > combine(List < TreeNode > left, List < TreeNode > right, int n, List < TreeNode > myRes) {

		if (left.size() != 0 && right.size() != 0) {
			for (TreeNode l : left) {
				for (TreeNode r : right) {

					TreeNode root = new TreeNode(n);
					root.left = l;
					root.right = r;

					myRes.add(root);
				}
			}
		} else if (left.size() != 0) {
			for (TreeNode l : left) {
				TreeNode root = new TreeNode(n);
				root.left = l;
				myRes.add(root);
			}
		} else if (right.size() != 0) {
			for (TreeNode r : right) {
				TreeNode root = new TreeNode(n);
				root.right = r;
				myRes.add(root);
			}
		} else {
			myRes.add(new TreeNode(n));
		}

		return myRes;
	}

	public List < TreeNode > build(int si, int ei) {

		List < TreeNode > myRes = new ArrayList < > ();
		for (int cut = si; cut <= ei; cut++) {
			List < TreeNode > lres = build(si, cut - 1);
			List < TreeNode > rres = build(cut + 1, ei);

			myRes = combine(lres, rres, cut, myRes);
		}

		// BASE CASE - when index out of bounds , for loop
		// dont run and left/right res , empty list(myRes) is returned

		return myRes;
	}
}