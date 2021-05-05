package main.java.com.exercise.week_009;

/**
 * @Author cl
 * @Date 2021/3/25 20:05
 * @Version 1.0
 */
public class LeetCode_82_2_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
       if (head == null || head.next == null) {
           return head;
       }
       if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
       } else {
           ListNode move = head.next;
           while (move != null && head.val == move.val){
               move = move.next;
           }
           return deleteDuplicates(move);
       }
        return head;
    }
}
