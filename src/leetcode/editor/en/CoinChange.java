//You are given an integer array coins representing coins of different denominat
//ions and an integer amount representing a total amount of money. 
//
// Return the fewest number of coins that you need to make up that amount. If th
//at amount of money cannot be made up by any combination of the coins, return -1.
// 
//
// You may assume that you have an infinite number of each kind of coin. 
//
// 
// Example 1: 
//
// 
//Input: coins = [1,2,5], amount = 11
//Output: 3
//Explanation: 11 = 5 + 5 + 1
// 
//
// Example 2: 
//
// 
//Input: coins = [2], amount = 3
//Output: -1
// 
//
// Example 3: 
//
// 
//Input: coins = [1], amount = 0
//Output: 0
// 
//
// Example 4: 
//
// 
//Input: coins = [1], amount = 1
//Output: 1
// 
//
// Example 5: 
//
// 
//Input: coins = [1], amount = 2
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics Dynamic Programming 
// 👍 7051 👎 198

// 2021-05-31 19:47:35

package leetcode.editor.en;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int coinChange(int[] coins, int amount) {
        // 最优子结构，子问题之间必须相互独立
        // 从amount入手，每个不同的amount最少需要几枚硬币这个问题是相互独立的，也就是所谓的最优子结构
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 最大结果是amount(全为1块的硬币)，相当于初始化为+INF来求最小值
        dp[0] = 0; // base case
        // 比较大的状态由比较小的状态决定，所以从小到大迭代求解
        for (int coin: coins) {
            // 用了这个coin，可以达成的amount一定比它大
            // 当coin比较大时，可以减小大量的amount<coin情况的迭代次数
            for (int i = coin; i <= amount; i += 1) {
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount]; // 如果还是初始值，说明无解
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public int coinChange(int[] coins, int amount) {
        // 最优子结构，子问题之间必须相互独立
        // 从amount入手，每个不同的amount最少需要几枚硬币这个问题是相互独立的，也就是所谓的最优子结构
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 最大结果是amount(全为1块的硬币)，相当于初始化为+INF来求最小值
        dp[0] = 0; // base case
        // 比较大的状态由比较小的状态决定，所以从小到大迭代求解
        for (int i = 1; i <= amount; i += 1) {
            for (int coin: coins) {
                if (i - coin < 0) continue; // 无解
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount]; // 如果还是初始值，说明无解
    }
}
}


