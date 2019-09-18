package interview.algorithm;

public class _189RotateArray {
    public void rotate(int[] nums, int k) {
        //O(N)  O(x)
        if (nums.length == 0 || nums == null || k == 0){
            return ;
        }
        if (k >= nums.length){
            k = k - nums.length;
        }
        int[] temp = new int[k];
        for (int i = nums.length - k, j = 0 ; i < nums.length ; i ++,j ++){
            temp[j] = nums[i];
        }
        for (int i = k + 1, j = 0;i <= nums.length; i ++ ,j ++){
            nums[nums.length - j - 1] = nums[nums.length - i];
        }
        for (int i = 0 ; i < temp.length; i ++){
            nums[i] = temp[i];
        }

    }
    public void rotate1(int[] nums,int k){
        int[] temp = new int[nums.length];
        for (int i = 0 ;i < nums.length ; i++){
            temp[ (i + k) % nums.length] = nums[i];
        }
        //重新赋值
        for (int i = 0  ; i < nums.length ; i++){
            nums[i] = temp[i];
        }
    }
    public void rotate2(int[] nums,int k) {

    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        new _189RotateArray().rotate(nums,4);
        for (int i :
                nums) {
            System.out.println(i);
        }
    }
}
