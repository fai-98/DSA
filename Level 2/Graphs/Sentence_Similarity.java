import java.io.*;
import java.util.*;

public class Sentence_Similarity {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    String[] sentence1 = br.readLine().split(" ");
    String[] sentence2 = br.readLine().split(" ");

    int m = Integer.parseInt(br.readLine());

    String[][] pairs = new String[m][2];
    for (int i = 0; i < m; i++) {
      pairs[i] = br.readLine().split(" ");
    }

    System.out.println(areSentencesSimilarTwo(sentence1, sentence2, pairs));

  }

  static HashMap<String, String> par;
  static HashMap<String, Integer> rank;
  public static boolean areSentencesSimilarTwo(String[] Sentence1, String[] Sentence2, String[][] pairs) {
    par = new HashMap<>();
    rank = new HashMap<>();

    if (sentence1.length != sentence2.length)
      return false;

    //union
    for (int[] pair : pairs) {
      union(pair[0], pair[1]);
    }

    for (int i = 0; i < sentence1.length; i++) {
      if (!Sentence1[i].equals(Sentence2[i]) && !findPar(Sentence1[i]).equals(findPar(Sentence2[i]))) {
        return false;
      }
    }

    return true;
  }

  //Map<Child,Parent>
  public static String findPar(String u) {
    //initialzie
    if (!par.containsKey(u)) {
      par.put(u, u);
      rank.put(u, 1);
    }

    //findPar recursive
    if (par.get(u).equals(u))
      return u;

    //return par[u] = findPar(par[u])
    String temp = findPar(par.get(u));
    par.put(u, temp);
    return temp;
  }

  public static void union(String u , String v) {
    String p1 = findPar(u);
    String p2 = findPar(v);

    //union by Rank , leader has greater rank
    if (!p1.equals(p2)) {
      if (rank.get(p1) > rank.get(p2)) {
        par.put(p2, p1); //parent of p2 = p1
      } else if (rank.get(p1) < rank.get(p2)) {
        par.put(p1, p2);
      } else {
        par.put(p2, p1);
        rank.put(p1, rank.get(p1) + 1);
      }
    }
  }

}
