//Given the head of a singly linked list, return true if it is a palindrome. 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,2,1]
//Output: true
// 
//
// Example 2: 
//
// 
//Input: head = [1,2]
//Output: false
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [1, 105]. 
// 0 <= Node.val <= 9 
// 
//
// 
//Follow up: Could you do it in O(n) time and O(1) space? Related Topics Linked 
//List Two Pointers 
// 👍 4893 👎 430

// 2021-03-31 17:50:31

package leetcode.editor.en;
public class PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public boolean isPalindrome(ListNode head) {
        // 只反转一半的链表
        // 1. 双指针找中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 如果是奇数长度12345，此时slow为3，fast为5，需要从4开始翻转
        // 如果是偶数长度1234，此时slow为3，fast为null，可以从3开始翻转
        if (fast != null) {
            slow = slow.next;
        }
        ListNode left = head;
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode next = head;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }



}
//leetcode submit region end(Prohibit modification and deletion)

    // 找出以l和r为中心的最长回文串的长度
    // 奇数长度 l = r; 偶数长度 l = r - 1
    public int findPalindrome(int[] s, int l, int r) {
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l -= 1;
            r += 1;
        }
        // 从l+1到r-1
        return r - l - 1;
    }

    // 判断是否回文串
    public boolean isPalindrome(int[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l <= r) {
            if (s[l] != s[r]) return false;
            l += 1;
            r -= 1;
        }
        return true;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution1 {
        private ListNode pre;

        public boolean isPalindrome(ListNode head) {
            pre = head;
            return traverse(head);

        }

        private boolean traverse(ListNode head) {
            if (head == null) return true;
            boolean res = traverse(head.next);
            ListNode next = head; // 通过递归的后序遍历来更新next
            // 此时有pre和next，根据val结果比较来更新res
            res = res && (pre.val == next.val);
            pre = pre.next; // 手动更新前序遍历
            return res;
        }

    }

}


