import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 最小栈
 *
 * @since 2025/8/22 23:42
 * @className LCR155
 * @author hc
 */
public class LCR155 {
    class MinStack {
        // 栈：先进后出
        // 如果一个元素 a 在入栈时，栈里有其它的元素 b, c, d
        // 任意时刻，只要栈顶的元素是 a，那么我们就可以确定栈里的元素一定是 a, b, c, d
        // 我们可以在每个元素 a 入栈时把当前栈的最小值 m 存储起来
        // 因此我们可以使用一个辅助栈，与元素栈同步插入与删除
        // 用于存储与每个元素对应的最小值

        Deque<Integer> xStack;
        Deque<Integer> minStack;

        public MinStack() {
            xStack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            xStack.push(val);
            minStack.push(Math.min(minStack.peek(), val));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
