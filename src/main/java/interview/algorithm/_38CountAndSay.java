package interview.algorithm;

public class _38CountAndSay {
    public static String countAndSay(int n) {
        StringBuilder s = new StringBuilder("1");
        StringBuilder newStr = new StringBuilder("");
        StringBuilder returnStr = new StringBuilder("");
        for (int i = 0 ; i < n - 1 ; i ++){

            for (int k = 0 ; k < s.length() ; ){
                int count = countChar(s, k);
                newStr.append(count);
                newStr.append(s.substring(k,k+1));
                k += count;
            }
            //每次的结果
            s = newStr;
            newStr = new StringBuilder("");
        }
        return s.toString();
    }
    public static int countChar(StringBuilder s,int k){
        int count = 0;
        char ch = s.charAt(k);
        for (int j = k ; j < s.length() ; j ++){
            if (s.charAt(j) == ch){
                count ++ ;
            }else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = countAndSay(1);
        System.out.println(s);
    }
}
