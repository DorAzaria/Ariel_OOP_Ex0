package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph my_graph;
    /**@param g
     * A parametric constructor.
     * this constructor init the given graph on which this set of algorithms operates on.*/
    public Graph_Algo(graph g) { init(g); }
    /**
     * A default constructor.
     * this constructor init the given graph on which this set of algorithms operates on.*/
    public Graph_Algo() { this.my_graph = new Graph_DS(); }
    /**@param g
     * Init the graph on which this set of algorithms operates on.*/
    @Override
    public void init(graph g) { this.my_graph = g; }
    /**@return a new deep-copied graph.
     * Time Complexity - O(N^2), |Vertex|=N */
    @Override
    public graph copy() {
        graph new_graph = new Graph_DS();
            for(node_data node : my_graph.getV()) {
                if(new_graph.getNode(node.getKey())==null) {
                    new_graph.addNode(new NodeData(node.getKey(), node.getTag(), node.getInfo()));
                }
                for(node_data neighbors : node.getNi()) {
                    if(new_graph.getNode(neighbors.getKey())==null) {
                        new_graph.addNode(new NodeData(neighbors.getKey(), neighbors.getTag(), neighbors.getInfo()));
                    }
                    new_graph.connect(node.getKey(), neighbors.getKey());
                }
        }
        return new_graph;
    }
    /**@return true IFF there is a valid path from every node to each
     * This method is solved with the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    @Override
    public boolean isConnected() { // poor and temp function algo O(N^2)
        ArrayList<node_data> list = new ArrayList<>(my_graph.getV());
        if(list.isEmpty())
            return true;
        return isConnected(list.get(0)) == my_graph.nodeSize();
    }
    /**@param node
     * @return the nodes counted in the current connected graph.
     * This method is under the boolean isConnected() method.
     * using the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    private int isConnected(node_data node) {
        reset();
        int counter = 1;
        LinkedList<node_data> queue = new LinkedList<node_data>();
        node.setTag(1);
        queue.add(node);
        while(queue.size() != 0) {
            node = queue.poll();
            for(node_data neighbors : node.getNi())
                if (neighbors.getTag() == -1) {
                    counter++;
                    neighbors.setTag(1);
                    queue.add(neighbors);
                }
            }
        return counter;
    }
    /**@param src - start node
     * @param dest - end (target) node
     * @return the length of the shortest path between src to dest
     * return the length between two given key nodes solved with the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    @Override
    public int shortestPathDist(int src, int dest) {
        List<node_data> list = BFS(src,dest);
        if(list == null) {
            return -1;
        }
        return list.size() -1;
    }
    /**@param src - start node
     * @param dest - end (target) node
     * @return the shortest path between src to dest - as an ordered List of nodes
     * solved with the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        List<node_data> list = BFS(src,dest);
        if(list == null)
            return null;
        return list;
    }
    /**@param src
     * @param dest
     * @return a LinkedList that contains all the nodes that are connected from src to dest.
     * The current node remembers its previous visited node by setting the key value
     * of the previous node inside the tag of the current node.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    private List<node_data> BFS(int src, int dest) {
        LinkedList<node_data> queue = new LinkedList<node_data>();
        LinkedList<node_data> path = new LinkedList<node_data>();
        if(src == dest) return path;
        if(my_graph.getNode(src) == null || my_graph.getNode(dest) == null) return null;
        this.reset();
        queue.add(this.my_graph.getNode(src));
        this.my_graph.getNode(src).setTag(1);

        while(!queue.isEmpty()) {
            node_data current = queue.poll();
            Iterator<node_data> runner = current.getNi().iterator();
            while (runner.hasNext()) {
                node_data next_node = runner.next();
                if(next_node.getTag() == -1) {
                    next_node.setTag(current.getKey());
                    if(next_node.equals(this.my_graph.getNode(dest))) {
                        path.addFirst(this.my_graph.getNode(dest));
                        while(!next_node.equals(this.my_graph.getNode(src))) {
                            path.addFirst(next_node);
                            next_node = this.my_graph.getNode(next_node.getTag());
                        }
                        return path;
                    }
                    queue.add(next_node);
                }
            }
        }
        return null;
    }
    /**reset the tags from any value to number -1.
     * this method is used in every method in this class
     * if it's 1 or any other value except -1 so it signed as visited.
     * and if the value is -1 so it's signed as unvisited.
     * Time Complexity - O(N), |Vertex| = N.*/
    private void reset() {
        for(node_data runner : this.my_graph.getV())
            runner.setTag(-1);
    }
}
