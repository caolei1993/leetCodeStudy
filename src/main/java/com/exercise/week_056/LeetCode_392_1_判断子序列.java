package main.java.com.exercise.week_056;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/4 19:34
 */
public class LeetCode_392_1_判断子序列 {
    public boolean isSubsequence(String s, String t) {
        char[] arrS = s.toCharArray();
        if (s.length() == 0) {
            return true;
        }
        char[] arrT = t.toCharArray();
        int j = 0;
        for (int i = 0; i < arrS.length; i++) {
            for ( ; j < arrT.length; j++) {
                if (arrS[i] == arrT[j]) {
                    if (i == arrS.length - 1) {
                        return true;
                    }
                    j++;
                    break;
                }
                if (j == arrT.length - 1) {
                    return false;
                }
            }
        }
        return false;
    }
}
