package main.java.com.exercise.week_031;

/**
 * @Author cl
 * @Date 2021/9/2 8:38
 * @Version 1.0
 */
public class LeetCode_剑指Offer22_1_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        int count = len - k;
        while (count > 0) {
            head = head.next;
            count--;
        }
        return head;
    }
}
