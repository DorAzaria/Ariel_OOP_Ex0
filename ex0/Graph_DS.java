package ex0;

import java.util.*;

public class Graph_DS implements graph {
    private HashMap<Integer,node_data> nodes;
    private int nodes_size , edges_size , mc;
    /**
     * A default constructor.
     * Graph_DS is basically a HashMap contains all the nodes in the graph.<br>
     * K - an Integer key as same as the key of each node.<br>
     * V - a node_data value.
     */
    public Graph_DS() { nodes = new HashMap<Integer,node_data>(); }
    /**
     * Returns a specific node from the graph by retrieving a node via a key
     * from the HashMap DS.<br>
     * <strong>Time Complexity</strong> - O(1)
     * @param key the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) { return nodes.containsKey(key) ? nodes.get(key) : null; }
    /**
     * Returns true if node1 is in node2's neighbors list and vise versa. <br>
     * <strong>Time Complexity</strong> - O(1)
     *  @param node1
     *  @param node2
     *  @return true or false
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(nodes.containsKey(node1) && nodes.containsKey(node2)) //O(1)
            if(getNode(node1).hasNi(node2) && getNode(node2).hasNi(node1)) //O(1)
                return true;
        return false;
    }
    /**
     * Adding a new node to the nodes HashMap DS.<br>
     * <strong>Time Complexity</strong> - O(1)
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(),n); //O(1)
        nodes_size++; //O(1)
        mc++; //O(1)
    }
    /**
     * Adding node1 to node2's neighbors list and vise versa.<br>
     * <strong>Time Complexity</strong> - O(1)
     *  @param node1
     *  @param node2
     */
    @Override
    public void connect(int node1, int node2) {
        node_data n1 = getNode(node1), n2 = getNode(node2);
        if(n1==null||n2==null||n1.hasNi(node2)||n1.equals(n2)) //O(1)
            return;
        n1.addNi(n2); //O(1)
        n2.addNi(n1); //O(1)
        edges_size++;
        mc++;
    }
    /**
     * Returns a Collection view of the values contained in HashMap.<br>
     * <strong>Time Complexity</strong> - O(1)
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() { return new ArrayList<>(nodes.values()); } //O(1)
    /**
     * Returns a list of neighbors of a given node_id contained within a new ArrayList. <br>
     * <strong>Time Complexity</strong> - O(1)
     *  @param node_id
     *  @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV(int node_id) { return getNode(node_id).getNi(); } //O(1)
    /**
     * Delete the node from the nodes HashMap DS and also remove it<br>
     * from every neighbor list it belongs to, by using removeEdge().<br>
     * <strong>Time Complexity</strong> - O(N) , |Vertex|=N.
     * @return the data of the removed node (null if none).
     * @param key
     */
    @Override
    public node_data removeNode(int key) {
        if(nodes.containsKey(key)){  //O(1)
            for(node_data runner : getNode(key).getNi()) // O(N)
                removeEdge(key,runner.getKey()); // O(1)
            nodes_size--;
            mc++;
            return nodes.remove(key); // O(1)
        }
        return null;
    }
    /**
     * Remove each given node from the other neighbor list it belongs to. <br>
     * <strong>Time Complexity</strong> - O(1).
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        node_data n1 = getNode(node1) , n2 = getNode(node2);
        if(hasEdge(node1,node2)) { // O(1)
                n1.removeNode(n2); // O(1)
                n2.removeNode(n1); // O(1)
                edges_size--;
                mc++;
            }
    }
    /**
     * <strong>Time Complexity</strong> - O(1).
     * @return the number of nodes from the map counted during its build and delete functions.
     */
    @Override
    public int nodeSize() { return this.nodes_size; }
    /**
     * <strong>Time Complexity</strong> - O(1).
     * @return the number of edges from the map counted during its build and delete functions.
     */
    @Override
    public int edgeSize() { return this.edges_size; }
    /**
     * <strong>Time Complexity</strong> - O(1).
     *  @return the number of actions from the map counted during its build and delete functions.
     */
    @Override
    public int getMC() { return this.mc; }
}