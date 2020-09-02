package OfferDemo.test;

import _modal.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: 10413
 * @Date: 2020/9/1 15:05
 * @Description:
 */
public class Offer {
    // 03数组中重复的数字
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            boolean s = set.add(num);
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }

    //04. 二维数组中的查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        // i=0,j=m
        int i = 0, j = m - 1;
        while (i < n && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    // 05. 替换空格
    public String replaceSpace(String s) {
        return s.replaceAll(" ","%20");
    }

    // 07
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

    //20 表示数值的字符串
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

    //38 字符串排序


    public static void main(String[] args) {
        Offer offer = new Offer();
        String c = "3.e ";
        System.out.println(offer.isNumber(c));
    }
}
