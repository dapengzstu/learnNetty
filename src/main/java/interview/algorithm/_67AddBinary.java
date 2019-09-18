package interview.algorithm;

public class _67AddBinary {
    public static String addBinary(String a, String b) {
        int com = a.length() - b.length();
        if (com != 0){
            StringBuilder s = new StringBuilder();
            for (int i = 0 ;i < Math.abs(com); i++){
                s.append("0");
            }
            if (com > 0){
                b = s + b;
            }else {
                a = s +a;
            }

        }
        int k = 0;
        int ak = a.length() - k - 1;
        int bk = b.length() - k - 1;
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        while (ak >= 0 && bk >= 0){
            if (a.charAt(ak) == '0' && b.charAt(bk) == '0'){
                if (flag){
                    result.append(1);
                }else {
                    result.append(0);
                }
                flag = false;
            }else if (a.charAt(ak) == '1' && b.charAt(bk) == '1'){
                if (flag){
                    result.append(1);
                }else {
                    result.append(0);
                }
                flag = true;
            }else {
                if (flag){
                    result.append(0);
                    flag = true;
                }else {
                    result.append(1);
                    flag = false;
                }
            }
            ak --;
            bk --;
        }

        if (flag){
            result.append("1");
        }
        StringBuilder reverse = result.reverse();
        return reverse.toString();
    }
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        String s = addBinary(a, b);
        System.out.println(s);
    }
}
