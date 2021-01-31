package main.java.com.exercise.week_004;

/**
 * @Author cl
 * @Date 2021/1/31 14:11
 * @Version 1.0
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 */
public class LeetCode_559_1_N叉树的最大深度 {
    public int maxDepth(Node root) {
        int ans = 0;
        if (root == null) {
            return ans;
        }
        if (root.children.isEmpty()) {
            return 1;
        }
        for (Node child : root.children) {
            ans = Math.max(ans, (maxDepth(child) + 1));
        }
        return ans;
    }
}
