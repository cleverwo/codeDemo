package LeetCodeDemo.eassy;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/8/24 23:45
 * @Description:
 * 每日一题
 */
public class Test {

    //459 重复的子字符串 类型：枚举 时间： n*n 空间： 1 8-24
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        // 遍历次数 是 【1，n/2】次，子串最长为 len的一半
        for (int i = 1; i * 2 <= len; ++i) {
            // 子串长度 n’ 能 让len整除
            if (len % i == 0) {
                boolean match = true;
                //任取 [n', n-1] 中的i 必有s[i] == s[i-n']
                for (int j = i; j < len; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
    //
    public boolean repeatedSubstringPattern2(String s) {
        int a = (s+s).indexOf(s,1);
        if (a != s.length()){
            return true;
        }
        return false;
    }
    // 用kmp代替indexOf
    public boolean repeatedSubstringPattern3(String s){
        // s+s 中去点首尾 是否存在 s
        return kmp(s+s,s);
    }
    public boolean kmp(String query, String pattern) {
        int n = query.length();
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail, -1);
        for (int i = 1; i < m; ++i) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int match = -1;
        for (int i = 1; i < n - 1; ++i) {
            while (match != -1 && pattern.charAt(match + 1) != query.charAt(i)) {
                match = fail[match];
            }
            if (pattern.charAt(match + 1) == query.charAt(i)) {
                ++match;
                if (match == m - 1) {
                    return true;
                }
            }
        }
        return false;
    }



    //491. 递增子序列 8-25
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> result = new ArrayList<>();
        return  null;
    }
    /*List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> findSubsequences1(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<Integer>(temp));
            }
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }*/

    public static void main(String[] args) {
        int[] test = {4,6,7,7};
        List<List<Integer>> list = new Test().findSubsequences1(test);
        System.out.println(list.toString());

    }

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> findSubsequences1(int[] nums) {
        dfs(0,Integer.MIN_VALUE,nums);
        return result;
    }

    public void dfs(int cur,int last,int[] nums){
        if(cur == nums.length){
            if(temp.size() >=2 ){
                result.add(new ArrayList<Integer>(temp));
            }
            return;
        }
        if(nums[cur] >= last){
            temp.add(nums[cur]);
            dfs(cur+1,nums[cur],nums);
            temp.remove(temp.size()-1);
        }
        if(nums[cur] != last){
            dfs(cur+1,last,nums);
        }
    }
}
