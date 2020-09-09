package LeetCodeDemo.middle;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/9/6 10:30
 * @Description:
 *
 * 739. 每日温度
 */
public class S739 {


    // 单调栈
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Arrays.fill(result,0);
        if (T.length == 0){
            return new int[]{};
        }
        int n = T.length;
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<n;i++){
            while (!stack.isEmpty() && T[i] > T[stack.peek()]){
                result[stack.peek()] = i- stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }

    //-----------------------------------------------------
    public static void main(String[] args) {
        S739 s = new S739();
        int[] test = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] a = s.dailyTemperatures(test);
        System.out.println(Arrays.toString(a));
    }
}
