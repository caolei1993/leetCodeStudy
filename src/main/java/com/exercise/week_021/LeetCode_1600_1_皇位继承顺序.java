package main.java.com.exercise.week_021;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/6/21 17:47
 * @Version 1.0
 */
public class LeetCode_1600_1_皇位继承顺序 {
    Map<String, List<String>> family;
    Set<String> dead;
    String kingName;

    public LeetCode_1600_1_皇位继承顺序(String kingName) {
        family = new HashMap<>();
        dead = new HashSet<>();
        this.kingName = kingName;
    }

    public void birth(String parentName, String childName) {
        List<String> chirdren = family.getOrDefault(parentName, new ArrayList<>());
        chirdren.add(childName);
        family.put(parentName, chirdren);
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<>();
        preorder(kingName, ans);
        return ans;
    }

    private void preorder(String name, List<String> ans) {
        if (!dead.contains(name)) {
            ans.add(name);
        }
        List<String> children = family.getOrDefault(name, new ArrayList<>());
        for (String child : children) {
            preorder(child, ans);
        }
    }
}
