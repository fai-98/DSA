public class linkedlist_cycle {

	//141. Linked List Cycle
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null)return false;
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)return true;
		}

		return false;
	}

	//142. Linked List Cycle II - Cycle Node

	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) return null;
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) break;
		}
		if (fast != slow) {
			return null;
		}

		ListNode meeting_node = fast;
		slow = head;

		int cycle_len = 1; //(B+C)
		int tail_len = 1; // (A)
		boolean flag = false;
		int m_Dash = 0; //no of circles after mpt before intersection

		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
			if (!flag && fast != meeting_node) {
				cycle_len++;
			}
			if (!flag && fast == meeting_node) {
				flag = true;
				m_Dash++;
			}

			tail_len++;
		}

		int C = tail_len - cycle_len * m_Dash; //dist from mpt to start pt
		int m = m_Dash + 1;
		int B = cycle_len - C;

		// System.out.println("Cycle len : " + cycle_len);
		// System.out.println("Tail len : " + tail_len);
		// System.out.println("Rotations after meeting till st point : " + m_Dash);
		// System.out.println("Dist from st_Node to meeting_node i.e B : " + B);
		// System.out.println("Dist from meetin_node to st_node : " + C);
		// System.out.println("Rotations before meeting : " + m);

		return fast;

	}


	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null)return null;
		// return mapMethod(head);
		return floydMethod(head);
	}


	public ListNode floydMethod(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)break;
		}

		ListNode entry = head;
		while (entry != null && slow != null) {
			if (slow == entry)return slow;
			entry = entry.next;
			slow = slow.next;

		}

		return null;

	}


	public ListNode mapMethod(ListNode head) {
		ListNode itr = head;
		Map<ListNode, Integer> map = new HashMap<>();

		while (itr != null) {
			if (map.containsKey(itr))
				return itr;
			else
				map.put(itr, itr.val);
			itr = itr.next;
		}
		return null;
	}


	//Intersection Node In Two Linkedlist Using Difference Method
	public static int length(ListNode head) {
		ListNode curr = head;
		int len = 0;
		while (curr != null) {
			len++;
			curr = curr.next;
		}

		return len;
	}

	public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
		int lenA = length(headA);
		int lenB = length(headB);

		ListNode biggerList = lenA > lenB ? headA : headB;
		ListNode smallerList = lenB < lenA ? headB : headA;

		int diff = Math.abs(lenA - lenB);
		while (diff-- > 0)
			biggerList = biggerList.next;

		while (biggerList != smallerList) {
			biggerList = biggerList.next;
			smallerList = smallerList.next;
		}

		return biggerList;
	}


	//Intersection Node In Two Linkedlist Using FLoyd Method

	public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)return null;
		ListNode A = headA, B = headB;
		while (A != B) {
			A = A == null ? headB : A.next;
			B = B == null ? headA : B.next;
		}
		return A;
	}


	public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
		ListNode A = headA;
		while (A.next != null) {
			A = A.next;
		}
		A.next = headB;

		//starting point of loop using Floyd ...
		ListNode slow = headA, fast = headA;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) break;
		}
		if (fast != slow) {
			A.next = null;
			return null;
		}

		slow = headA;
		while (fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}
		A.next = null;
		return fast;
	}

	//

}