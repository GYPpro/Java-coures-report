package sis3;

import java.util.Scanner;

// 输入一行一个整数T，表示数据组数T
//对于每组数据，格式如下
// 第一行是两个整数，分别表示模式串的个数n和询问的个数q
// 接下来n行，每行一个模式串，表示模式串集合
// 接下来q行，每行一个询问，表示询问集合
public class Test {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int T = sc.nextInt();
            TrieTree t = new TrieTree();
            while (T --> 0) {
                int n = sc.nextInt();
                int q = sc.nextInt();
                sc.nextLine();
                while (n-- > 0) {
                    String s = sc.nextLine();
                    t.addedge(s);
                }
                while (q-- > 0) {
                    String s = sc.nextLine();
                    if (t.ifcmp(s))
                        System.out.println("YES");
                    else
                        System.out.println("NO");
                }
            }
        }
    }

}

