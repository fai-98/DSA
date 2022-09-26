class Solution {

	// 847. Shortest Path Visiting All Nodes

	public int shortestPathLength(int[][] graph) {
		//1. You have to start BFS from all st pts (multisource BFS)
		//2. Typical BFS with vis will not work
		//3. Avoid indefinite loops -> cycle , ex. 0-1-0-1-0...
		//4. 2^n possible states keep vis of [node][state]
		//ex sz = 3all vis = 1,1,1 , all unvis = 0,0,0 , only oth vis = 001 so on..


	}
}