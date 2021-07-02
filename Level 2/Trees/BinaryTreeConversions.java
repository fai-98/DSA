import java.util.ArrayList;
import java.util.*;

public class BinaryTreeConversions {
	// public static class Node {
	// int val = 0;
	// Node left = null;
	// Node right = null;

	// Node(int val) {
	// this.val = val;
	// }
	// }

	// public class ListNode {
	// ListNode left = null;
	// ListNode right = null;
	// int val;

	// ListNode(int val) {
	// this.val = val;
	// }

	// ListNode(ListNode left, ListNode right, int val) {
	// this.left = left;
	// this.right = right;
	// this.val = val;
	// }
	// }

	public static class Node {
		int val = 0;
		Node left = null;
		Node right = null;

		Node(int val) {
			this.val = val;
		}
	}

	/*
	 * Binary Tree to DLL
	 * https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-
	 * set-3/
	 * https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link
	 * -list/
	 */

	/*
	 * iterative inorder traversal , can also do recursive using static node left ,
	 * same logic if static not allowed , make Node[] arr of 1 size , the changes
	 * will persist also can make a Pair Class
	 * 
	 * Same Code for CDLL head.left = tail; tail.right = head; in DLL left = prev,
	 * right = next;
	 */

	public static Node convertDLL(Node root) {
		Stack<Node> st = new Stack<>();

		Node curr = root;
		Node left = null;

		while (curr != null || !st.isEmpty()) {
			while (curr != null) { // push all left
				st.push(curr);
				curr = curr.left;
			}

			curr = st.pop();
			if (left != null) {
				curr.left = left;
				left.right = curr;
			}
			left = curr;
			curr = curr.right;

		}

		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	// Convert sorted DLL to BST left-left , right-right
	public static Node DLLtoBST(Node head) {
		if (head == null || head.right == null)
			return new Node(head.val);
		Node mid = mid(head);

		// catch
		Node left = mid.left;
		Node right = mid.right;
		// unlink
		left.right = mid.left = null;
		right.left = mid.right = null;

		Node root = new Node(mid.val);
		// recur
		root.left = DLLtoBST(head);
		root.right = DLLtoBST(right);

		return root;
	}

	public static Node mid(Node head) {
		if (head == null || head.right == null)
			return head;
		Node slow = head;
		Node fast = head;

		// this gives second mid
		while (fast != null && fast.right != null) {
			slow = slow.right;
			fast = fast.right.right;
		}
		return slow;
	}

	// mergeSort DLL left-left , right-right
	public static Node sortDLL(Node head) {
		if (head == null || head.right == null)
			return head;

		Node mid = mid(head);
		// forw unlink
		Node nHead = mid.right;
		nHead.left = mid.right = null;

		// left call head to mid(inclusive) & Rc (mid.next - end);

		Node l1 = sortDLL(head);
		Node l2 = sortDLL(nHead);

		return merge2DLL(l1, l2);
	}

	public static Node merge2DLL(Node l1, Node l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}

		Node dummy = new Node(-1);
		Node dd = dummy;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				dd.right = l1;
				l1 = l1.right;
			} else {
				dd.right = l2;
				l2 = l2.right;
			}
			dd = dd.right;
		}

		dd.right = l1 != null ? l1 : l2;
		Node head = dummy.right;
		dummy.right = null;

		return head;
	}

	public Node BT_to_BST(Node root) {
		Node head = convertDLL(root);
		head = sortDLL(head);
		Node nHead = DLLtoBST(head);
		return nHead;
	}

	// Binary Tree to BST using all functions

	// BT-DLL O(N) , S: logN;
	// DLL sort O(NlogN) , S: logN
	// srotedDLL->BSt O(NlogN) , S: logN

	// T:O(N+2logN);
	// S : 3*logN
}