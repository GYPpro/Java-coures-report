package sis4;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;


// 第一行包含两个整数n,m，分别表示该数列数字的个数和操作的总个数第二行包含n个用空格分隔的整数，其中第个数字表示数列第项的初始值接下来m行每行包含3或4个整数，表示一个操作，具体如下
// 1.1xyk:将区间a,y 内每个数加上K。
// 2.2xy:输出区间 ,y 内每个数的和。

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>(n);
        segTree<Integer> st = new segTree<Integer>(n, 0);
        for (int i = 0; i < n; i++)
            arr.add(sc.nextInt());
        st.build(arr);
        for (int i = 0; i < m; i++) {
            int op = sc.nextInt(), l = sc.nextInt(), r = sc.nextInt();
            if (op == 1) {
                k = sc.nextInt();
                st.update(l, r, k);
            } else if (op == 2) {
                System.out.println(st.getsum(l, r));
            }
        }
        sc.close();
    }
}
