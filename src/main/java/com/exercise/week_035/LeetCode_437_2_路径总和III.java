package main.java.com.exercise.week_035;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/9/28 9:37
 * @Version 1.0
 */
public class LeetCode_437_2_路径总和III {
    Map<Integer, Integer> map = new HashMap<>();
    int ans, target;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        target = targetSum;
        map.put(0, 1);
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int val) {
        if (map.containsKey(val - target)) {
            ans += map.get(val - target);
        }
        map.put(val, map.getOrDefault(val, 0) + 1);
        if (root.left != null) {
            dfs(root.left, val + root.left.val);
        }
        if (root.right != null) {
            dfs(root.right, val + root.right.val);
        }
        map.put(val, map.get(val) - 1);
    }
}
