package main.java.com.exercise.week_014;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cl
 * @Date 2021/5/1 11:57
 * @Version 1.0
 */
public class LeetCode_690_1_员工的重要性 {

    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;
        List<Employee> list = employees.stream().filter(e -> e.id == id).collect(Collectors.toList());
        if (!list.isEmpty()) {
            Employee employee = list.get(0);
            ans += employee.importance;
            List<Employee> employeeList = employees.stream().filter(e -> employee.subordinates.contains(e.id)).collect(Collectors.toList());
            for (Employee e : employeeList) {
                ans += getImportance(employees, e.id);
            }
        }
        return ans;
    }
}
