package main.java.com.exercise.week_014;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/5/5 21:05
 * @Version 1.0
 */
public class LeetCode_690_2_员工的重要性 {
    Map<Integer, Employee> map = new HashMap<>();
    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        Deque<Employee> deque = new ArrayDeque<>();
        deque.addLast(map.get(id));
        while (!deque.isEmpty()) {
            Employee employee = deque.pollFirst();
            ans += employee.importance;
            for (Integer subId : employee.subordinates) {
                deque.addLast(map.get(subId));
            }
        }
        return ans;
    }

}
