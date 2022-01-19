public class reverse_linkedlist {
	ListNode tHead = null, tTail = null;


	// 25. Reverse Nodes in k-Group
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k == 1) return head;

		int len = length(head);
		ListNode curr = head, pTail = null, pHead = null;

		while (curr != null && len >= k) {
			int itr = k;
			while (curr != null && itr-- > 0) {
				ListNode next = curr.next; //catch
				curr.next = null; //unlink
				addFirst(curr); //reverse
				curr = next; //move
			}

			if (pHead == null) {
				pHead = tHead;
				pTail = tTail;
			} else {
				pTail.next = tHead;
				pTail = tTail;
			}
			tHead = null;
			tTail = null;
			len -= k;
		}

		pTail.next = curr;
		return pHead;
	}

	public int length(ListNode node) {
		ListNode curr = node;
		int size = 0;
		while (curr != null) {
			curr = curr.next;
			size++;
		}
		return size;
	}

	public void addFirst(ListNode node) {
		if (tHead == null) {
			tHead = node;
			tTail = node;
		} else {
			node.next = tHead;
			tHead = node;
		}
	}

	//92. Reverse Linked List II
	static ListNode tTail;
	static ListNode tHead;

	public void addFirst(ListNode node) {
		if (tHead == null) {
			tHead = tTail = node;
		} else {
			node.next = tHead;
			tHead = node;
		}
	}
	public ListNode reverseBetween(ListNode head, int left, int right) {
		if (head == null || head.next == null) return head;

		tTail = tHead = null;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode st = dummy;

		int diff = right - left;

		while (left-- > 1) {
			st = st.next;
		}

		ListNode curr = st.next;
		ListNode end = null;

		while (diff-- >= 0) {
			end = curr.next;
			curr.next = null;
			addFirst(curr);
			curr = end;
		}

		st.next = tHead;
		tTail.next = end;

		return dummy.next;
	}

	//ALternate
	public ListNode reverseBetween(ListNode head, int left, int right) {
		ListNode prev = null;
		ListNode curr = head;

		int i = 1;
		while (curr != null) {
			while (i >= left && i <= right) {
				ListNode next = curr.next; // catch forw
				curr.next = null; //link break
				addFirst(curr);
				curr = next;
				i++;
			}
			if (i > right) {
				//st point is 1st node
				//if end pt is last node this works coz cuur = null
				// so tTail.next=null
				if (prev == null) {
					tTail.next = curr;
					return tHead;
				} else {    //middle part
					prev.next = tHead;
					tTail.next = curr;
					return head;
				}
			}

			prev = curr;
			curr = curr.next;
			i++;
		}

		return null;
	}

	// 83. Remove Duplicates from Sorted List
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		ListNode curr = head;

		while (curr != null) {
			//skip dups
			while (curr.next != null && curr.val == curr.next.val) {
				curr = curr.next;
			}

			p.next = curr;
			p = p.next;
			curr = curr.next;
		}

		return dummy.next;
	}

	// 82. Remove Duplicates from Sorted List II
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)return head;

		ListNode prev = new ListNode(101);
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy , curr = head;
		prev.next = curr;

		while (curr != null) {
			while (curr.next != null && (prev.val == curr.val || curr.val == curr.next.val)) {
				prev = prev.next;
				curr = curr.next;
			}

			p.next = curr.val == prev.val ? null : curr;
			p = p.next;
			prev = prev.next;
			curr = curr.next;
		}

		return dummy.next;
	}

	//ALt (using itr)
	//if loop run -> remove all nodes itr.next.........before curr
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode dummy = new ListNode(-1);
		ListNode itr = dummy, curr = head.next;
		itr.next = head; //potential unique element

		while (curr != null) {
			boolean isLoopRun = false;
			while (curr != null && itr.next.val == curr.val) {
				isLoopRun = true;
				curr = curr.next;
			}

			if (isLoopRun == true) { //dups exist
				itr.next = curr;     //new pot dup
			} else {
				itr = itr.next;      //confirm unique
			}

			if (curr != null) {
				curr = curr.next;
			}
		}

		return dummy.next;
	}


	//138. Copy List with Random Pointer

	public void copyNodes(Node head) {
		Node curr = head;
		while (curr != null) {
			Node next = curr.next;
			Node temp = new Node(curr.val);
			curr.next = temp;
			temp.next = next;
			curr = next;
		}
	}

	public void setRandom(Node head) {
		Node curr = head;
		Node copy = head.next;
		while (curr != null) {
			if (curr.random != null) {
				copy.random = curr.random.next;
			}
			curr = copy.next;
			if (curr != null)copy = curr.next;
		}
	}
	public Node extractList(Node head) {
		Node dummy = new Node(-1);
		Node curr = head;
		Node copy = head.next;
		dummy.next = copy;

		while (curr != null) {
			curr.next = copy.next;
			curr = curr.next;
			if (curr != null) copy.next = curr.next;
			copy = copy.next;
		}
		return dummy.next;
	}
	public Node copyRandomList(Node head) {
		if (head == null) return null;
		copyNodes(head);
		setRandom(head);
		return extractList(head);
	}
}