package ex0;


import java.util.Vector;

public class myTest {

    static graph g0 = new Graph_DS(), g1;
    public static void main(String[] args) {
        for(int i=0;i<7;i++) {
            node_data n = new NodeData();
            g0.addNode(n);
        }

        g0.connect(1,2);
        g0.connect(1,3);
        g0.connect(3,4);
        g0.connect(4,5);
        g0.connect(5,6);
        g0.removeNode(1);
        g0.getNode(2).getConnections().forEach(n -> System.out.print(n.getKey() + " "));
        System.out.println();
        g0.getNode(3).getConnections().forEach(n -> System.out.print(n.getKey() + " "));
    }
}
