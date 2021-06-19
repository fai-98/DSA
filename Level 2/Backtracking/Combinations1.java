import java.io.*;
import java.util.*;

public class Combinations1 {


  //box chooses
  public static void combinations(int cb, int tb, int ssf, int ts, String asf) {

    if (cb > tb) {
      if (ssf == ts) {
        System.out.println(asf);
      }
      return;
    }

    if (ssf < ts) {
      combinations(cb + 1, tb, ssf + 1, ts, asf + "i");
    }
    combinations(cb + 1, tb, ssf, ts, asf + "-");
  }



  //derived from permu 1 , item chooses
  public static void combinations2(int[] boxes, int ci, int ti, int lbi) {
    if (ci > ti) {
      for (int i = 0; i < boxes.length; i++) {
        if (boxes[i] == 0) {
          System.out.print("-");
        } else {
          System.out.print("i");

        }
      }
      System.out.println();
      return;
    }


    //aage aage s chlega so 1-2 ke baad 2-1 na aapaye
    for (int i = lbi + 1; i < boxes.length; i++) {
      boxes[i] = ci;
      combinations2(boxes, ci + 1, ti, i);
      boxes[i] = 0;
    }
  }



  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int nboxes = Integer.parseInt(br.readLine());
    int ritems = Integer.parseInt(br.readLine());
    // combinations(1, nboxes, 0, ritems, "");
    // combinations2(new int[nboxes], 1, ritems, -1);
    combinations(1, 5, 0, 3, "");

  }

}