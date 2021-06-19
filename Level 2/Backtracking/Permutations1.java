import java.io.*;
import java.util.*;

public class Permutations1 {

	public static void permutations(int[] boxes, int ci, int ti ) {
		if (ci > ti ) {
			for (int num : boxes) {
				System.out.print(num);
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < boxes.length; i++) {
			if (boxes[i] == 0) {
				boxes[i] = ci;                    //place
				permutations(boxes, ci + 1, ti);  //recur
				boxes[i] = 0;                     //unplace
			}
		}
	}


	//yes no call , if yes loop either 1 or 2 or 3, if no go to nxt box currBox+1
	public static void permutations2(int currBox, int totalBox, int[] items, int selSoFar, int ts, String asf) {

		if (currBox > totalBox) {
			if (selSoFar == ts ) {
				System.out.println(asf);
			}
			return;
		}

		for (int i = 1; i <= ts ; i++) { //YES

			if (items[i - 1] == 0) {
				items[i - 1] = currBox; //do
				permutations2(currBox + 1, totalBox, items, selSoFar + 1, ts, asf + i); //recur
				items[i - 1] = 0; //undo
			}
		}
		permutations2(currBox + 1, totalBox, items, selSoFar, ts, asf + 0); //NO

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nboxes = Integer.parseInt(br.readLine());
		int ritems = Integer.parseInt(br.readLine());
		// permutations(new int[nboxes], 1, ritems);


		// int nboxes = Integer.parseInt(br.readLine());
		// int ritems = Integer.parseInt(br.readLine());
		permutations2(1, nboxes, new int[ritems], 0, ritems, "");
	}

}