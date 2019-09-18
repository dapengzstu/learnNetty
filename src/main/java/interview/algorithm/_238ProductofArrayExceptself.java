package interview.algorithm;

import java.util.Arrays;

public class _238ProductofArrayExceptself {

    public  int[] productExceptSelf(int[] nums){
        int[] result1 = new int[nums.length];
        int[] result2 = new int[nums.length];
        result1[0] = 1;
        for (int i = 1 ;i < result1.length ; i++){
            result1[i] = result1[i - 1] * nums[i - 1];
        }
        result2[nums.length - 1] = 1;
        for (int i = nums.length - 2 ; i >= 0 ; i --){
            result2[i] = result2[i + 1] * nums[i + 1];
        }
        for (int i = 0 ; i < nums.length ; i++){
            result1[i] = result1[i ] * result2[i ];
        }
        return result1;
    }
    public static void main(String[] args) {
        int[] nums = {4,5};
        int[] ints = new _238ProductofArrayExceptself().productExceptSelf(nums);
        for (int i :
                ints) {
            System.out.println(i);
        }
    }
}
