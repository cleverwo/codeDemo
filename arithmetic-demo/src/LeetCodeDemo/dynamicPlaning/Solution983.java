package LeetCodeDemo.dynamicPlaning;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/3/10 21:30
 * @Description:
 * 983. 最低票价
 */
public class Solution983 {

    /**
     * 思路是，拆分数组求最小
     */
    public int mincostTickets(int[] days, int[] costs) {
        List<Integer> dayss = new ArrayList<>();
        for (int day: days){
            dayss.add(day);
        }
        return min(dayss,costs);
    }

    public int min(List<Integer> days,int[] costs){
        if (days.size() == 0){
            return 0;
        }
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        List<Integer> a3 = new ArrayList<>();
        int start = days.get(0);
        for (int i=1;i<days.size();i++){
            if (days.get(i)>=start+1){
                a1.add(days.get(i));
            }
            if (days.get(i)>=start+7){
                a2.add(days.get(i));
            }
            if (days.get(i)>=start+30){
                a3.add(days.get(i));
            }
        }
        int a = costs[0] + min(a1,costs);
        int b = costs[1] + min(a2,costs);
        int c = costs[2] + min(a3,costs);
        return Math.min(a, Math.min(b,c));
    }


    /**
     * 答案：	dp(i) = min（dp(i+1)+costs[0],dp(i+7)+costs[1],dp(i+30)+costs[2])
     */
    int[] costs;
    Integer[] memo;
    List<Integer> dayset;
    int end;
    public int mincostTickets1(int[] days, int[] costs) {
        this.costs = costs;
        this.end = days[days.length-1];
        memo = new Integer[366];
        dayset = new ArrayList<>();
        for (int d: days){
            dayset.add(d);
        }
        return dpp(days[0]);
    }
    public int dp(int i){
        if (i>365){
            return 0;
        }
        if (memo[i]!=null){
            return memo[i];
        }
        int ans;
        if (dayset.contains(i)){
            ans = Math.min(dp(i+1)+costs[0],dp(i+7)+costs[1]);
            ans = Math.min(ans,dp(i+30)+costs[2]);
        }else {
            ans = dp(i+1);
        }
        memo[i] = ans;
        return ans;
    }
    //没有必要算到365天吧，感觉光算days数组里的就好了
    public int dpp(int i){
        if (i>end){
            return 0;
        }
        if (memo[i]!=null){
            return memo[i];
        }
        int ans;
        if (dayset.contains(i)){
            ans = Math.min(dpp(i+1)+costs[0],dpp(i+7)+costs[1]);
            ans = Math.min(ans,dpp(i+30)+costs[2]);
        }else{
            ans = dpp(i+1);
        }
        memo[i] = ans;
        return ans;
    }

    /**
     * 答案2：和我想得优化一样 ，看看人家的代码
     */
    int[] days;
    int[] durations = new int[]{1,7,30};
    public int mincostTickets2(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];
        //这个0是下标，表示days的下标
        return dd(0);
    }
    public int dd(int i){
        if (i>=days.length){
            return 0;
        }
        if (memo[i] != null){
            return memo[i];
        }
        int ans = Integer.MAX_VALUE;
        int j=i;
        // 求3个分支中的最小值
        for (int k=0;k<3;k++){
            while (j<days.length&&days[j]<days[i]+durations[k]){
                j++;
            }
            ans = Math.min(ans,dd(j)+costs[k]);
        }
        memo[i]=ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution983 s = new Solution983();
        int[] a = {2,4,6,7,8,20};
        int[] b = {2,7,15};
        System.out.println(s.mincostTickets1(a,b));
        System.out.println();
    }
}
