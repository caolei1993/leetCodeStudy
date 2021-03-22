[toc]
# [LeetCode_239_1_滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)
## 题目
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

示例 1：
```
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

示例 2：
```
输入：nums = [1], k = 1
输出：[1]
```

示例 3：
```
输入：nums = [1,-1], k = 1
输出：[1,-1]
```

示例 4：
```
输入：nums = [9,11], k = 2
输出：[11]
```

示例 5：
```
输入：nums = [4,-2], k = 2
输出：[4]
```

提示：

* 1 <= nums.length <= 105  
* -104 <= nums[i] <= 104  
* 1 <= k <= nums.length  

## 理解
* 解法一：(单调队列)借助双端队列，假设下标为i和下标为j的两个元素都在窗口中，并且i<j，nums[i]<nums[j]，
如果窗口移动，ij依然在窗口中，那么最大值永远不可能是i对应的值，因为j对应的值比它大，这样我们就
可以永久删除i，只保留j。借助这个思想，队列中存放下标，下标保证从小到大，下标对应的值保证由大到小，
如果遇到后一个坐标的值不比前一个小，就删除队尾元素，否则就将坐标添加至队尾，随着窗口移动，
我们需要判断队头的元素是否还在窗口中（队头元素与窗口的最左下标比较），如果不在则移除队头元素。
这样保证我们在每个窗口都取队头元素坐标对应的值就是这个窗口的最大值。

## 解法一
### 代码
```java
public class LeetCode_239_1_滑动窗口的最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 创建存放下标的双端队列
        Deque<Integer> deque = new LinkedList<>();
        // 先将第一个窗口的元素按规则入队
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int length = nums.length;
        int[] ans = new int[length - k + 1];
        // 存入首个窗口的最大元素的值
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 当前窗口的最左边坐标为 i-k+1 即 depue.peekFirst() < i-k+1的时候，需要去除队首元素，代表它不在窗口中
            // 在这里deuqe.peekFirst <= i-k，与上面同意义
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // 依次存入每个窗口中的最大值
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
```


# [LeetCode_199_1_二叉树的右视图](https://leetcode-cn.com/problems/binary-tree-right-side-view/)
## 题目
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:
```
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

```

## 理解
* 利用队列，广度优先搜索（层序遍历），获取每层最后一个元素，存入集合即为最终结果

### 代码
```java
public class LeetCode_199_1_二叉树的右视图 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        // 每层元素个数
        int size = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (size == 1) {
                list.add(root.val);
            }
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                size = queue.size();
            }
        }
        return list;
    }
}
```
# [LeetCode_1480_1_一维数组的动态和](https://leetcode-cn.com/problems/running-sum-of-1d-array/)
## 题目
给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。

请返回 nums 的动态和。

示例 1：
```
输入：nums = [1,2,3,4]
输出：[1,3,6,10]
解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
```

示例 2：
```
输入：nums = [1,1,1,1,1]
输出：[1,2,3,4,5]
解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
```

示例 3：
```
输入：nums = [3,1,2,10,1]
输出：[3,4,6,16,17]
 
```

提示：

* 1 <= nums.length <= 1000  
* -10^6 <= nums[i] <= 10^6

## 理解
创建和数组长度一致的数据，通过遍历给定数组，借助临时变量不断求和再存入指定的数组中返回。

### 代码
```java
public class LeetCode_1480_1_一维数组的动态和 {
    public int[] runningSum(int[] nums) {
        int[] ans =  new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans[i] = sum;
        }
        return ans;
    }
}
```

# [LeetCode_1512_1_好数对的数目](https://leetcode-cn.com/problems/number-of-good-pairs/)
## 题目
给你一个整数数组 nums 。

如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。

返回好数对的数目。

示例 1：
```
输入：nums = [1,2,3,1,1,3]
输出：4
解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
```

示例 2：
```
输入：nums = [1,1,1,1]
输出：6
解释：数组中的每组数字都是好数对
```

示例 3：
```
输入：nums = [1,2,3]
输出：0
```

提示：

* 1 <= nums.length <= 100
* 1 <= nums[i] <= 100

## 理解
遍历nums的除最后一个以外的数字，确定某一个x后，再遍历之后的所有值，判断是否为好数对。
（通过2层for循环解决）

### 代码
```java
public class LeetCode_1512_1_好数对的数目 {
    public int numIdenticalPairs(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
```
# [LeetCode_1672_1_最富有客户的资产总量](https://leetcode-cn.com/problems/richest-customer-wealth/)
## 题目
给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。

客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。

 
示例 1：
```
输入：accounts = [[1,2,3],[3,2,1]]
输出：6
解释：
第 1 位客户的资产总量 = 1 + 2 + 3 = 6
第 2 位客户的资产总量 = 3 + 2 + 1 = 6
两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
```

示例 2：
```
输入：accounts = [[1,5],[7,3],[3,5]]
输出：10
解释：
第 1 位客户的资产总量 = 6
第 2 位客户的资产总量 = 10 
第 3 位客户的资产总量 = 8
第 2 位客户是最富有的，资产总量是 10
```

示例 3：
```
输入：accounts = [[2,8,7],[7,1,3],[1,9,5]]
输出：17
```

提示：

* m == accounts.length
* n == accounts[i].length
* 1 <= m, n <= 50
* 1 <= accounts[i][j] <= 100

## 理解
通过二维数组的遍历求出最大值即可。

### 代码
```java
public class LeetCode_1672_1_最富有客户的资产总量 {

    public int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                sum += accounts[i][j];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
```

# [LeetCode_1773_1_统计匹配检索规则的物品数量](https://leetcode-cn.com/problems/count-items-matching-a-rule/)
## 题目
给你一个数组 items ，其中 items[i] = [typei, colori, namei] ，描述第 i 件物品的类型、颜色以及名称。

另给你一条由两个字符串 ruleKey 和 ruleValue 表示的检索规则。

如果第 i 件物品能满足下述条件之一，则认为该物品与给定的检索规则 匹配 ：
```
ruleKey == "type" 且 ruleValue == typei 。  
ruleKey == "color" 且 ruleValue == colori 。  
ruleKey == "name" 且 ruleValue == namei 。  
```

统计并返回 匹配检索规则的物品数量 。

 

示例 1：
```
输入：items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
输出：1
解释：只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
```

示例 2：
```
输入：items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
输出：2
解释：只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。
```
提示：

* 1 <= items.length <= 104
* 1 <= typei.length, colori.length, namei.length, ruleValue.length <= 10
* ruleKey 等于 "type"、"color" 或 "name"
* 所有字符串仅由小写字母组成

## 理解
借助switch来判断不同类型，不同类型需要判断每个物品不同下标的属性，借助自方法传入需要判断元素
的下标，返回统计结果。

### 代码
```java
public class LeetCode_1773_1_统计匹配检索规则的物品数量 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        switch (ruleKey) {
            case "type":
                return statics(items, 0, ruleValue);
            case "color":
                return statics(items, 1, ruleValue);
            case "name":
                return statics(items, 2, ruleValue);
            default:
                return 0;
        }
    }

    private int statics(List<List<String>> items, int index, String value) {
        int ans = 0;
        for (List<String> list : items) {
            if (value.equals(list.get(index))) {
                ans++;
            }
        }
        return ans;
    }
}
```
# [LeetCode_621_1_任务调度器](https://leetcode-cn.com/problems/task-scheduler/)
## 题目
给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。

 

示例 1：
```
输入：tasks = ["A","A","A","B","B","B"], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
```
 
示例 2：
```
输入：tasks = ["A","A","A","B","B","B"], n = 0
输出：6
解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
诸如此类
```

示例 3：
```
输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
输出：16
解释：一种可能的解决方案是：
     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
```

提示：

* 1 <= task.length <= 104
* tasks[i] 是大写英文字母
* n 的取值范围为 [0, 100]

## 理解
解法一：
* 想象一个矩阵，长度为n+1，宽度为需要执行次数最多的任务次数count，如果执行次数为count的
任务只有一个，且任务的种类未超过n+1，则最大需要的时间为(count-1)*(n+1)+1；
* 第二种情况，执行次数为count的任务有n个，则需要的最大的时间为(count-1)*(n+1)+n；
* 第三种情况，任务种类多于n+1，且排满了(n+1)*(count-1)的矩阵，任务执行不需要等待，
则最大需要时间则为task.length  
解法二：
* 优化解法一的解法，少遍历一次map，在统计每个任务的执行次数同时，统计最大次数任务的个数
## 解法一
### 代码
```java
public class LeetCode_621_1_任务调度器 {
    public int leastInterval(char[] tasks, int n) {
        int maxCount = 0;
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char s : tasks) {
            int value = map.getOrDefault(s, 0) + 1;
            maxCount = Math.max(maxCount, value);
            map.put(s, value);
        }

        Set<Map.Entry<Character, Integer>> set = map.entrySet();
        for (Map.Entry<Character, Integer> entry : set) {
            if (entry.getValue() == maxCount) {
                count++;
            }
        }

        return Math.max((maxCount - 1) * (n+1) + count, tasks.length);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_621_2_任务调度器 {
    public int leastInterval(char[] tasks, int n) {
        int maxCount = 0;
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char s : tasks) {
            int value = map.getOrDefault(s, 0) + 1;
            if (value > maxCount) {
                count = 1;
            } else if (value == maxCount) {
                count++;
            }
            maxCount = Math.max(maxCount, value);
            map.put(s, value);

        }
        return Math.max((maxCount - 1) * (n+1) + count, tasks.length);
    }

}
```