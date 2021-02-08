package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/8 16:58
 * @Version 1.0
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class LeetCode_876_1_链表的中间结点 {

    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode node = head;
        while(node != null) {
            node = node.next;
            size++;
        }
        int mid = (int)Math.floor(size / 2.0);
        while (mid != 0) {
            head = head.next;
            mid--;
        }
        return head;
    }
}
