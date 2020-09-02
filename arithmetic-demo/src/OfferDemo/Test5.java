package OfferDemo;

import java.util.List;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/1/28 09:02
 * @Description:
 * 5.用两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class Test5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    //stack1为入队栈
    public void push(int node) {
        while(!stack2.empty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    //stack2为出队栈
    public int pop() {
        while(!stack1.empty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        int[] list = {1,2,3,4,5,6,7,8,99};
    }
}
