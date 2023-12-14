package sis4;

import java.util.ArrayList;

public class segTree<N> {
    private ArrayList<N> d;
    private ArrayList<N> a;
    private ArrayList<N> b;
    private int n;
    private N INI;

    private void subbuild(int s, int t, int p)
    {
        if (s == t)
        {
            d.set(p, a.get(s));
            return;
        }
        int m = s + ((t - s) >> 1); //  (s+t)/2
        subbuild(s, m, p * 2);
        subbuild(m + 1, t, p * 2 + 1);
        d.set(p, merge(d.get(p * 2), d.get(p * 2 + 1)));
        //    合并运算符 ↑
    }

    private N subGetSum(int l, int r, int s, int t, int p)
    {
        if (l <= s && t <= r)
            return d.get(p);
        int m = s + ((t - s) >> 1);
        if (b.get(p) != null)
        {
            d.set(p * 2, merge(d.get(p * 2), b.get(p))); // 合并运算符的高阶运算 此处运算为应用懒惰标记
            d.set(p * 2 + 1, merge(d.get(p * 2 + 1), b.get(p))); // 合并运算符的高阶运算 此处运算为应用懒惰标记
            b.set(p * 2, merge(b.get(p * 2), b.get(p)));           // 下传标记，无需修改
            b.set(p * 2 + 1, merge(b.get(p * 2 + 1), b.get(p)));           // 下传标记，无需修改
            b.set(p, null);
        }
        N ansl = INI;
        N ansr = INI;
        if (l <= m)
            ansl = subGetSum(l, r, s, m, p * 2);
        if (r > m)
            ansr = subGetSum(l, r, m + 1, t, p * 2 + 1);
        return merge(ansl, ansr);
        // 合并运算符↑
    }

    private void subUpdate(int l, int r, N c, int s, int t, int p)
    {
        if (l <= s && t <= r)
        {
            d.set(p, merge(d.get(p), merge(c, (t - s + 1)))); // 合并运算符的高阶运算 此处运算为修改整匹配区间值
            b.set(p, merge(b.get(p), c));               // 记录懒惰标记，无需修改
            return;
        }
        int m = s + ((t - s) >> 1);
        if (b.get(p) != null && s != t)
        {
            d.set(p * 2, merge(d.get(p * 2), merge(b.get(p), (m - s + 1)))); // 合并运算符的高阶运算 此处运算为应用懒惰标记
            d.set(p * 2 + 1, merge(d.get(p * 2 + 1), merge(b.get(p), (t - m)))); // 合并运算符的高阶运算 此处运算为应用懒惰标记
            b.set(p * 2, merge(b.get(p * 2), b.get(p)));               // 下传标记，无需修改
            b.set(p * 2 + 1, merge(b.get(p * 2 + 1), b.get(p)));           // 下传标记，无需修改
            b.set(p, null);
        }
        if (l <= m)
            subUpdate(l, r, c, s, m, p * 2);
        if (r > m)
            subUpdate(l, r, c, m + 1, t, p * 2 + 1);
        d.set(p, merge(d.get(p * 2), d.get(p * 2 + 1)));
        //    合并运算符 ↑
    }

    private N merge(N a,N b)
    {
        return a;
    }

    private N merge(N a,int b)
    {
        return a;
    }


    public segTree(int _n,N _INI)
    {
        n = _n;
        INI = _INI;
        d = new ArrayList<N>(4 * n + 5);
        a = new ArrayList<N>(4 * n + 5);
        b = new ArrayList<N>(4 * n + 5);
    }

    public void build(ArrayList<N> _a)
    {
        a = _a;
        subbuild(1, n, 1);
    }

    public N getsum(int l, int r)
    {
        return subGetSum(l, r, 1, n, 1);
    }

    public void update(int l, int r, N c) // 修改区间
    {
        subUpdate(l, r, c, 1, n, 1);
    }

    public void update(int idx, N tar)
    { // 修改单点，未测试
        N tmp = getsum(idx, idx);
        tar = merge(tar, tmp);
        subUpdate(idx, idx, tar, 1, n, 1);
    }

}