package main.java.com.exercise.week_062;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/7 19:15
 */
public class LeetCode_2_1_两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode head = node;
        int val = 0;
        while (l1 != null || l2 != null) {
            int a = 0, b = 0;
            if (l1 != null) {
                a = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                b = l2.val;
                l2 = l2.next;
            }
            int sum = a + b + val;
            val = sum / 10;
            node.next = new ListNode(sum % 10);
            node = node.next;
        }
        if (val == 1) {
            node.next = new ListNode(1);
        }
        return head.next;
    }
}
