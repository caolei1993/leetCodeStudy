package main.java.com.exercise.week_047;

import java.util.Random;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/16 10:19 下午
 */
public class LeetCode_382_2_链表的随机节点 {
    ListNode node;
    Random random = new Random();
    public LeetCode_382_2_链表的随机节点(ListNode head) {
        node = head;
    }

    public int getRandom() {
        int i = 1;
        int ans = 0;
        ListNode n = node;
        while (n != null) {
            if (random.nextInt(i) == 0) {
                ans = n.val;
            }
            i++;
            n = n.next;
        }
        return ans;
    }
}
