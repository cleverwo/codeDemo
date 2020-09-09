package LeetCodeDemo.eassy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/9/6 11:50
 * @Description:
 * 496. 下一个更大元素 I
 */
public class S496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] curr = new int[nums2.length];
        Arrays.fill(curr,-1);
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums2.length;i++){
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]){
                int index = stack.pop();
                curr[index] = nums2[i];
                map.put(nums2[index],index);
            }
            stack.push(i);
        }
        int[] result = new int[nums1.length];
        for (int i=0;i<result.length;i++){
            if (map.containsKey(nums1[i])){
                result[i] = curr[map.get(nums1[i])];
            }else{
                result[i] = -1;
            }
        }
        return result;
    }
    //-----------------------------------------------
    //简化 方法1 最简答案， 单调栈解题。
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums2.length;i++){
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]){
                int index = stack.pop();
                map.put(nums2[index],nums2[i]);
            }
            stack.push(i);
        }
        int[] result = new int[nums1.length];
        for (int i=0;i<result.length;i++){
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }
    //------------------------------------------------
    public static void main(String[] args) {
        S496 s = new S496();
        int[] num1 = {1,3,5,2,4};
        int[] num2 = {5,4,3,2,1};
        System.out.println(Arrays.toString(s.nextGreaterElement2(num1,num2)));
    }
}
