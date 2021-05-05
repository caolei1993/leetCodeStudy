[toc]
# [LeetCode_90_1_子集II](https://leetcode-cn.com/problems/subsets-ii/)
## 题目
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

示例 1：
```
输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
```

示例 2：
```
输入：nums = [0]
输出：[[],[0]]
```

提示：
* 1 <= nums.length <= 10
* -10 <= nums[i] <= 10

## 理解
不考虑去重的话，总的子集方案共有2的length次方种，及 (1 << length)种，以2个数字的
数组为例，它的子集一共有（00,01,10,11）四种，二进制4个值中的1表示取该index的值到
子集中，最后我们再使用set将结果集去重，即可得到所有的子集。

### 代码
```java
public class LeetCode_90_1_子集II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        // 枚举 i 代表，枚举所有的选择方案状态
        // 例如 [1,2]，我们有 []、[1]、[2]、[1,2] 几种方案，分别对应了 00、10、01、11 4种状态
        for (int i = 0; i < (1 << length); i++) {
            list.clear();
            for (int j = 0; j < length; j++) {
                // 对当前状态进行诸位检查，如果当前状态为 1 代表被选择，加入当前方案中
                int t = (i >> j) & 1;
                if (t == 1) {
                    list.add(nums[j]);
                }
            }
            // 使用新的
            ans.add(new ArrayList<>(list));
        }
        return new ArrayList<>(ans);
    }
}
```

# [LeetCode_74_1_搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)
## 题目
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。  
每行的第一个整数大于前一行的最后一个整数。
 

示例 1：
```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true
```

示例 2：
```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
输出：false
```

提示：

* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 100
* -104 <= matrix[i][j], target <= 104

## 理解
解法一：直接遍历所有行，找到目标值可能存在的行，遍历该行元素，如果匹配上返回True，
否则返回False。
解法二：使用二分法求解，前提是每行的数据都是一样多。n为行数，m为列数，二分的左边
界l = 0, 右边界r = n * m - 1，所以搜索区间为[l,r]，每次取中间值，定位到二维数组
中的matrix[mid/m][mid%m]位置的值，用目标值与之比较，进行区间的缩减。直到遍历完或
找到目标值。

## 解法一
### 代码
```java
public class LeetCode_74_1_搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int length = matrix[i].length;
            if ( matrix[i][0] <= target && target <= matrix[i][length-1]) {
                for (int j = 0; j < length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
            if (matrix[i][0] > target) {
                return false;
            }
        }
        return false;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_74_2_搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int value = matrix[mid / m][mid % m];
            if (target > value) {
                l = mid + 1;
            } else if (target < value) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

# [LeetCode_190_1_颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)
## 题目
颠倒给定的 32 位无符号整数的二进制位。

提示：

请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。

在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 
进阶:
如果多次调用这个函数，你将如何优化你的算法？

示例 1：
```
输入: 00000010100101000001111010011100
输出: 00111001011110000010100101000000
解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
```

示例 2：
```
输入：11111111111111111111111111111101
输出：10111111111111111111111111111111
解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
```

示例 1：
```
输入：n = 00000010100101000001111010011100
输出：964176192 (00111001011110000010100101000000)
解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
```

示例 2：
```
输入：n = 11111111111111111111111111111101
输出：3221225471 (10111111111111111111111111111111)
解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
```
 
提示：

* 输入是一个长度为 32 的二进制字符串

## 理解 
因为固定是32位的二进制，直接循环处理32次，每次都将目标值右移，再与1做&运算（目标是取
某一位是0还是1），知道结果后，如果是1，再将1右移对称的位数，与原结果做|运算。

### 代码
```java
public class LeetCode_190_1_颠倒二进制位 {
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int t = (n >> i) & 1;
            if (t == 1) {
                ans |= (1 << 31 - i);
            }
        }
        return ans;
    }
}
```

# [LeetCode_1006_1_笨阶乘](https://leetcode-cn.com/problems/clumsy-factorial/)
## 题目
通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。

相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。

例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。

另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。

实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。

示例 1：
```
输入：4
输出：7
解释：7 = 4 * 3 / 2 + 1
```

示例 2：
```
输入：10
输出：12
解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
```

提示：

* 1 <= N <= 10000
* -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）

## 理解
利用队列存储计算的值，优先计算*/，将乘除法优先计算再入队，+-法将值带上符号之间入队，
等所有数据处理完后，队列中所有数据出队，并求和即为答案。

### 代码
```java
public class LeetCode_1006_1_笨阶乘 {
    public int clumsy(int N) {
        Queue<Integer> queue = new LinkedList<>();
        int a = 0, ans = 0, b = 1;
        int pre = N;
        for (int i = N-1; i > 0; i--) {
            a = a % 4;
            switch (a) {
                case 0:
                    pre = pre * i;
                    break;
                case 1:
                    pre = pre / i;
                    queue.offer(pre * b);
                    pre = 0;
                    break;
                case 2:
                    b = 1;
                    queue.offer(i * b);
                    break;
                case 3:
                    b = -1;
                    pre = i;
                    break;
                default:
            }
            a++;
        }
        if (pre != 0) {
            queue.offer(pre * b);
        }
        while (!queue.isEmpty()) {
            ans += queue.poll();
        }
        return ans;
    }
}
```

# [LeetCode_面试题17_21_1_直方图的水量](https://leetcode-cn.com/problems/volume-of-histogram-lcci/)
## 题目
给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。


上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。

示例:
```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```

## 理解
解法一：按列求蓄水量，每一列的蓄水量加在一起就是总的蓄水量，现在问题转化为如何某列的蓄水量
问题。首先我们明确，某一列能蓄水，那么他左侧肯定有比它高的列，右边也有比它高的列，它的蓄水
量取决于两个高值中小的那个，即蓄水量 = Math.min(leftMax, rightMax) - height[i]。按照
这个思想我们遍历除首尾以外的列，依次求解左侧最大值与右侧最大值，最终求取蓄水量。
解法二：优化解法一，预先求好每个位置的左侧最大值与右侧最大值，后面只需要利用求取的值求取蓄水
量即可。
解法三：利用单调栈，栈中的元素按从大到小，当遍历到栈中至少包含2个元素且当前的高度比栈顶元素
对应列的高度高时，表示找到了可以蓄水的一个位置，因为栈中保存的是列的下标，所以利用下标计算
蓄水位置的宽度和高度，累加蓄水量，直到遍历完数组。
解法四：利用双指针，我们发现某个列的蓄水量其实只与左边和右边最大值中的较小的那个有关，利用
变量leftMax和rightMax来存储左边及右边的最大值。
* 如果height[left] < height[right]，必有liftMax < rightMax，ans += liftMax - height[left]，
left++
* 如果height[left] >= height[right]，必有liftMax >= rightMax，ans += rightMax - height[right]，
right--

## 解法一
### 代码
```java
public class LeetCode_面试题17_21_1_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0;
        if (length < 3) {
            return ans;
        }
        for (int i = 1; i < length - 1; i++) {
            int cur = height[i];
            int lHeight = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                lHeight = Math.max(lHeight, height[j]);
            }
            if (cur >= lHeight) {
                continue;
            }
            int rHeight = Integer.MIN_VALUE;
            for (int j = i+1; j < length; j++) {
                rHeight = Math.max(rHeight, height[j]);
            }
            if (cur >= rHeight) {
                continue;
            }
            ans += Math.min(lHeight, rHeight) - cur;
        }
        return ans;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_面试题17_21_2_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0;
        // 小于三个柱状图无法蓄水
        if (length < 3) {
            return ans;
        }
        // 创建左侧最大值数组，并初始化
        int[] leftMax = new int[length];
        leftMax[0] = height[0];
        for (int i = 1; i < length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 创建右侧最大值数组，并初始化
        int[] rightMax = new int[length];
        rightMax[length - 1] = height[length -1];
        for (int i = length - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 首位置和最后一个位置不可能蓄水，所以只需遍历首尾之前的值
        for (int i = 1; i < length - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}
```
## 解法三
### 代码
```java
public class LeetCode_面试题17_21_3_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0;
        if (length < 3) {
            return ans;
        }
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length ; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                // 蓄水的宽度
                int curWidth = i - left - 1;
                // 蓄水的高度
                int curHeight = Math.min(height[left], height[i]) - height[top];
                ans += curWidth * curHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}
```
## 解法四
### 代码
```java
public class LeetCode_面试题17_21_4_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0, left = 0, right = length - 1, leftMax = 0, rightMax = 0;
        if (length < 3) {
            return ans;
        }
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}
```

# [LeetCode_1143_1_最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)
## 题目
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。  
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

 

示例 1：
```
输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace" ，它的长度为 3 。
```

示例 2：
```
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。
```

示例 3：
```
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。
```

提示：

* 1 <= text1.length, text2.length <= 1000
* text1 和 text2 仅由小写英文字符组成。

## 理解
解法：使用动态规划求解，创建二维数组，f[i][j]表示text1的前i个字符与text2前j个字符
的最长公共子序列的长度。
* 需要注意我们想要统一处理字符串，所以给每个字符串前都拼了一个空格
* 初始化二维数组的第一行和第一列，方便我们后面统一处理
* 状态方程为：text1[i]==text2[j] : f[i][j]=f[i-1][j-1]+1f[i][j]=f[i−1][j−1]+1。
代表使用 text1[i]text1[i] 与 text2[j]text2[j]形成最长公共子序列的长度。  
        text1[i]!=text2[j] : f[i][j]=max(f[i-1][j], f[i][j - 1]。
        代表不使用 text1[i] 形成最长公共子序列的长度、不使用 text2[j] 形成最长公共子序列的长度。这两种情况中的最大值。
        
## 解法
### 代码
```java
public class LeetCode_1143_1_最长公共子序列 {
    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        text1 = " " + text1;
        text2 = " " + text2;
        int[][] f = new int[length1 + 1][length2 + 1];
        for (int i = 0; i < length1 + 1; i++) {
            f[i][0] = 1;
        }
        for (int i = 0; i <length2 ; i++) {
            f[0][i] = 1;
        }
        for (int i = 1; i < length1 + 1 ; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i][j - 1], f[i - 1][j]);
                }
            }
        }
        return f[length1][length2] - 1;
    }
}

```
# [LeetCode_781_1_森林中的兔子](https://leetcode-cn.com/problems/rabbits-in-forest/)
## 题目
森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。

返回森林中兔子的最少数量。

示例:
```
输入: answers = [1, 1, 2]
输出: 5
解释:
两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
设回答了 "2" 的兔子为蓝色。
此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
```

```
输入: answers = [10, 10, 10]
输出: 11
```

```
输入: answers = []
输出: 0
```

说明:
* answers 的长度最大为1000。
* answers[i] 是在 [0, 999] 范围内的整数。

## 理解
解法一：利用map统计所有报数的兔子，key为兔子报的数，value为报该数的兔子数量，
因为求的是最少的兔子数量，报x的颜色的兔子假设为白色，肯定有x + 1只白兔，如果报x的
兔子少于x + 1只都认为是白兔，多余x + 1只说明还有其他颜色的兔子也是x只。利用这种思想
求出兔子的最少数量。
解法二：先对数组进行排序，保证报x数字的兔子都在一起，接着遍历数组，过滤掉最多连续的x - 1
个相同的报数，并同时叠加该颜色的兔子数量，求取数字的最少数量

## 解法一
### 代码
```java
public class LeetCode_781_1_森林中的兔子 {
    public int numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : answers) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // 某种颜色兔子总数
            int count = entry.getKey() + 1;
            ans += Math.ceil(((double) entry.getValue())/count) * count;
        }
        return ans;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_781_2_森林中的兔子 {
    public int numRabbits(int[] answers) {
        Arrays.sort(answers);
        int ans = 0;
        int length = answers.length;
        for (int i = 0; i < length ; i++) {
            int k = answers[i];
            ans += k + 1;
            while (k-- > 0 && i + 1 < length && answers[i] == answers[i + 1]) {
                i++;
            }
        }
        return ans;
    }
}

```
# [LeetCode_88_1_合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/)
## 题目
给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。

示例 1：
```
输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
输出：[1,2,2,3,5,6]
```

示例 2：
```
输入：nums1 = [1], m = 1, nums2 = [], n = 0
输出：[1]
```

提示：

* nums1.length == m + n
* nums2.length == n
* 0 <= m, n <= 200
* 1 <= m + n <= 200
* -109 <= nums1[i], nums2[i] <= 109

## 理解
解法一：利用数组copy方法，将数组二的元素copy到数组一的尾部，再对数组一排序
解法二：利用双指针，从数组一（有值的尾部）及数组二的尾部开始往前遍历，判断数组一，数组二
指针位置元素的大小，大的放在数组一尾部，并继续往前遍历（因为数组一，二都是有序数组），依次
判断直到两个数组都遍历完成。

## 解法一
### 代码
```java
public class LeetCode_88_1_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_88_2_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 初始化尾部双指针
        int p1 = m - 1, p2 = n - 1;
        // 尾部
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
```