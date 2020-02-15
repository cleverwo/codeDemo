package baseDemo.search;


/**
 * @Auther: wangzhendong
 * @Date: 2020/1/2 13:32
 * @Description: 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1,3,4,5,11,22,23,45};
        System.out.println(binarySearch(array,1));
    }

    //折半查找
    public static int binarySearch(int[] array,int key){
        int low = 0, hight = array.length-1, mid;
        while(low <=hight ){
            mid = (low+hight)/2;
            if (array[mid] == key){
                return mid;
            }else if (array[mid] > key){
                hight = mid -1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }
}
