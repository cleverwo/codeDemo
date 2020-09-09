package LeetCodeDemo;

/**
 * @Auther: 10413
 * @Date: 2020/3/19 22:00
 * @Description:
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class Solution34 {
    /**
     * 二分查找看似简单，，细节边界会让你爆炸
     */
    public int[] searchRange(int[] nums, int target) {
        int[] out = new int[]{-1,-1};
        if (nums==null||nums.length==0){
            return out;
        }
        int low = 0,high =nums.length-1;
        while (low<high){
            int mid = (low+high)/2;
            if (mid>target||mid == target){

            }
            if (target<nums[mid]){

            }
        }
        return out;
    }

    /**
     * 答案： 找到了也不结束，直到全部弄完 这道题里额一个帖子，整合的二分查找
     */
    // 查左侧边界
    public int searchLeft(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return -1;
        }
        int low = 0;
        int high = nums.length; //这里不是length-1 注意
        while (low<high){ // 注意
            int mid = (low + high)/2;
            if (nums[mid]==target){
                high = mid;
            }else if(nums[mid]<target){
                low = mid + 1;
            }else if (nums[mid]>target){
                high = mid; // 注意
                //这个很好解释，因为我们的「搜索区间」是 [left, right) 左闭右开，所以当 nums[mid] 被检测之后，下一步的搜索区间应该去掉 mid 分割成两个区间，即 [left, mid) 或 [mid + 1, right)。
            }
        }
        // 这里的low的意思是比target小的数的个数，取值范围为[0-nums.length]
        // target 比所有数都大 low =length 表示所有元素都比target小
        if (low == nums.length) return -1;
        // low = 0 表示比target小的元素为0个，看0坐标是否为target
        return nums[low] == target ? low : -1;
    }

    //寻找右边界
    public int searchRight(int[] nums, int target) {
        if(nums==null||nums.length==0){
            return -1;
        }
        int low = 0;
        int high = nums.length;
        while (low<high){
            int mid = (low+high)/2;
            if (nums[mid]==target){
                low = mid+1;
            }else if (target < nums[mid]){
                high=mid;
            }else if (nums[mid] < target){
                low = mid +1;
            }
        }
        //low 指向比target大的元素的坐标，low的范围为0-length
        //low 为0表示没有比target大的元素，low=length表示末尾元素<=target
        if (low == 0) return -1;
        return nums[low-1] == target ? (low-1) : -1;
    }
    //找左右边界
    public int[] searchRange1(int[] nums, int target){
        int[] out = new int[2];
        if(nums==null||nums.length==0){
            out[0]=-1;
            out[1]=-1;
            return out;
        }
        int left = searchLeft(nums,target);
        int right = searchRight(nums,target);
        out[0] = left;
        out[1] = right;
        return out;
    }

    /**
     * 左右一块找
     */
    public int[] searchRange2(int[] nums, int target){
        int[] targetRange = {-1, -1};
        int leftIdx = extremeInsertionIndex(nums, target, true);
        //leftIdx 为 true时，但leftIdx = length，表示所有元素都小于target，没有左边界返回-1
        //leftIdx=0时，表示比target小的元素有0个，判读0是否和target相等，不相等表示所有元素比target大，也就没有左边界
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }
        targetRange[0] = leftIdx;
        //左边界存在，表示右边界也一定存在，右边界 leftIdx-1 = target
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;
        return targetRange;
    }
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            //left = true 为找左边界和searchLeft一样， left = false 找右边界
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        //lo 取值范围为[0-length]
        return lo;
    }

    public static void main(String[] args) {
        Solution34 s = new Solution34();
        int[] a = {1,3,3,3,3,3};
        System.out.println(s.searchLeft(a,1));
    }
}
