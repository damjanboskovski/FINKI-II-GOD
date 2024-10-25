package lab1;

import java.util.Arrays;
import java.util.Scanner;

public class PushZero {
    static void pushZeroToFront(Integer[] arr, int n){
        Arrays.sort(arr);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        pushZeroToFront(arr,n);
        System.out.println("Transformed: ");
        for(int i = 0; i < n; i++){
            System.out.println(arr[i] + " ");
        }
    }
}
