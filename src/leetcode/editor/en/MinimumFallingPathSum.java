//Given an n x n array of integers matrix, return the minimum sum of any falling
// path through matrix. 
//
// A falling path starts at any element in the first row and chooses the element
// in the next row that is either directly below or diagonally left/right. Specifi
//cally, the next element from position (row, col) will be (row + 1, col - 1), (ro
//w + 1, col), or (row + 1, col + 1). 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
//Output: 13
//Explanation: There are two falling paths with a minimum sum underlined below:
//[[2,1,3],      [[2,1,3],
// [6,5,4],       [6,5,4],
// [7,8,9]]       [7,8,9]]
// 
//
// Example 2: 
//
// 
//Input: matrix = [[-19,57],[-40,-5]]
//Output: -59
//Explanation: The falling path with a minimum sum is underlined below:
//[[-19,57],
// [-40,-5]]
// 
//
// Example 3: 
//
// 
//Input: matrix = [[-48]]
//Output: -48
// 
//
// 
// Constraints: 
//
// 
// n == matrix.length 
// n == matrix[i].length 
// 1 <= n <= 100 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics Dynamic Programming 
// 👍 1274 👎 84

// 2021-05-31 21:10:21

package leetcode.editor.en;
public class MinimumFallingPathSum {
    public static void main(String[] args) {
        Solution solution = new MinimumFallingPathSum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] curr = new int[n];
        int[] next = new int[n];
        for (int col = 0; col < n; col += 1) {
            curr[col] = matrix[0][col]; // base case 终点在row = 0时
        }
        for (int row = 1; row < n; row += 1) {
            // 题目给出的条件是from (row, col) to (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1)
            // 转换成from (row, col - 1), (row, col), (row, col + 1) to (row + 1, col)
            // 只需要记录两行的结果即可
            next[0] = matrix[row][0] + Math.min(curr[0], curr[1]);
            next[n - 1] = matrix[row][n - 1] + Math.min(curr[n - 1], curr[n - 2]); // 两个边只有两个可能的from
            for (int col = 1; col <= n - 2; col += 1) {
                next[col] = matrix[row][col] + Math.min(curr[col - 1], Math.min(curr[col], curr[col + 1]));
            }
            int[] temp = curr;
            curr = next;
            next = temp;
            // 最后curr是最终结果
        }
        int res = Integer.MAX_VALUE;
        for (int col = 0; col < n; col += 1) {
            res = Math.min(res, curr[col]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


