import java.util.ArrayList;
import java.util.*;
public class BinaryTreeConversions {
	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public class ListNode {
		ListNode prev = null;
		ListNode next = null;
		int val;

		public ListNode(int val) {}

		public ListNode(ListNode prev, ListNode next, int val) {
			this.prev = prev;
			this.next = next;
			this.val = val;
		}
	}

	/*Binary Tree to DLL
	https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
	https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
	*/

	/*iterative inorder traversal ,
	can also do recursive using static node prev , same logic
	if static not allowed , make Node[] arr of 1 size , the changes will persist
	also can make a Pair Class

	Same Code for CDLL
		head.left = tail;
	    tail.right = head;
	*/

	public static TreeNode convertDLL(TreeNode root) {
		Stack<TreeNode> st = new Stack<>();

		TreeNode curr = root;
		TreeNode prev = null;


		while (curr != null || !st.isEmpty()) {
			while (curr != null) { //push all left
				st.push(curr);
				curr = curr.left;
			}

			curr = st.pop();
			if (prev != null) {
				curr.left = prev;
				prev.right = curr;
			}
			prev = curr;
			curr = curr.right;

		}

		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	//Convert sorted DLL to BST left-prev , right-next
	public static TreeNode DLLtoBST(ListNode head) {
		if (head == null || head.next == null)return new TreeNode(head.val);
		ListNode mid = mid(head);

		// catch
		ListNode prev = mid.prev;
		ListNode next = mid.next;
		//unlink
		prev.next = mid.prev = null;
		next.prev = mid.next = null;


		TreeNode root = new TreeNode(mid.val);
		//recur
		root.left = DLLtoBST(head);
		root.right = DLLtoBST(next);

		return root;
	}

	public static ListNode mid(ListNode head) {
		if (head == null || head.next == null)return head;
		ListNode slow = head;
		ListNode fast = head;

		//this gives second mid
		while (fast != tail && fast.next != tail) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	//mergeSort DLL left-prev , right-next
	public static ListNode sortDLL(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode mid = mid(head);
		ListNode nHead = mid.next;
		nHead.prev = mid.next = null;

		ListNode l1 = sortDLL(head);
		ListNode l2 = sortDLL(nHead);

		return merge2DLL(l1, l2);
	}

	public static ListNode merge2DLL(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}

		ListNode dummy = new ListNode();
		ListNode dd = dummy;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				dd.next = l1;
				l1 = l1.next;
			} else {
				dd.next = l2;
				l2 = l2.next;
			}
			dd = dd.next;
		}

		dd.next = l1 != null ? l1 : l2;
		ListNode head = dummy.next;
		dummy.next = null;

		return head;
	}

	//Binary Tree to BST using all functions

	// BT-DLL O(N)  , S: logN;
	// DLL sort O(NlogN) , S: logN
	// srotedDLL->BSt O(NlogN) , S: logN

	// T:O(N+2logN);
	// S : 3*logN
}