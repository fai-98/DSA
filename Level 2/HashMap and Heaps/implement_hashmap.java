implement_hashmapimport java.io.*;
import java.util.*;

public class Main {

	public static class HashMap < K, V > {
		private class HMNode {
			K key;
			V value;

			HMNode(K key, V value) {
				this.key = key;
				this.value = value;
			}
		}

		private int size; // n
		private LinkedList < HMNode > [] buckets; // N = buckets.length

		public HashMap() {
			initbuckets(4);
			size = 0;
		}

		private void initbuckets(int N) {
			buckets = new LinkedList[N];
			for (int bi = 0; bi < buckets.length; bi++) {
				buckets[bi] = new LinkedList < > ();
			}
		}

		public void put(K key, V value) throws Exception {
			int bucket_idx = hashfn(key);
			int data_idx = getIdxWithinBucket(key, bucket_idx);

			if (data_idx != -1) {
				//update
				HMNode node = buckets[bucket_idx].get(data_idx);
				node.value = value;
			} else {
				//insert
				HMNode node = new HMNode(key, value);
				buckets[bucket_idx].add(node);
				size++;

			}

			double lambda = size * 1.0 / buckets.length;
			//threshold k = 2.0
			if (lambda > 2.0) {
				rehash();
			}
		}

		private void rehash() throws Exception {
			LinkedList < HMNode > [] orig_buckets = buckets;
			initbuckets(orig_buckets.length * 2);
			size = 0;
			for (int i = 0; i < orig_buckets.length; i++) {
				for (HMNode node : orig_buckets[i]) {
					put(node.key, node.value);
				}
			}

		}

		private int getIdxWithinBucket(K key, int bi) {
			int di = 0;
			for (HMNode node : buckets[bi]) {
				if (node.key.equals(key)) {
					return di;
				}
				di++;
			}
			return -1;
		}

		private int hashfn(K key) {
			int hashCode = key.hashCode();
			return Math.abs(hashCode) % buckets.length;
		}

		public V get(K key) throws Exception {
			int bucket_idx = hashfn(key);
			int data_idx = getIdxWithinBucket(key, bucket_idx);

			if (data_idx != -1) {
				return buckets[bucket_idx].get(data_idx).value;
			} else {
				return null;

			}
		}

		public boolean containsKey(K key) {
			int bucket_idx = hashfn(key);
			int data_idx = getIdxWithinBucket(key, bucket_idx);

			if (data_idx != -1) {
				return true;
			} else {
				return false;

			}
		}

		public V remove(K key) throws Exception {
			int bucket_idx = hashfn(key);
			int data_idx = getIdxWithinBucket(key, bucket_idx);

			if (data_idx != -1) {
				size--;
				return buckets[bucket_idx].remove(data_idx).value;

			} else {
				return null;

			}
		}

		public ArrayList < K > keyset() throws Exception {
			ArrayList < K > keyset = new ArrayList < > ();
			for (int i = 0; i < buckets.length; i++) {
				for (HMNode node : buckets[i]) {
					keyset.add(node.key);
				}
			}
			return keyset;
		}

		public int size() {
			return size;
		}

		public void display() {
			System.out.println("Display Begins");
			for (int bi = 0; bi < buckets.length; bi++) {
				System.out.print("Bucket" + bi + " ");
				for (HMNode node : buckets[bi]) {
					System.out.print(node.key + "@" + node.value + " ");
				}
				System.out.println(".");
			}
			System.out.println("Display Ends");
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap < String, Integer > map = new HashMap();

		String str = br.readLine();
		while (str.equals("quit") == false) {
			if (str.startsWith("put")) {
				String[] parts = str.split(" ");
				String key = parts[1];
				Integer val = Integer.parseInt(parts[2]);
				map.put(key, val);
			} else if (str.startsWith("get")) {
				String[] parts = str.split(" ");
				String key = parts[1];
				System.out.println(map.get(key));
			} else if (str.startsWith("containsKey")) {
				String[] parts = str.split(" ");
				String key = parts[1];
				System.out.println(map.containsKey(key));
			} else if (str.startsWith("remove")) {
				String[] parts = str.split(" ");
				String key = parts[1];
				System.out.println(map.remove(key));
			} else if (str.startsWith("size")) {
				System.out.println(map.size());
			} else if (str.startsWith("keyset")) {
				System.out.println(map.keyset());
			} else if (str.startsWith("display")) {
				map.display();
			}
			str = br.readLine();
		}
	}
}