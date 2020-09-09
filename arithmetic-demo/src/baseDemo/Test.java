package baseDemo;

import java.util.*;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/2 14:15
 * @Description:
 */
public class Test {
    //初始化二位数组
    public void test() {
        //第一种方式：
        int a[][] = {{1, 2, 6}, {4, 5, 6}, {7, 8, 9}};
        //第二种方式；
        int[][] ints = new int[4][2];
        //ints[i][j] =__; //分别赋值
        //第三种方式：第二维的长度可以动态申请
        int[][] arr3 = new int[5][];//五行的长度
        for (int i = 0; i < arr3.length; ++i) {
            arr3[i] = new int[i + 1];   //列的长度每次都变化。每次都要重新申请空间(长度)
        }
    }
    // 打印数组
    public void printList(){
        int[] a = {1,1,1};
        char[] b = {'a','b','c'};
        String[] c = {"aa","bb"};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
    }

    // 数组转List
    public void changeArrayToList(){
        // int 基本数据类型不行，必须为包装类
        Integer[] a = {1,2,3};
        List<Integer> list =  new ArrayList<>(Arrays.asList(a));
        list.remove(0);
        System.out.println(list.toString());
    }

    //遍历map
    public void test2() {
        // step1 map.entrySet()
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        // step2
        // 遍历建
        for (Integer key : map.keySet()) {
            System.out.println(key);
        }
        // 遍历值
        for (Integer value : map.values()) {
            System.out.println(value);
        }
        //step3
        // iterator 遍历
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        //step 4 lambo 表达式
        map.forEach((k, v) -> System.out.println("key: " + k + " value:" + v));
    }

    //map 排序 使用TreeMap
    public void test3(){
        // key 排序，TreeMap 默认升序，需要修改要实现Comparator接口
        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        map.put("b", "bbbbb");
        map.put("d", "ddddd");
        map.put("c", "ccccc");
        map.put("a", "aaaaa");

        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + "-:" + map.get(key));
        }

        //values 排序 Collections的sort(List<T> list, Comparator<? super T> c)方法
        //这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for(Map.Entry<String,String> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
    }


    // set  转 数组
    public static void  setToArray(){
        Set<String> set = new HashSet<>();
        set.add("aa");
        set.add("bb");
        String[] test = new String[3];
        String[] strs = set.toArray(test);
        System.out.println(Arrays.toString(strs));
        System.out.println(Arrays.toString(test));
        System.out.println(strs.equals(test));
        System.out.println(strs == test);
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.setToArray();
    }
}
