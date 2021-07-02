import java.util.*;

public class SlidingWindowLeetCode {
    /*
     * SLIDING WINDOW 1. Fixed Size a. size fixed b. minimize maximize sum/etc any
     * condition 2. Variable Size a. size variable b. given fixed condition , c. acc
     * to condition you have to minimize/maximize/count etc 2.1 in variable size
     * substring search types subarray types
     */
    // template for substring search problems

    // int findSubstring(string s){
    // vector<int> map(128,0);
    // int counter; // check whether the substring is valid
    // int begin=0, end=0; //two pointers, one point to tail and one head
    // int d; //the length of substring

    // for() { /* initialize the hash map here */ }

    // while(end<s.size()){

    // if(map[s[end++]]-- ?){ /* modify counter here */ }

    // while(/* counter condition */){

    // /* update d here if finding minimum*/

    // //increase begin to make it invalid/valid again

    // if(map[s[begin++]]++ ?){ /*modify counter here*/ }
    // }

    // /* update d here if finding maximum*/
    // }
    // return d;

    /*
     * PROBLEMS 76. Minimum Window Substring 30. Substring with Concatenation of All
     * Words 438. Find All Anagrams in a String 3. Longest Substring Without
     * Repeating Characters 159. Longest Substring with At Most 2 Distinct
     * Characters 567. Permutation in String 340. Longest Substring with At Most K
     * Distinct Characters which is literally the same as the one with K = 2. 424.
     * Longest Repeating Character Replacement
     */

    // #76 Minimum Window Substring

    public String minWindow(String s, String t) {
        int st = 0, end = 0, len = (int) 1e9, missing = 0, head = 0;

        Map<Character, Integer> map = new HashMap<>();

        // initialize freq map of target str
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // initially st=end=0 , so all chars are missing
        missing = map.size();

        while (end < s.length()) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) {
                    missing--; // req number of that char found
                }
            }
            // here end has moved 1 step ahead of the valid window end
            end++;

            /*
             * if missing == 0 , -> valid window reached , // now try to shrink the window
             * by moving st++, also store the minimum possible valid window size before the
             * win gets invalid
             */

            /*
             * begin char could be in table or not, if not then good for us, it was a
             * wasteful char and we shortened the previously found substring.
             */

            /*
             * if found in table increment count in table, as we are leaving it out of
             * window and moving ahead,
             */

            /*
             * so it would no longer be a part of the substring marked by begin-end window
             * table only has count of chars required to make the present substring a valid
             * candidate if the count goes above zero means that the current window is
             * missing one char to be an answer candidate
             */
            while (missing == 0) {

                char ch2 = s.charAt(st);
                if (map.containsKey(ch2)) {
                    map.put(ch2, map.get(ch2) + 1);
                    if (map.get(ch2) > 0)
                        missing++; // we are leaving char out of the window
                }

                if (end - st < len) {
                    head = st;
                    len = end - st;
                }

                st++;
            }
        }

        return len == (int) 1e9 ? "" : s.substring(head, head + len);
    }

    // without comments
    public String minWindow2(String s, String t) {
        int st = 0, end = 0, len = (int) 1e9, missing = 0, head = 0;

        Map<Character, Integer> map = new HashMap<>();

        // initialize freq map of target str
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // initially st=end=0 , so all chars are missing
        missing = map.size();

        while (end < s.length()) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) {
                    missing--; // req number of that char found
                }
            }
            // here end has moved 1 step ahead of the valid window end
            end++;

            while (missing == 0) {

                char ch2 = s.charAt(st);
                if (map.containsKey(ch2)) {
                    map.put(ch2, map.get(ch2) + 1);
                    if (map.get(ch2) > 0)
                        missing++; // we are leaving char out of the window
                }

                if (end - st < len) {
                    head = st;
                    len = end - st;
                }

                st++;
            }
        }

        return len == (int) 1e9 ? "" : s.substring(head, head + len);
    }

    // #3 longest substr without repeating chars

    // sliding window approach
    public int lengthOfLongestSubstring(String s) {
        int st = 0, end = 0, len = 0, extra = 0, head = 0;

        Map<Character, Integer> map = new HashMap<>();

        // initially st=end=0 , so all chars are extra

        while (end < s.length()) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
                if (map.get(ch) > 1) {
                    extra++; // req number of that char found
                }
            } else {
                map.put(ch, 1);
            }
            // here end has moved 1 step ahead of the valid window end
            end++;

            while (extra > 0) {

                char ch2 = s.charAt(st);
                if (map.containsKey(ch2)) {
                    map.put(ch2, map.get(ch2) - 1);
                    if (map.get(ch2) == 1)
                        extra--; // we are leaving char out of the window
                }

                st++;
            }

            // find max here
            len = Math.max(len, end - st);

        }

        return len;
    }

    // set and sliding window
    public int lengthOfLongestSubstring_02(String s) {
        int i = 0, j = 0, count = 0, max = 0;
        Set<Character> set = new HashSet<>();

        while (j < s.length()) {
            char ch1 = s.charAt(j);
            char ch2 = s.charAt(i);
            if (!set.contains(ch1)) {
                count++;
                set.add(ch1);
                max = Math.max(count, max);
                j++;
            } else {
                count--;
                set.remove(ch2);
                i++;
            }
        }

        return max;
    }

}