package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph my_graph;
    /**
     * A parametric constructor. <br>
     * this constructor init the given graph on which this set of algorithms operates on.
     * @param g
     */
    public Graph_Algo(graph g) { init(g); }
    /**
     * A default constructor. <br>
     * this constructor init the given graph on which this set of algorithms operates on.
     */
    public Graph_Algo() { this.my_graph = new Graph_DS(); }
    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(graph g) { this.my_graph = g; }
    /**
     * <strong>Time Complexity</strong> - O(N^2), |Vertex|=N.
     * @return a new deep-copied graph.
     */
    @Override
    public graph copy() {
        graph new_graph = new Graph_DS();
        for(node_data node : my_graph.getV()) {
            if(new_graph.getNode(node.getKey())==null) { copyNode(new_graph,node); }
            copyNeighbors(new_graph,node);
        }
        return new_graph;
    }
    /**
     * This method is solved with the BFS algorithm.<br>
     * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
     * @return true IFF there is a valid path from every node to each
     */
    @Override
    public boolean isConnected() { return connection() == my_graph.nodeSize(); }
    /**
     * return the length between two given key nodes solved with the BFS algorithm.<br>
     * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
     * @param src start node.
     * @param dest end (target) node.
     * @return the length of the shortest path between src to dest.
     */
    @Override
    public int shortestPathDist(int src, int dest) { return path(src,dest) == null ? -1 : path(src,dest).size()-1; }
    /**
     * Solved with the BFS algorithm. <br>
     * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
     * @param src - start node
     * @param dest - end (target) node
     * @return the shortest path between src to dest - as an ordered List of nodes
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) { return path(src,dest) == null ? null : path(src,dest); }
    /**
     * Adding a new node_data to new_graph initialized with node's fields.<br>
     * <strong>Time Complexity</strong> - O(1).
     * @param new_graph
     * @param node
     */
    private void copyNode(graph new_graph, node_data node) {
        new_graph.addNode(new NodeData(node.getKey(), node.getTag(), node.getInfo()));}
    /**
     * connect the neighbors with the current node.<br>
     * <strong>Time Complexity</strong> - O(N)
     * @param new_graph
     * @param node
     */
    private void copyNeighbors(graph new_graph, node_data node) {
        for(node_data neighbor : node.getNi()) {
            if (new_graph.getNode(neighbor.getKey()) != null)
                new_graph.connect(node.getKey(), neighbor.getKey());
        }
    }
    /**
     * This method is under the boolean isConnected() method<br>
     * using the BFS algorithm:<br>
     * a queue contains a temp path of unvisited node, <br>
     * I used a counter to count all the unvisited nodes from this connectivity, <br>
     * each node is marked with a tag, 1 - visited, -1 - not visited.<br>
     * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
     * @return the nodes counted in the current connected graph.
     */
    private int connection() {
        if(!my_graph.getV().iterator().hasNext()) return 0;
        node_data node = my_graph.getV().iterator().next();
        LinkedList<node_data> queue = new LinkedList<>();
        resetTags();
        int counter = 1;
        markAsVisited(node);
        queue.add(node);
        while(!queue.isEmpty()) {
            node = queue.poll();
            for(node_data neighbors : node.getNi())
                if(!isVisited(neighbors)) {
                    counter++;
                    markAsVisited(neighbors);
                    queue.add(neighbors);
                }
        }
        return counter;
    }
    /**
     * it checks all the edge cases to optimize the run time
     * before using the BFS method if it's necessary.<br>
     * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
     * @param src
     * @param dest
     * @return a LinkedList that contains all the nodes that are connected from src to dest.
     */
    private List<node_data> path(int src, int dest) {
        if(src == dest) return new LinkedList<>();
        if(my_graph.getNode(src) != null && my_graph.getNode(dest) != null) {
            return BFS(my_graph.getNode(src), my_graph.getNode(dest));
        }
        return null;
    }
    /**
     * About the BFS algorithm: <br>
     * I learned about this algorithm via YouTube videos,
     * I wanted to make it useful by using the tags of each node. <br>
     * Each current node remembers its previous visited node by setting the key value
     * of its previous node inside the tag of the current node.<br>
     * in other words, (node1)(tag 15)(key 22) <-- prev < -- (node2)(tag 22)(key 13).<br>
     * I used a queue to contain a temp. path of the unvisited nodes from src to any node
     * until we reach the dest node.<br>
     * when we reach it we are sending the path and queue to makeAPath() method, more details found there.<br>
     * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
     * @param source
     * @param destination
     * @return a LinkedList that contains all the nodes that are connected from src to dest.
     */
    private List<node_data> BFS(node_data source, node_data destination) {
        LinkedList<node_data> queue = new LinkedList<>(), path = new LinkedList<>();
        this.resetTags();
        queue.add(source);
        markAsVisited(source);
        while (!queue.isEmpty()) {
            node_data current = queue.poll();
            for(node_data next_node : current.getNi()) {
                if (!isVisited(next_node)) {
                    setPrevious(next_node, current);
                    if (next_node == destination) return makeAPath(path, source, next_node);
                    else queue.add(next_node);
                }
            }
        }
        return path;
    }
    /**
     * <strong>Time Complexity</strong> - O(N).
     * @param path
     * @param src
     * @param next
     * @return a LinkedList of node_data, it contains the path from source to destination.
     */
    private List<node_data>  makeAPath(LinkedList<node_data> path,node_data src,node_data next) {
        while(next != src) {
            path.addFirst(next);
            next = getPrevious(next);
        }
        path.addFirst(next);
        return path;
    }
    /**
     * reset the tags from any value to number -1. <br>
     * this method is used in every method in this class
     * if it's 1 or any other value except -1 so it signed as visited.<br>
     * and if the value is -1 so it's signed as unvisited.<br>
     * <strong>Time Complexity</strong> - O(N), |Vertex| = N.
     */
    private void resetTags() { for(node_data runner : this.my_graph.getV()) runner.setTag(-1);}
    /**
     * <strong>Time Complexity</strong> - O(1).
     */
    private boolean isVisited(node_data node) { return node.getTag() != -1;}
    /**
     * marks the node as a visited node.<br>
     * <strong>Time Complexity</strong> - O(1).
     * @param node
     */
    private void markAsVisited(node_data node) { node.setTag(1);}
    /**
     * each tag's node is marked by the key of its previous iterated node.<br>
     * <strong>Time Complexity</strong> - O(1)
     * @param node
     * @return the previous visited node.
     */
    private node_data getPrevious(node_data node) { return my_graph.getNode(node.getTag());}
    /**
     * set a tag in src node, the tag value is the previous iterated node - des.<br>
     * <strong>Time Complexity</strong> - O(1)
     * @param src
     * @param des
     */
    private void setPrevious(node_data src, node_data des) { src.setTag(des.getKey());}
}
