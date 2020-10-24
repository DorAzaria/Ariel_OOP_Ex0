package ex0;

import java.util.HashSet;
import java.util.List;

public class Graph_Algo implements graph_algorithms {
    private graph my_graph;

    public Graph_Algo(graph g) {
        init(g);
    }

    @Override
    public void init(graph g) {
        my_graph = g;
    }

    @Override
    public graph copy() {
        graph new_graph = new Graph_DS();

        // copy nodes
        for(node_data node : my_graph.getV())
            new_graph.addNode(node);

        // copy neighbors
        for(node_data node : my_graph.getV())
            for(node_data neighbors : my_graph.getV(node.getKey()))
                new_graph.connect(node.getKey(),neighbors.getKey());

        return new_graph;
    }

    @Override
    public boolean isConnected() { // poor and temp function algo O(N^2)
        HashSet<node_data> check = new HashSet<node_data>();
        int nodes_check = 0;

        for(node_data node : my_graph.getV()) {
            check.add(node);
            nodes_check++;
            for(node_data neighbors : my_graph.getV(node.getKey())) {
                if(!check.contains(neighbors)) {
                    check.add(neighbors);
                    nodes_check++;
                }
                }
            }

        return nodes_check == my_graph.nodeSize();
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
    }
}
