package ex0;

import java.util.Collection;
import java.util.Vector;

public class NodeData implements node_data{

    private static int node_counter = 0;
    private final int node_id;
    private Vector<node_data> neighbors;
    private String node_info;
    private int tag;

    // a default constructor
    public NodeData() {
        this.node_id = node_counter++;
        this.neighbors = new Vector<node_data>();
        this.node_info = "";
        this.tag = 404;
    }


    @Override
    public int getKey() {
        return this.node_id;
    }

    @Override
    public Collection<node_data> getNi() {
        return this.neighbors;
    }

    @Override
    public boolean hasNi(int key) {
        for(node_data itA : neighbors) {
            if(itA.getKey() == key) { // if b is in a's list
                for(node_data itB : itA.getNi()) {
                    if(itB.getKey() == this.node_id) { // and if a is in b's list
                        return true; // a <--> b
                    }
                }
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
        if(this.neighbors.contains(node))  // if a contains b
            this.neighbors.remove(node); // remove b from a

        if(node.getNi().contains(this))  // if b contains a
            node.getNi().remove(this); // remove a from b
                // the edge (a,b) has been removed.
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
