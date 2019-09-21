package interview.algorithm;

/**
 * @auther dapeng
 * @date 19-9-19 下午6:28
 */
public class _1021RemoveOutmostParentheses {
    public String removeOuterParentheses(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        int pre = 0;
        for(int i = 0 ; i < S.length() ; i ++){
            if(S.charAt(i) == '('){
                if(count == 0){
                    pre = i;
                    count = 1;
                }else{
                    count ++;
                }
            }else if(S.charAt(i) == ')'){
                count --;
                if(count == 0){
                    stringBuilder.append(S.substring(pre + 1,i));
                }
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = new _1021RemoveOutmostParentheses().removeOuterParentheses("((sss)(s))(s)");
        System.out.println(s);

        String S = "abcde";
        System.out.println(S.substring(2,5));

    }

}
