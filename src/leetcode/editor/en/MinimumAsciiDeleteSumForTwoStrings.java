//Given two strings s1 and s2, return the lowest ASCII sum of deleted characters
// to make two strings equal. 
//
// 
// Example 1: 
//
// 
//Input: s1 = "sea", s2 = "eat"
//Output: 231
//Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the 
//sum.
//Deleting "t" from "eat" adds 116 to the sum.
//At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum pos
//sible to achieve this.
// 
//
// Example 2: 
//
// 
//Input: s1 = "delete", s2 = "leet"
//Output: 403
//Explanation: Deleting "dee" from "delete" to turn the string into "let",
//adds 100[d] + 101[e] + 101[e] to the sum.
//Deleting "e" from "leet" adds 101[e] to the sum.
//At the end, both strings are equal to "let", and the answer is 100+101+101+101
// = 403.
//If instead we turned both strings into "lee" or "eet", we would get answers of
// 433 or 417, which are higher.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s1.length, s2.length <= 1000 
// s1 and s2 consist of lowercase English letters. 
// 
// Related Topics Dynamic Programming 
// 👍 1378 👎 57

// 2021-06-01 17:55:42

package leetcode.editor.en;
public class MinimumAsciiDeleteSumForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new MinimumAsciiDeleteSumForTwoStrings().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // dp[i][j]表示前i个和前j个对应需要的删除ASCII和
        int[][] dp = new int[m + 1][n + 1];
        // base case
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 不用删除
                } else {
                    // 删除任意一边
                    dp[i][j] = Math.min(dp[i][j - 1] + s2.charAt(j - 1), dp[i - 1][j] + s1.charAt(i - 1));
                }
            }
            // 删除之后剩下的结果就是Longest Common Subsequence
        }
        return dp[m][n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


