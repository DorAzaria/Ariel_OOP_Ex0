package ex0;

import java.util.Collection;
import java.util.HashSet;

public class NodeData implements node_data {

    private HashSet<node_data> neighbors;
    private static int id_counter = 0;
    private int id, tag;
    private String info;

    public NodeData() {
        this.id = id_counter++;
        this.neighbors = new HashSet<node_data>();
        this.info = "";
        this.tag = 0;
    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public Collection<node_data> getNi() {
        return this.neighbors;
    }

    @Override
    public boolean hasNi(int key) {
        return neighbors.contains(key);
    }

    @Override
    public void addNi(node_data t) {
        if(neighbors.contains(t))
            return;
        neighbors.add(t);
    }

    @Override
    public void removeNode(node_data node) {
        if(!neighbors.contains(node)) // O(1)
            return;
        neighbors.remove(node); // O(1) , one hand of the removing edge.
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info += s;
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
