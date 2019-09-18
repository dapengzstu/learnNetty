package interview.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class _1025DivisorGame {
    public static boolean divisorGame(int N) {
        if (N == 1){
            return false;
        }
        int[] array = new int[N + 1];
        //1表示Alice赢，2表示Alice输
        array[1] = 2;
        array[2] = 1;

        int devisor = devisor(N, array);
        return devisor == 1? true: false;

    }
    public static int devisor(int N,int[] list){
        if (list[N] == 1){
            return 1;
        }else if (list[N] == 2){
            return 2;
        }
        List<Integer> divisor = getDivisor(N);
        int[] result = new int[3];
        for (Integer integer:divisor){
            int i = devisor(N - integer,list);
            if (i == 1){
                result[2] ++;
            }else if (i == 2){
                result[1] ++;
            }
        }
        if (result[1] > 0){
            list[N] = 1;
            return 1;
        }else{
            list[N] = 2;
            return 2;
        }


    }
    public static List<Integer> getDivisor(int N){
        List<Integer> list  = new ArrayList<Integer>();
        for (int i = 1;i <= N/2;i ++){
            if (N % i == 0){
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,5,6,7,8,9,10,11,15,16,20};
        for (int n:nums){
            System.out.println(divisorGame(n));
        }


    }
}
