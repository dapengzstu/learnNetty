package interview.algorithm.find;

/**
 * @auther dapeng
 * @date 19-9-13 上午10:32
 */
public class er {
    //二分查找，n替换掉第一个大于n的
    private static int find(int n,int[] tail,int end){
        int left = 0 , right = end;
        while (left < right){

            int mid = left + ((right - left) >>> 1);
            if (tail[mid] < n){
                left = mid + 1;
            }else {
                right = mid;
            }

        }
        return left;
    }

    //二分查找，如果没有就返回-1
    public  int find(int[] nums,int target){
        int left = 0 , right = nums.length - 1;
        int mid = 0;

        while(left < right){
            mid = (left + right)/2;
            if (nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return (left == right && nums[left] == target )? left : -1;
    }
}
