package LeetCodeDemo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**是·
 * @Auther: 10413
 * @Date: 2020/3/17 12:05
 * @Description:
 * 17. 电话号码的字母组合
 */
public class Solution17 {
    /**
     * 暴力破解
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits==null||digits.length()==0){
            return list;
        }
        List<String> numbers = new ArrayList<>();
        numbers.add("abc");
        numbers.add("def");
        numbers.add("ghi");
        numbers.add("jkl");
        numbers.add("mno");
        numbers.add("pqrs");
        numbers.add("tuv");
        numbers.add("wxyz");
        char[] digit = digits.toCharArray();
        for (int i=0;i<digit.length;i++){

        }
        //放弃了，遍历次数太多了。。。
        return list;
    }

    /**
     * 答案： 回溯法 和 offer中二叉树的建立有点像,建立二叉树，忘了哪道题了，回去再看看
     * 回溯其实就是dfs 递归
     */
    Map<String,String> map = new HashMap<String,String>(){
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
    List<String> out = new ArrayList<>();
    public List<String> letterCombinations1(String digits){
        if (digits==null||digits.length()==0){
            return out;
        }
        backtrack("",digits);
        return out;
    }
    public void backtrack(String combination,String next_digits){
        if (next_digits.length()==0){
            out.add(combination);
        }else{
            String digit = next_digits.substring(0,1);
            String letter = map.get(digit);
            for (int i=0;i<letter.length();i++){
                String let = letter.substring(i,i+1);
                backtrack(combination+let,next_digits.substring(1));
            }
        }
    }
    public void output(){
        System.out.println(out.toString());
    }

    public static void main(String[] args) {
        Solution17 s = new Solution17();
        String numbers = "232";
        s.letterCombinations1(numbers);
        s.output();
    }
}
