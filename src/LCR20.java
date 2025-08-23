import java.util.*;

/**
 * 有效的括号
 *
 * @since 2025/8/22 23:18
 * @className LCR20
 * @author hc
 */
public class LCR20 {
//    在java中，栈 -> 官方推荐使用 Deque 接口，其最常用的实现类是 ArrayDeque
//     Deque 接口为栈操作定义了清晰的方法：
//    压栈 (Push): push(E e) 或 addFirst(E e)
//    弹栈 (Pop): pop() 或 removeFirst()
//    查看栈顶元素 (Peek): peek() 或 peekFirst()
//     另一个选项：LinkedList
//    LinkedList 也实现了 Deque 接口，因此它同样可以被用作栈。
//     LinkedList允许存入 null，ArrayDeque不允许存入 null
//     ArrayDeque性能通常更快
    Map<Character, Character> map = Map.of(
            ')', '(',
            '}', '{',
            ']', '['
    );

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsValue(c)) {
                stack.push(c);
            } else if (map.containsKey(c)) {
                if (stack.isEmpty()) return false;
                Character top = stack.pop();
                if (map.get(c) != top) return false;
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    // 注意到有效字符串的长度一定为偶数
    // 因此如果字符串的长度为奇数，我们可以直接返回 False，省去后续的遍历判断过程。
}
