package main.java.com.exercise.week_043;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/6 5:45 下午
 */
public class LeetCode_1816_1_截断句子 {
    public String truncateSentence(String s, int k) {
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder(ss[0]);
        int index = 1;
        while(--k > 0) {
            sb.append(" ").append(ss[index++]);
        }
        return sb.toString();
    }
}
