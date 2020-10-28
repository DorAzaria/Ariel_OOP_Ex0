package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data {
    private HashMap<Integer,node_data> neighbors;
    private static int id_counter = 0;
    private int id, tag;
    private String info;
    /** A default constructor.
     * NodeData is basically node_data type,
     * it has a unique key that's obtained serially by a static counter.
     * it also contains its neighbors inside a HashMap DS,
     * K - the unique key of each node.
     * V - a node_data value.*/
    public NodeData() {
        this.id = id_counter++;
        this.neighbors = new HashMap<Integer,node_data>();
        this.info = "";
        this.tag = -1;
    }
    /** @param key
     * @param t
     * @param i
     * A parametric constructor designed for deep-copy methods.*/
    public NodeData(int key,int t,String i) {
        this.id = key;
        this.tag = t;
        this.info = i;
        this.neighbors = new HashMap<Integer,node_data>();
    }
    /** @return the unique key associated with this node_data.
     * Time Complexity - O(1).*/
    @Override
    public int getKey() { return this.id; }
    /**@return this node_data's neighbors contained in a new ArrayList.
     * the ArrayList is initialized by adding a Collection view of the values contained in this HashMap.
     * Time Complexity - O(1).*/
    @Override
    public Collection<node_data> getNi() { return new ArrayList<>(this.neighbors.values()); }
    /** @param key
     * @return true IFF (this node)<==>(key node) are adjacent.
     * Time Complexity - O(1).*/
    @Override
    public boolean hasNi(int key) {
        return neighbors.containsKey(key)&&neighbors.get(key).getNi().contains(this); }
    /**@param t
     * Adds a node_data to this neighbors list.
     * Time Complexity - O(1).*/
    @Override
    public void addNi(node_data t) {
        if(!this.neighbors.containsValue(t))
            this.neighbors.put(t.getKey(), t);
    }
    /**@param node
     * removes the given node from this neighbors HashMap.
     * Time Complexity - O(1).*/
    @Override
    public void removeNode(node_data node) {
        if(this.neighbors.containsValue(node))
            this.neighbors.remove(node.getKey());
    }
    /**@return this node's information.
     * Time Complexity - O(1).*/
    @Override
    public String getInfo() { return this.info;}
    /**@param s
     * set a new information to this node.
     * Time Complexity - O(1).*/
    @Override
    public void setInfo(String s) { this.info += s; }
    /**@return this node's tag.
     * Time Complexity - O(1).*/
    @Override
    public int getTag() { return this.tag; }
    /**@param t
     * set a new tag to this node.
     * Time Complexity - O(1).*/
    @Override
    public void setTag(int t) { this.tag = t; }
}