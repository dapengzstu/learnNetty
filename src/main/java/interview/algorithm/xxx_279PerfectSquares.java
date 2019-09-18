package interview.algorithm;

public class xxx_279PerfectSquares {
    // 所有的完美平方数都是看做一个普通的数字，加上一个完美平方数。那么递推式就是: dp[i + j * j] = min(dp[i]+1,dp[i + j * j])
    public static int numSquares(int n){
        int[] dp = new int[n+1];
        for (int i = 0 ;i < dp.length ; i ++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i= 0 ; i <= n ; i ++){
            for (int j = 1; i + j * j <= n ; j++){
                int result = i + j * j;
                dp[i + j * j] = Math.min(dp[i + j *j ] ,dp[i] + 1);
            }
        }
        return dp[n];
    }

    //第二种方法
    public int numSquares2(int n) {

        if (n < 4)
            return n;

        int[] res = new int[n+1];

        // initialize the array values for all perfect squares
        for (int i = 1; i*i <= n; ++i)
            res[i*i] = 1;

        return recNumSq(n, res);
    }


    private int recNumSq(int n, int[] cache) {

        if (cache[n] != 0)
            return cache[n];

        int min = n;
        // for each i*i less than n, add count of n/(i*i) and recurse over remainder
        for (int i = (int)Math.sqrt(n); i > 1 && min > 2; --i) {
            min = Math.min(min, n/(i*i) + recNumSq(n-i*i*(n/(i*i)), cache));
        }

        cache[n] = min;
        return min;
    }


    //自己写的第二种
    public static int ns(int n){
        int[] cache = new int[n + 1];
        for (int i = 1; i * i <= n; i ++){
            cache[i * i] = 1;
        }
        return re(n,cache);
    }
    public static int re(int n,int[] cache){
        if (cache[n] != 0 ){
            return cache[n];
        }
        int min = n;
        for (int i = (int)Math.sqrt(n); i>1 && min >2 ; i--){
            min = Math.min(min,n/(i*i) + re(n-i * i *(n/(i*i)),cache));
        }
        cache[n] = min;
        return min;
    }
    public static void main(String[] args) {
        int i = new xxx_279PerfectSquares().ns(99);
        System.out.println(i);

    }

}
