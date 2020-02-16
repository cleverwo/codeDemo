package OfferDemo.stack;

import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 20:00
 * @Description:
 * 21，栈的压入，弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 */
public class Test21 {

    /**
     * 该问题就是判断popA是否可以为pushA的出栈元素
     * 思路：新建一个栈，将数组A压入栈中，当栈顶元素等于数组B时，就将其出栈，当循环结束时，判断栈是否为空，若为空则返回true.
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0,j=0;i<pushA.length;i++){
            //入栈
            stack.push(pushA[i]);
            //遍历数组popA 判断栈顶元素是否出栈，不是则继续入栈
            while (j<popA.length&&stack.peek()==popA[j]){
                //相同出栈
                stack.pop();
                j++;
            }
        }
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 答案1：
     * 同我的思路，有细节的简化
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder2(int [] pushA,int [] popA) {
        // 前提条件
        if (pushA.length == 0 || popA.length == 0 || popA.length != pushA.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        int[] b = {4,3,5,2,1};
        Test21 t = new Test21();
        t.IsPopOrder(a,b);
    }
}
