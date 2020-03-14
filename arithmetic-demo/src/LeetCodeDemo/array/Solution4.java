package LeetCodeDemo.array;

/**
 * @Auther: 10413
 * @Date: 2020/3/12 11:07
 * @Description: 4, 寻找两个有序数组的中位数
 */
public class Solution4 {

    /**
     * 思路： 双指针做咋样
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0;
        }
        int n = nums1.length;
        int m = nums2.length;
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums2[m / 2 - 1] + nums2[m / 2]) / 2.0;
            } else {
                return nums2[m / 2];
            }
        }
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums1[n / 2 - 1] + nums1[n / 2]) / 2.0;
            } else {
                return nums1[n / 2];
            }
        }
        int[] k = new int[m + n];
        int i = 0, j = 0, s = 0;
        while (i < n && j < m) {
            if (nums1[i] > nums2[j]) {
                k[s++] = nums2[j++];
            } else {
                k[s++] = nums1[i++];
            }
        }
        if (i < n) {
            while (i < n) {
                k[s++] = nums1[i++];
            }
        }
        if (j < m) {
            while (j < m) {
                k[s++] = nums2[j++];
            }
        }
        if (k.length % 2 == 0) {
            return (k[k.length / 2 - 1] + k[k.length / 2]) / 2.0;
        } else {
            return k[k.length / 2];
        }
    }

    // 想法是遍历中位数次，用mid指向中位数，mid1指向中位数的前一个
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0;
        }
        int n = nums1.length;
        int m = nums2.length;
        int a = 0, b = 0;
        int mid, mid1;
        if (n == 0) {
            mid = nums2[b];
        }else if (m==0){
            mid = nums1[a];
        }else{
            mid = Math.min(nums1[a],nums2[b]);
        }
        mid1=mid;
        int count = (m + n) / 2;
        while (count > 0) {
            if (a < n && b < m) {
                mid1 = mid;
                if (nums1[a] > nums2[b]) {
                    mid = nums1[a];
                    b++;
                } else {
                    mid = nums2[b];
                    a++;
                }
            } else if (a < n) {
                mid = nums1[a];
                a++;
            } else {
                mid = b;
                b++;
            }
            count--;
        }
        if ((m + n) % 2 == 0) {
            return (mid + mid1) / 2.0;
        } else {
            return mid;
        }
    }
    //遍历 ，right值一直指向小的值，多遍历一次，比我的mid指向中间值多循环1次
    /**
     * 答案1：遍历len/2+1次
     */
    public double findMedianSortedArrays1_1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            // n1 不越界且n2越界，或者n1的值小于n2的值
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                //中值指向小的值
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    /**
     *答案2： 求第k小的数字，
     * 两个数组每次取k/2 的数字比较，小的那个前面肯定都小于k/2，所以删了，删了m=k/2-1个数，这时求的k=k-m
     * 在对剩下的数组 在都取 k/2 的坐标在进行比较
     * 这样每次排除一般的数，直到比较最开始的值
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (m+n+1)/2;
        int right = (m+n+2)/2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }
    //递归寻找第k小的数
    public int getKth(int[] nums1,int start1,int end1,int[] nums2,int start2,int end2,int k){
        int len1 = end1 - start1 +1;
        int len2 = end2 - start2 +1;
        // 让len1 的长度小于len2 的长度，这样必定是len1先为空
        if (len1>len2){
            return getKth(nums2,start2,end2,nums1,start1,end1,k);
        }
        if (len1 == 0) return nums2[start2 + k - 1];
        //最小的数，直接取nums1和nums2起始值的最小
        if (k==1){
            return Math.min(nums1[start1],nums2[start2]);
        }
        //这里的i，j指向每个数组的第k/2小的数的下标，因为可能存在，这个下标比数组的实际长度要大，所以取最小值
        int i = start1 + Math.min(len1,k/2)-1;
        int j = start2 + Math.min(len2,k/2)-1;
        if(nums1[i]>nums2[j]){
            //数组1 大于 数组2 ，数组2 删元素坐标前面的都不算数了包含坐标，坐标是k/2 的数
            // 总的元素是k 要减去 删除的坐标的个数，删了j-start2+1个数
            return getKth(nums1,start1,end1,nums2,j+1,end2,k-(j-start2+1));
        }else{
            return getKth(nums1,i+1,end1,nums2,start2,end2,k-(i-start1+1));
        }

    }

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        int[] a = new int[]{1,2};
        int[] b = new int[]{3,4};
        System.out.println(s.findMedianSortedArrays2(a, b));
    }
}
