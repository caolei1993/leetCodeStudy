package main.java.com.exercise.week_014;

import java.util.List;

/**
 * @Author cl
 * @Date 2021/5/5 21:09
 * @Version 1.0
 */
public class Employee {
    public int id;
    public int importance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public List<Integer> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Integer> subordinates) {
        this.subordinates = subordinates;
    }

    public List<Integer> subordinates;
}
