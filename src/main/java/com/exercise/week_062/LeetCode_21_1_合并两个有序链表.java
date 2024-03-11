package main.java.com.exercise.week_062;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/8 23:14
 */
public class LeetCode_21_1_合并两个有序链表 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ans = new ListNode(0);
        ListNode head = ans;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                int val1 = list1.val;
                int val2 = list2.val;
                if (val1 <= val2) {
                    ans.next = new ListNode(val1);
                    list1 = list1.next;
                } else {
                    ans.next = new ListNode(val2);
                    list2 = list2.next;
                }
            } else if (list1 == null){
                ans.next = new ListNode(list2.val);
                list2 = list2.next;
            } else {
                ans.next = new ListNode(list1.val);
                list1 = list1.next;
            }
            ans = ans.next;
        }
        return head.next;
    }
}
