package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/4 16:13
 * @Version 1.0
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class LeetCode_206_1_反转链表 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        ListNode lastNode = head.next;
        lastNode.next = head;
        head.next = null;
        return newHead;
    }
}
