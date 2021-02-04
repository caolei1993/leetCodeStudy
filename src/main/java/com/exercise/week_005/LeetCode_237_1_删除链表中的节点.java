package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/4 15:26
 * @Version 1.0
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class LeetCode_237_1_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
