import java.util.ArrayList;

/**
 * @Author:
 * @CreateTime: 2025-07-23
 * @Description: 回文链表
 */
public class LCR234 {
    public boolean mySolution1(ListNode head) {
        // 空间复杂度为 O(N)
        ArrayList<Integer> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }
        for (int i = 0, j = arr.size() - 1; i < j; i++, j--) {
            if (arr.get(i) != arr.get(j)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


//    private int length = 0;

//    public boolean mySolution2(ListNode head) {
    // 这是错误的解法
//        ListNode revNode = reverse(head); // reverse之后head的next会变成null
//        int i = 0;
//        while (head != null && i < length/2) {
//            if (head.val != revNode.val) {
//                return false;
//            }
//            head = head.next;
//            revNode = revNode.next;
//            i++;
//        }
//        return true;
//    }
//
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(1))));
        LCR234 lcr234 = new LCR234();
//        boolean result = lcr234.mySolution2(head);
//        System.out.println("result = " + result);
    }
}
