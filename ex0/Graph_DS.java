package ex0;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class Graph_DS implements graph {

    private HashMap<Integer,node_data> node;
    private HashMap<  Integer , HashMap<Integer,Integer>> node_pairs;

    //an empty constructor
    public Graph_DS(){
        node = new HashMap<Integer, node_data>();
        node_pairs = new HashMap<  Integer , HashMap<Integer,Integer>  >();
    }

    @Override
    public node_data getNode(int key) {

        return node.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        return false;
    }

    @Override
    public void addNode(node_data n) {
        node.put(n.getKey(),n);
    }

    @Override
    public void connect(int node1, int node2) {
        node_pairs.put(1,new HashMap<Integer, Integer>());
        node_pairs.get(1).put(node1,node2);
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
