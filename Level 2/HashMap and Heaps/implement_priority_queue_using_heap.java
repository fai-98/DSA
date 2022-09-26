import java.io.*;
import java.util.*;

public class Main {

	public static class PriorityQueue {
		ArrayList<Integer> data;

		public PriorityQueue() {
			data = new ArrayList<>();
		}

		public void add(int val) {
			//1 add at end
			data.add(val);
			//upheapify
			upheapify(data.size() - 1);

		}

		private void upheapify(int i) {
			if (i == 0)return; //base case , no parent

			int pi = (i - 1) / 2;
			if (data.get(i) < data.get(pi)) {
				swap(i, pi);
				upheapify(pi);
			}
		}

		private void swap(int i, int j) {
			int ith = data.get(i);
			int jth = data.get(j);
			data.set(i, jth);
			data.set(j, ith);
		}



		public int remove() {
			if (data.size() == 0) {
				System.out.println("Underflow");
				return -1;
			}
			//preserve 0th idx , swap 1st, last

			int ans = data.get(0); //preserved
			swap(0, data.size() - 1);

			//remove last
			data.remove(data.size() - 1);

			//downheapify(0) coz H.O.P may be invalidated
			downheapify(0);
			return ans;
		}

		private void downheapify(int pi) {
			int lci = 2 * pi + 1;
			int rci = 2 * pi + 2;

			int minIdx = pi; //for now

			//if lc exist and is smaller
			if (lci < data.size() && data.get(minIdx) > data.get(lci)) {
				minIdx = lci;
			}
			if (rci < data.size() && data.get(minIdx) > data.get(rci)) {
				minIdx = rci;
			}

			if (minIdx != pi) {
				swap(pi, minIdx);
				downheapify(minIdx);
			}

		}

		public int peek() {
			if (data.size() == 0) {
				System.out.println("Underflow");
				return -1;
			} else return data.get(0);
		}

		public int size() {
			return data.size();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue qu = new PriorityQueue();

		String str = br.readLine();
		while (str.equals("quit") == false) {
			if (str.startsWith("add")) {
				int val = Integer.parseInt(str.split(" ")[1]);
				qu.add(val);
			} else if (str.startsWith("remove")) {
				int val = qu.remove();
				if (val != -1) {
					System.out.println(val);
				}
			} else if (str.startsWith("peek")) {
				int val = qu.peek();
				if (val != -1) {
					System.out.println(val);
				}
			} else if (str.startsWith("size")) {
				System.out.println(qu.size());
			}
			str = br.readLine();
		}
	}
}