//Given an integer array nums, return the length of the longest strictly increas
//ing subsequence. 
//
// A subsequence is a sequence that can be derived from an array by deleting som
//e or no elements without changing the order of the remaining elements. For examp
//le, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7]. 
//
// 
// Example 1: 
//
// 
//Input: nums = [10,9,2,5,3,7,101,18]
//Output: 4
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the 
//length is 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [0,1,0,3,2,3]
//Output: 4
// 
//
// Example 3: 
//
// 
//Input: nums = [7,7,7,7,7,7,7]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
// Follow up: Can you come up with an algorithm that runs in O(n log(n)) time co
//mplexity? 
// Related Topics Binary Search Dynamic Programming 
// 👍 7366 👎 162

// 2021-06-01 15:06:00

package leetcode.editor.en;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestIncreasingSubsequence().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]表示以nums的第i个数结尾的最长子序列的长度
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1); // base case 以自己结尾的最长子序列起码要包含自己
        dp[0] = 0;
        int res = 0; // 最终的结果不确定以哪个数结尾子序列最长
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j - 1] < nums[i - 1]) {
                    // 此时nums的第i个数可以加到第j个数后面，严格递增
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                } else {
                    dp[i] = dp[i];
                }
            }
            // 此时确定了dp[i]
            res = Math.max(res, dp[i]);
        }
        return res;
        // TODO: 二分查找 Patience Sort
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


