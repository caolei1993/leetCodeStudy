package main.java.com.exercise.week_038;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/10/24 12:07
 * @Version 1.0
 */
public class LeetCode_638_1_大礼包 {
    int ans = 0x3f3f3f3f;
    List<Integer> price1, needs1;
    List<List<Integer>> special1;
    Map<Integer, Integer> map = new HashMap<>();
    int n, m;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        price1 = price;
        needs1 = needs;
        special1 = special;
        // 产品数量为n
        n = price.size();
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tmp.add(0);
        }
        for (int i = 0; i < n; i++) {
            List<Integer> clone = new ArrayList<>(tmp);
            clone.set(i, 1);
            clone.add(price.get(i));
            special.add(clone);
        }
        // 所有大礼包的数量
        m = special.size();
        for (int i = 0; i < m; i++) {
            int max = 0;
            List<Integer> s = special.get(i);
            for (int j = 0; j < n; j++) {
                int a = needs.get(j);
                int b = s.get(j);
                if (a == 0 || b == 0) {
                    continue;
                }
                max = Math.max(max, (int) Math.ceil(a / b));
            }
            map.put(i, max);
        }
        dfs(0, needs, 0);
        return ans;
    }

    /**
     * @param u     当前遍历的大礼包的下标
     * @param list 需求集合
     * @param cur   当前花费的金额
     */
    private void dfs(int u, List<Integer> list, int cur) {
        if (cur >= ans) {
            return;
        }
        // 如果当前已经遍历到最后一个大礼包
        if (u == m) {
            for (int i = 0; i < n; i++) {
                if (list.get(i) != 0) {
                    return;
                }
            }
            ans = Math.min(ans, cur);
            return;
        }
        List<Integer> s = special1.get(u);
        // 按最大次数遍历当前大礼包
        out:
        for (int k = 0; k <= map.get(u); k++) {
            List<Integer> cList = new ArrayList<>(list);
            for (int i = 0; i < n; i++) {
                // 大礼包中包含i的个数
                int a = s.get(i);
                // 需要的i的个数
                int b = cList.get(i);
                if (a * k > b) {
                    break out;
                }
                cList.set(i, b - a * k);
            }
            dfs(u + 1, cList, cur + s.get(n) * k);
        }
    }
}
