package baseDemo;

import java.util.Arrays;

/**
 * @Auther: wangzhendong
 * @Date: 2019/12/20 14:03
 * @Description: 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {5,8,6,3,9,2,1,7};
        //bubbleSort(a);
        //advanceSort2(a);
        int[] b = {2,3,4,5,6,7,8,1};
        //cocktailSort(b);
        cocktailSort2(b);
        System.out.println(Arrays.toString(b));
    }


    /**
     * 基本方法
     * 基本思路： 数组长度为8的{5,8,6,3,9,2,1,7} 冒泡排序中一共会比较8轮，每轮确定1个数的最终位置
     * 1: 5,6,3,8,2,1,7 | 9 --> 2: 5,3,6,2,1,7| 8.9 -->3: 3,5,2,1,6 | 7.8.9 --> ...
     * 7: 1| 2.3.5.6.7.8.9
     * 7轮已经确定了有序了，第8轮没有必要比较了，每轮分别比较了7，6，5，4，3，2，1次
     * @param array
     */
    public static void bubbleSort(int[] array){
        int tmp = 0;
        //外循环比较轮数 比较（长度-1）轮就有序了
        for(int i=0;i<array.length-1;i++){
            //内循环比较次数 每轮确定一个数有序了 每轮比较了（长度-1-确定的数的个数）次
            for (int j=0;j<array.length - 1 - i ;j++){
                if (array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 改进冒泡
     * 改进思路：优化比较的轮数
     * 在每轮的比较中发现数组{5,8,6,3,9,2,1,7}在第7轮以前就有序了，没有必要进行后面几轮的比较，
     * 所以添加一个标志符，当该轮比较中没有发生数组中数值的换位，表示数组已经整体有序了跳出循环，直接打印有序数组。
     * @param array
     */
    public static void advanceSort(int[] array){
        int tmp = 0;
        // 外循环控制轮数
        for (int i=0;i<array.length-1;i++){
            //默认有序
            boolean isSort = true;
            //内循环比较次数 如果每次冒泡没有换数说明有序了，不用在进行下一轮比较了，直接跳出循环输出数组
            for (int j=0;j<array.length-1-i;j++){
                if (array[j]<array[j+1]){
                    // 换数表示无序
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    isSort = false;
                }
            }
            //本次冒泡无交互表示数组已经有序
            if (isSort){
                break;
            }
        }
    }

    /**
     * 改进冒泡2
     * 改进思路： 优化每轮冒泡中的比较次数
     * 如若数组{2,4,1,3,5,6,7,8} 在排序的过程中后面的5,6,7,8都是有序的了，但在每轮的冒泡中还是要对后续的有序系列进行比较，费时
     * 设立一个边界值表示有序数组的边界，在原冒泡的排序中，每轮比较结束后会确定一个数字的最终位置，其核心就是在本次冒泡中的比较了所有数的值，
     * 并且只有替换发生时才能确定这个数的位置发生了变化，那么发现每轮冒泡的最后一次替换会决定当前替换和其后面的数字有序。所以我们把每次替换后
     * 交换的数字作为有序数组的边界值，下次的冒泡中只需要比较到此就可以了因为后面的数组都是有序的了。
     * 如: 2,4,1,3,5,6,7,8 第一轮冒泡中就不仅仅确定一个8的最总位置，会确定5，6，7，8的位置： 2,1,3,4|5,6,7,8 这里的边界值为4所在的位置。
     * 对于边界值的位置时交换的前置位还是后置位： 核心是保证交换的前置位参与下次冒泡的比较，后置位不参与下次冒泡的比较，所以边界值的设立只要符合
     * 这个原则即可。
     * @param array
     */
    public static void advanceSort2(int[] array){
        int tmp = 0;
        // 记录最后换位的坐标
        int lastExchangeIndex = 0;
        // 记录有序的边界
        int sortBorder = array.length-1;
        // 冒泡轮数
        for (int i=0;i<array.length-1;i++){
            boolean isSort = true;
            // 冒泡中比较次数，通过优化边界值减小比较的次数，优于array.length-1-i,i为轮数。
            for (int j=0;j<sortBorder;j++){
                if (array[j]>array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    isSort = false;
                    // 记录比较的前置位
                    lastExchangeIndex = j;
                }
            }
            // 重置比较的边界值,注：比较的关键是j比较j+1,设边界值位j，保证了下次冒泡比较前置位，后置位不参与比较
            sortBorder = lastExchangeIndex;
            if (isSort){
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序 冒泡排序的改进
     * 改进思路：优化比较轮数
     * 改进冒泡的比较轮数相对于冒泡轮数改进，区别是后者是最简单的，只是将后续轮数没有数值的交互作为了结束符，
     * 前置是针对{2,3,4,5,6,7,8,1}这样的数组，特点是前序数组都有序了，因为后序的1，导致整个数组需要排8轮
     * 鸡尾酒排序就优化了这个过程，变成奇数轮从左往右冒泡，偶数轮从右往左冒泡，这样在从右往左中，1就排好了，简化了
     * 轮数到3轮。
     * @param array
     */
    public static void cocktailSort(int[] array){
        int tmp = 0;
        int lastExchangeIndexRight = 0;
        int lastExchangeIndexLeft = array.length-1;
        int sortBorderRight = array.length-1;
        int sortBorderLeft = 0;
        for (int i=0;i<array.length-1;i++){
            // 判断是否有序了，没数值交互即有序
            boolean isSortRight = true;
            boolean isSortLeft = true;
            //奇偶排序方向不同
            if (i%2==0){
                // 偶数从左往右
                for (int j=sortBorderLeft;j<sortBorderRight;j++){
                    if (array[j]>array[j+1]){
                        tmp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = tmp;
                        lastExchangeIndexRight = j;
                        isSortRight = false;
                    }
                }
                sortBorderRight = lastExchangeIndexRight;
            }else{
                for (int j=sortBorderRight;j>sortBorderLeft;j--){
                    if (array[j]<array[j-1]){
                        tmp = array[j];
                        array[j]= array[j-1];
                        array[j-1] = tmp;
                        lastExchangeIndexLeft = j;
                        isSortLeft = false;
                    }
                }
                sortBorderLeft = lastExchangeIndexLeft;
            }
            if (isSortLeft && isSortRight){
                break;
            }
        }
    }


    /**
     * 鸡尾酒排序优化代码
     * @param array
     */
    public static void cocktailSort2(int[] array){
        int tmp = 0;
        // 最后交互的数值坐标
        int lastExchangeIndexRight = 0,lastExchangeIndexLeft = 0;
        // 左右边界坐标
        int sortBorderRight = array.length-1;
        int sortBorderLeft = 0;
        // 一次大循环中由两次小循环，为奇数轮和偶数轮，所以大循环循环次数减半
        for (int i=0;i<array.length/2;i++){
            // 是否有序
            boolean isSorted = true;
            // 奇数轮
            for(int j=sortBorderLeft;j<sortBorderRight;j++){
                if (array[j]>array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    isSorted = false; // 交互数值表示此次排序是无序的
                    lastExchangeIndexRight = j; // 记录最后交互的数值坐标
                }
            }
            sortBorderRight = lastExchangeIndexRight; // 重置有有序数组边界
            if (isSorted){
                break;
            }
            isSorted = true;
            // 偶数轮
            for (int j=sortBorderRight;j>sortBorderLeft;j--){
                if (array[j]<array[j-1]){
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    isSorted = false;
                    lastExchangeIndexLeft = j;
                }
            }
            sortBorderLeft = lastExchangeIndexLeft;

            if (isSorted){
                // 奇数轮和偶数轮都没有数值互换，表示数组有序了，跳出循环
                break;
            }
        }
    }
}
