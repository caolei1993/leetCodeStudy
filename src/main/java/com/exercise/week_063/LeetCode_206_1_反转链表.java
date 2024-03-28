package main.java.com.exercise.week_063;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/21 20:38
 */
public class LeetCode_206_1_反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
