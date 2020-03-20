package com.example.javaApiTest;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/18 22:44
 * @Description:
 */
public class ArrayTest {

    public void ArrayListTest() {
        //构造方法
        //1 构造一个初始容量为十的空列表。
        List<Integer> l1= new ArrayList<>();
        //2 ArrayList(int initialCapacity) 构造具有指定初始容量的空列表 inital 初始化数组的大小，没啥大用，再list的底层与Vector的区别上有用，默认的大小是10
        //3 ArrayList(Collection<? extends E> c)构造一个包含指定集合的元素的列表，按照它们由集合的迭代器返回的顺序。

        //方法
        //添加元素
        //boolean	add(E e) 默认加道末尾
        //void	add(int index, E element) 在此列表中的指定位置插入指定的元素。其后元素后移动
        List<Integer> addlist = new ArrayList<>();
        addlist.add(1);
        addlist.add(2);
        addlist.add(3);
        addlist.add(4);
        addlist.add(0,11);
        System.out.println(addlist);
        //boolean	addAll(Collection<? extends E> c) 按指定集合的Iterator返回的顺序将指定集合中的所有元素追加到此列表的末尾。

    }

    //队列的API实验
    public void QueueTest(){
        //优先队列 PriorityQueue 构造方法
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1>o2?-1:1;
            }
        });
        queue.add(1);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(6);
        queue.add(7);
        System.out.println(queue.toString());

    }

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1>o2){
                    return 1;
                }else if (o1==o2){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
        queue.add(1);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(6);
        queue.add(7);
        System.out.println(queue.toString());
    }
}
