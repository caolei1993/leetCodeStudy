package main.java.com.exercise.week_016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cl
 * @Date 2021/5/11 9:24
 * @Version 1.0
 * https://leetcode-cn.com/problems/decode-xored-permutation/
 */
public class LeetCode_1734_1_解码异或后的排列 {
    public static int[] decode(int[] encoded) {
        int n = encoded.length;
        int[] ans = new int[n + 1];
        List<Integer> list = new ArrayList<>(n + 1);
        for (int i = 1; i <= n + 1 ; i++) {
            list.add(i);
        }
        // 遍历首个元素可能的值
        for (int i = 1; i <= n + 1 ; i++) {
            ans[0] = i;
            for (int j = 1; j < n + 1; j++) {
                ans[j] = ans[j - 1] ^ encoded[j - 1];
            }
            System.out.println(Arrays.toString(ans));
            List<Integer> list1 = Arrays.stream(ans).boxed().collect(Collectors.toList());
            if (list1.containsAll(list)){
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] encoded = new int[]{6,5,4,6};
        System.out.println(Arrays.toString(decode(encoded)));
    }
}
