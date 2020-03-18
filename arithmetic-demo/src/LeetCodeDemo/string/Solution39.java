package LeetCodeDemo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/3/17 18:00
 * @Description:
 * 39 组合总和  17,22
 */
public class Solution39 {

    /**
     * 这个和三数之和类型的很像，不一样，前者是固定数量，后者没有
     * 还是用dfs深度遍历，自己做有两个问题没有解决：
     * 1. 怎么去重
     * 2. 怎么回退路径
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (candidates==null||candidates.length==0){
            return list;
        }
        List<String> list2 = new ArrayList<>();
        dfsString(list2,"",target,candidates);
        System.out.println(list2);
//        dfs(list,new ArrayList<>(),target,candidates);
        return list;
    }
    public void dfs(List<List<Integer>> list,List<Integer> cur,int target,int[] candidates){
        if (target==0){
            list.add(cur);
        }else{
            for (int i=0;i<candidates.length;i++){
                int now = target-candidates[i];
                if (now<0){
                    return;
                }
                cur.add(candidates[i]);
                dfs(list,cur,now,candidates);
            }
        }
    }
    public void dfsString(List<String> list,String cur,int target,int[] candidates){
        if (target==0){
            list.add(cur);
        }else{
            for (int i=0;i<candidates.length;i++){
                int now = target-candidates[i];
                if (now<0){
                    return;
                }
                dfsString(list,cur+""+candidates[i],now,candidates);
            }
        }
    }
    public List<List<Integer>> combinationSum1(int[] candidates, int target){
        List<List<Integer>> list = new ArrayList<>();
        if (candidates==null||candidates.length==0){
            return list;
        }
        Arrays.sort(candidates);
        dfsAnswer(list,new ArrayList<>(),candidates,target,0);
        return list;
    }
    /**
     * 答案： dfs + 剪枝
     */
    public void dfsAnswer(List<List<Integer>> list,List<Integer> cur,int[] candidates,int target,int begin){
        if (target==0){
            list.add(new ArrayList<Integer>(cur));
        }else{
            // 这里去重用的的begin控制数组遍历的起始位置，当数组是2，3，6，7时：
            // 2遍历了可以选择3，6，7 但时 遍历3的时候就只能选择 6，7，从而达到去重的效果
            for (int i =begin;i<candidates.length;i++){
                if (target-candidates[i]<0){
                    break; // return;
                }
                // 回退路径
                cur.add(candidates[i]);//用队列和栈都可以，这里就是路径，不符合就移除刚加入的元素
                dfsAnswer(list,cur,candidates,target-candidates[i],i);
                cur.remove(cur.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Solution39 s = new Solution39();
        int[] a = {2,3,6,7};
        List<List<Integer>> out = s.combinationSum1(a,7);
        System.out.println(out);
    }


}
