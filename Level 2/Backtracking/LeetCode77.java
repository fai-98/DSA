import java.io.*;
import java.util.*;

public class LeetCode77 {



  public static void combinations2(int cb, int tb, int ssf, int ts, List < List < Integer >> res, List < Integer > temp) {
    if (cb > tb) {
      if (ssf == ts) {             //only ncr will print
        res.add(new ArrayList<>(temp));
      }
      return;
    }

    if (ssf < ts) {
      temp.add(cb);
      combinations2(cb + 1, tb, ssf + 1, ts, res, temp); //yes calls cut
      temp.remove(temp.size() - 1);
    }
    combinations2(cb + 1, tb, ssf, ts, res, temp); //NO
  }

  public static void combinations(int cb, int tb, int ssf, int ts, String asf) {

    if (cb > tb) {
      if (ssf == ts) {             //only ncr will print
        System.out.println(asf);
      }
      return;
    }

    if (ssf < ts) {
      combinations(cb + 1, tb, ssf + 1, ts, asf + "i"); //yes calls cut
    }
    combinations(cb + 1, tb, ssf, ts, asf + "-"); //NO
  }



  public static void subsets(int[] nums, int idx, List < List < Integer >> res, List<Integer> temp) {
    if (idx == nums.length) {
      res.add(new ArrayList<>(temp));
      return;
    }

    if (idx < nums.length) {
      temp.add(nums[idx]);
      subsets(nums, idx + 1 , res , temp);
      temp.remove(temp.size() - 1);
    }

    subsets(nums,  idx + 1, res , temp);

  }

  public static void main(String[] args) throws Exception {
    //   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //   int nboxes = Integer.parseInt(br.readLine());
    //   int ritems = Integer.parseInt(br.readLine());
    //   combinations(1, nboxes, 0, ritems, "");

    List < List < Integer >> ans = new ArrayList < > ();
    List < Integer > temp = new ArrayList < > ();
    // combinations2(1, 4, 0, 2, ans, temp);


    int[] arr = {1, 2, 3};
    subsets(arr, 0 , ans , temp);

    System.out.println(ans);

  }

}