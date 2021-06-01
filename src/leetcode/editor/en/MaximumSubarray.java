//Given an integer array nums, find the contiguous subarray (containing at least
// one number) which has the largest sum and return its sum. 
//
// 
// Example 1: 
//
// 
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.
// 
//
// Example 2: 
//
// 
//Input: nums = [1]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: nums = [5,4,-1,7,8]
//Output: 23
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//Follow up: If you have figured out the O(n) solution, try coding another solut
//ion using the divide and conquer approach, which is more subtle. Related Topics 
//Array Divide and Conquer Dynamic Programming 
// 👍 12216 👎 591

// 2021-06-01 16:06:20

package leetcode.editor.en;
public class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 状态压缩 dp[i]只和dp[i-1]相关
        // dp[i]表示nums[i]结尾的最大子数组和
        // base case
        int curr = nums[0];
        int res = curr; // n=1的情况为初始值
        for (int i = 1; i < n; i++) {
            if (curr > 0) curr = nums[i] + curr;
            else curr = nums[i]; // 以这个数结尾的数组，是否要接上前面的数组
            res = Math.max(res, curr);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i]表示nums[i]结尾的最大子数组和
        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];
        int res = dp[0]; // n=1的情况为初始值
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] > 0) dp[i] = nums[i] + dp[i - 1];
            else dp[i] = nums[i]; // 以这个数结尾的数组，是否要接上前面的数组
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
}


