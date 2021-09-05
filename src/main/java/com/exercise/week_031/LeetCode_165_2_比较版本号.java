package main.java.com.exercise.week_031;

/**
 * @Author cl
 * @Date 2021/9/1 19:26
 * @Version 1.0
 */
public class LeetCode_165_2_比较版本号 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int l1 = v1.length;
        int l2 = v2.length;
        int i = 0, j = 0;
        while (i < l1 || j < l2) {
            int a = 0, b = 0;
            if (i < l1) {
                a = Integer.parseInt(v1[i++]);
            }
            if (j < l2) {
                b = Integer.parseInt(v2[j++]);
            }
            if (a != b) {
                return a > b ? 1 : -1;
            }
        }
        return 0;
    }
}
