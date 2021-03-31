//Given an input string (s) and a pattern (p), implement regular expression matc
//hing with support for '.' and '*' where: 
//
// 
// '.' Matches any single character. 
// '*' Matches zero or more of the preceding element. 
// 
//
// The matching should cover the entire input string (not partial). 
//
// 
// Example 1: 
//
// 
//Input: s = "aa", p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
// 
//
// Example 2: 
//
// 
//Input: s = "aa", p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, 
//by repeating 'a' once, it becomes "aa".
// 
//
// Example 3: 
//
// 
//Input: s = "ab", p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
// 
//
// Example 4: 
//
// 
//Input: s = "aab", p = "c*a*b"
//Output: true
//Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, i
//t matches "aab".
// 
//
// Example 5: 
//
// 
//Input: s = "mississippi", p = "mis*is*p*."
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 20 
// 0 <= p.length <= 30 
// s contains only lowercase English letters. 
// p contains only lowercase English letters, '.', and '*'. 
// It is guaranteed for each appearance of the character '*', there will be a pr
//evious valid character to match. 
// 
// Related Topics String Dynamic Programming Backtracking 
// 👍 5208 👎 810

// 2021-01-13 16:31:53

package leetcode.editor.en;
public class RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isMatch(String s, String p) {
        // Dynamic Programming
        int sLen = s.length();
        int pLen = p.length();
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        boolean[][] match = new boolean[sLen+1][pLen+1];
        // match[i][j]表示s的前i个和p的前j个是否match
        match[0][0] = true;
        // 有s无p，不match
        for (int i = 1; i <= sLen; i++) {
            match[i][0] = false;
        }
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                // 要匹配的是s[i-1]和p[j-1]
                // 无s有p是可能通过 匹配*前面的零次
                if (i == 0) {
                    if (pChars[j-1] == '*' && j - 2 >= 0) {
                        match[0][j] = match[0][j-2];
                    } else {
                        match[0][j] = false;
                    }
                } else {
                    if (pChars[j-1] == '*') {
                        if (j - 2 >= 0 && (pChars[j-2] == sChars[i-1] || pChars[j-2] == '.')) {
                            // 匹配p的上一个字符一次，把s匹配了一个，但是此时也可以选择匹配0次
                            match[i][j] = match[i-1][j] || match[i][j-2];
                        } else {
                            // 匹配p的上一个零次，把a*一起匹配掉
                            // 保证了*前面一定有东西m
                            match[i][j] = match[i][j-2];
                        }
                    } else if (pChars[j-1] == '.' || pChars[j-1] == sChars[i-1]) {
                        // 匹配当前的字符
                        match[i][j] = match[i-1][j-1];
                    } else {
                        match[i][j] = false;
                    }
                }
            }
        }
        return match[sLen][pLen];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


