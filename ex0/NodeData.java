package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data {
    private HashMap<Integer,node_data> neighbors;
    private static int id_counter = 0;
    private int id, tag;
    private String info;
    /**
     * A default constructor.<br>
     * NodeData is basically node_data type,
     * it has a unique key that's obtained serially by a static counter.<br>
     * it also contains its neighbors inside a HashMap DS.<br>
     * K - the unique key of each node.<br>
     * V - a node_data value.
     */
    public NodeData() {
        this.id = id_counter++;
        this.neighbors = new HashMap<>();
        this.info = "";
        this.tag = -1;
    }
    /**
     * A parametric constructor designed for deep-copy methods.
     * @param key
     * @param t
     * @param i
     */
    public NodeData(int key,int t,String i) {
        this.id = key;
        this.tag = t;
        this.info = i;
        this.neighbors = new HashMap<>();
    }
    /**
     * <strong>Time Complexity</strong> - O(1).
     * @return the unique key associated with this node_data.
     */
    @Override
    public int getKey() { return this.id; }
    /**
     * the ArrayList is initialized by adding a Collection view of the values contained in this HashMap.<br>
     * <strong>Time Complexity</strong> - O(1).
     * @return this node_data's neighbors contained in a new ArrayList.
     */
    @Override
    public Collection<node_data> getNi() { return new ArrayList<>(this.neighbors.values()); }
    /**
     * <strong>Time Complexity</strong> - O(1).
     * @param key
     * @return true IFF (this node)<==>(key node) are adjacent.
     */
    @Override
    public boolean hasNi(int key) { return neighbors.containsKey(key)&&neighbors.get(key).getNi().contains(this); }
    /**
     * Adds a node_data to this neighbors list.<br>
     * <strong>Time Complexity</strong> - O(1).
     * @param t
     */
    @Override
    public void addNi(node_data t) { if(!neighbors.containsKey(t.getKey())) neighbors.put(t.getKey(), t); }
    /**
     * removes the given node from this neighbors HashMap.<br>
     * <strong>Time Complexity</strong> - O(1).
     * @param node
     */
    @Override
    public void removeNode(node_data node) { if(neighbors.containsValue(node)) neighbors.remove(node.getKey()); }
    /**
     * <strong>Time Complexity</strong> - O(1).
     * @return this node's information.
     */
    @Override
    public String getInfo() { return this.info;}
    /**
     * set a new information to this node.<br>
     * <strong>Time Complexity</strong> - O(1).
     * @param s
     */
    @Override
    public void setInfo(String s) { this.info += s; }
    /**
     * <strong>Time Complexity</strong> - O(1).
     * @return this node's tag.
     */
    @Override
    public int getTag() { return this.tag; }
    /**
     * set a new tag to this node.<br>
     * <strong>Time Complexity</strong> - O(1).
     * @param t
     */
    @Override
    public void setTag(int t) { this.tag = t; }
}