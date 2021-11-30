package main.java.com.exercise.week_040;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode_559_1_N叉树的最大深度 {
    public int maxDepth(Node root) {
        int size = 1;
        int res = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            size--;
            for (Node n : node.children) {
                queue.offer(n);
            }
            if (size == 0) {
                size = queue.size();
                res++;
            }
        }
        return res;
    }
}
