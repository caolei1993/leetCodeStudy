package main.java.com.exercise.week_001;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/1/6 9:32
 * @Version 1.0
 */
public class LeetCode_399_1_除法求值 {
//    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
//
//    }
    public enum ABC{
        A,
        B,
        C
    }

    public static String digitSum(String s, int k) {
        int len = s.length();
        if (len <= k) {
            int sum = 0;
            for (char c : s.toCharArray()) {
                sum += c - '0';
            }
            return String.valueOf(sum);
        }

        int count = len / k;
        if (count * k < len) {
            count++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int start = i * k, end = (i + 1) * k;
            if (i == count - 1) {
                end = len;
            }
            String cur = s.substring(start, end);
            int sum = 0;
            for (char c : cur.toCharArray()) {
                sum += c - '0';
            }
            sb.append(sum);
        }
        return digitSum(sb.toString(), k);
    }

    public static List<String> subStr(String str) {
        int n = str.length();
        boolean[] vis = new boolean[n];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(str.charAt(i));
            vis[i] = true;
            dfs(str, sb, vis, set);
        }
        return new ArrayList<>(set);
    }

    private static void dfs(String str, StringBuilder sb, boolean[] vis, Set<String> set) {
        for (int i = 0; i < str.length(); i++) {
            if (vis[i]) {
                continue;
            }
            sb.append(str.charAt(i));

        }

    }


    public static void main(String[] args) {

//        LocalDate now = LocalDate.of(2022,7,4);
//        System.out.println(now);
//        int year = now.getYear();
//        System.out.println(year);
//        Month month = now.getMonth();
//        System.out.println(month);
//        int daysOfMonth = now.lengthOfMonth();
//        System.out.println(daysOfMonth);
//        DayOfWeek dow = now.getDayOfWeek();
//        System.out.println(dow);
//        boolean leap = now.isLeapYear();
//        System.out.println(leap);
//        LocalTime time1 = LocalTime.of(12, 34,13);
//        LocalTime time2 = LocalTime.of(11, 39,14);
//        Duration duration1 = Duration.between(time1, time2);
//        System.out.println(duration1);
//
//        LocalDate date1 = LocalDate.of(2011, 11, 11);
//        LocalDate date2 = date1.withYear(2022);
//        System.out.println(date1);
//        System.out.println(date2);
//        LocalDate date3 = date1.with(temporal -> temporal.plus(1, ChronoUnit.DAYS));
//        System.out.println(date3);
//        LocalDate date4 = date1.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(date4);
//        List<Integer> list = new ArrayList<>();
//        list.add(5);
//        list.add(3);
//        list.add(2);
//        list.add(4);
//        list.add(1);
//        Collections.sort(list);
//        System.out.println(Collections.binarySearch(list, 8));
//        System.out.println(list);

//        TreeMap<Integer,Integer> map = new TreeMap<>();
//        map.put(1, 1);
//        map.put(1, 2);
//        System.out.println(map.get(1));

//        String a = "abcd";
//        String b = "cd";
//        System.out.println(a.indexOf(b));
//        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + "start1");
//                    queue.put(1);
//                    System.out.println(Thread.currentThread().getName() + "end1");
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + "start2");
//                    Thread.sleep(10000);
//                    queue.take();
//                    System.out.println(Thread.currentThread().getName() + "end2");
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        System.out.println(Long.parseLong("20210508110300010177000010077000"));
//        System.out.println(5 ^ 5);
//        System.out.println(0 ^ 5);
//        System.out.println(4 ^ 5);
//        System.out.println(ABC.A == null);
//        Stack<String> stack = new Stack<>();
//        stack.push(null);
//        System.out.println(stack.empty());
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.pop());

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        System.out.println(list.subList(0,3));

//        StringBuilder builder = new StringBuilder();
//        String[] strArray = "年级:3;姓名:我".split(";");
//        for (String str : strArray) {
//            builder.append(str.split(":")[1]).append(" ");
//        }
//        System.out.println(builder.toString());
//        int[] inorder = new int[]{1,2,3,4,5,6};
//        List<Integer> inorderList = Arrays.stream(inorder).boxed().collect(Collectors.toList());
//        System.out.println(inorderList.get(0));
//        System.out.println(inorderList.toString());
//        System.out.println(inorderList.indexOf(3));
//        String s = "15";
//        System.out.println(s.matches("\\d+"));
//        System.out.println("121222在1线，123456".contains("在线"));

//        LocalDate date = LocalDate.of(2021,3,1);
//        LocalDate date1 = LocalDate.of(2021,3,11);
//        System.out.println(date);
//        System.out.println(date.getYear());
//        System.out.println(date.getMonth());
//        System.out.println(date.getDayOfMonth());
//        System.out.println(date.getDayOfWeek());
//        System.out.println(date.isLeapYear());
//        LocalDate now = LocalDate.now();
//        System.out.println(now);
//        LocalDate now1 = now.withMonth(5);
//        System.out.println(now1);
//        LocalDate now2 = now1.withDayOfMonth(13);
//        System.out.println(now2 + "~~~~~~");
//        LocalDate now3 = now2.withDayOfYear(1);
//        System.out.println(now3);
//        LocalTime time = LocalTime.of(12,54,48,987);
//        System.out.println(time);
//        LocalTime time1 = LocalTime.parse("14:00:00.1");
//        System.out.println(time1);
//        LocalDateTime dateTime = LocalDateTime.of(2021,3,8,15,21,50);
//        System.out.println(dateTime);
//
//        LocalDateTime dateTime1 = LocalDateTime.of(date, time);
//        System.out.println(dateTime1);
//        Instant instant = Instant.now();
//        System.out.println(instant);
//        Duration duration = Duration.between(time, time1);
//        System.out.println(duration);
//        Period period = Period.between(date, date1);
//        System.out.println(period);
//        String a =
//        int[] nums = new int[]{ 1, 2, 3};
//        // 搜索区间为[l,r)
//        int low = 0;
//        int high = nums.length - 1;
//        while (low < high) {
//            int pivot = low + (high - low) / 2;
//            if (nums[pivot] < nums[high]) {
//                high = pivot;
//            } else {
//                low = pivot + 1;
//            }
//        }
//
//        System.out.println("low = "+ low + ", high = "+ high);

//        String[] str = new String[]{"30","3","334"};
//        Arrays.sort(str, (a, b) -> (b + a).compareTo(a + b));
//        System.out.println(Arrays.toString(str));
//        String a = " 113";
//        char[] ch = a.toCharArray();
//        System.out.println(Arrays.toString(ch));
//        int i = ch[0] - '0';
//        System.out.println(i);
//        System.out.println((int)1e8);
    }
}
