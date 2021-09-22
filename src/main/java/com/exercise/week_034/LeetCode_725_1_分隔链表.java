package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/22 9:18
 * @Version 1.0
 */
public class LeetCode_725_1_分隔链表 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }
        int per = n / k;
        int count = 0;
        if (per * k != n) {
            count = n - per * k;
        }
        ListNode[] ans = new ListNode[k];
        ListNode pre;
        for (int i = 0; i < k; i++) {
            int v = per;
            if (count > 0) {
                v = v + 1;
                count--;
            }
            ans[i] = head;
            while (v > 0) {
                pre = head;
                head = head.next;
                v--;
                if (v == 0) {
                    pre.next = null;
                }
            }
        }
        return ans;
    }
}
