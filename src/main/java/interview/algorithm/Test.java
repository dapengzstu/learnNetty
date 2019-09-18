package interview.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public List<Integer> findDuplicates(int[] nums) {


        ArrayList<Integer> ar = new ArrayList<Integer>();
        int res = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {

            int k = (nums[i] % res) - 1;
            nums[k] += res;

            if ((nums[k] / res) == 2)
                ar.add(k + 1);

        }

        return ar;


    }

    public static void main(String[] args) {
        int[] arr = {4,3,2,7,8,2,2,3};
        List<Integer> duplicates = new Test().findDuplicates(arr);
    }
}
