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
    public boolean isConnected() {
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
        LinkedList<node_data> queue = new LinkedList<node_data>(), path = new LinkedList<node_data>();
        if(src == dest) return path;
        node_data source = my_graph.getNode(src), destination = my_graph.getNode(dest);
        this.reset();
        queue.add(source);
        markAsVisited(source);

        while(!queue.isEmpty()) {
            node_data current = queue.poll();
            for(node_data next_node : current.getNi()) {
                if(!isVisited(next_node)) {
                    setPrevious(next_node,current);
                    if(next_node == destination) {
                        path.addFirst(destination);
                        while(next_node != source) {
                            path.addFirst(next_node);
                            next_node = getPrevious(next_node);
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
    private void reset() { for(node_data runner : this.my_graph.getV()) runner.setTag(-1); }
    /**@param node
     * @return true if this node was marked by a tag (visited).
     * Time Complexity - O(1).*/
    private boolean isVisited(node_data node) { return node.getTag() != -1; }
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
