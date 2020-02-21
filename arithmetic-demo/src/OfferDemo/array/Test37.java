package OfferDemo.array;


import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/2/20 16:33
 * @Description:
 * 37，数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数。
 */
public class Test37 {

    /**
     * 思路：
     * k 在有序数组array出现的次数
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {
        int count = 0,flag=1;
        for (int i=0;i<array.length&&flag==1;i++){
            if (array[i]==k){
                count++;
            }else{
                if(i!=0&&array[i-1]==k){
                    flag=0;
                }
            }
        }
        return count;
    }

    /**
     * 答案：
     * 一看有序还需要查找要想到二分查找
     * 用二分查找找到这个数，在从这个数遍历取累积量
     */
    public int GetNumberOfK1(int [] array , int k) {
        //二分查找找k的坐标
        int index = Arrays.binarySearch(array, k);
        if(index<0){
            return 0;
        }
        int cnt = 1;
        for(int i=index+1; i < array.length && array[i]==k;i++){
            cnt++;
        }
        for(int i=index-1; i >= 0 && array[i]==k;i--){
            cnt++;
        }
        return cnt;
    }
    /**
     * 手写二分查找
     */
    public int binarySearch(int[] array,int k){
        int low=0,high = array.length-1;
        int mid;
        while (low<=high){
            mid =(low+high)/2;
            if (array[mid]>k){
                high=mid-1;
            }else if(array[mid]<k){
                low = mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = {1,2,2,2,2,2,2,2,5,6};
        Test37 t = new Test37();
        System.out.println(t.binarySearch(a,2));
    }
}
