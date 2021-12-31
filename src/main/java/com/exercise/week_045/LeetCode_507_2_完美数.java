package main.java.com.exercise.week_045;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/31 9:13 上午
 */
public class LeetCode_507_2_完美数 {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                sum += i + num / i;
            }
        }
        return num == sum;
    }
}
