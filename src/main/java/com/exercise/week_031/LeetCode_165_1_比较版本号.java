package main.java.com.exercise.week_031;

/**
 * @Author cl
 * @Date 2021/9/1 17:01
 * @Version 1.0
 */
public class LeetCode_165_1_比较版本号 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int l1 = v1.length;
        int l2 = v2.length;
        if (l1 >= l2) {
            int count = l1 - l2;
            int ans = 0;
            for (int i = 0; i < l2; i++) {
                int i1 = Integer.parseInt(v1[i]);
                int i2 = Integer.parseInt(v2[i]);
                if (i1 > i2) {
                    return 1;
                } else if (i1 < i2) {
                    return -1;
                } else {
                    continue;
                }
            }
            for (int i = l2; i < l1; i++) {
                int i1 = Integer.parseInt(v1[i]);
                if (i1 == 0) {
                    continue;
                } else {
                    return 1;
                }
            }
            return ans;
        } else {
            int count = l2 - l1;
            int ans = 0;
            for (int i = 0; i < l1; i++) {
                int i1 = Integer.parseInt(v1[i]);
                int i2 = Integer.parseInt(v2[i]);
                if (i1 > i2) {
                    return 1;
                } else if (i1 < i2) {
                    return -1;
                } else {
                    continue;
                }
            }
            for (int i = l1; i < l2; i++) {
                int i2 = Integer.parseInt(v2[i]);
                if (i2 != 0) {
                    return -1;
                }
            }
            return ans;
        }
    }
}
