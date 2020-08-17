package OfferDemo.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 12:51
 * @Description:
 * 20.包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class Test20 {
    /**
     * 定义栈的数据结构，栈先进后出
     * 错误做法，自己瞎想的
     */
    List<Integer> stacklist = new ArrayList();
    int num =0;
    public void push(int node) {
        stacklist.add(node);
        this.num ++;

    }
    public void pop() {
        stacklist.remove(this.num--);
    }
    public int top() {
        return stacklist.get(this.num);
    }
    public int min() {
        int min = stacklist.get(this.num-1);
        for (int i=0;i<this.num-1;i++){
            if (stacklist.get(i)<min){
                min = stacklist.get(i);
            }
        }
        return min;
    }

    /**
     * 一个用来存所有的元素“stackTotal”,另一个用来存加入新的元素后当前stackTotal中对应的最小值。
     * 两个栈中的元素数量始终保持一致，当新的元素小于“stackLittle”栈顶元素时，“stackLittle”像栈顶push新来的元素，否则，“stackLittle”向栈顶加入原栈顶元素。
     * 执行“pop”方法时，两个栈同时弹出各自的栈顶元素。
     *
     * little栈用来存最小值，total用来存栈内元素，little与total的元素对应关系是
     * 入栈total元素--对应当前total的最小值入栈little
     * 这样total出栈后，littel栈出栈元素为对应这个total出栈元素在栈内时的最小值
     */
    // 存元素
    Stack<Integer> stackTotal = new Stack<Integer>();
    //存最小值
    Stack<Integer> stackLittle = new Stack<Integer>();
    public void push2(int node) {
        stackTotal.push(node);
        if(stackLittle.empty()){
            // 最小值栈为空，直接入栈
            stackLittle.push(node);
        }else{
            if(node <= stackLittle.peek()){
                //当前值小于最小值栈的栈顶元素，直接入栈
                stackLittle.push(node);
            }else{
                //当前值大于最小值将最小值在入栈一次
                stackLittle.push(stackLittle.peek());
            }
        }
    }
    public void pop2() {
        stackTotal.pop();
        stackLittle.pop();
    }

    public int top2() {
        return stackTotal.peek();
    }

    public int min2() {
        return stackLittle.peek();
    }

    /**
     * 对上一个方法的改进
     * 不用保证little栈的元素和total栈元素相同
     * 在total出栈时判断，little的栈顶元素是否和total出栈元素相同，相同则出栈，不同不出栈
     * 这里就是把little栈在total栈入栈时的元素不是最小值的时候，littel不在入栈，节省little的空间
     */
    //最修改了pop和push方法
    public void push2_2(int node){
        stackTotal.push(node);
        if (stackLittle.empty()){
            stackLittle.push(node);
        }else {
            // 等于属于相同最小值元素，需要入栈，不如栈会造成total出栈时把该元素也栈了，但出栈后的total的最小
            //值还是这个元素，而little里却没有了，所以相等要入栈
            if (node <=stackLittle.peek()){
                stackLittle.push(node);
            }
        }
    }
    public void pop2_2(){
        int out = stackTotal.pop();
        if (out == stackLittle.peek()){
            stackLittle.pop();
        }
    }



    /**
     * 不同上一个方法是用一个栈来存贮，
     * 区别在于，双栈中total用来存栈内元素，little存做过最小值的元素
     * 而单栈中会让做过最小值元素也存到该栈中，令stack栈有冗余元素，
     * 实质是将little栈存的元素放到了stack来，stack即存了栈内元素，也存了最小元素
     * 因为栈内元素是无法获取到，所以用min来表示了最小值
     *
     */
    Stack<Integer> stack = new Stack<Integer>();
    //默认最小值时int的最大值
    Integer minElement = Integer.MAX_VALUE;

    public void push3(int node) {
        if(stack.empty()){
            // 栈空初始化最小值，最小值入栈
            minElement = node;
            stack.push(node);
        }else{
            if(node <= minElement){
                //在push更小的值时需要保留在此值之前的最小值
                //最小值更换时先保留一下最小值，所以让其入栈
                stack.push(minElement);
                //更新最小值
                minElement = node;
            }
            // 栈内元素入栈
            stack.push(node);
        }
    }
    public void pop3() {
        //增加最后一个元素以及栈为空时候的检测
        if(stack.size() == 0){
            return;
        }
        // 如果出栈元素不是最小值，栈顶元素不是最小值，直接出栈即可
        if(minElement.equals(stack.peek())){
            // 栈顶元素是最小值，则栈顶元素出栈后，需要把min更新为当前元素入栈前栈内元素的最小值，
            // 即上一个最小值
            if(stack.size() >1){
                //栈内元素是多个时，入栈前会将原最小值入栈，所以这里做栈内元素个数判别
                //这里出栈，后面又出栈，其实，这里出栈的时栈顶元素，出栈后，栈内保留的是
                //该栈顶元素入栈前栈内的最小值，这个值在栈顶元素入栈前，提前入栈到栈内，用与保留原最小值元素
                stack.pop();
                //更新最小值为原最小值，之后末尾将这个最小值出栈了，保证了栈顶元素为入栈元素而不是保留的最小值元素
                minElement = stack.peek();
            }else{
                //栈内元素只有一个，出栈后将min设为初始值
                minElement = Integer.MAX_VALUE;
            }
        }
        stack.pop();
    }
    public int top3() {
        return stack.peek();
    }
    public int min3() {
        return minElement;
    }

    public static void main(String[] args) {
        Test20 t = new Test20();
        //["PSH3","MIN","PSH4","MIN","PSH2","MIN","PSH3","MIN","POP","MIN","POP","MIN","POP","MIN","PSH0","MIN"]
        t.push2_2(3);
        System.out.println(t.min2());
        t.push2_2(4);
        System.out.println(t.min2());
        t.push2_2(2);
        System.out.println(t.min2());
        t.push2_2(3);
        System.out.println(t.min2());
        t.pop2_2();
        System.out.println(t.min2());
        t.pop2_2();
        System.out.println(t.min2());
        t.pop2_2();
        System.out.println(t.min2());
        t.push2_2(0);
        System.out.println(t.min2());
    }
}
