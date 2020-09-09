package LeetCodeDemo;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/4/2 12:05
 * @Description: 146. LRU缓存机制
 */
public class Solution146 {
    private int capacity = 0;
    private Queue<Integer> queue = new LinkedList<>();
    private HashMap<Integer, Integer> map = new HashMap<>();

    public Solution146(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (queue.contains(key)) {
            queue.remove(key);
            queue.offer(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        //先判断是否存在key
        if (queue.isEmpty()) {
            map.put(key, value);
            queue.offer(key);
        } else {
            if (queue.contains(key)) {
                queue.remove(key);
                queue.offer(key);
                map.put(key, value);
            } else if (queue.size() < capacity) {
                map.put(key, value);
                queue.offer(key);
            } else {
                int oldKey = queue.poll();
                map.remove(oldKey);
                queue.offer(key);
                map.put(key, value);
            }
        }

    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.remove("b");
        System.out.println(queue.toString());
    }

    public int get1(int key){
        if (map.containsKey(key)){
            queue.remove(key);
            queue.offer(key);
            return map.get(key);
        }
        return -1;
    }
    public void put1(int key,int value){
        int size = queue.size();
        if (size>=capacity){
            if (queue.contains(key)){
                queue.remove(key);
                queue.offer(key);
                map.put(key,value);
            }else{
                int oldKey = queue.poll();
                map.remove(oldKey);
                queue.offer(key);
                map.put(key,value);
            }
        }else{
            if (queue.contains(key)){
                queue.remove(key);
                queue.offer(key);
                map.put(key,value);
            }else{
                queue.offer(key);
                map.put(key,value);
            }
        }
    }
}
