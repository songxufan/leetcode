//Given a string s, find the longest palindromic subsequence's length in s. 
//
// A subsequence is a sequence that can be derived from another sequence by dele
//ting some or no elements without changing the order of the remaining elements. 
//
// 
// Example 1: 
//
// 
//Input: s = "bbbab"
//Output: 4
//Explanation: One possible longest palindromic subsequence is "bbbb".
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: 2
//Explanation: One possible longest palindromic subsequence is "bb".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists only of lowercase English letters. 
// 
// Related Topics Dynamic Programming 
// 👍 3227 👎 220

// 2021-06-01 18:07:20

package leetcode.editor.en;
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp[i][j]表示从i到j的回文子序列长度
        // 注意子序列不一定是连续的
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 0;
        // base case 子序列的长度是1
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1; // 其他地方都是0
        }
        // 从子序列长度是2开始迭代
        for (int i = n; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    // 这两个字母相等，加到回文子序列里面去
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    // 这两个字母不相等，分别舍弃其中一个
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[1][n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


