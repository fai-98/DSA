public class advance_linkedlist {

	public static class ListNode {
		int val = 0;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public static class ListNode {
		int val = 0;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public static ListNode midNode2(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	public static ListNode midNode(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	public static ListNode reverse(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode curr = head;
		ListNode prev = null;

		while (curr != null) {
			ListNode forw = curr.next; // backup

			curr.next = prev; // link

			prev = curr; // move
			curr = forw;
		}

		return prev;
	}



	//Palindrome LinkedList
	public static ListNode midNode(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	public static ListNode reverse(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode curr = head;
		ListNode prev = null;

		while (curr != null) {
			ListNode forw = curr.next; // backup

			curr.next = prev; // link

			prev = curr; // move
			curr = forw;
		}

		return prev;
	}

	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}

		ListNode mid = midNode(head);
		ListNode nHead = mid.next;
		mid.next = null;

		nHead = reverse(nHead);

		ListNode p1 = head, p2 = nHead;

		while (p2 != null) {
			if (p1.val != p2.val) {
				return false;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		//correct List
		mid.next = reverse(nHead);

		return true;
	}

	//Fold
	public static void fold(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}

		ListNode mid = midNode(head);
		ListNode nHead = mid.next;
		mid.next = null;

		nHead = reverse(nHead);

		ListNode p1 = head, p2 = nHead;

		while (p2 != null) {

			ListNode f1 = p1.next ;
			ListNode f2 = p2.next ;

			p1.next = p2 ;
			p2.next = f1 ;

			p1 = f1;
			p2 = f2;

		}

	}

	//Unfold
	public static void unfold(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}

		ListNode l1 = head;
		ListNode l2 = head.next;

		ListNode p1 = l1 , p2 = l2;

		while (p2 != null && p2.next != null) {
			//catch forw
			ListNode fwd = p2.next;

			//connect
			p1.next = fwd;
			p2.next = fwd.next;

			//move
			p1 = p1.next;
			p2 = p2.next;
		}

		l2 = reverse(l2);
		p1.next = l2;


	}

	public static void unfold(ListNode head) {
		if (head == null || head.next == null)
			return;

		ListNode l1 = new ListNode(-1);
		ListNode l2 = new ListNode(-1);
		ListNode p1 = l1, p2 = l2, c1 = head, c2 = head.next;

		while (c1 != null && c2 != null) {
			p1.next = c1;
			p2.next = c2;

			p1 = p1.next;
			p2 = p2.next;

			if (c2 != null) // for odd Len
				c1 = c2.next;
			if (c1 != null) // for even
				c2 = c1.next;
		}

		p1.next = null;
		p2 = reverse(l2.next);
		p1.next = p2;
	}

	//Merge 2 sorted LL
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		ListNode p1 = l1;
		ListNode p2 = l2;

		while (p1 != null && p2 != null) {
			if (p1.val <= p2.val) {
				p.next = p1;
				p1 = p1.next;
			} else {
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}

		p.next = p1 != null ? p1 : p2;
		return dummy.next;
	}

	//Merge K Lists

	// T : O(NlogkK), S : O(logK) -> N = k times of (avg length Of Linkedlist),
	// where k is length of lists.
	public static ListNode mergeKLists(ListNode[] lists) {

		return merge(lists, 0, lists.length - 1);
	}

	public static ListNode merge(ListNode[] arr , int st , int end) {
		if (st >= end) {
			return arr[st];
		}
		int mid = st + (end - st) / 2;
		ListNode left = merge(arr, st, mid);
		ListNode right = merge(arr, mid + 1, end);
		return mergeTwoLists(left, right);
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		ListNode p1 = l1;
		ListNode p2 = l2;

		while (p1 != null && p2 != null) {
			if (p1.val <= p2.val) {
				p.next = p1;
				p1 = p1.next;
			} else {
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}

		p.next = p1 != null ? p1 : p2;
		return dummy.next;
	}

	//Merge Sort LL
	public static ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode mid = mid(head);
		ListNode nHead = mid.next;
		mid.next = null;

		ListNode left = mergeSort(head);
		ListNode right = mergeSort(nHead);
		return mergeTwoLists(left, right);
	}

	//Remove nth node from end
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head.next == null) return null;
		int len = 0;
		ListNode node = head;

		while (node != null) {
			node = node.next;
			len++;
		}
		if (len == n) return head.next;
		int diff = len - n;

		ListNode fast = head, slow = head;

		for (int i = 0; i < n - 1; i++) {
			fast = fast.next;
		}

		for (int i = 0; i < diff - 1; i++) {
			slow = slow.next;
			fast = fast.next;
		}

		slow.next = slow.next.next;
		return head;
	}

	//Segregate even odd
	public static ListNode segregateEvenOdd(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode d1 = new ListNode(-1);
		ListNode d2 = new ListNode(-1);
		ListNode p1 = d1 , p2 = d2;

		ListNode curr = head;

		while (curr != null) {
			ListNode fwd = curr.next; //preserve

			//connect
			//odd
			if (curr.val % 2 == 1) {

				curr.next = null;
				p1.next = curr;
				p1 = curr;

				//move
				curr = fwd;
			} else {
				curr.next = null;
				p2.next = curr;
				p2 = curr;
				curr = fwd;
			}

		}

		p2.next = d1.next;


		return d2.next;
	}

	//Segregate 0 - 1
	public static ListNode segregate01(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode d1 = new ListNode(-1);
		ListNode d2 = new ListNode(-1);
		ListNode p1 = d1 , p2 = d2;

		ListNode curr = head;

		while (curr != null) {
			ListNode fwd = curr.next; //preserve

			//connect
			//odd
			if (curr.val == 1) {

				curr.next = null;
				p1.next = curr;
				p1 = curr;

				//move
				curr = fwd;
			} else {
				curr.next = null;
				p2.next = curr;
				p2 = curr;
				curr = fwd;
			}

		}

		p2.next = d1.next;


		return d2.next;
	}


	//Segregate 0-1-2
	public static ListNode segregate012(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode zero = new ListNode(-1);
		ListNode one = new ListNode(-1);
		ListNode two = new ListNode(-1);

		ListNode p0 = zero, p1 = one , p2 = two , curr = head;

		while (curr != null) {
			if (curr.val == 0) {
				p0.next = curr;
				p0 = p0.next;
			} else if (curr.val == 1) {
				p1.next = curr;
				p1 = p1.next;
			} else {
				p2.next = curr;
				p2 = p2.next;
			}

			curr = curr.next;
		}

		p2.next = null;
		p1.next = two.next;
		p0.next = one.next;

		return zero.next;
	}

	//Segregate Over Last Index

	public static ListNode segregateOnLastIndex(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode last = head;
		while (last.next != null) {
			last = last.next;
		}

		ListNode second = new ListNode(-1);
		ListNode first = new ListNode(-1);
		ListNode p1 = first , p2 = second;

		ListNode curr = head;

		while (curr != null) {
			ListNode fwd = curr.next; //preserve

			//connect
			//odd
			if (curr.val <= last.val) {

				curr.next = null;
				p1.next = curr;
				p1 = curr;

				//move
				curr = fwd;
			} else { //first list
				curr.next = null;
				p2.next = curr;
				p2 = curr;
				curr = fwd;
			}

		}

		p1.next = second.next;
		// p2.next=null;
		return p1;

	}

	//Segregate over pivot Index
	public static ListNode segregate(ListNode head, int pivotIdx) {
		if (head == null || head.next == null)
			return head;

		ListNode small = new ListNode(-1);
		ListNode large = new ListNode(-1);
		ListNode sp = small, lp = large, curr = head;

		ListNode pivotNode = head;
		while (pivotIdx-- > 0)
			pivotNode = pivotNode.next;

		while (curr != null) {
			if (curr != pivotNode && curr.val <= pivotNode.val) {
				sp.next = curr;
				sp = sp.next;
			} else if (curr != pivotNode) {
				lp.next = curr;
				lp = lp.next;
			}
			curr = curr.next;
		}

		sp.next = pivotNode;
		pivotNode.next = large.next;
		lp.next = null;

		head = small.next;
		return head;
	}

	//Add Two Numbers
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode d1 = reverse(l1);
		ListNode d2 = reverse(l2);
		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;
		int carry = 0;

		while (d1 != null || d2 != null || carry != 0) {
			int sum = 0;
			if (d1 != null) {
				sum += d1.val;
				d1 = d1.next;
			}
			if (d2 != null) {
				sum += d2.val;
				d2 = d2.next;
			}
			sum += carry;

			carry = sum / 10;
			ListNode temp = new ListNode(sum % 10);

			curr.next = temp;
			curr = curr.next;
		}
		l1 = reverse(l1);
		l2 = reverse(l2);
		return reverse(dummy.next);
	}

	//Subtract Two Numbers
	public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
		l1 = reverse(l1);
		l2 = reverse(l2);

		ListNode dummy = new ListNode(-1);
		ListNode c1 = l1, c2 = l2, prev = dummy;
		int borrow = 0;
		while (c1 != null || c2 != null) {
			int diff = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
			if (diff < 0) {
				diff += 10;
				borrow = -1;
			} else
				borrow = 0;

			prev.next = new ListNode(diff);
			prev = prev.next;

			if (c1 != null)
				c1 = c1.next;
			if (c2 != null)
				c2 = c2.next;
		}

		ListNode head = dummy.next;
		head = reverse(head);

		while (head != null && head.val == 0) // 1000000000 - 99999999 = 1, 999 - 999 = 0
			head = head.next;

		l1 = reverse(l1);
		l2 = reverse(l2);

		return head;
	}
}