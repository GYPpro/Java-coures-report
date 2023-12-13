package sis1;

import java.util.ArrayList;
import java.util.Scanner;

public class basicIO {
    public static void main(String[] args) {
        String initOutputMessage = "Input a div_arr(差分数组),this program will cacu the row arr.\nfirstly, you should input the length of your arr\n";
        System.out.print(initOutputMessage);
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>(n);
        for(int i = 0;i < n;i ++)
        {
            int tmp = sc.nextInt();
            arr.add(tmp);
        }
        System.out.print(arr.get(0) + " ");
        for(int i = 1;i < n;i ++)
        {
            arr.set(i,arr.get(i-1)+arr.get(i));
            System.out.print(arr.get(i) + " ");
        }
        System.out.print("\n");
        sc.close();
    }
}
