package interview.algorithm;

import java.util.Arrays;

public class xxx_300LongestIncreasingSubsequence {
    //1.    暴力求解
    public static int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    public static int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        int nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }
    /**
     *  状态转移
     *  如果nums[i] > nums[j] 那么dp[i] = nums[j] + 1;,找出dp[i] 的最大值
     * */
    //2.dp    不完全上升的
    public static int leng(int[] nums){
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;

    }
    //3. dp   完全上升的
    public static int l(int []nums){
        int[] dp = new int[nums.length];
        int res = 0;
        for (int i = 1 ;i < nums.length; i ++){
            int m = 0;
            for (int j = 0 ; j <= i ; j ++){
                if (nums[i] > nums[j]){
                    m = Math.max(dp[j],m);
                }
            }
            dp[i] += 1;
            res = Math.max(dp[i],res);
        }
        return  res;
    }
    /**
     * 4.    贪心算法
     *      思想：如果前面的数越小，后面接上一个随机数，就会有更大的可能性构成一个更长的“上升子序列”。
     *      1.  设置一个数组tail【】
     *      2.  如果一个数字大于tail中的最后一个数字，放在后面
     *      3.  如果不大于，在tail中查找第一个大于这个数字的，让他变小，这一步用二分查找
     *
     *
     */
    public static int LengthOFLIS(int[] nums){
        int[] tail = new int[nums.length];
        if (nums.length <= 1){
            return nums.length;
        }
        tail[0] = nums[0];
        int tailP = 0;
        for (int i = 1 ; i < nums.length ; i ++){
            if (nums[i] > tail[tailP]){
                tailP ++;
                tail[tailP] = nums[i];
            }else{
                int left = findTheFirstlargeNum(nums[i], tail, tailP);
                tail[left] = nums[i];
            }
            for(int k = 0 ; k <= tailP ; k ++){
                System.out.print(tail[k]+ ",");
            }
            System.out.println();
        }
        return tailP;
    }
    //二分查找法
    private static int findTheFirstlargeNum(int n,int[] tail,int end){
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


    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int i = LengthOFLIS(nums);
        System.out.println(i);
    }
}
