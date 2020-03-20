package baseDemo.sort;

import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 18:28
 * @Description:
 * 2-路归并排序
 * 归并排序： 时间O（nlogn） 空间是O（n） 稳定排序
 */
public class MergeSort {
    public void mergeSort(int[] a,int low,int high){
        if (low<high){
            int mid=(low+high)/2;
            mergeSort(a,low,mid);
            mergeSort(a,mid+1,high);
            merge(a,low,mid,high);
        }
    }
    //2路归并成一个有序的数组
    //两路归并算法，两个排好序的子序列合并为一个子序列
    public void merge(int []a,int left,int mid,int right){
        int[] tmp=new int[a.length];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针

        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2])
                tmp[k++]=a[p1++];
            else
                tmp[k++]=a[p2++];
        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//同上

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];
    }

    public static void main(String[] args) {
        MergeSort m = new MergeSort();
        int[] a = {49,38,65,97,76,13,27};
        m.mergeSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
