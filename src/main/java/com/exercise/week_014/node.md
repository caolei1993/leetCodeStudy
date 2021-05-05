[toc]
# [LeetCode_1011_1_在D天内送达包裹的能力](https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/)
## 题目
传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。


示例 1：
```
输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
输出：15
解释：
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 
```

示例 2：
```
输入：weights = [3,2,2,4,1,4], D = 3
输出：6
解释：
船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
第 1 天：3, 2
第 2 天：2, 4
第 3 天：1, 4
```

示例 3：
```
输入：weights = [1,2,3,1,1], D = 4
输出：3
解释：
第 1 天：1
第 2 天：2
第 3 天：3
第 4 天：1, 1
```


提示：

* 1 <= D <= weights.length <= 50000
* 1 <= weights[i] <= 500

## 理解
解法一：暴力枚举，最小运送能力应该是货物的最大重量，从货物的最大重量定位初始输送能力。
定义无限循环区间，遍历所有货物，统计运送天数，如果运送天数大于指定天数，那输送能力+1，
再次重新统计，直到运送天数为指定天数时返回运送能力。

解法二：二分查到，二分查找的区间为[货物中的最大质量，所有货物的总和]，当运送能力为所有
货物的总和时，一天可运完，所以定义为二分的右边界。通过二分查找运送货物的能力，最小运送
能力能把整个区间分为两部分，前半部分运输能力不足，不能在指定天数完成运输，后半部分运输
能力充足能在指定天数内完成运输。根据此二段性，我们求取最终状态，left = right时返回，
为满足条件的最小运输能力。

## 解法一
### 代码
```java
public class LeetCode_1011_1_在D天内送达包裹的能力 {
    public static int shipWithinDays(int[] weights, int D) {
        int ans = weights[0];
        for (int weight : weights) {
            ans = Math.max(ans, weight);
        }
        int length = weights.length;

        while (true) {
            int value = weights[0];
            int count = 1;
            for (int i = 1; i < length; i++) {
                if (value + weights[i] > ans) {
                    value = weights[i];
                    count++;
                } else {
                    value += weights[i];
                }
            }
            if (count <= D) {
                return ans;
            } else {
                ans++;
            }
        }
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1011_2_在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int D) {
        int left = weights[0], right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        // 搜索区间为[left, right]
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] weights, int d, int mid) {
        int sum = 0;
        int count = 1;
        for (int i = 0; i < weights.length; i++) {
            if (sum + weights[i] > mid) {
                sum = weights[i];
                count++;
            } else {
                sum += weights[i];
            }
        }
        return count <= d;
    }
}
```
# [LeetCode_633_1_平方数之和](https://leetcode-cn.com/problems/sum-of-square-numbers/)
## 题目
给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。

示例 1：
```
输入：c = 5
输出：true
解释：1 * 1 + 2 * 2 = 5
```

示例 2：
```
输入：c = 3
输出：false
```

示例 3：
```
输入：c = 4
输出：true
```

示例 4：
```
输入：c = 2
输出：true
```

示例 5：
```
输入：c = 1
输出：true
```

提示：

* 0 <= c <= 231 - 1

## 理解
解法一：使用爆搜，从0开始直到c的开平方，找到了符合题意的两个整数，返回true，否则遍历
完返回false.
解法二：双指针，left指向0，right指向开平方c，判断left²+right²与c的大小，如果求取的
值刚好等于c，则返回true，如果大于c，right--，如果小于c，left++，遍历完后都未匹配，返回
false.

## 解法一
### 代码
```java
public class LeetCode_633_1_平方数之和 {
    public boolean judgeSquareSum(int c) {
        int max = (int)Math.sqrt(c);
        for (int a = 0; a <= max ; a++) {
            int b = (int)Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_633_2_平方数之和 {
    public boolean judgeSquareSum(int c) {
        int right = (int)Math.sqrt(c);
        int left = 0, value = 0;

        while (left <= right) {
            value = left * left + right * right;
            if (value == c) {
                return true;
            } else if (value < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
```
# [LeetCode_403_1_青蛙过河](https://leetcode-cn.com/problems/frog-jump/)
## 题目
一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。  

给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。  

开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。  

如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。  

 

示例 1：
```
输入：stones = [0,1,3,5,6,8,12,17]
输出：true
解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
```

示例 2：
```
输入：stones = [0,1,2,3,4,8,9,11]
输出：false
解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
```

提示：

* 2 <= stones.length <= 2000
* 0 <= stones[i] <= 231 - 1
* stones[0] == 0

## 理解


# [LeetCode_137_1_只出现一次的数字II](https://leetcode-cn.com/problems/single-number-ii/)
## 题目
给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

示例 1：
```
输入：nums = [2,2,3,2]
输出：3
```

示例 2：
```
输入：nums = [0,1,0,1,0,1,99]
输出：99
```

提示：

* 1 <= nums.length <= 3 * 104
* -231 <= nums[i] <= 231 - 1
* nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 
进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

## 理解
解法一：将数组排序，原因是让数值相等的元素都在一起，接着统计每个元素的数量，如果数量为3，
就遍历下一个，数量不为3，就返回。
解法二：利用位运算，把数字都转化为二进制，统计每个二进制位的1的个数，再通过对每个位置的统计
数据对3做取余操作，取余为1的结果通过反向运算，获得原来只出现一次的十进制数字。

## 解法一
### 代码
```java
public class LeetCode_137_1_只出现一次的数字II {
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (pre == nums[i]) {
                count++;
            } else {
                if (count == 3) {
                    count = 1;
                    pre = nums[i];
                } else {
                    return nums[i - 1];
                }
            }
        }
        return pre;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_137_2_只出现一次的数字II {
    public int singleNumber(int[] nums) {
        // 创建存储数字转化为2进制，每个位置1的数量
        int[] arr = new int[32];
        // 统计每个数字转化为2进制后每个位置的1的数量
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    arr[i]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (arr[i] % 3 == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }
}

```

# [LeetCode_690_1_员工的重要性](https://leetcode-cn.com/problems/employee-importance/)
## 题目
给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。  

比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。  

现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。  

 
示例：
```
输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
输出：11
解释：
员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
```

提示：

* 一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
* 员工数量不超过 2000 。

## 理解
解法一：使用递归的方法，先查询目标id的员工，然后找到他的所有下属，递归计算出所有下属的重要性值。
解法二：也可使用迭代的方法，利用队列，将每次找到的下属都添加到队列尾部，从队列中取值，取重要值，
取下属存入队列。

## 解法一
### 代码
```java
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
```
## 解法二
### 代码
```java
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
```
# [LeetCode_554_1_砖墙](https://leetcode-cn.com/problems/brick-wall/)
## 题目
你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。

你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。

给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。


示例 1：
```
输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
输出：2
```

示例 2：
```
输入：wall = [[1],[1],[1]]
输出：3
```
 
提示：

* n == wall.length
* 1 <= n <= 104
* 1 <= wall[i].length <= 104
* 1 <= sum(wall[i].length) <= 2 * 104
* 对于每一行 i ，sum(wall[i]) 应当是相同的
* 1 <= wall[i][j] <= 231 - 1

## 理解
使用哈希表辅助求解，我们利用哈希表来存储每个缝隙出现的次数，进而找到出现次数最多的缝隙，在用
墙的行数减去这个缝隙出现的次数，就是穿过的砖的数量（因为每行，每个指定缝隙只可能出现一次）。  
问题转化为如何求解缝隙，我们可以使用前缀和来代表缝隙，比如一个[1,2,3,1]的墙，他的缝隙就是
[1,3,6]。用这种方法我们统计出每个缝隙在整个墙的出现的次数，然后找到出现次数最多的缝隙，用
墙上砖的行数减去这个最大次数，就得到了穿过最少砖的数量。（需要注意处理前缀和时，需要剔除每行
砖的最大宽度，因为题意中不包含两边的边界）

### 代码
```java
public class LeetCode_554_1_砖墙 {
    public int leastBricks(List<List<Integer>> wall) {
        int size = wall.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, sum = 0; i < size; i++, sum = 0) {
            for (int cur : wall.get(i)) {
                sum += cur;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            // 因为不包含最后的边界，所以去除整行sum的缝隙记录
            map.remove(sum);
        }
        int ans = size;
        for (int cur : map.keySet()) {
            ans = Math.min(ans, size - map.get(cur));
        }
        return ans;
    }
}
```

