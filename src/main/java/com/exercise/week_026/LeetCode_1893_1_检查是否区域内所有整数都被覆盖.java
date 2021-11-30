package main.java.com.exercise.week_026;

/**
 * @Author cl
 * @Date 2021/7/30 16:03
 * @Version 1.0
 */
public class LeetCode_1893_1_检查是否区域内所有整数都被覆盖 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        for (int i = left; i <= right ; i++) {
            boolean flag = false;
            for (int[] range : ranges) {
                int a = range[0], b = range[1];
                if (i >= a && i <= b) {
                    flag =  true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}
