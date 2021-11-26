import java.util.*;

public class binary_search_ceil_floor {

    private static int ceilIndex(int[] arr, int val) {
        int n = arr.length;
        int lo = 0, hi = n - 1, ceil = -1;

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= val) {
                lo = mid + 1;
            } else {
                ceil = mid;
                hi = mid - 1;
            }
        }

        return ceil == -1 ? n : ceil;
    }

    private static int floorIndex(int[] arr, int val) {
        int n = arr.length;
        int lo = 0, hi = n - 1 , floor = -1;

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;

            if (arr[mid] < val) {
                lo = mid + 1;
                floor = mid;
            } else {
                hi = mid - 1;
            }
        }

        return floor;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 5, 6, 7, 7, 8, 9, 10, 10};
        System.out.println("Ceil Indexes");
        for (int val : arr) {
            int indx = ceilIndex(arr, val);
            System.out.println(val + " --> " + indx);
        }
        System.out.println("\nFloor Indexes");
        for (int val : arr) {
            int indx = floorIndex(arr, val);
            System.out.println(val + " --> " + indx);
        }
    }
}
