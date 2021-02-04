package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/4 16:39
 * @Version 1.0
 */
public class LeetCode_206_2_反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            // 临时存入当前节点的下一个
            ListNode tep = curr.next;
            // 当前节点的next指向前一个
            curr.next = pre;
            // 更新前一个节点
            pre = curr;
            // 更新当前节点
            curr = tep;
        }
        return pre;
    }
}
