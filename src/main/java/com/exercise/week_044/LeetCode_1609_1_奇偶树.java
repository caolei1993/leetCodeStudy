package main.java.com.exercise.week_044;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/25 10:30 下午
 */
public class LeetCode_1609_1_奇偶树 {
    public boolean isEvenOddTree(TreeNode root) {
        List<List<Integer>> list = levelOrder(root);
        for (int i = 0; i < list.size(); i++) {
            List<Integer> levelList = list.get(i);
            if (i % 2 == 0) {
                // 偶数层
                int pre = 0;
                for (int j = 0; j < levelList.size(); j++) {
                    if (j > 0 && levelList.get(j) <= pre) {
                        return false;
                    }
                    if (levelList.get(j) % 2 == 0) {
                        return false;
                    }
                    pre = levelList.get(j);
                }
            } else {
                // 奇数层
                int pre = 0;
                for (int j = 0; j < levelList.size(); j++) {
                    if (j > 0 && levelList.get(j) >= pre) {
                        return false;
                    }
                    if (levelList.get(j) % 2 == 1) {
                        return false;
                    }
                    pre = levelList.get(j);
                }
            }
        }
        return true;
    }

    private List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 1;
        queue.offer(root);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            levelList.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                size = queue.size();
                list.add(levelList);
                levelList = new ArrayList<Integer>();
            }
        }
        return list;
    }
}
