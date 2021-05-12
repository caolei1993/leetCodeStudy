package main.java.com.exercise.week_015;

/**
 * @Author cl
 * @Date 2021/5/6 21:04
 * @Version 1.0
 * https://leetcode-cn.com/problems/decode-xored-array/
 */
public class LeetCode_1720_1_解码异或后的数组 {
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] arr = new int[n + 1];
        arr[0] = first;
        for (int i = 1; i < n + 1; i++) {
            arr[i] = encoded[i - 1] ^ arr[i - 1];
        }
        return arr;
    }
}
