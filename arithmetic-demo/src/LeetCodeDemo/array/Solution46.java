package LeetCodeDemo.array;

import javax.swing.event.TreeWillExpandListener;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: 10413
 * @Date: 2020/3/19 14:55
 * @Description:
 * 46. 全排列 31题变形
 */
public class Solution46 {

    /**
     * 和offer的SortString 一样一样的
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums==null||nums.length==0){
            return list;
        }
        //先排序
        Arrays.sort(nums);
        list.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        List<Integer> result = numsSort(nums);
        while (!result.isEmpty()){
            list.add(result);
            result = numsSort(nums);
        }
        return list;
    }
    public List<Integer> numsSort(int[] nums){
        int i = nums.length-2;
        while (i>=0&&nums[i]>=nums[i+1]){
            i--;
        }
        if (i<0){
            return new ArrayList<>();
        }
        int j = nums.length-1;
        while (j>=0&&nums[j]<=nums[i]){
            j--;
        }
        int swap = nums[i];
        nums[i]=nums[j];
        nums[j]=swap;
        for (int a=i+1,b=nums.length-1;a<b;a++,b--){
            swap = nums[a];
            nums[a] = nums[b];
            nums[b] = swap;
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums){
            list.add(x);
        }
        return list;
    }

    /**
     * 答案： 回溯法 和 17 22 39 一样的类型，，还是不会写
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        if (nums==null||nums.length==0){
            return lists;
        }
        for (int a : nums){
            num.add(a);
        }
        dfs(lists,num,0);
        return lists;
    }
    public void dfs(List<List<Integer>> lists,List<Integer> curr,int n){
        if (n==curr.size()-1){
            lists.add(new ArrayList<>(curr));
        }else{
            // 遍历，用n去和所有的元素交换并继续递归
            for (int i=n;i<curr.size();i++){
                Collections.swap(curr,n,i);
                dfs(lists,curr,n+1);
                Collections.swap(curr,i,n);
            }
        }
    }

    public static void main(String[] args) {
        Solution46 s = new Solution46();
        int[] a = {1,2,3};
        System.out.println(s.permute(a));
        System.out.println("-----");
        System.out.println(s.permute1(a));
    }



}
