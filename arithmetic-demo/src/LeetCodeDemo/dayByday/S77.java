package LeetCodeDemo.dayByday;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/9/8 22:08
 * @Description:
 * 77 组合
 */
public class S77 {

    // dfs
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {

        List<Integer> curr = new ArrayList<>();
        dfs(1,n,k,curr);
        return res;
    }

    public void dfs(int s, int e,int length,List<Integer> curr){
        if (curr.size() == length){
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = s;i<=e;i++){
            curr.add(i);
            dfs(i+1,e,length,curr);
            curr.remove(curr.size()-1);
        }

    }

    public static void main(String[] args) {
        S77 s = new S77();
        List<List<Integer>> list = s.combine(4,2);
        System.out.println(list.toString());
    }
}
