package main.java.com.exercise.week_004;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/27 21:13
 * @Version 1.0
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
public class LeetCode_589_1_N叉树的前序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        perorder(root);
        return list;
    }
    public void perorder (Node node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        for (Node node1: node.children) {
            preorder(node1);
        }
    }
}
