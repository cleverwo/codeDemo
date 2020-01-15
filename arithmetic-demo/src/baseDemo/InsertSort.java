package baseDemo;

import java.util.Arrays;

/**
 * @Auther: wangzhendong
 * @Date: 2019/12/23 16:40
 * @Description: 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] a = {5,3,4,8,1,9,2};
        insertSort2(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 快速排序
     * 基本思想：外循环控制轮数，一共执行leght-1轮，待插入数据从有序数组的尾部开始比较，当待插入数据小于有序数组时，有序数组后移动
     * 如果待插入数比比较的数小，比较数后移，如果待插入数比比较的数大，跳出比较，插入到该数值后方即可。
     * @param array
     */
    public static void insertSort(int[] array){
        // 外循环控制轮数，一共执行leght-1轮
        for (int i=1;i<array.length;i++){
            // 待插入数据
            int insertValue = array[i];
            // 待插入数据从有序数组的尾部开始比较，当待插入数据小于有序数组时，有序数组后移动
            // i-1时有序数组尾部的第一个数，0是有序数组的开始的数，因为是有序数组，
            int j=i-1;
            for(;j>=0;j--){
                if (array[j] > insertValue){
                    //如果待插入数比比较的数小，比较数后移
                    array[j+1] = array[j];
                }else{
                    //如果待插入数比比较的数大，跳出比较，插入到该数值后方即可
                    break;
                }
            }
            array[j+1] = insertValue;
        }
    }

    public static void insertSort2(int[] array){
        for (int i=1;i<array.length;i++){
            int insertValue = array[i];
            int j=i-1;
            for (;j>=0&&insertValue<array[j];j--){
                array[j+1] = array[j];
            }
            array[j+1] = insertValue;
        }
    }

}
