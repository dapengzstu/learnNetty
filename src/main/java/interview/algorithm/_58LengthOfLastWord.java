package interview.algorithm;

public class _58LengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        char[] chars = new char[s.length()];
        s.getChars(0,s.length(),chars,0);
        int count = s.length() - 1;

        while (count >= 0 && chars[count] == ' '){
            count --;
        }
        int c = count;
        while (count >= 0 && chars[count] != ' '){
            count --;
        }
        return c - count;

        /*if (count == -1){
            return 0;
        }else {
            c = count;
            System.out.println(" c: " + c);
            while (count >= 0 && chars[count] != ' '){
                count --;
            }
            System.out.println("count : " + count);
        }*/
    }
    //faster

    public static void main(String[] args) {
        int hello_word = lengthOfLastWord("s");
        System.out.println(hello_word);
    }
}
