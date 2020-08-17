package baseDemo.sort;

import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/4/20 17:40
 * @Description:
 * 快速排序
 * 时间 nlogn 空间 logn 不稳定排序
 */
public class QuickSort {

    public void quickSort(int[] num, int low, int high){
        if (low<high){
            int mid = partition(num,low,high);
            quickSort(num,low,mid-1);
            quickSort(num,mid+1,high);
        }
    }
    //一趟快速
    public int partition(int[] num,int low, int high){
        int a = num[low];
        while (low<high){
            while (low<high&&num[high]>=a){
                high--;
            }
            num[low] = num[high];
            while (low<high&&num[low]<=a){
                low++;
            }
            num[high] = num[low];
        }
        num[low] = a;
        return low;
    }

    public static void main(String[] args) {
        QuickSort m = new QuickSort();
        int[] a = {3,4,6,1,2,5,10};
        m.quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

}
