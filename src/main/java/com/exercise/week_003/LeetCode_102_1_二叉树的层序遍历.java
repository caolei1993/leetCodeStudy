package main.java.com.exercise.week_003;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/1/21 16:48
 * @Version 1.0
 *https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class LeetCode_102_1_二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 1;

        List<Integer> level = new ArrayList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            level.add(node.val);
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                size = queue.size();
                list.add(level);
                level = new ArrayList<>();
            }
        }
        return list;
    }
}
