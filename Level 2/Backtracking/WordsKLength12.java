import java.io.*;
import java.util.*;

public class WordsKLength12 {


	// levels==items->spots - similar to P1 , mapping items->spots , boxes->chars ,
	public static void permute(String s , int cs, int spots,  String asf , boolean[] vis) {

		if (cs == spots) {
			System.out.println(asf);
			return;
		}

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (vis[i] == false) {
				vis[i] = true;
				permute(s, cs + 1, spots , asf + ch, vis);
				vis[i] = false;
			}

		}
	}


	// P2 derived -> levels = chars , yes - a,b,c or NO , but no NO opt coz spots==items
	public static void permute2(String str , int idx , int cs, Character[] spots ) {


		if (idx == str.length()) {
			if (cs == spots.length) {
				for (char ch : spots) {
					System.out.print(ch);
				}
				System.out.println();
			}
			return;
		}


		//YES
		for (int i = 0; i < spots.length; i++) {
			if (spots[i] == null) {
				spots[i] = str.charAt(idx);
				permute2(str, idx + 1, cs + 1, spots);
				spots[i] =  null;
			}
		}
		//NO
		permute2(str, idx + 1, cs, spots);
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "aabbbccdde";
		//Convert into unique str using HashSet
		String ustr = "abcde";
		int spot = 3;

		Map<Character, Boolean> map = new HashMap<>();

		for (char ch : s.toCharArray()) {
			map.put(ch, false);
		}

		boolean[] vis = new boolean[ustr.length()];
		int[] spots = new int[spot];

		// permute(ustr , 0 , spots ,  "" , vis );
		// permute("abcd" , 0 , 2 ,  "" , vis );
		// permute2(s, 0, freq, "");
		// permute2(ustr, 0, spots, "");
		permute2("abc", 0, 0, new Character[2]);
		permute("abc", 0, spot , "", vis);

	}

}