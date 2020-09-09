package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/9/7 09:06
 * @Description:
 * 困难 逆序对
 */
public class Offer51 {

    // 归并排序解决
    int res = 0;
    public int reversePairs(int[] nums) {
        if (nums.length<2){
            return 0;
        }
        mergeSort(nums,0,nums.length-1);
        return res;
    }

    public void mergeSort(int[] nums, int l, int h) {
        if (l < h) {
            int mid = l+ (h-l) / 2;
            mergeSort(nums, mid+1, h);
            mergeSort(nums, l, mid);
            merge(nums, l, mid, h);
        }
    }

    public void merge(int[] nums, int l, int mid, int h) {
        int[] temp = new int[nums.length];
        int p1 = l, p2 = mid + 1, k = l;
        while (p1 <= mid && p2 <= h) {
            if (nums[p1] <= nums[p2]) {
                temp[k++] = nums[p1++];
                res += p2 - mid - 1;
            }else {
                temp[k++] = nums[p2++];
            }

        }
        while (p1 <= mid) {
            temp[k++] = nums[p1++];
            res += h-mid;
        }
        while (p2 <= h) {
            temp[k++] = nums[p2++];
        }
        for (int i = l; i <= h; i++) {
            nums[i] = temp[i];
        }
    }

    //--------------------------------------
    public static void main(String[] args) {
        Offer51 o = new Offer51();
        int[] test = {1,3,2,3,1};
        System.out.println(o.reversePairs(test));
    }
}
