[toc]

# [LeetCode_447_1_回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs/)
## 题目
给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。

示例 1：
```
输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
```

示例 2：
```
输入：points = [[1,1],[2,2],[3,3]]
输出：2
```

示例 3：
```
输入：points = [[1,1]]
输出：0
```
 

提示：

* n == points.length
* 1 <= n <= 500
* points[i].length == 2
* -10^4 <= xi, yi <= 10^4
* 所有点都 互不相同

## 理解
我们可以在给定i的情况下，统计与i距离相等的j,k的组合数有多少。  
我们可以借助哈希表预处理，在给定i的情况下，统计所有点与i的距离，存入哈希表中，key为距离，value为点的
个数，然后分别对所有距离进行累加处理。  
为了方便计算，避免使用sqrt，我们直接使用x^2 + y^2来指两个点之间的距离。  
时间复杂度为O(n^2)，空间复杂度为O(n)
### 代码
```java
public class LeetCode_447_1_回旋镖的数量 {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                int value = x * x + y * y;
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            for (Integer key : map.keySet()) {
                int cnt = map.get(key);
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }
}
```

# [LeetCode_524_1_通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)
## 题目
给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。

如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。

 
示例 1：
```
输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
输出："apple"
```

示例 2：
```
输入：s = "abpcplea", dictionary = ["a","b","c"]
输出："a"
```
 

提示：

* 1 <= s.length <= 1000
* 1 <= dictionary.length <= 1000
* 1 <= dictionary[i].length <= 1000
* s 和 dictionary[i] 仅由小写英文字母组成

## 理解
排序+双指针+贪心  
因为题目要返回长度最长的子串，如果长度相等则返回字典序最小的子串，所以我们先对给定的字典集合进行排序，
排序优先按长度，长度按从大到小，如果长度相等，则按字典排序。  
接着我们遍历排好序的集合，利用双指针看当前字符串是否是s的子串，如果是直接返回，否则遍历下一个，全都
遍历完，没有找到，返回空字符串。

### 代码
```java
public class LeetCode_524_1_通过删除字母匹配到字典里最长单词 {
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        int n = s.length();
        for (String ss : dictionary) {
            int m = ss.length();
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (s.charAt(i) == ss.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == m) {
                return ss;
            }
        }
        return "";
    }
}
```