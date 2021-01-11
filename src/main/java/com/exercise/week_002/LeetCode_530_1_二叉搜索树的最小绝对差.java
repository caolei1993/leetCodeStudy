package main.java.com.exercise.week_002;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author cl
 * @Date 2021/1/11 15:35
 * @Version 1.0
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 */
public class LeetCode_530_1_二叉搜索树的最小绝对差 {

    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, node -> {
            list.add(node.val);
        });
        int ans = Integer.MAX_VALUE;
        for (int i=1; i<list.size(); i++) {
            ans = Math.min(ans, list.get(i)-list.get(i-1));
        }
        return ans;
    }

    /**
     * 中序遍历
     * @param root
     * @param consumer
     */
    public void inorder(TreeNode root, Consumer<TreeNode> consumer) {
        if (root == null || consumer == null) {
            return;
        }
        inorder(root.left, consumer);
        consumer.accept(root);
        inorder(root.right, consumer);
    }

}
