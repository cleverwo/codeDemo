package OfferDemo;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/2/17 23:36
 * @Description:
 * 27，字符串排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class Test27 {

    /**
     * 这是一道动态规划的题
     * 需要了解 全排序算法
     * https://blog.csdn.net/babynumber/article/details/42706757
     */

    /**
     * 1 字典序算法
     * 简单的讲：
     * 1、从右向左找到第一个正序对（array[i] < array[i+1]，因为没有等号，所以可以完美去掉重复的排列）记为i
     * 2、从i开始向右搜索，找到比array[i]大的字符中最小的那个，记为array[j]
     * 3、交换array[i]和array[j]
     * 4、将i后面的字符反转
     * 这就得到了字典序的下一个排列。
     * 连续使用这个方法则可从字典序最小的排列推出全部排列。
     * 时间复杂度O(n*n!)
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if(str.length() == 0){
            return res;
        }
        char [] array = str.toCharArray();
        //对array进行从小到大排序，得到字典序的第一个数据
        Arrays.sort(array);
        String s = new String(array);
        res.add(s);
        while(true){
            s = nextString(s);
            if(!s.equals("finish")) {
                res.add(s);
            }else{
                break;
            }
        }
        return res;
    }
    // 全排序算法
    public String nextString(String str){
        char [] array = str.toCharArray();
        int length = str.length();
        // 第一步，从右往左找第一个对正序对
        // i的起始位置为数组的倒数第二个元素
        int i = length-2;
        // i的判断是i从右往左找，找不大于左边界，且不升序的数i
        // i》= i+1 为找第一个正序对的逆， 在正数对上i会停止循环
        while(i>=0&&array[i] >= array[i+1]){
            i--;
        }
        // 如果i==-1 证明str为降序的数组，为字典排序的最后一个数，返回；
        if(i == -1){
            return "finish";
        }
        // 第二步，找出i开始到数组结尾中，比i大的最小数
        int j = length-1;
        // 从右到左，如果i》=j 则 j--，j停留在i《j的位置上
        for(; j>=0 && array[j] <= array[i]; j--){}
        //第三步，交换i j的位置
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        //第四步，反转 从i+1到结尾
        for(int a=i+1, b=length-1; a<b;a++,b--){
            // a指向头，b指向尾，首位互换
            tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
        }
        return new String(array);
    }

    /**
     * 答案2 回溯法
     * 回溯法其实是在构造一棵生成树。对于"abc"，第一个位置有三种取值，
     * 第二个位置有两种取值，第三个位置有一种取值。定义函数next(k)，
     * 表示向第k个位置写值。递归的关键是维护一个剩余字符集合。
     * 没能解决重复和有序问题
     */
    HashSet<String> res2 = new HashSet<String>();
    ArrayList<String> r = new ArrayList<String>();
    int length;
    char[] array;
    public ArrayList<String> Permutation2(String str) {
        if(str.length() == 0){
            return new ArrayList<String>();
        }
        length = str.length();
        array = str.toCharArray();
        res2 = new HashSet<String>();
        perm(0);
        r = new ArrayList<String>(res2);
        Collections.sort(r);
        return r;
    }
    public void perm(int k){
        if(k == length){
            String s = new String(array);
            if(!res2.contains(s)){
                res2.add(s);
            }
        }
        else{
            int j=k;
            for(; j<length; j++){
                swap(k, j);
                perm(k+1);
                swap(k, j);
            }
        }
    }
    public void swap(int i, int j){
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    Set<String> set = new HashSet<>();
    char[] temp;
    public String[] permutation3(String s) {
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
