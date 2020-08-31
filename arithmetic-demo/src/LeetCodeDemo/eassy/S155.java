package LeetCodeDemo.eassy;

import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/8/28 11:13
 * @Description: 155. 最小栈
 */
public class S155 {
    Stack<Integer> stack;
    Stack<Integer> minStatk;

    public S155() {
        this.stack = new Stack<>();
        this.minStatk = new Stack<>();
    }

    public void push(int x) {
        if (!minStatk.isEmpty()){
            int top = minStatk.peek();
            if (x < top) {
                top = x;
            }
            minStatk.push(top);
        }else{
            minStatk.push(x);
        }
        stack.push(x);
    }


    public void pop() {
        stack.pop();
        minStatk.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStatk.peek();
    }

    public static void main(String[] args) {
        S155 s = new S155();
        s.push(2147483646);
        s.push(2147483646);
        s.push(2147483647);
        System.out.println(s.top());
        s.pop();
        System.out.println(s.getMin());
        s.pop();
        System.out.println(s.getMin());
        s.pop();
        s.push(2147483647);
        System.out.println(s.top());
        System.out.println(s.getMin());
        s.push(-2147483648);
        System.out.println(s.top());
        System.out.println(s.getMin());
        s.pop();
        System.out.println(s.getMin());

    }
}
