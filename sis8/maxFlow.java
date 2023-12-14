package sis8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class maxFlow {
    private class edge {
        public int nxt,                   // 出度
                   cap,                   // 容量
                   flow;                  // 流量
        public int[] revNodeIdx; // 反向边
        public edge(int _nxt, int _cap) {
            nxt = _nxt;
            cap = _cap;
            flow = 0;
        }
        public void setRevIdx(int _i, int _j) {
            revNodeIdx = new int[2];
            revNodeIdx[0] = _i;
            revNodeIdx[1] = _j;
        }
    }
    private ArrayList<ArrayList<edge>> edgeList; // 节点列表
    private ArrayList<Integer> dep;               // 深度
    private ArrayList<Integer> fir;               // 节点最近合法边
    private int maxFlowAns;

    private int T, S;

    public maxFlow(int _n) {
        maxFlowAns = 0;
        S = 1;
        T = _n;
        edgeList = new ArrayList<ArrayList<edge>>();
        for(int i = 0;i <= _n;i ++) edgeList.add(new ArrayList<edge>());
        dep = new ArrayList<Integer>();
        fir = new ArrayList<Integer>();
        for(int i = 0;i <= _n;i ++) dep.add(0);
        for(int i = 0;i <= _n;i ++) fir.add(0);
    }

    public void resetTS(int _T, int _S) {
        T = _T;
        S = _S;
    }

    public void addedge(int _u, int _v, int _w) {
        edgeList.get(_u).add(new edge(_v, _w));
        edgeList.get(_v).add(new edge(_u, 0)); // 反向建边
        edgeList.get(_u).get(edgeList.get(_u).size() - 1).setRevIdx(_v, edgeList.get(_v).size() - 1);
        edgeList.get(_v).get(edgeList.get(_v).size() - 1).setRevIdx(_u, edgeList.get(_u).size() - 1);
    }

    public boolean bfs() { // 统计深度
        Queue<Integer> que = new LinkedList<Integer>();
        for (int i = 0; i < dep.size(); i++)
            dep.set(i, 0);
        
        dep.set(S, 1);
        que.add(S);
        while (que.size() != 0) {
            int at = que.peek();
            que.poll();
            for (int i = 0; i < edgeList.get(at).size(); i++) {
                edge tar = edgeList.get(at).get(i);
                if ((dep.get(tar.nxt) == 0) && (tar.flow < tar.cap)) {
                    dep.set(tar.nxt, dep.get(at) + 1);
                    que.add(tar.nxt);
                }
            }
        }
        return dep.get(T) != 0;
    }

    public int dfs(int at, int flow) {
        if ((at == T) || (flow == 0))
            return flow; // 到了或者没了
        int ret = 0;  // 本节点最大流
        for (int i = fir.get(at); i < edgeList.get(at).size(); i++) {
            edge tar = edgeList.get(at).get(i);      // 目前遍历的边
            int tlFlow = 0;                  // 目前边的最大流
            if (dep.get(at) == dep.get(tar.nxt) - 1) { // 遍历到的边为合法边
                tlFlow = dfs(tar.nxt, Math.min(tar.cap - tar.flow, flow - ret));
                if (tlFlow == 0)
                    continue;                                                         // 若最大流为0，什么都不做
                ret += tlFlow;                                                        // 本节点最大流累加
                edgeList.get(at).get(i).flow += tlFlow;                                       // 本节点实时流量
                edgeList.get(tar.revNodeIdx[0]).get(tar.revNodeIdx[1]).flow -= tlFlow; // 反向边流量
                if (ret == flow)
                    return ret; // 充满了就不继续扫了
            }
        }
        return ret;
    }

    public int dinic() {
        if (maxFlowAns != 0)
            return maxFlowAns;
        while (bfs()) {
            for(int i = 0;i < fir.size();i ++) fir.set(i, 0);
            maxFlowAns += dfs(S, Integer.MAX_VALUE);
        }
        return maxFlowAns;
    }

}
