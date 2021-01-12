//Determine whether an integer is a palindrome. An integer is a palindrome when 
//it reads the same backward as forward. 
//
// Follow up: Could you solve it without converting the integer to a string? 
//
// 
// Example 1: 
//
// 
//Input: x = 121
//Output: true
// 
//
// Example 2: 
//
// 
//Input: x = -121
//Output: false
//Explanation: From left to right, it reads -121. From right to left, it becomes
// 121-. Therefore it is not a palindrome.
// 
//
// Example 3: 
//
// 
//Input: x = 10
//Output: false
//Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
// 
//
// Example 4: 
//
// 
//Input: x = -101
//Output: false
// 
//
// 
// Constraints: 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics Math 
// 👍 2933 👎 1678

// 2021-01-12 20:57:39

package leetcode.editor.en;
public class PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new PalindromeNumber().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(int x) {
        // 从数学方法来做
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            // 除了零之外没有数以0开始
            return false;
        }
        int res = 0;
        int temp = x;
        while (temp > 0) {
            res = res * 10 + temp % 10;
            temp = temp / 10;
        }
        return x == res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = Integer.toString(x);
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i <= len / 2 - 1; i++) {
            if (chars[i] != chars[len - 1 -i]) {
                return false;
            }
        }
        return true;
    }
}
}


