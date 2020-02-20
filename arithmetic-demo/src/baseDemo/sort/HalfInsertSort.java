package baseDemo.sort;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 18:07
 * @Description:
 * 插入类排序：
 * 折半插入排序: 时间O（n*n） 空间 O（1） 稳定排序，适合链表
 * 比较此时变nlogn
 */
public class HalfInsertSort {

    /**
     * 不同于直接插入的是，比较和移动相分离，
     * 比较次数变成了nlogn
     * 其他同直接插入排序，相当于直接插入排序的一种优化
     * @param a
     */
    public void insertSort(int[] a){
        int i,j,low,high,mid;
        for (i=2;i<a.length;i++){
            a[0] = a[i];
            //将查找待插入的位置用折半查找代替了顺序查找
            //设置折半查找的范围
            low=1;high=i-1;
            while(low<=high){
                mid = (low+high)/2;
                if (a[mid]>a[0]){
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }
            //最总折半的结果是hight》low 即hight的位置的元素是比待插入元素小的，
            //待插入元素插入到hight+1上，high+1~i-1的元素后移
            for (j=i-1;j>high;j--){
                a[j+1]=a[j];
            }
            a[j+1]=a[0];
        }
    }
}
