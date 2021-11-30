[toc]
# [LeetCode_726_1_原子的数量](https://leetcode-cn.com/problems/number-of-atoms/)
## 题目
给定一个化学式formula（作为字符串），返回每种原子的数量。

原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。

如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。

两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。

一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。

给定一个化学式 formula ，返回所有原子的数量。格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。

示例 1：
```
输入：formula = "H2O"
输出："H2O"
解释：
原子的数量是 {'H': 2, 'O': 1}。
```

示例 2：
```
输入：formula = "Mg(OH)2"
输出："H2MgO2"
解释： 
原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
```

示例 3：
```
输入：formula = "K4(ON(SO3)2)2"
输出："K4N2O14S4"
解释：
原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
```

示例 4：
```
输入：formula = "Be32"
输出："Be32"
```

提示：

* 1 <= formula.length <= 1000
* formula 由小写英文字母、数字 '(' 和 ')' 组成。
* formula 是有效的化学式。

## 理解
1. 处理过程中始终维护一个哈希表，哈希表map中维护着某个原子以及其对应的数量，因为同一个原子可能在
不同位置出现，在这里我们在预先处理的时候可以给原子加一个数字后缀用以区分，防止处理（）中的原子乘
某个数字的时候多算了原子数量。  
2. 根据当前处理到的字符分情况讨论。  
* 遇到'(',')'符号直接入栈
* 原子：继续往后取，直到取到完整的原子名称，将原子入栈，同时在map中记数+1。
* 数字：继续往后取，直到渠道完整的数字，并看栈顶是否为')'，如果是反括号则代表该数字需要作用在括号
内的所有原子上，如果不是只作用于栈顶原子。（需要注意这里处理完括号内的原子后，还需继续放回栈内，
防止多重括号的情况）
3. 处理完后对map中的原子做合并操作，相同的原子合并在一起
4. 使用优先队列对原子进行字典排序，并按要求构造答案返回

### 代码
```java
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

```

# [LeetCode_1418_1_点菜展示表](https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant/)
## 题目
给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。

请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。

注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。

 

示例 1：
```
输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]] 
解释：
点菜展示表如下所示：
Table,Beef Burrito,Ceviche,Fried Chicken,Water
3    ,0           ,2      ,1            ,0
5    ,0           ,1      ,0            ,1
10   ,1           ,0      ,0            ,0
对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
餐桌 10：Corina 点了 "Beef Burrito" 
```

示例 2：
```
输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]] 
解释：
对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
```

示例 3：
```
输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
```

提示：

* 1 <= orders.length <= 5 * 10^4
* orders[i].length == 3
* 1 <= customerNamei.length, foodItemi.length <= 20
* customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
* tableNumberi 是 1 到 500 范围内的整数。

## 理解
分析题意，最终结果由两部分组成
* "Table" + 排序去重的菜品
* 桌号 + 当前桌每个菜品的数量  

可以直观的看出利用Set来存储title栏，利用Map来存储内容栏，遍历所有的订单，将菜品和内容初始化到我们
预设的数据结构中，在利用集合类提供的排序方法进行排序，Collections.sort()。最终按给定的结果拼接返回
即可。

### 代码
```java
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
```
# [LeetCode_1711_1_大餐计数](https://leetcode-cn.com/problems/count-good-meals/)
## 题目
大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。

你可以搭配 任意 两道餐品做一顿大餐。

给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。

注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。

示例 1：
```
输入：deliciousness = [1,3,5,7,9]
输出：4
解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
```

示例 2：
```
输入：deliciousness = [1,1,1,3,3,3,7]
输出：15
解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
```

提示：

* 1 <= deliciousness.length <= 10^5
* 0 <= deliciousness[i] <= 2^20

## 理解
解法一与解法二的区别在与解法二使用map在遍历的过程中将遍历过的值存了起来，优化部分重复计算，整体时间
复杂度都在O（n^2），使用lowbit来判断是否是2的幂，leetCode提交时计算均会超时。

解法三：同样是使用Map记录了所有的值及出现次数，从题意知道最大值为2^20，两数相加最大也就2^21，所以
我们可以遍历map的key，即所有的数，在符合条件的2的幂的结果中找与遍历值的差值，看是否存在于map中，
如果存在再根据两两匹配原则，得到该方案的大餐数为原值数量与差值数量的乘积，当原值和差值相等时，计算
大餐数量需要排除自己与自己的方案，最终根据互斥原则对总方案除2。

解法四：遍历的过程中将遍历过的数值及数量存入map中，在遍历每一道餐时，再遍历对应的2的幂次，求取2的幂次
与其美味程度的差值，再从map中找是否有与其对应的餐的存在，并累计结果，每次求取完都对1e9 + 7取余。

## 解法一
### 代码
```java
public class LeetCode_1711_1_大餐计数 {
    public int countPairs(int[] deliciousness) {
        int mod = (int) 1e9 + 7;
        long ans = 0;
        int len = deliciousness.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int v = deliciousness[i] + deliciousness[j];
                if (v == lowbit(v)) {
                    ans++;
                }
            }
        }
        return (int)(ans % mod);
    }
    private int lowbit (int n) {
        return n & -n;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1711_2_大餐计数 {
    public int countPairs(int[] deliciousness) {
        int mod = (int) 1e9 + 7;
        long ans = 0;
        int len = deliciousness.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int other : map.keySet()) {
                if (check(deliciousness[i] + other)) {
                    ans += map.get(other);
                }
            }
            map.put(deliciousness[i], map.getOrDefault(deliciousness[i], 0) + 1);
        }
        return (int)(ans % mod);
    }
    private boolean check (int n) {
        return n != 0 && (n & -n) == n;
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_1711_3_大餐计数 {
    int mod = (int) 1e9 + 7;
    int max = 1 << 22;
    public int countPairs(int[] deliciousness) {
        long ans = 0;
        // 用于记录deliciousness中每个数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int d : deliciousness) {
            map.put(d, map.getOrDefault(d, 0) + 1);
        }
        for (int x : map.keySet()) {
            for (int i = 1; i < max; i = i << 1) {
                int t = i - x;
                if (map.containsKey(t)) {
                    if (x == t) {
                        ans += (long) (map.get(x) - 1) * map.get(t);
                    } else {
                        ans += (long) map.get(x) * map.get(t);
                    }
                }
            }
        }
        // 所有情况都计算了两次（x, y）和（y, x)）
        ans = ans >> 1;
        return (int)(ans % mod);
    }
}
```

## 解法四
### 代码
```java
public class LeetCode_1711_4_大餐计数 {
    int mod = (int) 1e9 + 7;
    int max = 1 << 22;
    public int countPairs(int[] deliciousness) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : deliciousness) {
            for (int i = 1; i < max; i <<= 1) {
                int t = i - x;
                if (map.containsKey(t)) {
                    ans += map.get(t);
                    ans %= mod;
                }
            }
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        return ans;
    }
}

```

# [LeetCode_930_1_和相同的二元子数组](https://leetcode-cn.com/problems/binary-subarrays-with-sum/)
## 题目
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。

子数组 是数组的一段连续部分。

示例 1：
```
输入：nums = [1,0,1,0,1], goal = 2
输出：4
解释：
有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
```

示例 2：
```
输入：nums = [0,0,0,0,0], goal = 0
输出：15
```

提示：

* 1 <= nums.length <= 3 * 104
* nums[i] 不是 0 就是 1
* 0 <= goal <= nums.length








# [LeetCode_274_1_H指数](https://leetcode-cn.com/problems/h-index/)
## 题目
给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。

h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。

例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。

 
示例：
```
输入：citations = [3,0,6,1,5]
输出：3 
解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
```

**提示**：如果 h 有多种可能的值，h 指数是其中最大的那个。

## 理解
解法一：使用数组提供的排序方法对论文进行排序，给定默认H值为0，再从后往前遍历论文（后面的引用次数最多），
如果引用次数大于H值（当前值大于H值，则之前遍历的所有论文引用量都大于H值），则H值+1，继续往前遍历，
直到不满足条件，返回H。此处理需要对数组预先做排序操作，整体时间复杂度为O(nlogn)。

解法二：使用二分法解决，根据题意我们要找到引用次数至少为x的x篇论文，并且要找到最大x的值。
那么我们以x在分割点把正整数轴分割为两部分：  
* 小于等于x的应该都满足题意
* 大于x的都不满足题意  

根据此二段性，我们利用二分法求解此题，按照题意提供check函数，遍历论文找引用次数大于等于给定值
的论文的篇数，如果篇数大于等于给定值，则满足H指数定义，否则不满足。根据条件压缩左右边界，求解结果。
二分的时间复杂度为O(logn)，check函数需要遍历论文，时间复杂度为O(n)，整体时间复杂度为O(nlogn)。

## 解法一
### 代码
```java
public class LeetCode_274_1_H指数 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_274_2_H指数 {
    public int hIndex(int[] citations) {
        // 使用二分法求解
        int l = 0;
        int r = citations.length;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(citations, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] citations, int mid) {
        int value = 0;
        for (int c : citations) {
            if (c >= mid) {
                value++;
            }
        }
        return value >= mid;
    }
}
```