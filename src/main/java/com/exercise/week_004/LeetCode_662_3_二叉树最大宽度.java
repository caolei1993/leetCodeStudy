package main.java.com.exercise.week_004;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/1/26 22:03
 * @Version 1.0
 */
public class LeetCode_662_3_二叉树最大宽度 {
    int ans = 0;
    Map<Integer, Integer> map = new HashMap<>();
    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0 ,0);
        return ans;
    }
    public void dfs (TreeNode node, int depth, int position) {
        if (node == null) {
            return;
        }
        map.computeIfAbsent(depth, x -> position);
        ans = Math.max(ans, position - map.get(depth) + 1);
        dfs(node.left, depth + 1, position * 2);
        dfs(node.right, depth +1, position * 2 + 1);
    }
}
