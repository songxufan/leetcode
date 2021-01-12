//Given a 32-bit signed integer, reverse digits of an integer. 
//
// Note: Assume we are dealing with an environment that could only store integer
//s within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, ass
//ume that your function returns 0 when the reversed integer overflows. 
//
// 
// Example 1: 
// Input: x = 123
//Output: 321
// Example 2: 
// Input: x = -123
//Output: -321
// Example 3: 
// Input: x = 120
//Output: 21
// Example 4: 
// Input: x = 0
//Output: 0
// 
// 
// Constraints: 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics Math 
// 👍 4180 👎 6480

// 2021-01-12 17:43:40

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        solution.reverse(-2147483412);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        // 在除的时候同时乘，只做一个循环
        // 123/10 = 12 + 3 同时 3
        // 12/10 = 1 + 2 同时 3*10+2=32
        // 1/10 = 0 + 1 同时 32*10+1=321
        // 除以十之后，即使overflow也可以去比较了
        int reminder = x % 10;
        int result = reminder;
        x = x / 10;
        while (x != 0) {
            reminder = x % 10;
            // 判断result是否overflow，此时的result还没有乘以10
            if ((result > Integer.MAX_VALUE / 10)
                    || (result == Integer.MIN_VALUE / 10 && reminder > 7)) {
                return 0;
            }
            if ((result < Integer.MIN_VALUE / 10)
                    || (result == Integer.MIN_VALUE / 10 && reminder < -8)) {
                return 0;
            }
            result = result * 10 + reminder;
            x = x / 10;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        List<Integer> maxIntDigits = new LinkedList<>();
        int maxInt = Integer.MAX_VALUE;
        int reminder = 0;
        while (maxInt > 0) {
            reminder = maxInt % 10;
            maxIntDigits.add(reminder);
            maxInt = maxInt / 10;
        }
        int maxSize = maxIntDigits.size();
        int sign = x > 0 ? 1 : -1;
        int absX = Math.abs(x);
        List<Integer> xDigits = new LinkedList<>();
        while (absX > 0) {
            reminder = absX % 10;
            xDigits.add(reminder);
            absX = absX / 10;
        }
        int xSize = xDigits.size();
        int result = 0;
        // 首先判断是否overflow
        // 只有高位相等时候才比较低位
        // 注意和原来的数进行比较，而maxIntDigits里面是Integer.MAX各digit的reverse order
        // 个位的range对于正负数不一样
        boolean overflow = false;
        if (xSize == maxSize) {
            for (int i = 0; i < maxSize; i++) {
                if (i == maxSize - 1) { // 对于个位
                    if ((sign > 0 && xDigits.get(i) > maxIntDigits.get(0))
                            || (sign < 0 && xDigits.get(i) > maxIntDigits.get(0) + 1)) {
                        overflow = true;
                    }
                } else {
                    if (xDigits.get(i) > maxIntDigits.get(maxSize - i - 1)) {
                        overflow = true;
                        break;
                    } else if (xDigits.get(i) < maxIntDigits.get(maxSize - i - 1)) {
                        break;
                    }
                }
            }
        }
        if (overflow) {
            return 0;
        } else {
            for (int i = 0; i < xSize; i++) {
                result += xDigits.get(i) * Math.pow(10, xSize - i - 1);
            }
            return result * sign;
        }
    }
}

}


