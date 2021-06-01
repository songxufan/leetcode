//You are given an integer array nums and an integer target. 
//
// You want to build an expression out of nums by adding one of the symbols '+' 
//and '-' before each integer in nums and then concatenate all the integers. 
//
// 
// For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 
//and concatenate them to build the expression "+2-1". 
// 
//
// Return the number of different expressions that you can build, which evaluate
//s to target. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,1,1,1,1], target = 3
//Output: 5
//Explanation: There are 5 ways to assign symbols to make the sum of nums be tar
//get 3.
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// Example 2: 
//
// 
//Input: nums = [1], target = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics Dynamic Programming Depth-first Search 
// 👍 4257 👎 171

// 2021-05-31 21:32:48

package leetcode.editor.en;
public class TargetSum {
    public static void main(String[] args) {
        Solution solution = new TargetSum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // 转换成背包问题，找出子集A，使得sumA*2=sum+target<=2*sum，即sumA-sumB=target, target<=sum
        // 这是一个0-1背包问题，每个元素只有一个，要么选择要么不选择
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        if (target > sum || (sum + target) % 2 != 0) return 0;
        int newTarget = (sum + target) / 2;
        int n = nums.length;
        // 压缩成一维数组 dp[j] j表示容量
        int[] dp = new int[newTarget + 1];
        /**
         * 前面的coinChange问题，因为每个种类的硬币是无限多的，所以每个不同的amount是最优子结构，相互独立的子问题
         * 而背包问题，每个num都只有一个，所以不能按照target来划分最优子结构
         * 比如说target是10，用了num=2，剩下的是8，但是8的背包问题里面可能也用了num=2
         * 你想到了二维数组，但是i的含义，你只想到了用第i个或者不用第i个，都没有办法互相实现最优子结构
         * 醍醐灌顶的一个hint：前i个
         * row表示前i个，col表示当前容量，dp[row][col]表示有多少种
         */
        // base case
        dp[0] = 1;
        for (int row = 1; row <= n; row++) { // 每一次迭代，表示将dp数组更新成前i个对应的种类数 row=0肯定为0
            for (int col = newTarget; col >= 0; col--) { // 要考虑到col=0，可能出现0+0+0=0和0+0=0的情况
                if (col - nums[row - 1] >= 0) {
                    // 背包空间足够
                    // 第i次迭代的dp[col] = 第i-1次迭代的dp[col] + 第i-1次迭代的dp[col - nums[row - 1]]
                    // 下一次迭代的结果依赖的是上一次迭代坐标较小的结果，所以将坐标从大往小迭代
                    dp[col] = dp[col] + dp[col - nums[row - 1]];
                } else {
                    // 背包空间不够，只能不用第row个
                    dp[col] = dp[col];
                }
            }
        }
        return dp[newTarget];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    class Solution1 {
        public int findTargetSumWays(int[] nums, int target) {
            // 转换成背包问题，找出子集A，使得sumA*2=sum+target<=2*sum，即sumA-sumB=target, target<=sum
            // 这是一个0-1背包问题，每个元素只有一个，要么选择要么不选择
            int sum = 0;
            for (int num: nums) {
                sum += num;
            }
            if (target > sum || (sum + target) % 2 != 0) return 0;
            int newTarget = (sum + target) / 2;
            int n = nums.length;
            int[][] dp = new int[n + 1][newTarget + 1];
            /**
             * 前面的coinChange问题，因为每个种类的硬币是无限多的，所以每个不同的amount是最优子结构，相互独立的子问题
             * 而背包问题，每个num都只有一个，所以不能按照target来划分最优子结构
             * 比如说target是10，用了num=2，剩下的是8，但是8的背包问题里面可能也用了num=2
             * 你想到了二维数组，但是i的含义，你只想到了用第i个或者不用第i个，都没有办法互相实现最优子结构
             * 醍醐灌顶的一个hint：前i个
             * row表示前i个，col表示当前容量，dp[row][col]表示有多少种
             */
            // base case
            for (int row = 0; row <= n; row++) {
                dp[row][0] = 1; // 前row个容量为0，只有一种，全部不要
            }
            for (int row = 1; row <= n; row++) { // row=0的其他col均为0，前0个容量不为0，为0种，无法实现
                for (int col = 0; col <= newTarget; col++) { // 从col=0开始迭代，可能出现0+0+0=0和0+0=0的情况
                    int remainTarget = col - nums[row - 1];
                    if (remainTarget >= 0) {
                        // 背包空间足够
                        // dp[row][col] = dp[row-1]不用第row个 + dp[row-1]用了第row个
                        dp[row][col] = dp[row - 1][col] + dp[row - 1][remainTarget];
                    } else {
                        // 背包空间不够，只能不用第row个
                        dp[row][col] = dp[row - 1][col];
                    }
                }
            }
            return dp[n][newTarget];
        }
    }
}


