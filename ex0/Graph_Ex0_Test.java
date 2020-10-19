package ex0;

import java.util.List;
import java.util.Random;

public class Graph_Ex0_Test {
    static int seed = 31;
    static Random _rnd = new Random(seed);
    static int v_size = 10;
    static int e_size = v_size*3;
    static graph g0 = new Graph_DS(), g1;
    static graph_algorithms ga;
    public static void main(String[] args) {
        test1();
        System.out.println(g0);
    }
    public static void test1() {

        for(int i=0;i<v_size;i++) {
            node_data n = new NodeData();
            g0.addNode(n);
        }
        System.out.println(g0.getNode(1).getKey());
        g0.connect(1,2);
        g0.getNode(1).getNi();

//        while(g0.edgeSize() < e_size) {
//            int a = nextRnd(0,v_size);
//            int b = nextRnd(0,v_size);
//            g0.connect(a,b);
//        }
        // System.out.println(g0);
    }

    public static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    public static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
}
