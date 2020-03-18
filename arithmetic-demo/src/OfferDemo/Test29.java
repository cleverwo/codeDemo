package OfferDemo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/2/18 11:28
 * @Description: 29, 最小的K个数
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class Test29 {

    /**
     * 暴力求解
     * 或者；冒泡/堆排/快排/希尔排序
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input.length == 0 || k > input.length) {
            return list;
        }
        //排序
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    /**
     * 答案1：
     * 利用大根堆
     * 创建一个大小为k的数组，遍历n个整数，如果遍历到的数小于大小为k的数组的最大值，则将此数与其最大值替换
     * 由于每次都要拿n个整数和数组中的最大值比较，所以选择大根堆这一数据结构
     * (大家要分清楚大根堆这一数据结构和堆排序之间的区别：堆排序是在大根堆这一数据结构上进行排序的一种排序算法，
     * 一个是数据结构，一个是算法)
     */

    public ArrayList<Integer> GetLeastNumbers_Solution1(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k == 0) {
            return list;
        }
        //数组下标0的位置作为哨兵，不存储数据
        int[] arr = new int[k + 1];
        //初始化数组
        for (int i = 1; i < k + 1; i++) {
            arr[i] = input[i - 1];
        }
        //构造大根堆
        buildMaxHeap(arr, k + 1);
        for (int i = k; i < input.length; i++) {
            if (input[i] < arr[1]) {
                arr[1] = input[i];
                //将改变了根节点的二叉树继续调整为大根堆
                adjustDown(arr, 1, k + 1);
            }
        }
        for (int i = 1; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * 构造大根堆
     */
    public void buildMaxHeap(int[] arr, int length) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return;
        }
        for (int i = (length - 1) / 2; i > 0; i--) {
            adjustDown(arr, i, arr.length);
        }
    }

    /**
     * 堆排序中对一个子二叉树进行堆排序
     */
    public void adjustDown(int[] arr, int k, int length) {
        //哨兵
        arr[0] = arr[k];
        for (int i = 2 * k; i <= length; i *= 2) {
            if (i < length - 1 && arr[i] < arr[i + 1]) {
                //取k较大的子结点的下标
                i++;
            }
            if (i > length - 1 || arr[0] >= arr[i]) {
                break;
            } else {
                arr[k] = arr[i];
                //向下筛选
                k = i;
            }
        }
        arr[k] = arr[0];
    }
}
