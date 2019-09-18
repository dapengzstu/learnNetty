package interview.algorithm.math;

/**
 * @auther dapeng
 * @date 19-9-14 上午8:04
 */
public class _69Sqrt {
    public int mySqrt(int x) {
        if (x == 0 || x == 1){
            return x;
        }
        long left = 0 , right = x/2 + 1;
        while(left + 1 != right){
            long mid = (left + right + 1) >>> 1 ;
            long re = mid * mid;
            if(re == x  ){
                return (int)mid;
            }else if(re < x){
                left = mid;
            }else{
                right = mid;
            }
        }
        return (int)left;
    }

    public static void main(String[] args) {
        int i = new _69Sqrt().mySqrt(2147395599);
        System.out.println(i);

    }
}
