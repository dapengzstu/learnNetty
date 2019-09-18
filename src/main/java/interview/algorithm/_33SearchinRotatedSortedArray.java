package interview.algorithm;

/**
 * @auther dapeng
 * @date 19-9-13 下午7:55
 */
public class _33SearchinRotatedSortedArray {
    public int search(int[] nums,int target){
        int mid = 0;
        int left = 0 , right = nums.length - 1;
        while (left < right){
            mid = (left + right)/2;

            if(target < nums[mid] && target > nums[0]){
                right = mid;
            }else if((nums[0] > nums[mid]) && (target < nums[mid] || target > nums[0])){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left == right && nums[left] == target ? left : -1;
    }

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

    public static void main(String[] args) {
//        int []nums = {5,4,3,0,1,2};
//        int search = new _33SearchinRotatedSortedArray().search(nums, 1);
//        System.out.println(search);


    }
}
