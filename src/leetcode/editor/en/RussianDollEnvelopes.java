//You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] r
//epresents the width and the height of an envelope. 
//
// One envelope can fit into another if and only if both the width and height of
// one envelope are greater than the other envelope's width and height. 
//
// Return the maximum number of envelopes you can Russian doll (i.e., put one in
//side the other). 
//
// Note: You cannot rotate an envelope. 
//
// 
// Example 1: 
//
// 
//Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
//Output: 3
//Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] 
//=> [5,4] => [6,7]).
// 
//
// Example 2: 
//
// 
//Input: envelopes = [[1,1],[1,1],[1,1]]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= envelopes.length <= 5000 
// envelopes[i].length == 2 
// 1 <= wi, hi <= 104 
// 
// Related Topics Binary Search Dynamic Programming 
// 👍 2109 👎 59

// 2021-06-01 15:28:26

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes {
    public static void main(String[] args) {
        Solution solution = new RussianDollEnvelopes().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // 二维的最长子序列
        int n = envelopes.length;
        // dp[i]表示以第i个信封结尾的最大长度
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        // 注意迭代需要有顺序，把[wi, hi]中的wi先递增排序
        // 对于wi的非严格递增需要特殊处理，将hi递减，保证不会错误的取到相同的wi
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0]; // wi严格递增
                else return o2[1] - o1[1]; // wi相同，hi非严格递减
            }
        });
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // 如果wj和wi相等，不可能有hj小于hi的情况，一定是hj>=hi
                if (envelopes[j - 1][1] < envelopes[i - 1][1]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
        // TODO: 二分查找 Patience Sort
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


