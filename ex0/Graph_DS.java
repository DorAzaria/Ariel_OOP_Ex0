package ex0;

import java.util.*;

public class Graph_DS implements graph{

    private Vector<node_data> nodes;
    private int no_nodes;
    private int no_edges;

    // a default constructor
    public Graph_DS() {
        nodes = new Vector<node_data>();
        no_nodes = 0;
        no_edges = 0;
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
        if(getNode(node1).hasNi(node2))
            return true;

        return false;
    }

    @Override
    public void addNode(node_data n) {
        if(n != null) {
            nodes.add(n);
            no_nodes++;
        }
    }

    @Override
    public void connect(int node1, int node2) {
        if(nodes.contains(getNode(node1)) && nodes.contains(getNode(node2))) {
                getNode(node1).addNi(getNode(node2));
                getNode(node2).setConnection(getNode(node1).getConnections());
                getNode(node2).getConnections().add(getNode(node2));

            System.out.print("the nodes : " + node1 + " and " + node2 + " are now neighbors and connected to: ");
            getNode(node1).getConnections().forEach((n) -> System.out.print(n.getKey() + " "));
            System.out.println();
            no_edges++;
        }
    }

    @Override
    public Collection<node_data> getV() {
        return nodes;
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        return getNode(node_id).getConnections();
    }


    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
    @Override
    public node_data removeNode(int key) {

        if(getNode(key)!=null) {
            no_nodes--;
            getNode(key).getNi().forEach((n) -> n.removeNode(getNode(key)));
            return getNode(key);
        }
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        no_edges--;
    }

    @Override
    public int nodeSize() {
        return no_nodes;
    }

    @Override
    public int edgeSize() {
        return no_edges;
    }

    @Override
    public int getMC() {
        return 0;
    }
}
