package main.java.com.exercise.week_063;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/28 19:32
 */
public class LeetCode_82_1_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummyNode = new ListNode(0, head);
        ListNode pre = dummyNode;
        int preVal = head.val;
        while (head.next != null) {
            if (head.next.val == preVal) {
                pre.next = head.next.next;
                head = pre;
            } else {
                pre = head;
                head = head.next;
                preVal = head.val;

            }
        }
        return dummyNode.next;
    }
}
