package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/6 21:55
 * @Version 1.0
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class LeetCode_83_1_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            if (node.next.val == node.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }
}
