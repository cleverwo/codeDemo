package baseDemo.sort;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 17:44
 * @Description:
 * 插入类排序
 * 直接插入排序: 时间O（n*n） 空间 O（1） 稳定排序，适合链表
 */
public class DirectInsertSort {

    /**
     * 直接插入排序
     * 利用a[0] 作为哨兵 存储待插入的数据
     * 默认a【1】是有序的，从a【2】开始遍历
     * @param array
     */
    public static void insertSort(int[] array){
        int i,j;
        for (i=2;i<array.length;i++){
            // 如果当前a【i】比前一位小，则将a【i】插入到前面的有序序列中
            if (array[i]<array[i-1]){
                // a【0】作为哨兵，复制a【i】数据
                array[0] = array[i];
                // 从后往前找，从i-1位置找到a【0】哨兵位置
                for (j=i-1;array[0]<array[j];j--){
                    // 不是这个位置，元素后移动
                    array[j+1]=array[j];
                }
                //最后将空出的位置插入哨兵，跳出循环的条件是待插入数大于a【j】，则在其后面插入数
                array[j+1]=array[0];
            }
        }
    }
}
