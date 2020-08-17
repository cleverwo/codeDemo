package com.example.javaApiTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/4/16 15:21
 * @Description:
 */
public class CollectionsTest {

    /**
     * Collections.addAll()方法的使用
     */
    public static void collectionAddAll(){
        List<Integer> list1 = new ArrayList<Integer>() {{add(0); add(-1);}};
        List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(2, 4, -9));

        list1.addAll(list2);
        System.out.println(list1);
        list2.set(0, 100000);
        System.out.println(list1);//深复制
        System.out.println();

        //功能性展示
        Collections.addAll(list2, 34, 67, 78);
        System.out.println(list2);
        list2.addAll(Arrays.asList(34, 67, 78));
        System.out.println(list2);
        System.out.println();

        //性能展示
        System.out.println("添加元素---------------------------\n");
        for(int size = 1000; size < 100000000; size *= 10) {
            long time = System.nanoTime();
            List<Integer> list3 = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                list3.addAll(Arrays.asList(1, 45, 34, 456, 1212, -6, 43, 55));
            }
            System.out.println("ArrayList.addAll()@size = " + size + "\ntime:" + (System.nanoTime() - time));

            time = System.nanoTime();
            List<Integer> list4 = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                Collections.addAll(list4, 1, 45, 34, 456, 1212, -6, 43, 55);
            }
            System.out.println("Collections.addAll()@size = " + size + "\ntime:" + (System.nanoTime() - time) + "\n");
        }
        System.gc();

        System.out.println("添加数组---------------------------\n");
        Integer[] arr = {1, 45, 34, 456, 1212, -6, 43, 55};
        for(int size = 1000; size < 100000000; size *= 10) {
            long time = System.nanoTime();
            List<Integer> list3 = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                list3.addAll(Arrays.asList(arr));
            }
            System.out.println("ArrayList.addAll()@size = " + size + "\ntime:" + (System.nanoTime() - time));

            time = System.nanoTime();
            List<Integer> list4 = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                Collections.addAll(list4, arr);
            }
            System.out.println("Collections.addAll()@size = " + size + "\ntime:" + (System.nanoTime() - time) + "\n");
        }
        System.gc();

        System.out.println("添加列表---------------------------\n");
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 45, 34, 456, 1212, -6, 43, 55));
        for(int size = 1000; size < 100000000; size *= 10) {
            long time = System.nanoTime();
            List<Integer> list3 = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                list3.addAll(list);
            }
            System.out.println("ArrayList.addAll()@size = " + size + "\ntime:" + (System.nanoTime() - time));

            time = System.nanoTime();
            List<Integer> list4 = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                Collections.addAll(list4, list.toArray(new Integer[0]));
            }
            System.out.println("Collections.addAll()@size = " + size + "\ntime:" + (System.nanoTime() - time) + "\n");
        }

    }

    public static void main(String[] args) {

    }
}
