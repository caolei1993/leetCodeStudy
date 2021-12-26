package main.java.com.exercise.week_044;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/25 10:36 下午
 */
public class LeetCode_1609_2_奇偶数 {
    public boolean isEvenOddTree(TreeNode root) {
        boolean flag = true;
        Queue<TreeNode> queue = new LinkedList<>();
        int size, pre;
        queue.offer(root);
        while (!queue.isEmpty()) {
            size = queue.size();
            pre = flag ? 0 : 0x3f3f3f3f;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (flag) {
                    if (node.val <= pre || node.val % 2 == 0) {
                        return false;
                    }
                } else {
                    if (node.val >= pre || node.val % 2 == 1) {
                        return false;
                    }
                }
                pre = node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            flag = !flag;
        }
        return true;
    }
}
