package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph my_graph;
    private int connectionCounter = 0;

    public Graph_Algo(graph g) {
        init(g);
    }
    public Graph_Algo() { }

    @Override
    public void init(graph g) {
        this.my_graph = g;
    }

    @Override
    public graph copy() {
        graph new_graph = new Graph_DS();

            // copy nodes
            for(node_data node : my_graph.getV()) {
            new_graph.addNode(node);
                // copy neighbors
                for (node_data neighbors : my_graph.getV(node.getKey())) {
                    new_graph.addNode(neighbors);
                    new_graph.connect(node.getKey(), neighbors.getKey());
                }
        }
        return new_graph;
    }

    @Override
    public boolean isConnected() { // poor and temp function algo O(N^2)
        if(this.my_graph.nodeSize() >= 2 && this.my_graph.edgeSize() == 0)
            return false;

        reset();
        for(node_data node : my_graph.getV()) {
            if(node.getTag() == -1){
                node.setTag(1);
                connectionCounter++;
            }
            for(node_data runner : node.getNi()) {
                if(runner.getTag() == -1) {
                    connectionCounter++;
                    runner.setTag(1);
                }
            }
        }

        return connectionCounter == my_graph.nodeSize();
    }

    private List<node_data> BFS(int src, int dest) {

        LinkedList<node_data> queue = new LinkedList<node_data>();
        LinkedList<node_data> path = new LinkedList<node_data>();
        if(src == dest) return path;
        this.reset();

        queue.add(this.my_graph.getNode(src));

        while(!queue.isEmpty()) {
            node_data current = queue.poll();
            Iterator<node_data> runner = current.getNi().iterator();
            while (runner.hasNext()) {
                node_data next_node = runner.next();

                if(next_node.getTag() == -1) {
                    next_node.setTag(current.getKey());

                    if(next_node == this.my_graph.getNode(dest)) {
                        while(next_node != this.my_graph.getNode(src)) {
                            path.addFirst(next_node);
                            next_node = this.my_graph.getNode(next_node.getTag());
                        }
                        return path;
                    }
                    queue.add(next_node);
                }
            }
        }

        if(path.size() == 0) {
            return null;
        }
        return path;
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        List<node_data> list = BFS(src,dest);
        if(list == null) {
            return -1;
        }
        return list.size();
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        List<node_data> list = BFS(src,dest);
        if(list == null)
            return null;
        return list;
    }

    private void reset() {
        this.connectionCounter = 0;
        Collection<node_data> nodes = this.my_graph.getV();
        for(node_data runner : nodes)
            runner.setTag(-1);
    }
}
