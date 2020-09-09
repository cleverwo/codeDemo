package OfferDemo;


import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/6 17:38
 * @Description:
 * 中等 字符串的排列
 */
public class Offer38 {
    //38 字符串排序 全排序
    /*
    * 全排序： 分三步， 按从小到大排序 即字典序
    * 从后往前找 正序对 确定i 的位置，在从后往前找比 i 大的最小数字
    * 交互 i 和 j的位置
    * 反转i+1 ~ length 的元素
    * 这就是下一个字典序
    *
    * 从到小则 找逆序对， j为 比i小的最大数字
     */
    public String[] permutation(String s) {
        if(s.length() == 0){
            return new String[]{};
        }
        int n = s.length();
        List<String> result = new ArrayList<>();
        char[] c = s.toCharArray();
        Arrays.sort(c);
        s = new String(c);
        result.add(s);
        while(true){
            s = nextStr(s);
            if(s.equals("#")){
                break;
            }
            result.add(s);
        }
        String[] strs = new String[result.size()];
        for (int i=0;i<result.size();i++){
            strs[i] = result.get(i);
        };
        return strs;
    }
    public String nextStr(String str){
        int n = str.length();
        char[] c = str.toCharArray();
        int i = n-2;
        // 找正序对
        while(i>=0&&c[i] >= c[i+1]){
            i --;
        }
        if(i<0){
            return "#";
        }
        // 从i-j找第一个大于i的最小数组
        int j = n-1;
        for(;j>=0&&c[j]<=c[i];j--){}
        // 交互 i=j
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
        // 反转i以后的元素
        for(int a =i+1,b=n-1;a<b;a++,b--){
            char mid = c[b];
            c[b] = c[a];
            c[a] = mid;
        }
        return new String(c);

    }
    //      dfs
    Set<String> set = new HashSet<>();
    char[] temp;
    public String[] permutation2(String s) {
        if(s.length()==0){
            return new String[]{};
        }
        temp = new char[s.length()];
        int k = 0;
        List<Character> curr = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            curr.add(s.charAt(i));
        }
        next(k,s.length(),curr);
        String[] result = new String[set.size()];
        int i = 0;
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            result[i] = iterator.next();
            i++;
        }
        return result;
    }
    public void next(int k,int length, List<Character> list){
        if(k == length){
            String s = new String(temp);
            set.add(s);
        }else{
            for(int i=0;i<list.size();i++){
                char c = list.get(i);
                temp[k] = c;
                List<Character> curr = new ArrayList<>(list);
                curr.remove(i);
                next(k+1,length,curr);
            }
        }
    }



}
