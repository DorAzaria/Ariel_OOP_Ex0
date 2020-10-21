package ex0;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

public class NodeData implements node_data{

    private static int node_counter = 0;
    private int node_id;
    private Vector<node_data> neighbors;
    private Vector<node_data> connections;
    private String node_info;
    private int tag;

    // a default constructor
    public NodeData() {
        this.node_id = node_counter++;
        this.neighbors = new Vector<node_data>();
        this.connections = new Vector<node_data>();
        this.connections.add(this);
        this.node_info = "";
        this.tag = 404;
    }

    @Override
    public Vector<node_data> getConnections() {
        return this.connections;
    }

    @Override
    public void setConnection(Vector<node_data> other){
        this.connections = other;
    }

    @Override
    public void setNewConnection() {
        this.connections = new Vector<node_data>();
        this.connections.add(this);
    }

    @Override
    public int getKey() {
        return this.node_id;
    }

    @Override
    public Collection<node_data> getNi() {
        for(node_data node : neighbors) {
        }
        return this.neighbors;
    }

    @Override
    public boolean hasNi(int key) {

        for(node_data itA : neighbors) {
            if(itA.getKey() == key) { // if b is in a's list
                if(itA.getNi().contains(this))// and if a is in b's list
                        return true; // a <--> b
                }
            }
        return false;
    }

    @Override
    public void addNi(node_data t) {
        if(t != null) {
            this.neighbors.add(t);
            t.getNi().add(this);
        }
    }

    @Override
    public void removeNode(node_data node) {
        if(this.neighbors.contains(node))
            this.neighbors.remove(node); // remove b from a

            // the edge (a,b) has been removed.
            if(this.connections.contains(node)) {
                this.connections.remove(node);
            }

            this.setNewConnection();
            for(node_data run : this.neighbors) {
                recursiveConnection(this,run,0);
            }
    }

    @Override
    public void recursiveConnection(node_data main, node_data run, int steps) {
            main.getConnections().add(run);
            if(run.getNi().isEmpty()) {
                return;
            }
    }

    @Override
    public String getInfo() {
        return this.node_info;
    }

    @Override
    public void setInfo(String s) {
        this.node_info+=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
