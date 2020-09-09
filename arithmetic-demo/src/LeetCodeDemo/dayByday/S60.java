package LeetCodeDemo.dayByday;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/9/5 15:45
 * @Description:
 * 60. 第k个排列
 *
 * 全排序问题， 全排序为枚举，对那个元素进行全排序，获的字典序在按k取值就好来
 * 数学问题： n个数共n！个排序方式，则以1为开头的数的个数是 （n-1）！个
 *      if k < n-1 ! 则确定 a1 位 为1，
 */
public class S60 {

    // 全排序问题
    // 9-5
    public String getPermutation(int n, int k) {
        List<String> list = new ArrayList<>();
        StringBuffer str = new StringBuffer();
        for (int i=1;i<=n;i++){
            str.append(i);
        }
        list.add(str.toString());
        String next = nextStr(str.toString());
        while (next != "#"){
            list.add(next);
            next = nextStr(next);
        }
        return list.get(k-1);
    }

    private String nextStr(String str) {
        int n = str.length();
        char[] cs = str.toCharArray();
        // step 1
        int i = n-2;
        while(i>=0&&cs[i] >= cs[i+1]){
            i--;
        }
        if (i<0){
            return "#";
        }
        //step 2
        int j=n-1;
        for (;cs[j] <= cs[i];j--){}
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
        //step3
        for (int l =i+1,h=n-1;l<h;l++,h--){
            char curr = cs[l];
            cs[l] = cs[h];
            cs[h] = curr;
        }
        return new String(cs);
    }
    //-----------------------------------------------------------------------------
    public String getPermutation2(int n, int k) {
        if (k<=0){
            return  "";
        }
        StringBuffer str = new StringBuffer();
        for (int i=1;i<=n;i++){
            str.append(i);
        }
        String next = str.toString();
        if (k == 1){
            return next;
        }
        while (next != "#"&&k>1){
            next = nextStr(next);
            k--;
        }
        return next;
    }
    //-------------------------------------------------------------------------------
    public static void main(String[] args) {
        S60 s = new S60();
        System.out.println(s.getPermutation2(3,3));
        System.out.println(s.getPermutation(3,3));

    }
}
