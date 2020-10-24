package ex0;

import java.util.*;

public class Graph_DS implements graph {

    private HashMap<Integer,node_data> nodes;
    private int nodes_size , edges_size , mc;

    public Graph_DS() {
        nodes = new HashMap<Integer,node_data>();
        nodes_size = 0;
        edges_size = 0;
        mc = 0;
    }
    @Override
    public node_data getNode(int key) {
        // O(1), checks whether the node exists.
        // O(1), get the node from the structure.
        return nodes.containsKey(key) ? nodes.get(key) : null; // O(1)
    }
    @Override
    public boolean hasEdge(int node1, int node2) {

        if(nodes.containsKey(node1) && nodes.containsKey(node2)) // O(1)
            if(getNode(node1).hasNi(node2) && getNode(node2).hasNi(node1)) // O(1).
                return true;

        return false;
    }
    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(),n); // O(1)
        nodes_size++;
        mc++;
    }
    @Override
    public void connect(int node1, int node2) {
        if(getNode(node1) == null || getNode(node2) ==null) // O(1) , if it's none.
            return;

        if(getNode(node1).hasNi(node2) ) // O(1), if the edge exists.
            return;

        // a new edge
        getNode(node1).addNi(getNode(node2)); // O(1) , A <--> B.
        getNode(node2).addNi(getNode(node1)); // O(1) , B <--> A.
        edges_size++;
        mc++;
    }
    @Override
    public Collection<node_data> getV() {
        return nodes.values(); // returns a Collection view of the values contained in this map.
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        return getNode(node_id).getNi();
    }

    @Override
    public node_data removeNode(int key) {
        if(nodes.containsKey(key)){ // O(1)

            if(getNode(key).getNi().size() == 0) { // O(1)
                nodes_size--;
                mc++;
                return nodes.remove(key); // O(1)
            }


            for(node_data runner : getNode(key).getNi()){ // O(N)
                removeEdge(key,runner.getKey()); // O(1)
            }

            nodes_size--;
            mc++;
            return nodes.remove(key); // O(1)
        }
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if(nodes.containsKey(node1) && nodes.containsKey(node2)) { // O(1)
            if(getNode(node1).hasNi(node2) && getNode(node2).hasNi(node1)) { // O(1)
                getNode(node1).removeNode(getNode(node2)); // O(1)
                getNode(node2).removeNode(getNode(node1)); // O(1)
                edges_size--;
                mc++;
            }
        }
    }

    @Override
    public int nodeSize() {
        return this.nodes_size;
    }

    @Override
    public int edgeSize() {
        return this.edges_size;
    }

    @Override
    public int getMC() {
        return this.mc;
    }
}
