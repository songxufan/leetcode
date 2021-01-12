//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number o
//f rows like this: (you may want to display this pattern in a fixed font for bett
//er legibility) 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R
// 
//
// And then read line by line: "PAHNAPLSIIGYIR" 
//
// Write the code that will take a string and make this conversion given a numbe
//r of rows: 
//
// 
//string convert(string s, int numRows);
// 
//
// 
// Example 1: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
// 
//
// Example 2: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// Example 3: 
//
// 
//Input: s = "A", numRows = 1
//Output: "A"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists of English letters (lower-case and upper-case), ',' and '.'. 
// 1 <= numRows <= 1000 
// 
// Related Topics String 
// 👍 2094 👎 5373

// 2021-01-12 16:51:17

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new ZigzagConversion().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        // 遍历但是不存储
        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int currRow = 0;
        int i = 0;
        boolean down = true;
        StringBuilder sb = new StringBuilder();
        while (currRow < numRows) {
            if (i < len) {
                sb.append(chars[i]);
                if (currRow == 0 || currRow == numRows - 1) {
                    i += 2 * (numRows - 1);
                } else {
                    // 中间的行，两次的步幅互补
                    if (down) {
                        i += 2 * (numRows - currRow - 1);
                    } else {
                        i += 2 * currRow;
                    }
                    down = !down;
                }
            } else {
                // 开始下一行，恢复初始化
                currRow += 1;
                down = true;
                i = currRow;
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public String convert(String s, int numRows) {
        // 遍历并且存储
        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int direction = 1;
        int currRow = 0;
        List<List<Character>> record = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            record.add(new LinkedList<>());
        }
        for (int i = 0; i < chars.length; i++) {
            // 要注意numRows=1的情况，此时0和numRows-1是相同的
            // sense for edge case
            record.get(currRow).add(chars[i]);
            if (currRow == 0) {
                direction = 1;
            }
            if (currRow == numRows - 1) {
                direction = -1;
            }
            currRow += direction;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (Character c : record.get(i)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
}


