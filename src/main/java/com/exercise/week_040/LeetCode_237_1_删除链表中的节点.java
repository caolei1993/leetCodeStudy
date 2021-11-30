package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/2 9:37
 * @Version 1.0
 */
public class LeetCode_237_1_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
