package main.java.com.exercise.week_005;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/2/5 15:31
 * @Version 1.0
 */
public class LeetCode_141_2_环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
