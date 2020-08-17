package com.example.javaApiTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/4/12 17:10
 * @Description:
 * Map集合类之 链表map 特性，可以排序，保证插入key值的顺序
 * 底层实现是一个双链表格式
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        // LinkedHashMap 有序
        //test1();
        // LinkedHashMap 在此基础上再根据访问顺序(get,put)排序
        test2();

    }

    /**
     * 和HashMap比较，LinkedHashMap是有序的
     * 会根据插入元素的顺序输出，和HashMap不是
     */
    public static void  test1(){
        // ① 保证插入的顺序
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("apple","苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");
        // ② 用Interor遍历map
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }

        System.out.println("**********************************比较HashMap是无序的**********************************");
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("apple","苹果");
        hashMap.put("watermelon", "西瓜");
        hashMap.put("banana", "香蕉");
        hashMap.put("peach", "桃子");
        // ② 用Interor遍历map
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }
    }

    /**
     * LinkedHashMap特性 根据getput对元素在排序
     */
    public static void test2(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>(16,0.75f,true);
        map.put("apple","苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");
        // ② 用Interor遍历map
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }
        System.out.println("**********************************在此基础上在getput重新排序 只要get或put就重新排序到末尾**********************************");
        map.get("banana");
        map.get("apple");
        map.put("watermelon","西瓜2");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println(entry.getKey()+ " "+ entry.getValue());
        }
    }
}
