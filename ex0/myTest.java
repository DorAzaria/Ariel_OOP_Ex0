package ex0;


public class myTest {

    static graph g0 = new Graph_DS(), g1;
    public static void main(String[] args) {
        for(int i=0;i<5;i++) {
            node_data n = new NodeData();
            g0.addNode(n);
        }

        g0.connect(1,2);
//        g0.connect(1,4);
//        g0.connect(2,3);
    }
}
