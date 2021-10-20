package main.java.com.exercise.week_038;

/**
 * @Author cl
 * @Date 2021/10/18 21:17
 * @Version 1.0
 */
public class LeetCode_476_2_数字的补数 {
    public static int findComplement(int num) {
        int index = 0;
        int ans = 0;
        for (int i = 31; i >= 0 ; i--) {
            if (((num >> i) & 1) == 1) {
                index = i;
                break;
            }
        }
        for (int i = index - 1; i >= 0; i--) {
            if (((num >> i) & 1) == 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

}
