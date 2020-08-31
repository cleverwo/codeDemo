package LeetCodeDemo.eassy;

import java.nio.channels.Pipe;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/8/24 23:45
 * @Description: 每日一题
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
        int a = (s + s).indexOf(s, 1);
        if (a != s.length()) {
            return true;
        }
        return false;
    }

    // 用kmp代替indexOf
    public boolean repeatedSubstringPattern3(String s) {
        // s+s 中去点首尾 是否存在 s
        return kmp(s + s, s);
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
        return null;
    }

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> findSubsequences2(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return result;
    }

    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                result.add(new ArrayList<Integer>(temp));
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
    }

    //17. 电话号码的字母组合 8-26
    public List<String> letterCombinations(String digits) {
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backup(result, map, digits, 0, new StringBuffer());
        return result;
    }

    private void backtrack(
            List<String> result, Map<String, String> map, String digits,
            int index, StringBuffer combination) {
        if (index == digits.length()) {
            result.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(result, map, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public void backup(List<String> result, Map<String, String> map,
                       String digits, int index, StringBuffer path) {
        if (index == digits.length()) {
            result.add(path.toString());
        } else {
            String num = digits.substring(index, index + 1);
            String value = map.get(num);
            for (int i = 0; i < value.length(); i++) {
                path.append(value.charAt(i));
                backup(result, map, digits, index + 1, path);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    //332. 重新安排行程 8.27
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) { //  将数据 放到map中
                map.put(src, new PriorityQueue<String>());// value 为优先队列
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) { //存在键值且value不为空
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }


    //657. 机器人能否返回原点 8-28
    public boolean judgeCircle(String moves) {
        Map<Character, int[]> map = new HashMap<>();
        map.put('U', new int[]{0,1});
        map.put('D', new int[]{0,-1});
        map.put('L', new int[]{1,0});
        map.put('R', new int[]{-1,0});
        int[] point = {0,0};
        for (int i =0;i<moves.length();++i){
            char c = moves.charAt(i);
            int[] move = map.get(c);
            point[0] += move[0];
            point[1] += move[1];
        }
        if (point[0] == 0&& point[1]==0){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.judgeCircle("LL"));


    }
}
