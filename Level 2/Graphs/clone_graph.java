/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {

	//133. Clone Graph

	public Node cloneGraph(Node node) {
		if (node == null) return null;
		Node[] map = new Node[101];
		boolean[] vis = new boolean[101];

		Queue < Node > q = new ArrayDeque < > ();
		q.offer(node);
		Node src = new Node(node.val);
		map[node.val] = src;

		while (q.size() > 0) {
			//rem
			Node rem = q.poll();
			//mark*
			if (vis[rem.val]) {
				continue;
			} else vis[rem.val] = true;

			//add unvis nbrs
			for (Node nbr : rem.neighbors) {
				if (!vis[nbr.val]) {

					if (map[nbr.val] == null) {
						map[nbr.val] = new Node(nbr.val);
					}
					map[rem.val].neighbors.add(map[nbr.val]);
					map[nbr.val].neighbors.add(map[rem.val]);

					q.offer(nbr);
				}
			}

		}

		return src;
	}
}