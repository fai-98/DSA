import java.io.*;
import java.util.*;

public class Words12 {


	// levels==items==spots - similar to P1 , mapping items->spots , boxes->chars , spots==chars here
	//boxes>items , chars > spots , greater one can say no , lesser one can choose

	public static void permute(String s , int idx , String asf , Map<Character, Integer> map) {

		if (idx == s.length()) {
			System.out.println(asf);
			return;
		}

		int liu = map.get(s.charAt(idx));

		for (int i = liu + 1; i < s.length(); i++) {
			if (liu >= -1) {
				char ch = s.charAt(i);
				map.put(ch, map.get(ch) + 1);
				permute(s, idx + 1, asf + s.charAt(idx), map);
				map.put(ch, map.get(ch) - 1);
			}

		}
	}


	// P2 derived -> levels = boxes = chars , yes - a,b,c or NO , but no NO opt coz spots==items
	public static void permute2(String str , int cs ,  Map<Character, Integer> map , String asf) {

		if (cs == str.length()) {
			System.out.println(asf);
			return;
		}

		for (char ch : map.keySet()) {
			if (map.get(ch) > 0) {
				map.put(ch, map.get(ch) - 1);
				permute2(str, cs + 1, map, asf + ch);
				map.put(ch, map.get(ch) + 1);
			}
		}

	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "aabb";

		Map<Character, Integer> prev = new HashMap<Character, Integer>();
		Map<Character, Integer> freq = new HashMap<Character, Integer>();

		for (char ch : s.toCharArray()) {
			prev.put(ch, -1);
			freq.put(ch, freq.getOrDefault(ch, 0) + 1);
		}

		// permute(s , 0 , "" , prev);
		permute2(s, 0, freq, "");

	}

}