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
    /*
    1 入队栈和出队栈
    2 入队全放入s1中，
    3 出队判断s2是否为空，为空s1出栈到s2中，s2在出队
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    //stack1为入队栈
    public void push(int node) {
        stack1.push(node);
    }

    //stack2为出队栈
    public int pop() {
        // 如果第二个栈为空
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            return -1;
        } else {
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        int[] list = {1,2,3,4,5,6,7,8,99};
    }
}
