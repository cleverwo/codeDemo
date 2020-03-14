package baseDemo.sort;

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
    int[] b = new int[100];
    public void merge(int[] a,int low,int mid,int high){
        int i,j,k;
        for (k=low;k<=high;k++){
            b[k] = a[k];
        }
        for (i=low,j=mid+1,k=i;i<mid&&j<=high;k++){
            if (b[i]<=b[j]){
                a[k]=b[i++];
            }else {
                a[k]=b[j++];
            }
        }
        while (i<=mid){
            a[k++]=b[i++];
        }
        while (j<=high){
            a[k++]=b[j++];
        }
    }
}
