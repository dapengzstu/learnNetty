package interview.algorithm;

import java.util.Arrays;

public class Contest153 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start == destination){
            return 0;
        }
        int clockwise = clockwise(distance, start, destination);

        int counter = counterclockwise(distance,start,destination);
        return Math.min(clockwise,counter);
    }

    private int counterclockwise(int[] distance, int start, int destination) {
        int sum = 0 ;
        if(destination < start){
            for (int i = start - 1 ; i >= destination ; i--){
                sum += distance[i];
            }
        }else{
            for (int i = start - 1 ; i >= 0 ;i --){
                sum += distance[i];
            }
            for (int i = distance.length - 1 ; i >=destination ;i--){
                sum += distance[i];
            }
        }
        return sum;
    }

    private int clockwise(int[] distance, int start, int destination) {
        int sum = 0;
        if (destination > start){
            for (int i = start ; i < destination ; i ++){
                sum += distance[i];
            }
        }else {
            for (int i = start ; i <= distance.length  - 1 ; i ++){
                sum += distance[i];
            }
            for (int i = 0 ;i < destination ; i ++){
                sum += distance[i];
            }
        }
        return  sum;
    }
    public String dayOfTheWeek(int day, int month, int year) {
        int []monthdays = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        String []array = {"Thursday","Friday", "Saturday","Sunday",  "Monday", "Tuesday", "Wednesday"};
        int days = 0;
        for (int i = 1971 ;i < year ; i ++){
            if (isRUn(i)){
                days += 366;
            }else {
                days += 365;
            }
        }
        for (int i = 1 ; i < month ; i ++){
            days += monthdays[i];
        }
        if (isRUn(year) && month > 2){
            days ++;
        }
        days += day;
        int offset = days % 7 ;
        return array[offset];

    }
    public boolean isRUn(int y){
        if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0 && y % 3200 != 0)){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String s = new Contest153().dayOfTheWeek(8, 9, 2019);
        System.out.println(s);
        boolean rUn = new Contest153().isRUn(1996);
        System.out.println(rUn);
    }
}

