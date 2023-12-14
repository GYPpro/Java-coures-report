package sis8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class minCost { //JAVA version
    public static class edge {
        public int v, f, c, next;

        edge(int _v, int _f, int _c, int _next) {
            v = _v;
            f = _f;
            c = _c;
            next = _next;
        }

        edge() {
        }
    }

    private static final int INF = 0x3f3f3f3f;

    public static void vecset(int value, ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++)
            arr.set(i, value);
        return;
    }

    public static class node {
        public int v, e;
    }

    public static class mypair implements Comparable<mypair> {
        public int dis, id;

        public mypair(int d, int x) {
            dis = d;
            id = x;
        }

        public int compareTo(mypair a) {
            return dis - a.dis;
        }
    }

    public ArrayList<Integer> head;
    public ArrayList<Integer> dis;
    public ArrayList<Integer> vis;
    public ArrayList<Integer> h;
    public ArrayList<edge> e;
    public ArrayList<node> p;
    public int n, m, s, t, cnt = 1, maxf, minc;

    public minCost(int _n, int _m, int _s, int _t) {
        n = _n;
        m = _m;
        s = _s;
        t = _t;
        maxf = 0;
        minc = 0;
        head = new ArrayList<Integer>();
        dis = new ArrayList<Integer>();
        vis = new ArrayList<Integer>();
        e = new ArrayList<edge>();
        h = new ArrayList<Integer>();
        p = new ArrayList<node>();
        for (int i = 0; i <= n + 2; i++) {
            head.add(0);
            dis.add(0);
            vis.add(0);
            h.add(0);
        }
        for (int i = 0; i <= m + 2; i++)
            p.add(new node());
    }

    public void addedge(int u, int v, int f, int c) {
        e.add(new edge(v, f, c, head.get(u)));
        head.set(u, e.size() - 1);
        e.add(new edge(u, 0, -c, head.get(v)));
        head.set(v, e.size() - 1);
    }

    public boolean dijkstra() {
        PriorityQueue<mypair> q = new PriorityQueue<mypair>();
        vecset(INF, dis);
        vecset(0, vis);
        dis.set(s, 0);
        q.add(new mypair(0, s));
        while (!q.isEmpty()) {
            int u = q.peek().id;
            q.poll();
            if (vis.get(u) == 1)
                continue;
            vis.set(u, 1);
            for (int i = head.get(u); i != 0; i = e.get(i).next) {
                int v = e.get(i).v, nc = e.get(i).c + h.get(u) - h.get(v);
                if (e.get(i).f != 0 && dis.get(v) > dis.get(u) + nc) {
                    dis.set(v, dis.get(u) + nc);
                    p.get(v).v = u;
                    p.get(v).e = i;
                    if (vis.get(v) != 1)
                        q.add(new mypair(dis.get(v), v));
                }
            }
        }
        return dis.get(t) != INF;
    }

    public void spfa() {
        Queue<Integer> q = new LinkedList<Integer>();
        vecset(63, h);
        h.set(s, 0);
        vis.set(s, 1);
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.peek();
            q.poll();
            vis.set(u, 0);
            for (int i = head.get(u); i != 0; i = e.get(i).next) {
                int v = e.get(i).v;
                if (e.get(i).f != 0 && h.get(v) > h.get(u) + e.get(i).c) {
                    h.set(v, h.get(u) + e.get(i).c);
                    if (vis.get(v) != 1) {
                        vis.set(v, 1);
                        q.add(v);
                    }
                }
            }
        }
    }

    public int pd() {
        spfa();
        while (dijkstra()) {
            int minf = INF;
            for (int i = 1; i <= n; i++)
                h.set(i, h.get(i) + dis.get(i));
            for (int i = t; i != s; i = p.get(i).v)
                minf = Math.min(minf, e.get(p.get(i).e).f);
            for (int i = t; i != s; i = p.get(i).v) {
                e.get(p.get(i).e).f -= minf;
                e.get(p.get(i).e ^ 1).f += minf;
            }
            maxf += minf;
            minc += minf * h.get(t);
        }
        return 0;
    }   

    public void printAns() {
        System.out.println(maxf + " " + minc);
    }

}
