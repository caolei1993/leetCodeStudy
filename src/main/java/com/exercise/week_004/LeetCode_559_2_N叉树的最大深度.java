package main.java.com.exercise.week_004;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/1/31 14:34
 * @Version 1.0
 */
public class LeetCode_559_2_N叉树的最大深度 {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        // 每一层元素个数
        int size = 1;
        int ans = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            size--;
            for (Node child : node.children) {
                queue.offer(child);
            }
            if (size == 0) {
                size = queue.size();
                ans++;
            }
        }
        return ans;
    }
}
