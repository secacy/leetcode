import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:
 * @CreateTime: 2025-07-24
 * @Description: 环形链表Ⅱ
 */
public class LCR142 {
    public ListNode solution2(ListNode head) {
        // 快慢指针
        // 空间复杂度：O(1)
        // 链表：a + b + c
        // t = a + b
        // 2t = a + n(b+c) + b
        // a = (n-1)(b+c) + c
        // 从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
        // 因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr。
        // 起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }
}
