[toc]
# [LeetCode_815_1_公交路线](https://leetcode-cn.com/problems/bus-routes/)
## 题目
给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。

* 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。

求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。

 
示例 1：
```
输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
输出：2
解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
```

示例 2：
```
输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
输出：-1
```


提示：

* 1 <= routes.length <= 500.
* 1 <= routes[i].length <= 105
* routes[i] 中的所有值 互不相同
* sum(routes[i].length) <= 105
* 0 <= routes[i][j] < 106
* 0 <= source, target < 106

## 理解
解法一：可以将每条公交线路看做一个点，线路之前能换乘的相当于两点之前有连线，在图中标出起点和
终点(可能会存在多个点是起点或终点的情况)，题目转化为在起点和终点之前找最短的连线。
* 利用map记录某个站能换乘的线路
* 利用数组来记录已经坐过的线路，预防重复选取，导致无法获得最优解
* 利用队列来处理所有可能坐的线路，从起点来求取所有能到的线路，记录在数组中
* 查询终点所在的线路，并遍历线路看是否在记录的可达线路数组中，返回最短路线

解法二：利用双向bfs来解决问题，因为在无解的情况下，双向bfs的效率比bfs更低，所有我们可以使用并查集
排除掉无解的情况，再使用双向bfs来解决。
* 判断起始点一致情况，直接返回0
* 并查集排除无解情况
* 判断起始点如果在同一线路上时，返回1
* 使用双向bfs求解最优路线

## 解法一
### 代码
```java
public class LeetCode_815_1_公交路线 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // 公交的总的线路数
        int n = routes.length;
        // 定义线路图，所有线路看做点，可以换乘的线路间连线
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 遍历所有的线路
        for (int i = 0; i < n; i++) {
            // 遍历某条线路的所有车站
            for (int p : routes[i]) {
                List<Integer> list = map.getOrDefault(p, new ArrayList<>());
                // 遍历该站可以换乘的所有线路
                for (int j : list) {
                    // 可以换乘的线路之前连线
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                map.put(p, list);
            }
        }

        // 记录已经做过的线路
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Deque<Integer> deque = new ArrayDeque<>();
        // 起始站线路入队，并记录乘坐记录
        for (int w : map.getOrDefault(source, new ArrayList<>())) {
            dis[w] = 1;
            deque.add(w);
        }

        while (!deque.isEmpty()) {
            int x = deque.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    deque.add(y);
                }
            }
        }
        // 查看所有能到达的线路中是否有终点站所在的路线，并找出最短乘坐线路
        int ret = Integer.MAX_VALUE;
        for (int w : map.getOrDefault(target, new ArrayList<>())) {
            if (dis[w] != -1) {
                ret = Math.min(ret, dis[w]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_815_2_公交路线 {
    // 记录某个车站能换乘的路线
    Map<Integer, Set<Integer>> bus = new HashMap<>();
    int s, t;
    int[][] r;
    // 最大车站为10^6，所以初始化并查集集合
    int max = (int)1e6 + 10;
    int[] p = new int[max];
    private int find (int x) {
        if (x != p[x]) {
            x = find(p[x]);
        }
        return x;
    }

    private void union (int x, int y) {
        p[find(x)] = p[find(y)];
    }

    private boolean query (int x, int y) {
        return find(x) == find(y);
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        s = source;
        t = target;
        r = routes;
        if (s == t) {
            return 0;
        }
        // 并查集排除不能到的情况
        for (int i = 0; i < max; i++) {
            p[i] = i;
        }
        for (int[] rou : routes) {
            for (int station : rou) {
                union(station, rou[0]);
            }
        }
        if (!query(s, t)) {
            return -1;
        }

        return dfs();
    }

    private int dfs() {
        Deque<Integer> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        Map<Integer, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();

        // 初始化map,记录每个车站能换成的线路
        for (int i = 0; i < r.length; i++) {
            int n = r[i].length;
            for (int j = 0; j < n; j++) {
                int st = r[i][j];
                if (st == s) {
                    d1.add(i);
                    m1.put(i, 1);
                }
                if (st == t) {
                    d2.add(i);
                    m2.put(i, 1);
                }
                Set<Integer> set = bus.getOrDefault(st, new HashSet<>());
                set.add(i);
                bus.put(st, set);
            }
        }
        Set<Integer> s1 = bus.getOrDefault(s, new HashSet<>()), s2 = bus.getOrDefault(t, new HashSet<>());
        Set<Integer> ss = new HashSet<>();
        ss.addAll(s1);
        ss.retainAll(s2);
        if (!ss.isEmpty()) {
            return 1;
        }

        int v;
        while (!d1.isEmpty() && !d2.isEmpty()) {
            if (d1.size() <= d2.size()) {
                v = update(d1, m1, m2);
            } else {
                v = update(d2, m2, m1);
            }
            if (v != -1) {
                return v;
            }
        }
        return -1;
    }

    private int update(Deque<Integer> deque, Map<Integer, Integer> source, Map<Integer, Integer> other) {
        Integer p = deque.poll();
        // 遍历当前线路上的所有站点
        int[] w = r[p];
        for (int station : w) {
            Set<Integer> ways = bus.get(station);
            for (Integer way : ways) {
                if (source.containsKey(way)) {
                    continue;
                }
                if (other.containsKey(way)) {
                    return source.get(p) + other.get(way);
                }
                deque.add(way);
                source.put(way, source.get(p) + 1);
            }
        }
        return -1;
    }
}

```

# [LeetCode_168_1_Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/)
## 题目
给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。

例如：
```
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
```

示例 1：
```
输入：columnNumber = 1
输出："A"
```

示例 2：
```
输入：columnNumber = 28
输出："AB"
```

示例 3：
```
输入：columnNumber = 701
输出："ZY"
```

示例 4：
```
输入：columnNumber = 2147483647
输出："FXSHRXW"
```

提示：

* 1 <= columnNumber <= 2^31 - 1

## 理解
将十进制转化为26进制的问题，注意从低位到高位处理

### 代码
```java
public class LeetCode_168_1_Excel表列名称 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char)((columnNumber % 26) + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}
```

# [LeetCode_剑指Offer37_1_序列化二叉树](https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/)
## 题目
请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

 

示例：

```
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
```


注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/

## 理解
序列化的时候主要思想还是层序遍历，空的节点不一定非要用null表示，我们可以定义一个特殊值的节点来作为空
节点，当发现某个子节点的子节点为空时，我们就入队空节点。需要注意我们层序遍历入队节点的子节点时，需要保证
当前节点是非空节点。  
反序列化的时候也是取头部节点为根节点，依次往后每次遍历两个节点，如果为非空节点就创建节点并赋值为当前
节点的左右子节点，并入队。循环往后遍历直至结束。

### 代码
```java
public class LeetCode_剑指Offer37_1_序列化二叉树 {
    int INF = -2000;
    TreeNode emptyNode = new TreeNode(INF);

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            sb.append(node.val).append("_");
            if (!node.equals(emptyNode)) {
                deque.offer(node.left == null ? emptyNode : node.left);
                deque.offer(node.right == null ? emptyNode : node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data) || data == null) {
            return null;
        }
        String[] ss = data.split("_");
        int n = ss.length;
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        deque.offer(root);
        for (int i = 1; i < n - 1 ; i += 2) {
            TreeNode node = deque.poll();
            int left = Integer.parseInt(ss[i]);
            if (left != INF) {
                node.left = new TreeNode(left);
                deque.offer(node.left);
            }
            int right = Integer.parseInt(ss[i + 1]);
            if (right != INF) {
                node.right = new TreeNode(right);
                deque.offer(node.right);
            }
        }
        return root;
    }
}
```

# [LeetCode_LCP07_1_传递信息](https://leetcode-cn.com/problems/chuan-di-xin-xi/)
## 题目
小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：

1. 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
2. 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
3. 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人

给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。

示例 1：
```
输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3

输出：3

解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
```

示例 2：
```
输入：n = 3, relation = [[0,2],[2,1]], k = 2

输出：0

解释：信息不能从小 A 处经过 2 轮传递到编号 2
```

限制：

* 2 <= n <= 10
* 1 <= k <= 5
* 1 <= relation.length <= 90, 且 relation[i].length == 2
* 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]

## 理解
解法一：图论常规bfs，利用map初始化每个位置可以向下传递的坐标，通过k次传递，遍历队列查找位置为n-1出现的
次数。

解法二：动态规划，我们假设现在已经走了i步，到达位置j，剩余的步数为k-i，我们不关注是如何走到i的，只
关注剩余的k-i步是否能走到目标位置n-1。所以这是一个无后效性问题，可以使用动态规划解决。  
* 确认状态定义f[i][j]意义为走了i步到达坐标为j的位置的方案数。i的范围为[0, k]，j的范围为[0, n-1]
* 确认初始化值，f[0][0] = 1，代表走0步，在坐标为0的位置的方案数为1，即最初状态
* 确认转移方程 f[i][j] = f[i-1][a] + ... + f[i-1][n]，a...n代表所有能一步走到j的坐标

最终返回f[k][n-1]即可

## 解法一
### 代码
```java
public class LeetCode_LCP07_1_传递信息 {
    // 记录每个人对应的传递关系
    Map<Integer, Set<Integer>>  map = new HashMap<>();
    public int numWays(int n, int[][] relation, int k) {
        // 遍历所有的传递关系，初始化map
        for (int[] re : relation) {
            Set<Integer> set = map.getOrDefault(re[0], new HashSet<>());
            set.add(re[1]);
            map.put(re[0], set);
        }
        int ans = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        for (int i = 0; i < k; i++) {
            Deque<Integer> deque1 = new ArrayDeque<>();
            if (deque.isEmpty()) {
                return 0;
            }
            while (!deque.isEmpty()) {
                int a = deque.poll();
                Set<Integer> ways = map.getOrDefault(a, new HashSet<>());
                for (int way : ways) {
                    if (i == k - 1 && way == n - 1) {
                        ans++;
                    }
                    deque1.offer(way);
                }
            }
            deque = deque1;
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_LCP07_2_传递信息 {
    public int numWays(int n, int[][] relation, int k) {

        // 定义dp二维数组，f[i][j]代表走了i步，当前位置在坐标为j的位置的方案总数
        int[][] f = new int[k + 1][n];
        // 初始化走0步，在index=0的位置的方案为1
        f[0][0] = 1;
        for (int i = 1; i <= k ; i++) {
            for (int[] r : relation) {
                int a = r[0], b = r[1];
                f[i][b] += f[i - 1][a];
            }
        }
        return f[k][n - 1];
    }
}
```