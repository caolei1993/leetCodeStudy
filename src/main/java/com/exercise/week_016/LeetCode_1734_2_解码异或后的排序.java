package main.java.com.exercise.week_016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cl
 * @Date 2021/5/11 21:29
 * @Version 1.0
 */
public class LeetCode_1734_2_解码异或后的排序 {
    public int[] decode(int[] encoded) {
        int n = encoded.length;
        int[] ans = new int[n + 1];
        // a = ans[0] ^ ans[1] ^ ans[2] .... ^ ans[n]
        int a = 0;
        for (int i = 1; i <= n + 1 ; i++) {
            a = a ^ i;
        }
        // b = ans[0] ^ ans[1] ^ ans[2] ..... ^ ans[n - 1]
        int b = 0;
        for (int i = 0; i < n - 1 ; i += 2) {
            b = b ^ encoded[i];
        }
        ans[n] = a ^ b;
        for (int i = n - 1; i >= 0  ; i--) {
            ans[i] = ans[i + 1] ^ encoded[i];
        }
        return ans;
    }
}
