//Given an integer n, return the number of structurally unique BST's (binary sea
//rch trees) which has exactly n nodes of unique values from 1 to n. 
//
// 
// Example 1: 
//
// 
//Input: n = 3
//Output: 5
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 19 
// 
// Related Topics Dynamic Programming Tree 
// 👍 4714 👎 175

// 2021-05-31 17:23:19

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTrees().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numTrees(int n) {
        // 需要记录之前的结果，避免重叠子问题
        // 用数组而不是map来提高效率
        // 数组需要初始化size，在numTrees方法里面，所以需要新增一个辅助方法来进行递归
        record = new int[n];
        return getNum(n);
    }

    private int getNum(int n) {
        // 没有节点，代表节点为null的情况
        if (n == 0) return 1;
        if (record[n-1] != 0) return record[n-1];
        int sum = 0;
        for (int i = 1; i <= n; i += 1) {
            // 1到n分别作为根节点，左边有i-1个节点，右边有n-i个节点
            sum += getNum(i - 1) * getNum(n - i);
        }
        record[n-1] = sum;
        return sum;
    }

    private int[] record;

}
//leetcode submit region end(Prohibit modification and deletion)

}


