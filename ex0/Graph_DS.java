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
            n.getDistances().put(0,n);
            edges.put(n.getKey(),n.getDistances());
        }
    }

    @Override
    public void connect(int node1, int node2) {
        if(nodes.contains(getNode(node1)) && nodes.contains(getNode(node2))) {
                getNode(node1).addNi(getNode(node2));
                getNode(node1).getDistances().put(1,getNode(node2));
                edges.put(node1,getNode(node1).getDistances());
        }
        Set<Integer> runner = nodes.keySet();

        for(Integer run : runner) {
            updateConnections(getNode(run),getNode(run).getNi(),1);
        }
    }

    private void updateConnections(node_data main_node, Collection<node_data> neighbors, int distance) {

        if(neighbors.isEmpty() || main_node == null)
            return;

        for(node_data run : neighbors) {
            main_node.getDistances().put(distance+1,run);
            edges.put(main_node.getKey(),main_node.getDistances());
            if(run.getNi().size() == 1)
                return;
            updateConnections(run,run.getNi(),distance+1);
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
