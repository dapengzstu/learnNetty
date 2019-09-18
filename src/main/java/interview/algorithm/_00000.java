package interview.algorithm;

import interview.algorithm.sort.QuckSort;

/**
 * @auther dapeng
 * @date 19-9-13 上午10:35
 */
public class _00000 {
    public static void qs(int [] nums){
        qs(nums,0,nums.length - 1);
    }
    public static void qs(int[] nums,int left,int right){
        if (left < right){
            int partitionIndex = partitionIndex(nums,left,right);
            qs(nums,left,partitionIndex - 1);
            qs(nums,partitionIndex + 1,right);
        }
    }
    private static int partitionIndex(int[] nums,int left,int right){
        int pivot = nums[right];
        int i = left - 1;
        for (int j = left ; j < right ; j ++){
            if (nums[j] < pivot){
                i ++;
                swap(nums,i,j);
            }
        }
        i ++;
        swap(nums,i,right);
        return i;
    }
    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args) {
        int[] nums = {6,1,2,7,9,3,4,5,10,8};
        _00000.qs(nums);
        for (int n :
                nums) {
            System.out.println(n);
        }
    }
}
