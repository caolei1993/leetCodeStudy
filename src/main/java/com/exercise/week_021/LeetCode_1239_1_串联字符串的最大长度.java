package main.java.com.exercise.week_021;

import java.util.List;

/**
 * @Author cl
 * @Date 2021/6/21 17:33
 * @Version 1.0
 */
public class LeetCode_1239_1_串联字符串的最大长度 {
    public int maxLength(List<String> arr) {
        return dfs(arr, 0, 0);
    }

    /**
     * dfs函数
     *
     * @param arr    目标数组
     * @param index  遍历的坐标
     * @param bitMap 当前字符串包含的字符，用26位二进制表示（0代表无此字符，1代表有此字符），转化为了int传输
     * @return
     */
    private int dfs(List<String> arr, int index, int bitMap) {
        // 遍历结束后返回0，递归结束条件
        if (index == arr.size()) {
            return 0;
        }
        // 获取目标子字符串
        String s = arr.get(index);
        // 不选index位置的字符串
        int p1 = dfs(arr, index+1, bitMap);

        // 选择index位置的字符串（是否符合选择的条件）
        int p2 = 0;
        boolean can = true;
        for (char c : s.toCharArray()) {
            // 判断该字符是否已经包含
            if ((bitMap & (1 << c-'a')) != 0) {
                can = false;
                break;
            } else {
                // 修改该字符标记位，代表已存在
                bitMap |= 1 << c-'a';
            }
        }
        if (can) {
            p2 = s.length() + dfs(arr, index+1, bitMap);
        }
        return Math.max(p1, p2);
    }
}
