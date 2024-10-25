package lab1;

import java.util.Scanner;

public class ReverseWord {
    public static void pritnReversed(String str){
        String revStr = new StringBuilder(str).reverse().toString();
        System.out.println(revStr);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            String str = sc.nextLine();
            pritnReversed(str);
        }
    }
}
