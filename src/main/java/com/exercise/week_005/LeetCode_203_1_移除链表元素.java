package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/8 17:09
 * @Version 1.0
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class LeetCode_203_1_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(0, head);
        ListNode curr = head;
        ListNode pre = node;
        while(curr != null) {
            if (curr.val == val) {
               pre.next = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }
        return node.next;
    }
}
