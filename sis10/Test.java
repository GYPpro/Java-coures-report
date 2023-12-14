package sis10;

import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            myLinearLib lib = new myLinearLib();
            for(;;)
            {
                String s = sc.nextLine();
                try {
                    System.out.println(lib.parseCommand(s));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
