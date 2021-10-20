package main.java.com.exercise.week_038;

/**
 * @Author cl
 * @Date 2021/10/18 21:07
 * @Version 1.0
 */
public class LeetCode_476_1_数字的补数 {
    public int findComplement(int num) {
        int x = 0;
        for (int i = num; i != 0 ; i -= i & -i) {
            x = i;
        }
        return ~num & (x - 1);
    }
}
