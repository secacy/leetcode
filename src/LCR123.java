import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author:
 * @CreateTime: 2025-07-08
 * @Description:
 */
public class LCR123 {
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
    public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
    int[] res;
    int i = 0;
    int j = 0;

    /**
     * 图书整理 Ⅰ
     * 反转链表
     */
    ArrayList<Integer> tmp = new ArrayList<>();
    public int[] solution1(ListNode head) {
        // 利用递归
        // 这个例子充分说明了递归本质上就是一个栈，先进后出。
        recur(head);
        int[] res = new int[tmp.size()];
        for(int i = 0; i < res.length; i++)
            res[i] = tmp.get(i);
        return res;
        // 时间复杂度：O(N)
        // 空间复杂度：O(N) 系统递归需要使用O(N)栈空间
    }
    void recur(ListNode head) {
        if(head == null) return;
        recur(head.next);
        tmp.add(head.val);
    }

    public int[] solution2(ListNode head) {
        // 也是利用递归的解法
        solve(head);
        return res;
    }

    private void solve(ListNode head) {
        if (head == null) {
            res = new int[i];
            return;
        }
        i++;
        solve(head.next);
        res[j] = head.val;
        j++;
    }

    public int[] solution3(ListNode head) {
        // 辅助栈法
        // 利用栈的先入后出
        LinkedList<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.addLast(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.removeLast();
        }
        return res;
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
    }

    public int[] mySolution(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Collections.reverse(list);
        return list.stream().filter(integer -> integer != null).mapToInt(i->i).toArray();
    }
}
