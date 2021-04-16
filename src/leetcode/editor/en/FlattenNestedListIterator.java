//You are given a nested list of integers nestedList. Each element is either an 
//integer or a list whose elements may also be integers or other lists. Implement 
//an iterator to flatten it. 
//
// Implement the NestedIterator class: 
//
// 
// NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with 
//the nested list nestedList. 
// int next() Returns the next integer in the nested list. 
// boolean hasNext() Returns true if there are still some integers in the nested
// list and false otherwise. 
// 
//
// 
// Example 1: 
//
// 
//Input: nestedList = [[1,1],2,[1,1]]
//Output: [1,1,2,1,1]
//Explanation: By calling next repeatedly until hasNext returns false, the order
// of elements returned by next should be: [1,1,2,1,1].
// 
//
// Example 2: 
//
// 
//Input: nestedList = [1,[4,[6]]]
//Output: [1,4,6]
//Explanation: By calling next repeatedly until hasNext returns false, the order
// of elements returned by next should be: [1,4,6].
// 
//
// 
// Constraints: 
//
// 
// 1 <= nestedList.length <= 500 
// The values of the integers in the nested list is in the range [-106, 106]. 
// 
// Related Topics Stack Design 
// 👍 2284 👎 836

// 2021-04-16 18:01:18

package leetcode.editor.en;

import java.util.*;

public class FlattenNestedListIterator {
    public static void main(String[] args) { }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    private Deque<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        // 调用hasNext时，如果nestedList的第一个元素是列表类型，则不断展开这个元素，直到第一个元素是整数类型
        // Iterator需要实现懒加载
        list = new LinkedList<>(nestedList);
    }

    @Override
    public Integer next() {
        // 依赖于hasNext方法来确保可以getInteger
        return list.poll().getInteger();
    }

    @Override
    public boolean hasNext() {
        // 如果第一个元素不是子节点，将第一个元素打平一次并且添加到list中，并且循环到第一个元素是子几点
        // 实现懒加载，每次都保证next一定可以取到
        // 利用LinkedList的addFirst方法较快的特性，把打平之后的元素倒序向list中添加
        while (!list.isEmpty() && !list.peek().isInteger()) {
            List<NestedInteger> firstChildren = list.poll().getList();
            for (int i = firstChildren.size() - 1; i >= 0; i -= 1) {
                list.offerFirst(firstChildren.get(i));
            }
        }
        return !list.isEmpty();
    }


}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }
    // 就可以理解成一个N叉树 integer对应于val，list对应于children
    // 叶子节点有val无children，非叶子节点无val有children

    public class NestedIterator2 implements Iterator<Integer> {

        public NestedIterator2(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger: nestedList) {
                traverse(nestedInteger);
            }
        }

        @Override
        public Integer next() {
            return res.remove(0);
        }

        @Override
        public boolean hasNext() {
            return !res.isEmpty();
        }

        private List<Integer> res = new LinkedList<>();

        private void traverse(NestedInteger nestedInteger) {
            if (nestedInteger.isInteger()) {
                res.add(nestedInteger.getInteger());
            } else {
                for (NestedInteger child: nestedInteger.getList()) {
                    traverse(child);
                }
            }
        }
    }
}




