package ex0;

import java.util.*;

public class Graph_DS implements graph{

    private Hashtable<Integer,node_data> nodes;
    private Hashtable<Integer,Hashtable<Integer,node_data>> edges;

    // a default constructor
    public Graph_DS() {
        nodes = new Hashtable<Integer, node_data>();
        edges = new Hashtable<Integer,Hashtable<Integer,node_data>>();
    }

    @Override
    public node_data getNode(int key) {
        if(nodes.get(key) == null) {
            System.out.println("node isn't exist.");
            return null;
        }
        return nodes.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if(edges.get(node1).get(node2) != null)
            return true;

        return false;
    }

    @Override
    public void addNode(node_data n) {
        if(n != null) {
            nodes.put(n.getKey(), n);
        }
    }

    @Override
    public void connect(int node1, int node2) {
        if(nodes.contains(getNode(node1)) && nodes.contains(getNode(node2))) {
                getNode(node1).addNi(getNode(node2));
        }
    }

    @Override
    public Collection<node_data> getV() {
        return null;
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        return null;
    }

    @Override
    public node_data removeNode(int key) {
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {

    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}
