package main.java.com.exercise.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/17 20:28
 * @Version 1.0
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 */
public class LeetCode_99_恢复二叉搜索树 {
    public void recoverTree(TreeNode root) {
        List<Integer> list = inorder(root, new ArrayList<>());
        int[] arr = findTwoSwapped(list);
        recover(root, 2 , arr[0], arr[1]);
    }

    public int[] findTwoSwapped(List<Integer> list) {
        int size = list.size();
        int x = -1;
        int y = -1;
        for (int i = 1; i < size; i++) {
            if (list.get(i - 1) > list.get(i)) {
                y = list.get(i);
                if (x == -1) {
                    x = list.get(i - 1);
                } else {
                    break;
                }
            }
        }
        return new int[]{x, y};
    }

    public void recover(TreeNode root, int count, int x, int y) {
        if (root == null || count == 0) {
            return;
        }
        if (root.val == x || root.val == y) {
            root.val = root.val == x ? y : x;
            count--;
        }
        recover(root.left, count, x, y);
        recover(root.right, count, x , y);
    }

    public List<Integer> inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
        return list;
    }
}
