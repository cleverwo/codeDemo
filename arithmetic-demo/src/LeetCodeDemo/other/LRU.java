package LeetCodeDemo.other;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/4/12 16:58
 * @Description:
 */
public class LRU extends LinkedHashMap<Integer,Integer> {
    public int size;

    public LRU(int size){
        super(size,0.75f,true);
        this.size = size;
    }

    public int get(int key){
        // 和get一样，就是get不到放回null，这里返回的时-1
        return super.getOrDefault(key,-1);
    }

    public void put(int key,int value){
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size()>size;
    }
}
