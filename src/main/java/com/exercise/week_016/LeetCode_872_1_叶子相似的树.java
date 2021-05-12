package main.java.com.exercise.week_016;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/5/10 9:34
 * @Version 1.0
 * https://leetcode-cn.com/problems/leaf-similar-trees/
 */
public class LeetCode_872_1_叶子相似的树 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        preOrder(root1, list1);
        preOrder(root2, list2);
        int n1 = list1.size();
        int n2 = list2.size();
        if (n1 != n2) {
            return false;
        }
        for (int i = 0; i < n1; i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
    public void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
        preOrder(node.left, list);
        preOrder(node.right, list);
    }
}