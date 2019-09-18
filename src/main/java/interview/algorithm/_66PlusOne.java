package interview.algorithm;

import java.util.Arrays;

public class _66PlusOne {
    public static int[] plusOne(int[] digits){
        int k = digits.length - 1;


        while ( k >= 0){
            if (digits[k] == 9){
                digits[k] = 0;
                k --;
            }else {
                digits[k] = digits[k] + 1;
                return digits;
            }
        }
        int[] newDigits = new int[digits.length + 1];
        for (int i = 0 ; i < digits.length ; i++){
            newDigits[i + 1] = digits[i];
        }
        newDigits[0] = 1;
        return newDigits;

    }
    public static void main(String[] args) {
        int [] digits = {8,9,9,9};
        int[] ints = plusOne(digits);
        for (int i : ints){
            System.out.println(i);
        }
    }
}
