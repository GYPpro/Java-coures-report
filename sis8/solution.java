package sis8;

public class solution { 
    //构建带权图，求解最大流与最小费用流
    public static void main(String[] args) {
        try (java.util.Scanner sc = new java.util.Scanner(System.in)) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int s = sc.nextInt();
            int t = sc.nextInt();
            minCost pd = new minCost(n, m, s, t);
            maxFlow mf = new maxFlow(n);
            for (int i = 1; i <= m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int c = sc.nextInt();
                int f = sc.nextInt();
                mf.addedge(u, v, c);
                pd.addedge(u, v, c, f);
            }
            pd.spfa();
            System.out.println(" 考虑费用的最小费用流 " + pd.maxf + " " + pd.minc);
            mf.resetTS(t, s);
            mf.dinic();
            System.out.println(" 不考虑费用的最大流 " + mf.dinic());
        }
    }
    
}
