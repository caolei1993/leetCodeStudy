package main.java.com.exercise.week_024;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/7/6 13:56
 * @Version 1.0
 */
public class LeetCode_1418_1_点菜展示表 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> ans = new ArrayList<>();
        // 用与保存每个桌号的各个菜品及数量， 桌号：（菜品：数量）
        Map<Integer, Map<String, Integer>> map = new HashMap<>();
        // 保存所有菜品名称，用来构建title
        Set<String> set = new HashSet<>();
        for (List<String> order : orders) {
            String c = order.get(0);
            int t = Integer.parseInt(order.get(1));
            String f = order.get(2);
            // 添加食品名称
            set.add(f);
            Map<String, Integer> m = map.getOrDefault(t, new HashMap<>());
            m.put(f, m.getOrDefault(f, 0) + 1);
            map.put(t, m);
        }
        List<String> list = new ArrayList<>(set);
        // 对菜名排序
        Collections.sort(list);
        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(list);
        ans.add(title);

        // 对桌号进行排序
        List<Integer> tables = new ArrayList<>(map.keySet());
        Collections.sort(tables);
        for (Integer table : tables) {
            List<String> menu = new ArrayList<>();
            menu.add(String.valueOf(table));
            Map<String, Integer> food = map.get(table);
            for (String ff : list) {
                menu.add(food.getOrDefault(ff, 0) + "");
            }
            ans.add(menu);
        }
        return ans;
    }
}
