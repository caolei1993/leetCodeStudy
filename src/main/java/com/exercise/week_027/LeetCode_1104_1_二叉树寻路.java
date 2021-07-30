package main.java.com.exercise.week_027;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/7/29 17:20
 * @Version 1.0
 */
public class LeetCode_1104_1_二叉树寻路 {
    public List<Integer> pathInZigZagTree(int label) {
        int row = 1, rowStart = 1;
        while (rowStart * 2 <= label) {
            row++;
            rowStart *= 2;
        }
        if (row % 2 == 0) {
            label = getReverse(row, label);
        }
        List<Integer> ans = new ArrayList<>();

        while (row > 0) {
            if (row % 2 == 0) {
                ans.add(getReverse(row, label));
            } else {
                ans.add(label);
            }
            row--;
            label >>= 1;
        }
        Collections.reverse(ans);
        return ans;
    }

    private int getReverse(int row, int label) {
        return (1 << row - 1) + (1 << row) - 1 - label;
    }
}
