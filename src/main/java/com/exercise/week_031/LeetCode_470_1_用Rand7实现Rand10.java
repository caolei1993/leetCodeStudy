package main.java.com.exercise.week_031;

/**
 * @Author cl
 * @Date 2021/9/5 20:44
 * @Version 1.0
 */
public class LeetCode_470_1_用Rand7实现Rand10 {
    public int rand10() {
        while (true) {
            int ans = (rand7() - 1) * 7 + (rand7() - 1);
            if (1 <= ans && ans <= 10) {
                return ans;
            }
        }
    }
    public int rand7() {
        return 1;
    }
}
