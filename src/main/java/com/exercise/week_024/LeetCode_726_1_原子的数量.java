package main.java.com.exercise.week_024;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/7/5 21:13
 * @Version 1.0
 */
public class LeetCode_726_1_原子的数量 {
    static class Node {
        String s;
        int v;
        public Node (String s, int v) {
            this.s = s;
            this.v = v;
        }

    }
    public String countOfAtoms(String formula) {
        char[] chars = formula.toCharArray();
        int n = chars.length;
        Map<String, Integer> map = new HashMap<>();
        Stack<String> stack = new Stack<>();
        int idx = 0;
        for (int i = 0; i < n; ) {
            char c = chars[i];
            if (c == '(' || c == ')') {
                stack.push(String.valueOf(c));
                i++;
            } else {
                if (Character.isDigit(c)) {
                    int j = i + 1;
                    while (j < n && Character.isDigit(chars[j])) {
                        j++;
                    }
                    String numStr = formula.substring(i, j);
                    i = j;
                    int num = Integer.parseInt(numStr);
                    if (!stack.isEmpty() && stack.peek().equals(")")) {
                        // 弹出')'
                        stack.pop();
                        List<String> tmp = new ArrayList<>();
                        while (!stack.isEmpty() && !stack.peek().equals("(")) {
                            String s = stack.pop();
                            map.put(s, map.getOrDefault(s, 1) * num);
                            tmp.add(s);
                        }
                        // 弹出'('
                        stack.pop();
                        // 将原子放回，防止有多重括号
                        for (int k = tmp.size() - 1; k >= 0 ; k--) {
                            stack.push(tmp.get(k));
                        }
                    } else {
                        // 栈顶不是')'，只需要处理单个原子
                        String s = stack.pop();
                        map.put(s, map.getOrDefault(s, 1) * num);
                        stack.push(s);
                    }
                } else {
                    // 不是数字，是字母，获取完整的原子名
                    int j = i + 1;
                    while (j < n && Character.isLowerCase(chars[j])) {
                        j++;
                    }
                    String cur = formula.substring(i, j) + "_" + idx++;
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                    i = j;
                    stack.push(cur);
                }
            }
        }

        // 将不同编号的相同原子进行合并
        Map<String, Node> mm = new HashMap<>();
        for (String cur : map.keySet()) {
            String key = cur.split("_")[0];
            int value = map.get(cur);
            Node node = null;
            if (mm.containsKey(key)) {
                node = mm.get(key);
            } else {
                node = new Node(key, 0);
            }
            node.v += value;
            mm.put(key, node);
        }

        PriorityQueue<Node> q = new PriorityQueue<>((a , b) -> a.s.compareTo(b.s));
        for (String k : mm.keySet()) {
            q.offer(mm.get(k));
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Node nd = q.poll();
            sb.append(nd.s);
            if (nd.v > 1) {
                sb.append(nd.v);
            }
        }
        return sb.toString();
    }
}
