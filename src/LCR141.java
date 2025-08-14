import java.util.HashSet;
import java.util.Set;

/**
 * @Author:
 * @CreateTime: 2025-07-24
 * @Description: 环形链表，存在环则返回true
 */
public class LCR141 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
//            if (set.contains(head)) {
//                return true;
//            }
//            set.add(head);
            while (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public boolean solution2(ListNode head) {
        // 快慢指针
        //「Floyd 判圈算法」（又称龟兔赛跑算法）
        // 假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。
        // 如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方
        // 如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动
        // 等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈

        // 空间复杂度：O(1)
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

        // 慢指针每次只移动一步，而快指针每次移动两步
        // 如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表
        // 当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 N 轮。
    }

}
