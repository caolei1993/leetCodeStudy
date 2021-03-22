package main.java.com.exercise.week_008;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/3/8 20:09
 * @Version 1.0
 */
public class LeetCode_199_1_二叉树的右视图 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        // 每层元素个数
        int size = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (size == 1) {
                list.add(root.val);
            }
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                size = queue.size();
            }
        }
        return list;
    }
}
