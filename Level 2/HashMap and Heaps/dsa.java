public class dsa {

	public class Node {
		int val;
		Node next;

		Node() {}

		Node(int val, Node node) {
			this.val = val;
			this.next = next;
		}
	}


	public Node reverse(Node head) {
		if (head == null || head.next == null) {
			return head;
		}

		Node curr = head;
		Node prev = null;

		while (curr != null) {
			Node next = curr.next;  //preserve the next node

			curr.next = prev;  //change pointers

			prev = curr;
			curr = next;
		}

		return prev;
	}

	curr = 1 , prev = null
	                  next = 2 , 1->null  , prev = 1, curr = 2
	                          next = null , 2 -> 1 , prev = 2, curr = null

	                                  prev 1->2


	public void func(String text) {

		int words = 0, sentences = 0, paragraphs = 0;

		String[] str = text.split(" ");

		hello world.

		int total = str.length;

		for (String s : str) {
			if (s.equals(".")) {
				sentences++;
			}
		}
	}
}