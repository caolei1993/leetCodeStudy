package main.java.com.exercise.week_063;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/4/1 19:46
 */
public class LeetCode_61_1_旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            n++;
        }
        int size = n - k % n;
        if (size == n) {
            return head;
        }
        node.next = head;
        while (size > 0) {
            node = node.next;
            size--;
        }
        ListNode ret = node.next;
        node.next = null;
        return ret;
    }
}
