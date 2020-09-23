package LeetCodeDemo.dayByday;

import baseDemo.map.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/9/9 21:39
 * @Description:
 * 组合总和
 */
public class S39 {

    // dfs 回溯
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        if (candidates.length == 0){
            return res;
        }
        Arrays.sort(candidates);
        List<Integer> curr = new ArrayList<>();
        dfs(candidates,curr,target);
        return res;
    }
    // 递归dfs
    private void dfs(int[] candidates, List<Integer> curr, int target) {
        if (target == 0){
            res.add(new ArrayList<>(curr));
            return;
        }
        // 这里有问题，处理时 2 2 3 可以，那 3，2，2的情况没有排除，出现重复
        for (int i=0;i<candidates.length;i++){
            int val = candidates[i];
            if (val<=target){
                curr.add(val);
                dfs(candidates,curr,target-val);
                curr.remove(curr.size()-1);
            }else{
                break;
            }
        }
    }
    public void dfs(int[] candidates, int target, List<Integer> curr, int idx){
        if (idx == candidates.length){
            return;
        }
        if (target == 0){
            res.add(new ArrayList<>(curr));
            return;
        }
        dfs(candidates,target,curr,idx+1);
        if ((target-candidates[idx]) >= 0){
            curr.add(candidates[idx]);
            dfs(candidates,target-candidates[idx],curr,idx);
            curr.remove(curr.size()-1);
        }
    }

    //-----------------------------------------------

    public static void main(String[] args) {
        S39 s = new S39();
        int[] test = {2,3,6,7};
        List<List<Integer>> list = s.combinationSum(test,7);
        System.out.println(list.toString());
    }
}
