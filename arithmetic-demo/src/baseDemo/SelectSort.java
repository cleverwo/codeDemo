package baseDemo;

import java.util.Arrays;

/**
 * @Auther: wangzhendong
 * @Date: 2019/12/23 16:14
 * @Description: 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] a = {2,3,1,7,9,5,6};
        selectSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 选择排序
     * 基本思路：数组长度是8则选择7轮，每轮选择无序数组中的最小值和数组头部交换，这样每轮选择完无序数组长度减1，
     * 每轮选择排序中默认第一个数最为最小值来与后续数组比较，获取最小的数组的下标，在最后交换数组代排序位置和最小的数值
     * 完成本轮的选择排序。
     * @param array
     */
    public static void selectSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            int min = i;
            for (int j=i+1;j<array.length;j++){
                min = array[min] > array[j] ? j : min;
            }
            int tmp = array[min];
            array[min] = array[i];
            array[i] = tmp;
        }
    }
}
