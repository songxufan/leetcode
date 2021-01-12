//Implement the myAtoi(string s) function, which converts a string to a 32-bit s
//igned integer (similar to C/C++'s atoi function). 
//
// The algorithm for myAtoi(string s) is as follows: 
//
// 
// Read in and ignore any leading whitespace. 
// Check if the next character (if not already at the end of the string) is '-' 
//or '+'. Read this character in if it is either. This determines if the final res
//ult is negative or positive respectively. Assume the result is positive if neith
//er is present. 
// Read in next the characters until the next non-digit charcter or the end of t
//he input is reached. The rest of the string is ignored. 
// Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no
// digits were read, then the integer is 0. Change the sign as necessary (from ste
//p 2). 
// If the integer is out of the 32-bit signed integer range [-231, 231 - 1], the
//n clamp the integer so that it remains in the range. Specifically, integers less
// than -231 should be clamped to -231, and integers greater than 231 - 1 should b
//e clamped to 231 - 1. 
// Return the integer as the final result. 
// 
//
// Note: 
//
// 
// Only the space character ' ' is considered a whitespace character. 
// Do not ignore any characters other than the leading whitespace or the rest of
// the string after the digits. 
// 
//
// 
// Example 1: 
//
// 
//Input: str = "42"
//Output: 42
//Explanation: The underlined characters are what is read in, the caret is the c
//urrent reader position.
//Step 1: "42" (no characters read because there is no leading whitespace)
//         ^
//Step 2: "42" (no characters read because there is neither a '-' nor '+')
//         ^
//Step 3: "42" ("42" is read in)
//           ^
//The parsed integer is 42.
//Since 42 is in the range [-231, 231 - 1], the final result is 42.
// 
//
// Example 2: 
//
// 
//Input: str = "   -42"
//Output: -42
//Explanation:
//Step 1: "   -42" (leading whitespace is read and ignored)
//            ^
//Step 2: "   -42" ('-' is read, so the result should be negative)
//             ^
//Step 3: "   -42" ("42" is read in)
//               ^
//The parsed integer is -42.
//Since -42 is in the range [-231, 231 - 1], the final result is -42.
// 
//
// Example 3: 
//
// 
//Input: str = "4193 with words"
//Output: 4193
//Explanation:
//Step 1: "4193 with words" (no characters read because there is no leading whit
//espace)
//         ^
//Step 2: "4193 with words" (no characters read because there is neither a '-' n
//or '+')
//         ^
//Step 3: "4193 with words" ("4193" is read in; reading stops because the next c
//haracter is a non-digit)
//             ^
//The parsed integer is 4193.
//Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
// 
//
// Example 4: 
//
// 
//Input: str = "words and 987"
//Output: 0
//Explanation:
//Step 1: "words and 987" (no characters read because there is no leading whites
//pace)
//         ^
//Step 2: "words and 987" (no characters read because there is neither a '-' nor
// '+')
//         ^
//Step 3: "words and 987" (reading stops immediately because there is a non-digi
//t 'w')
//         ^
//The parsed integer is 0 because no digits were read.
//Since 0 is in the range [-231, 231 - 1], the final result is 4193.
// 
//
// Example 5: 
//
// 
//Input: str = "-91283472332"
//Output: -2147483648
//Explanation:
//Step 1: "-91283472332" (no characters read because there is no leading whitesp
//ace)
//         ^
//Step 2: "-91283472332" ('-' is read, so the result should be negative)
//          ^
//Step 3: "-91283472332" ("91283472332" is read in)
//                     ^
//The parsed integer is -91283472332.
//Since -91283472332 is less than the lower bound of the range [-231, 231 - 1], 
//the final result is clamped to -231 = -2147483648. 
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 200 
// s consists of English letters (lower-case and upper-case), digits (0-9), ' ',
// '+', '-', and '.'. 
// 
// Related Topics Math String 
// 👍 2050 👎 11132

// 2021-01-12 20:29:50

package leetcode.editor.en;
public class StringToIntegerAtoi {
    public static void main(String[] args) {
        Solution solution = new StringToIntegerAtoi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int myAtoi(String s) {
        boolean started = false;
        int res = 0;
        int sign = 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!started) {
                if (chars[i] == ' ') {
                    continue;
                } else if (chars[i] == '+') {
                    sign = 1;
                    started = true;
                } else if (chars[i] == '-') {
                    sign = -1;
                    started = true;
                } else if (isDigit(chars[i])) {
                    res = sign * (chars[i] - '0');
                    started = true;
                } else {
                    break;
                }
            } else {
                if (isDigit(chars[i])) {
                    if (res > Integer.MAX_VALUE / 10
                            || (res == Integer.MAX_VALUE / 10 && chars[i] - '0' > 7)) {
                        res = Integer.MAX_VALUE;
                        break;
                    }
                    if (res < Integer.MIN_VALUE / 10
                            || (res == Integer.MIN_VALUE / 10 && chars[i] - '0' > 8)) {
                        res = Integer.MIN_VALUE;
                        break;
                    }
                    res = res * 10 + sign * (chars[i] - '0');
                } else {
                    break;
                }
            }
        }
        return res;
    }

    private boolean isDigit(char c) {
        return c - '0' >= 0 && c - '0' <= 9;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


