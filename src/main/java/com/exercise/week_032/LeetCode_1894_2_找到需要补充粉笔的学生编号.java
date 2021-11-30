package main.java.com.exercise.week_032;

/**
 * @Author cl
 * @Date 2021/9/10 17:02
 * @Version 1.0
 */
public class LeetCode_1894_2_找到需要补充粉笔的学生编号 {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        long max = 0;
        for (int i : chalk) {
            max += i;
        }
        k %= max;
        for (int i = 0; i < len; i++) {
            k -= chalk[i];
            if (k < 0) {
                return i;
            }
        }
        return -1;
    }
}
