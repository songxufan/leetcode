//Given a string s, find the length of the longest substring without repeating c
//haracters. 
//
// 
// Example 1: 
//
// 
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
// 
//
// Example 2: 
//
// 
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// Example 3: 
//
// 
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a 
//substring.
// 
//
// Example 4: 
//
// 
//Input: s = ""
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 5 * 104 
// s consists of English letters, digits, symbols and spaces. 
// 
// Related Topics Hash Table Two Pointers String Sliding Window 
// 👍 12614 👎 664

// 2021-01-11 20:46:22

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // extended ASCII 0-255 range
        // use array instead of map to optimize the time performance
        int[] record = new int[256];
        Arrays.fill(record, -1);
        char[] chars = s.toCharArray();
        int startIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (record[chars[i]] != -1) {
                startIndex = Math.max(startIndex, record[chars[i]] + 1);
            }
            record[chars[i]] = i;
            maxLength = Math.max(maxLength, i - startIndex + 1);
        }
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> record = new HashMap<>();
        char[] chars = s.toCharArray();
        int startIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (record.containsKey(chars[i])) {
                startIndex = Math.max(startIndex, record.get(chars[i]) + 1);
            }
            record.put(chars[i], i);
            maxLength = Math.max(maxLength, i - startIndex + 1);
        }
        return maxLength;
    }
}
}


