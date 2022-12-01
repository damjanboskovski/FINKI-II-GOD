package Auditoriski.Two.First;

public class StringPrefix {

    public static boolean isPrefix(String str1, String str2){
        if(str1.length() > str2.length()){ return false; }
        for(int i=0; i<str1.length(); i++){
            if(str1.charAt(i) == str2.charAt(i)) { return true; }
        }
        return false;
    }
    public static void main(String[] args){
        String str1 = "test";
        String str2 = "test";
        System.out.println(isPrefix(str1,str2));
    }
}
