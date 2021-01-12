//Given a string s, return the longest palindromic substring in s. 
//
// 
// Example 1: 
//
// 
//Input: s = "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: "bb"
// 
//
// Example 3: 
//
// 
//Input: s = "a"
//Output: "a"
// 
//
// Example 4: 
//
// 
//Input: s = "ac"
//Output: "a"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consist of only digits and English letters (lower-case and/or upper-case), 
//
// 
// Related Topics String Dynamic Programming 
// 👍 9201 👎 622

// 2021-01-12 16:05:31

package leetcode.editor.en;
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int maxLen = 0;
    int startIndex = 0;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 1) {
            return s;
        }
        for (int i = 0; i < len; i++) {
            // 从所有可能的出发点，往两边尽可能延伸，延伸到极限后再比较长度
            extend(s, i, i);
            extend(s, i, i+1);
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    private void extend(String s, int left, int right) {
        int len = s.length();
        char[] chars = s.toCharArray();
        while (left >= 0 && right < len) {
            if (chars[left] == chars[right]) {
                left -= 1;
                right += 1;
            } else {
                break;
            }
        }
        // 注意，跳出while循环之后，left多减了1，right多加了1
        if (maxLen < right - left - 1) {
            maxLen = right - left - 1;
            startIndex = left + 1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
动态规划 b[i][j]表示i开始，j结束是不是回文(包括j）
b[i][i] 0<=i<=length-1都是回文
b[i][j] = s[i] == s[j] && b[i+1][j-1]
是这样一个思路，但是你后面的第三和第四个循环其实本质上是重复了，对于奇数长度和偶数长度其实可以收敛成一个循环，
第一个循环和第二个循环也可以收敛成一个
从长度的角度
 */
class Solution1 {
    public String longestPalindrome(String s) {
        int maxLen = 1;
        int startIndex = 0;
        int endIndex = 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            // 种子为a
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < len - 1; i++) {
            // 种子为aa
            if (chars[i] == chars[i+1]) {
                maxLen = 2;
                startIndex = i;
                endIndex = i+1;
                isPalindrome[i][i+1] = true;
            } else {
                isPalindrome[i][i+1] = false;
            }
        }
        // 对于种子为a进行扩展
        for (int i = 1; i <= len - 2; i++) {
            for (int k = 1; k <= Math.min(i, len-1-i); k++) {
                isPalindrome[i-k][i+k] = chars[i-k] == chars[i+k] && isPalindrome[i-k+1][i+k-1];
                if (isPalindrome[i-k][i+k] && 2 * k + 1 > maxLen) {
                    maxLen = 2 * k + 1;
                    startIndex = i - k;
                    endIndex = i + k;
                }
            }
        }
        // 对于种子为aa进行扩展
        for (int i = 1; i <= len - 3; i++) {
            for (int k = 1; k <= Math.min(i, len-2-i); k++) {
                isPalindrome[i-k][i+1+k] = chars[i-k] == chars[i+1+k] && isPalindrome[i-k+1][i+k];
                if (isPalindrome[i-k][i+1+k] && 2 * k + 2 > maxLen) {
                    maxLen = 2 * k + 2;
                    startIndex = i - k;
                    endIndex = i + 1 + k;
                }
            }
        }
        return s.substring(startIndex, endIndex + 1);
    }
}

// 用长度进行动态规划
class Solution2 {
    public String longestPalindrome(String s) {
        int maxLen = 1;
        int startIndex = 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[][] isPalindrome = new int[len][len];
        for (int i = 0; i < len; i++) {
            // 长度为1或者2
            isPalindrome[i][i] = 1;
            if (i + 1 < len && chars[i] == chars[i+1]) {
                isPalindrome[i][i+1] = 1;
                maxLen = 2;
                startIndex = i;
            }
        }

        for (int l = 3; l <= len; l++) {
            // 长度为3即以上
            for (int i = 0; i <= len - l; i++) {
                int j = i + l - 1;
                if (chars[i] == chars[j] && isPalindrome[i+1][j-1] == 1) {
                    isPalindrome[i][j] = 1;
                    maxLen = l;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }
}
}


