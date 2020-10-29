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
            if(new_graph.getNode(node.getKey())==null) { copyNode(new_graph,node); }
            copyNeighbors(new_graph,node);
        }
        return new_graph;
    }
    /**@param new_graph
     * @param node
     * Adding a new node_data to new_graph initialized with node's fields.
     * Time Complexity - O(1).*/
    private void copyNode(graph new_graph, node_data node) {
        new_graph.addNode(new NodeData(node.getKey(), node.getTag(), node.getInfo()));}
    /**@param new_graph
     * @param node
     * Copy the neighbors to new_graph and connect them with the current node.
     * Time Complexity - O(N)*/
    private void copyNeighbors(graph new_graph, node_data node) {
        for(node_data neighbor : node.getNi()) {
            if(new_graph.getNode(neighbor.getKey())==null) { copyNode(new_graph,neighbor); }
            new_graph.connect(node.getKey(),neighbor.getKey());
        }
    }
    /**@return true IFF there is a valid path from every node to each
     * This method is solved with the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    @Override
    public boolean isConnected() { return connection() == my_graph.nodeSize(); }
    /**@return the nodes counted in the current connected graph.
     * This method is under the boolean isConnected() method.
     * using the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    private int connection() {
        ArrayList<node_data> list = new ArrayList<>(my_graph.getV());
        if(list.isEmpty()) return 0;
        LinkedList<node_data> queue = new LinkedList<>();
        node_data node = list.get(0);
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
    /**@param src - start node
     * @param dest - end (target) node
     * @return the length of the shortest path between src to dest
     * return the length between two given key nodes solved with the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    @Override
    public int shortestPathDist(int src, int dest) { return BFS(src,dest) == null ? -1 : BFS(src,dest).size()-1; }
    /**@param src - start node
     * @param dest - end (target) node
     * @return the shortest path between src to dest - as an ordered List of nodes
     * solved with the BFS algorithm.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    @Override
    public List<node_data> shortestPath(int src, int dest) { return BFS(src,dest) == null ? null : BFS(src,dest); }
    /**@param src
     * @param dest
     * @return a LinkedList that contains all the nodes that are connected from src to dest.
     * The current node remembers its previous visited node by setting the key value
     * of the previous node inside the tag of the current node.
     * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.*/
    private List<node_data> BFS(int src, int dest) {
        LinkedList<node_data> queue = new LinkedList<>(), path = new LinkedList<>();
        if(src == dest) return path;
        node_data source = my_graph.getNode(src), destination = my_graph.getNode(dest);
        if(source != null && destination != null) {
            this.resetTags();
            queue.add(source);
            markAsVisited(source);
            while (!queue.isEmpty()) {
                node_data current = queue.poll();
                for (node_data next_node : current.getNi()) {
                    if (!isVisited(next_node)) {
                        setPrevious(next_node, current);
                        if (next_node == destination) return makeAPath(path, source, next_node, destination);
                        else queue.add(next_node);
                    }
                }
            }
        }
        return null;
    }
    /**@param path
     * @param src
     * @param next
     * @param des
     * @return a LinkedList of node_data, it contains the path from source to destination.
     * Time Complexity - O(N).*/
    private List<node_data> makeAPath(LinkedList<node_data> path,node_data src,node_data next,node_data des) {
        path.addFirst(des);
        while(next != src) {
            path.addFirst(next);
            next = getPrevious(next);
        }
        return path;
    }
    /**reset the tags from any value to number -1.
     * this method is used in every method in this class
     * if it's 1 or any other value except -1 so it signed as visited.
     * and if the value is -1 so it's signed as unvisited.
     * Time Complexity - O(N), |Vertex| = N.*/
    private void resetTags() { for(node_data runner : this.my_graph.getV()) runner.setTag(-1);}
    /**@param node
     * @return true if this node was marked by a tag (visited).
     * Time Complexity - O(1).*/
    private boolean isVisited(node_data node) { return node.getTag() != -1;}
    /**@param node
     * marks the node as a visited node.
     * Time Complexity - O(1).*/
    private void markAsVisited(node_data node) { node.setTag(1);}
    /**@param node
     * @return the previous visited node.
     * each tag's node is marked by the key of its previous iterated node.
     * Time Complexity - O(1)*/
    private node_data getPrevious(node_data node) { return my_graph.getNode(node.getTag());}
    /**@param src
     * @param des
     * set a tag in src node, the tag value is the previous iterated node - des.
     * Time Complexity - O(1)*/
    private void setPrevious(node_data src, node_data des) { src.setTag(des.getKey());}
}
