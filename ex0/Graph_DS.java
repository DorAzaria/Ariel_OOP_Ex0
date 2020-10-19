package ex0;

import java.util.Collection;
import java.util.Hashtable;



public class Graph_DS implements graph {
    private Hashtable<Integer,node_data> nodes;

    //an empty graph
    public Graph_DS(){
        nodes = new Hashtable<Integer,node_data>(); // store nodes
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
        if(nodes.get(node1).hasNi(node2) && nodes.get(node2).hasNi(node1)){
            return true;
        }
        return false;
    }

    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(),n);
    }

    @Override
    public void connect(int node1, int node2) {
        if(nodes.get(node1) == null && nodes.get(node2) == null) {
            System.out.println("nodes " + node1 + " and "+ node2 + " are not exists.");
        }
        else if(nodes.get(node1) == null) {
            System.out.println("node " + node1 +" are not exist.");

        }
        else if (nodes.get(node2) == null) {
            System.out.println("node " + node2 +" are not exist.");
        }
        else {
            nodes.get(node1).addNi(getNode(node2));
            nodes.get(node2).addNi(getNode(node1));
            System.out.println("nodes " + node1 + " and " + node2 + " are neighbors now.");
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
