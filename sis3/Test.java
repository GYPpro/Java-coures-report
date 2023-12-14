package sis3;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SchoolLib lib = new SchoolLib();
        for(;;)
        {
            String s = sc.nextLine();
            try {
                System.out.print(lib.parseCommand(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
