[toc]
# [LeetCode_374_1_猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/)
## 题目
猜数字游戏的规则如下：

* 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。  
* 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。  

你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：  

* -1：我选出的数字比你猜的数字小 pick < num
* 1：我选出的数字比你猜的数字大 pick > num
* 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num  
返回我选出的数字。

示例 1：
```
输入：n = 10, pick = 6
输出：6
```

示例 2：
```
输入：n = 1, pick = 1
输出：1
```

示例 3：
```
输入：n = 2, pick = 1
输出：1
```

示例 4：
```
输入：n = 2, pick = 2
输出：2
```
 
提示：

* 1 <= n <= 231 - 1
* 1 <= pick <= n

## 理解
使用二分法来处理，需要注意是我们将左边界和右边界相等作为出循环条件，这个时候我们在处理过程中
需要将情况归结于两种情况，一边包含边界，一边不包含边界，保证逻辑的正确性，因为我们最终是向预设
值靠近，且返回时l=r，所以返回l和r都可以。

### 代码
```java
public class LeetCode_374_1_猜数字大小 {
    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            long tmp = (long)l + r >> 1;
            int mid = (int)tmp;
            if (guess(mid) <= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int guess(int num) {
        return -1;
    }
}
```

# [LeetCode_852_1_山脉数组的峰顶索引](https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/)
## 题目
符合下列属性的数组 arr 称为 山脉数组 ：
* arr.length >= 3  
* 存在 i（0 < i < arr.length - 1）使得：
    > arr[0] < arr[1] < ... arr[i-1] < arr[i]
    > arr[i] > arr[i+1] > ... > arr[arr.length - 1]  

给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。


示例 1：
```
输入：arr = [0,1,0]
输出：1
```

示例 2：
```
输入：arr = [0,2,1,0]
输出：1
```

示例 3：
```
输入：arr = [0,10,5,2]
输出：1
```

示例 4：
```
输入：arr = [3,4,5,1]
输出：2
```

示例 5：
```
输入：arr = [24,69,100,99,79,78,67,36,26,19]
输出：2
```

提示：

* 3 <= arr.length <= 104
* 0 <= arr[i] <= 106
* 题目数据保证 arr 是一个山脉数组

进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？

## 理解
使用二分法解决问题，需要注意一点，(l + r)/2 = mid计算mid的时候本身就是向下取整，如果l的
调整是在某种情况下l = mid，那么就会出现死循环，需要注意二分边界条件的移动与二分计算的规律，
避免进入死循环。

## 解法一
### 代码
```java
public class LeetCode_852_1_山脉数组的峰顶索引 {
    public  int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            long tem = (long)l + r + 1 >> 1;
            int mid = (int)tem;
            System.out.println("l=" + l +",r=" + r + ",mid=" + mid);
            if (arr[mid - 1] < arr[mid]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_852_2_山脉数组的峰顶索引 {
    public  int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            long tem = (long)l + r >> 1;
            int mid = (int)tem;
            if (arr[mid] < arr[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
```

# [LeetCode_65_1_有效数字](https://leetcode-cn.com/problems/valid-number/)
## 题目
**有效数字**（按顺序）可以分成以下几个部分：

1. 一个 **小数** 或者 **整数**
2. （可选）一个 'e' 或 'E' ，后面跟着一个 整数  

**小数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（'+' 或 '-'）
2. 下述格式之一：
* 至少一位数字，后面跟着一个点 '.'
* 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
* 一个点 '.' ，后面跟着至少一位数字

**整数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（'+' 或 '-'）
2. 至少一位数字  

部分有效数字列举如下：
```
["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
```

部分无效数字列举如下：
```
["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
```

给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。


示例 1：
```
输入：s = "0"
输出：true
```

示例 2：
```
输入：s = "e"
输出：false
```

示例 3：
```
输入：s = "."
输出：false
```

示例 4：
```
输入：s = ".1"
输出：true
```

提示：

* 1 <= s.length <= 20
* s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。

## 理解
字符串模拟法，利用e/E将字符串分割  
* 有e/E，分为两部分，前一部分可以是小数或整数，后一部分只能是整数
* 无e/E，整段是小数或整数  
此时我们需要一个check函数来判断字符串是整数或小数  
* '+','-'只能出现在头部
* '.'只能出现一次
* 至少包含一个数字

### 代码
```java
public class LeetCode_65_1_有效数字 {
    public boolean isNumber(String s) {
        int n = s.length();
        // 用来记录e/E的坐标
        int ids = -1;
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            if (a == 'e' || a == 'E') {
                if (ids == -1) {
                    ids = i;
                } else {
                    // 含有多个e/E
                    return false;
                }
            }
        }

        boolean ans = true;
        // 包含e，e之前可以是整数或小数，e之后只能是整数
        if (ids != -1) {
            ans &= check(0, ids - 1, s,false);
            ans &= check(ids + 1, n - 1, s,true);
        } else {
            ans &= check(0, n - 1, s,false);
        }
        return ans;
    }

    /**
     *
     * @param start 起始坐标
     * @param end 截止坐标
     * @param s 目标字符串
     * @param mustInteger 是否需要是整数
     * @return 符合有效数字或不符合
     */
    private boolean check(int start, int end, String s, boolean mustInteger) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '+' || s.charAt(start) == '-') {
            start++;
        }
        boolean hasDot = false, isNum = false;
        for (int i = start; i <= end; i++) {
            char v = s.charAt(i);
            if (v == '.') {
                // 需要是整数或多个.
                if (mustInteger || hasDot) {
                    return false;
                }
                hasDot = true;
            } else if (v >= '0' && v <= '9') {
                isNum = true;
            } else {
                return false;
            }
        }
        return isNum;
    }
}
```

# [LeetCode_1239_1_串联字符串的最大长度](https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/)
## 题目
给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。

请返回所有可行解 s 中最长长度。

示例 1：
```
输入：arr = ["un","iq","ue"]
输出：4
解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
```

示例 2：
```
输入：arr = ["cha","r","act","ers"]
输出：6
解释：可能的解答有 "chaers" 和 "acters"。
```

示例 3：
```
输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
输出：26
```

提示：

* 1 <= arr.length <= 16
* 1 <= arr[i].length <= 26
* arr[i] 中只含有小写英文字母

## 理解
dfs递归+位图，递归的终止条件为index达到最大值，递归函数的确立，递归函数需要返回串联字符串
的最大长度，所以返回值为int类型，需要有目标数组arr，当前遍历的坐标index，以及标记最大串中
包含字符的位图（26位二进制）传参时使用int。  
最小处理单元，每个子字符串都可能包含两种状态，选择与不选：  
* 不选，则p1等于递归调用index+1，p1 = dfs(arr, index+1, bitMap)
* 选择，则需要利用位图判断字符串是否可以选（无重复字符），可选时p2 = str.lengh + dfs(arr, index+1, bitMap)

结果返回p1，p2中的较大值。

### 代码
```java
public class LeetCode_1239_1_串联字符串的最大长度 {
    public int maxLength(List<String> arr) {
        return dfs(arr, 0, 0);
    }

    /**
     * dfs函数
     *
     * @param arr    目标数组
     * @param index  遍历的坐标
     * @param bitMap 当前字符串包含的字符，用26位二进制表示（0代表无此字符，1代表有此字符），转化为了int传输
     * @return
     */
    private int dfs(List<String> arr, int index, int bitMap) {
        // 遍历结束后返回0，递归结束条件
        if (index == arr.size()) {
            return 0;
        }
        // 获取目标子字符串
        String s = arr.get(index);
        // 不选index位置的字符串
        int p1 = dfs(arr, index+1, bitMap);

        // 选择index位置的字符串（是否符合选择的条件）
        int p2 = 0;
        boolean can = true;
        for (char c : s.toCharArray()) {
            // 判断该字符是否已经包含
            if ((bitMap & (1 << c-'a')) != 0) {
                can = false;
                break;
            } else {
                // 修改该字符标记位，代表已存在
                bitMap |= 1 << c-'a';
            }
        }
        if (can) {
            p2 = s.length() + dfs(arr, index+1, bitMap);
        }
        return Math.max(p1, p2);
    }
}
```

# [LeetCode_1600_1_皇位继承顺序](https://leetcode-cn.com/problems/throne-inheritance/)
## 题目
一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。

这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。我们定义递归函数 Successor(x, curOrder) ，给定一个人 x 和当前的继承顺序，该函数返回 x 的下一继承人。

```
Successor(x, curOrder):
    如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：
        如果 x 是国王，那么返回 null
        否则，返回 Successor(x 的父亲, curOrder)
    否则，返回 x 不在 curOrder 中最年长的孩子
```

比方说，假设王国由国王，他的孩子 Alice 和 Bob （Alice 比 Bob 年长）和 Alice 的孩子 Jack 组成。

1. 一开始， curOrder 为 ["king"].
2. 调用 Successor(king, curOrder) ，返回 Alice ，所以我们将 Alice 放入 curOrder 中，得到 ["king", "Alice"] 。
3. 调用 Successor(Alice, curOrder) ，返回 Jack ，所以我们将 Jack 放入 curOrder 中，得到 ["king", "Alice", "Jack"] 。
4. 调用 Successor(Jack, curOrder) ，返回 Bob ，所以我们将 Bob 放入 curOrder 中，得到 ["king", "Alice", "Jack", "Bob"] 。
5. 调用 Successor(Bob, curOrder) ，返回 null 。最终得到继承顺序为 ["king", "Alice", "Jack", "Bob"] 。

通过以上的函数，我们总是能得到一个唯一的继承顺序。

请你实现 ThroneInheritance 类：

* ThroneInheritance(string kingName) 初始化一个 ThroneInheritance 类的对象。国王的名字作为构造函数的参数传入。
* void birth(string parentName, string childName) 表示 parentName 新拥有了一个名为 childName 的孩子。
* void death(string name) 表示名为 name 的人死亡。一个人的死亡不会影响 Successor 函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
* string[] getInheritanceOrder() 返回 除去 死亡人员的当前继承顺序列表。
 

示例：
```
输入：
["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
[["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
输出：
[null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]

解释：
ThroneInheritance t= new ThroneInheritance("king"); // 继承顺序：king
t.birth("king", "andy"); // 继承顺序：king > andy
t.birth("king", "bob"); // 继承顺序：king > andy > bob
t.birth("king", "catherine"); // 继承顺序：king > andy > bob > catherine
t.birth("andy", "matthew"); // 继承顺序：king > andy > matthew > bob > catherine
t.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
t.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
t.death("bob"); // 继承顺序：king > andy > matthew > bob（已经去世）> alex > asha > catherine
t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]

```

提示：

* 1 <= kingName.length, parentName.length, childName.length, name.length <= 15
* kingName，parentName， childName 和 name 仅包含小写英文字母。
* 所有的参数 childName 和 kingName 互不相同。
* 所有 death 函数中的死亡名字 name 要么是国王，要么是已经出生了的人员名字。
* 每次调用 birth(parentName, childName) 时，测试用例都保证 parentName 对应的人员是活着的。
* 最多调用 105 次birth 和 death 。
* 最多调用 10 次 getInheritanceOrder 。

## 理解
解法一：继承顺序是先是自己，再是长子，如果长子有孩子，是长子的孩子，后面如果没了再是次子，这顺序与
二叉树的前序遍历顺序一致，我们可以利用map来存储某个人的所有的儿子，在利用set存储所有死亡的
人，再记录下国王的名字，继承顺序则是以国王为根节点进行前序遍历，遍历过程中需要判断该人是否
已经死亡，如果没有则加入继承列表。

解法二：利用单向链表加标记清除。
## 解法一
### 代码
```java
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
```

## 解法二
### 代码
```java
public class LeetCode_1600_2_皇位继承顺序 {
    static class Node {
        Node next;
        // 记录最后一个儿子
        Node last;
        String name;
        boolean isDeaded = false;

        public Node (String name) {
            this.name = name;
        }
    }

    Map<String, Node> map = new HashMap<>();
    Node head = new Node("");
    public LeetCode_1600_2_皇位继承顺序(String kingName) {
        Node node = new Node(kingName);
        map.put(kingName, node);
        head.next = node;
    }

    public void birth(String parentName, String childName) {
        Node child = new Node(childName);
        map.put(childName, child);
        Node p = map.get(parentName);
        Node tmp = p;
        while (tmp.last != null) {
            tmp = tmp.last;
        }
        child.next = tmp.next;
        tmp.next = child;
        p.last = child;
    }

    public void death(String name) {
        Node node = map.get(name);
        node.isDeaded = true;
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<>();
        Node node = head.next;
        while (node != null) {
            if (!node.isDeaded) {
                ans.add(node.name);
            }
            node = node.next;
        }
        return ans;
    }
}
```

# [LeetCode_483_1_最小好进制](https://leetcode-cn.com/problems/smallest-good-base/)
## 题目
对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。

以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。

示例 1：
```
输入："13"
输出："3"
解释：13 的 3 进制是 111。
```

示例 2：
```
输入："4681"
输出："8"
解释：4681 的 8 进制是 11111。
```

示例 3：
```
输入："1000000000000000000"
输出："999999999999999999"
解释：1000000000000000000 的 999999999999999999 进制是 11。
```

提示：

* n的取值范围是 [3, 10^18]。
* 输入总是有效且没有前导 0。

## 理解
基本分析，假设十进制n的k进制表示共有len位，那么我们肯定有：  
n(10) = 11...11(k) = k^0 + k^1 + k^2 + …………… + k^len-1  
在n值给定的情况下，k值随着len的减小而增大，由此我们可以推出len的最大值：  
* 当len取1时，k^0 = n，因此n=1，由于题目n的取值范围的限制[3, 10^18]，不满足题意所以排除
* 当len取2时，k^0 + k^1 = n，k = n-1，所以最大的好二进制就是n-1  

因为k的上限为n-1，len应该满足>=2，再分析一下len的上限：  
因为len随着k的减小而增大，当k取最小值2时，len达到最大，因为题意给出n的最大值为10^18，因此
可以分析出len应该小于等于log以2为底的10^18，计算出来大约是60。

我们再[2,max（60）]之前遍历长度，注意从大到小遍历，如果找到满足条件的k，那就是最小k直接返回。  
当len>=2时，我们令s = len-1，再根据二项式定律有：  
k^0 + k^1 + k^2 + …………… + k^s = n < a*k^0 + a1*k^1 + a2*k^2 + ………… + as*k^s = (k + 1)^s  
变形得： k^s < n < (k+1)^s，对表达式整理得：k < n^1/s < k+1 ，所以对于任意的s = len - 1,
都有唯一的正整数解n^1/s，我们只需要按题意计算看是否能获取到结果n即可。

### 代码
```java
public class LeetCode_483_1_最小好进制 {
    public String smallestGoodBase(String n) {
        long m = Long.parseLong(n);
        // log以2为底的m求取最大长度，因为转为int为向下取整所以加了1
        int max = (int)(Math.log(m) / Math.log(2) + 1);
        // 遍历所有长度到3，len=2时单独处理
        for (int i = max; i >= 3 ; i--) {
            // 计算进制数
            long k = (long)Math.pow(m, 1.0 / (i - 1));
            long ans = 0;
            for (int j = 0; j < i; j++) {
                ans = ans * k + 1;
            }
            if (ans == m) {
                return String.valueOf(k);
            }
        }
        // len为2时，k为n-1
        return String.valueOf(m - 1);
    }
}
```
