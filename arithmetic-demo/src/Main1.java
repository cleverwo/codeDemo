import OfferDemo.node.ListNode;

import java.util.*;

public class Main1 {
    //打印链表
    public static void printListNode(ListNode r){
        while (r!=null){
            System.out.print(r.val +  " ");
            r= r.next;
        }
    }
    //新建链表
    public static ListNode addListNode(int[] num){
        ListNode r = new ListNode(0);
        ListNode p = r;
        for (int i =0;i<num.length;i++){
            ListNode n = new ListNode(num[i]);
            ListNode q = p.next;
            p.next = n;
            n.next = q;
            p = p.next;
        }
        return r.next;
    }

    //1.俩数之和 注意用hashmap中存的是原值
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cha = target - nums[i];
            if (map.containsKey(cha)) {
                return new int[]{map.get(cha), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    //4.有序链表中位数  注意参数是下标还是第几个数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int len = m + n;
        int left = (len + 1) / 2;//①注意这里的left和right的数不是下标，是第几个数
        int right = (len + 2) / 2;
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;

    }
    public static int getKth(int[] nums1, int a, int b, int[] nums2, int c, int d, int k) {
        int n = b - a + 1;//②入参abcd是下标，k是指第几个数，不是下标
        int m = d - c + 1;
        //情况1 调整n为最短的数组，m为长数组
        if (n > m) {
            return getKth(nums2, c, d, nums1, a, b, k);
        }
        //情况2 n数组为空， 只考虑m数组
        if (n == 0) {
            return nums2[c + k - 1];
        }
        //情况3 k为1，只考虑n和m的第一个值
        if (k == 1) {
            return Math.min(nums1[a], nums2[c]);
        }
        //情况4 正常情况，取n和m的中值比较删除多余的
        int i = a + Math.min(k / 2, n) - 1;//i和j指向下标， a+len 表示的时比较第几个数
        int j = c + Math.min(k / 2, m) - 1;
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, a, b, nums2, j + 1, d, k - (j - c + 1));
        } else {
            return getKth(nums1, i + 1, b, nums2, c, d, k - (i - a + 1));
        }
    }

    //206. 反转链表 头插法
    public ListNode reverseList(ListNode head) {
        //头插法建链表
        ListNode r = new ListNode(0);
        ListNode p = head;
        while (p != null) {
            head = head.next;
            p.next = r.next;
            r.next = p;
            p = head;
        }
        return r.next;
    }

    //5. 最长回文子串 暴力法，必须会 注意stringBuffer 的reverse方法，String 是没有的
    public String longestPalindrome(String s) {
        //暴力遍历
        int n = s.length();
        String max = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                if (isPair(sub) && sub.length() > max.length()) {
                    max = sub;
                }
            }
        }
        return max;
    }
    public static boolean isPair(String sub) {
        String reve = new StringBuilder(sub).reverse().toString();
        if (sub.equals(reve)) {
            return true;
        }
        return false;
    }
    public static String longestPalindrome1(String s) {
        //构建dp[][]数组 例如abbc
        int n = s.length();
        boolean[][] dp = new boolean[n+1][n+1];
        String max = "";
        //②沿主对角线从下到上遍历 0,0- 1,1,0,1 -2,2-1,2-0,2- 。。。
        for (int j=0;j<n+1;j++){
            for (int i = j;i>=0;i--){
                //① 默认让对角线和第二对角线为真
                if (i==j || i == j-1){
                    dp[i][j] = true;
                }else if (dp[i+1][j-1]&&s.charAt(i)==s.charAt(j-1)){
                    // ③比较两边的字母和他斜对角线下的dp的真假
                    dp[i][j] = true;
                }
                if (dp[i][j]){
                    if (j-i>max.length()){
                        max = s.substring(i,j);
                    }
                }
            }
        }
        return max;
    }

    //2. 两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r = new ListNode(0);
        ListNode p = r;
        int val = 0;
        int out = 0;
        while (l1!=null||l2!=null || out!=0){
            val = (l1==null?0:l1.val) + (l2==null?0:l2.val) + out;
            if (val>=10){
                out = val/10;
                val = val%10;
            }else{
                out = 0;
            }
            p.next = new ListNode(val);
            p = p.next;
            l1 = l1==null?null:l1.next;
            l2 = l2==null?null:l2.next;
        }
        return r.next;
    }

    //3. 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int max = 0,i = 0, j = 0;
        while (i<n&&j<n){
            char c = s.charAt(j);
            if (!set.contains(s.charAt(j))){
                set.add(c);
                max = Math.max(max,j-i+1);
                System.out.println(s.substring(i,j+1));
                j++;
            }else{
                char cc = s.charAt(i);
                set.remove(cc);
                i++;
            }
        }
        return max;

    }
    public static int lengthOfLongestSubstring1(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int n = s.length();
        int max =0;
        for (int i=0,j=0;j<n;j++){
            char c = s.charAt(j);
            if (map.containsKey(c)){
                i = Math.max(map.get(c),i);
            }
            max = Math.max(max,j-i+1);
            map.put(c,j+1);
            System.out.println(s.substring(i,j+1));
        }
        return max;

    }

    //146. LRU缓存机制 这是一个类看146吧

    //21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode home = new ListNode(0);
        ListNode r = home;
        while (l1!=null&&l2!=null){
            ListNode p = l1.val>l2.val?l2:l1;
            if (p==l1){
                l1 = l1.next;
            }else{
                l2 = l2.next;
            }
            ListNode q = r.next;
            r.next = p;
            p.next = q;
            r = r.next;
        }
        if (l1==null){
            r.next = l2;
        }else{
            r.next = l1;
        }
        return home.next;
    }

    //15. 三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i=0;i<n;i++){
            if (i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = n-1;
            while (left<right){
                List<Integer> list1 = new ArrayList<>();
                int target = nums[i] + nums[left]+ nums[right];
                if (target==0){
                    list1.add(nums[i]);
                    list1.add(nums[left]);
                    list1.add(nums[right]);
                    list.add(list1);
                    left++;
                    right--;
                    while(left<n&&nums[left]==nums[left-1]){
                        left++;
                    }
                    while(right>i&&nums[right]==nums[right+1]){
                        right--;
                    }
                }else if (target > 0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return list;
    }

    //221. 最大正方形
    public static int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = n>0?matrix[0].length:-1;
        int max = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (matrix[i][j]=='1'){
                    int sqlen = 1;
                    boolean flag = true;
                    while (i+sqlen<n&&j+sqlen<m&&flag){
                        for (int k=i;k<=i+sqlen;k++){//k=i,遍历i到i+sqlen，表示列不动
                            if (matrix[k][j+sqlen]=='0'){
                                flag = false;
                                break;
                            }
                        }
                        if (flag){
                            for (int k=j;k<=j+sqlen;k++){
                                if (matrix[i+sqlen][k]=='0'){
                                    flag=false;
                                    break;
                                }
                            }
                        }
                        if (flag){
                            sqlen++;
                        }
                    }
                    max = Math.max(max,sqlen);
                }
            }
        }
        return max*max;
    }
    public static int maximalSquare1(char[][] matrix){
        int n = matrix.length;
        int m = n>0?matrix[0].length:-1;
        int max =0;
        int[][] dp = new int[n+1][m+1];
        for (int i =1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (matrix[i-1][j-1]=='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])+1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }

    //53. 最大子序和
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        //遍历数组
        //i表示个数
        for (int i=1;i<=n;i++){
            // 从j开始遍历
            for (int j=0;j<=n-i;j++){
                int curr = 0;
                //遍历个数
                for (int k=j;k<j+i;k++){
                    curr += nums[k];
                    max = Math.max(curr,max);
                }
            }
        }
        return max;
    }
    public static int maxSubArray1(int[] nums){
        return helper(nums,0,nums.length-1);
    }
    private static int helper(int[] nums,int left,int right){
        if(left==right) return nums[left];
        int p = (left+right)/2;
        int leftsum= helper(nums,left,p);
        int rightsum = helper(nums,p+1,right);
        int allsum = cross(nums,left,right,p);
        return Math.max(Math.max(leftsum,rightsum),allsum);
    }
    private static int cross(int[] nums,int left,int right,int p){
        if (left == right) return nums[left];
        int leftsum = Integer.MIN_VALUE;
        int leftcurr = 0;
        for(int i=p;i>=left;i--){
            leftcurr += nums[i];
            leftsum = Math.max(leftcurr,leftsum);
        }
        int rightsum = Integer.MIN_VALUE;
        int rightcurr = 0;
        for(int i=p+1;i<=right;i++){
            rightcurr += nums[i];
            rightsum = Math.max(rightcurr,rightsum);
        }
        return rightsum+leftsum;
    }
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    //46. 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        for(int a : nums){
            num.add(a);
        }
        dfs(lists,num,0);
        return lists;
    }
    public static void dfs(List<List<Integer>> lists,List<Integer> curr,int n){
        //回溯法
        if(n==curr.size()-1){
            lists.add(new ArrayList<>(curr));
        }else{
            for(int i=n;i<curr.size();i++){
                Collections.swap(curr,n,i);
                dfs(lists,curr,n+1);
                Collections.swap(curr,i,n);
            }
        }

    }

    //17. 电话号码的字母组合
    public static List<String> letterCombinations(String digits){
        List<String> result = new ArrayList<>();
        if (digits==null||digits.length()==0){
            return result;
        }
        backUp("",result,digits);
        return result;
    }
    static Map<String,String> numbersMap = new HashMap<String,String>(){
        //第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class)
        // 第二层括弧实际上是一个实例初始化块 (instance initializer block)
        // 这个块在内部匿名类构造时被执行。
        {
            put("2","abc");
            put("3","def");
            put("4","ghi");
            put("5","jkl");
            put("6","mno");
            put("7","pqrs");
            put("8","tuv");
            put("9","wxyz");
        }
    };
    public static void backUp(String curr,List<String> result,String next_digits){
        if (next_digits.length()==0){
            result.add(new String(curr));
        }else{
            String digits = next_digits.substring(0,1);
            String letter = numbersMap.get(digits);
            for (int i=0;i<letter.length();i++){
                String let = letter.substring(i,i+1);
                backUp(curr+let,result,next_digits.substring(1));
            }
        }
    }

    //22.括号生成 回溯加减法
    public static List<String> generateParenthesis(int n){
        List<String> list = new ArrayList<>();
        if (n<=0){
            return list;
        }
        dfs(list,"",n,n);
        return list;
    }
    public static void dfs(List<String> list,String curr, int left,int right){
        if (left == 0&&right ==0){
            list.add(new String(curr));
            return;
        }
        if (left>right){
            return;
        }
        if (left>0){
            dfs(list,curr+"(",left-1,right);
        }
        if (right>0){
            dfs(list,curr+")",left,right-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }


}
