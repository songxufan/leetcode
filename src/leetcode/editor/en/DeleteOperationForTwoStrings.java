//Given two strings word1 and word2, return the minimum number of steps required
// to make word1 and word2 the same. 
//
// In one step, you can delete exactly one character in either string. 
//
// 
// Example 1: 
//
// 
//Input: word1 = "sea", word2 = "eat"
//Output: 2
//Explanation: You need one step to make "sea" to "ea" and another step to make 
//"eat" to "ea".
// 
//
// Example 2: 
//
// 
//Input: word1 = "leetcode", word2 = "etco"
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= word1.length, word2.length <= 500 
// word1 and word2 consist of only lowercase English letters. 
// 
// Related Topics String 
// 👍 1823 👎 37

// 2021-06-01 17:34:11

package leetcode.editor.en;
public class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new DeleteOperationForTwoStrings().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j]表示前i个和前j个对应需要的操作步骤
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 不用删除
                } else {
                    // 删除任意一边
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
            // 删除之后剩下的结果就是Longest Common Subsequence
        }
        return dp[m][n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


