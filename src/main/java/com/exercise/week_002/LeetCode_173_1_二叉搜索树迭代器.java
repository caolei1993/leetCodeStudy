package main.java.com.exercise.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/17 15:11
 * @Version 1.0
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 */
public class LeetCode_173_1_二叉搜索树迭代器 {
    int index = 0;
    List<TreeNode> list = new ArrayList<>();
    public LeetCode_173_1_二叉搜索树迭代器(TreeNode root) {
        inorder(root);
    }

    public int next() {
        int ans = list.get(index).val;
        index++;
        return ans;
    }

    public boolean hasNext() {
        return index < list.size();
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root);
        inorder(root.right);
    }
}
