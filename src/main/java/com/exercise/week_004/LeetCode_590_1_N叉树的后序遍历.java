package main.java.com.exercise.week_004;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/31 13:41
 * @Version 1.0
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 */
public class LeetCode_590_1_N叉树的后序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        nodePostorder(root);
        return list;
    }

    public void nodePostorder(Node node) {
        if (node == null) {
            return;
        }
        for (Node child : node.children) {
            nodePostorder(child);
        }
        list.add(node.val);
    }

}
