package OfferDemo.test;

import _modal.TreeNode;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/1 15:05
 * @Description:
 */
public class Offer {

    // 62 与瑟夫环  数学问题
    // 38 字符串排序，全排序问题
    // 29 顺时针打印数组
    // 46 把数字翻译成字符串
    // 13 机器人运动范围
    // 42. 连续子数组的最大和 Test30

    // 07 先序中序遍历二叉树
    Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        int m = inorder.length;
        if(n != m){
            throw new RuntimeException("异常");
        }
        map = new HashMap<>();
        for(int i=0;i<m;i++){
            map.put(inorder[i],i);
        }
        TreeNode root = buildTree(preorder,0,n-1,inorder,0,m-1);
        return root;
    }
    public TreeNode buildTree(int[] pre, int pres, int pree,int[] in, int ins, int ine){
        if(pres < pree || ins < ine){
            return null;
        }
        int val = pre[pres];
        TreeNode t = new TreeNode(val);
        int index = map.get(val);
        t.left = buildTree(pre,pres+1,pres+index-ins,in,ins,index-1);
        t.right = buildTree(pre,pres+index-ins+1,pree,in,index+1,ine);
        return t;
    }

    //20 表示数值的字符串   数学问题
    public boolean isNumber(String s) {
        //if (!s.trim().matches("^[+-]?(((\\d+\\.?)|(\\.\\d+))|(\\d+\\.\\d+))([eE][+-]?\\d+)?$")){
        if (s == null || s.length() == 0) return false;
        //去掉首位空格
        s = s.trim();
        boolean numFlag = false;
        boolean dotFlag = false;
        boolean eFlag = false;
        for (int i = 0; i < s.length(); i++) {
            //判定为数字，则标记numFlag
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numFlag = true;
                //判定为.  需要没出现过.并且没出现过e
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
                //判定为e，需要没出现过e，并且出过数字了
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !eFlag && numFlag) {
                eFlag = true;
                numFlag = false;//为了避免123e这种请求，出现e之后就标志为false
                //判定为+-符号，只能出现在第一位或者紧接e后面
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {

                //其他情况，都是非法的
            } else {
                return false;
            }
        }
        return numFlag;
    }

    //56-I 数组中数字出现的次数 位运算
    public int[] singleNumbers(int[] nums) {
        int sum = 0;
        int[] res = new int[2];
        for(int num : nums){
            sum ^= num;
        }
        int lowbit = sum & (-sum);
        for(int num : nums){
            if((num & lowbit) == 0){
                res[0] ^= num;
            }else{
                res[1] ^= num;
            }
        }
        return res;
    }
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    // 46 把数字翻译成字符串 动规+滚动数组

    // 13 机器人移动  dfs
    //      对应 右，左，上，下
    private final int dx[] = {1, -1, 0, 0};
    private final int dy[] = {0, 0, 1, -1};
    //      获取坐标x的数位之和
    public int sum(int x) {
        int ans = 0;
        while (x > 0) {
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }
    //      移动坐标 dfs
    public int move(int threshold, int rows, int cols, int x, int y, boolean[][] vis) {
        vis[x][y] = true;
        int ans = 0;
        for (int i = 0; i < 4; i++) { // 向四个方向走
            int X = x + dx[i];
            int Y = y + dy[i];
            // x，y 大于0，x，y不能到负坐标
            if (X >= 0 && Y >= 0 && X < rows && Y < cols && !vis[X][Y] && sum(X) + sum(Y) <= threshold) {
                ans += move(threshold, rows, cols, X, Y, vis) + 1;
            }
        }
        return ans;
    }
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        //标记坐标
        boolean[][] vis = new boolean[rows][cols];
        return move(threshold, rows, cols, 0, 0, vis) + 1;
    }

    //48 最长不重复字符串子串
    public int lengthOfLongestSubstring(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        // ①，用map存字符来判断是否重复，c字符，i表示最近最新c出现的位置
        Map<Character,Integer> map = new HashMap<>();
        int n = s.length();
        int max = 0;
        //② i，j就是滑动窗口的边界
        for (int i=0,j=0;j<n;j++){
            char c = s.charAt(j);
            if (map.containsKey(c)){
                i = Math.max(map.get(c),i);
            }
            //如果当前map不穿在c，则表示没有重复，那i的坐标还是0，就用j-i+1表示子串的长度。
            //如果map中存在了c，则需要重新定位i的位置，在定位i之前需要记录上一个无重复子串的大小，max用来存最大的子串长度
            max = Math.max(max,j-i+1);
            //c是字符， j+1表示如果重复，i的位置，不重复这个值就没意义了。
            map.put(c,j+1);
        }
        return max;
    }

    //45. 把数组排成最小的数
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        fastSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
    void fastSort(String[] strs, int l, int r) {
        if(l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while(i < j) {
            while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        fastSort(strs, l, i - 1);
        fastSort(strs, i + 1, r);
    }

    public static void main(String[] args) {
        Offer offer = new Offer();
        String c = offer.minNumber(new int[]{1,10,3});
        System.out.println(c);

    }
}
