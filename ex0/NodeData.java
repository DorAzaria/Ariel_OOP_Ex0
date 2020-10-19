package ex0;

import java.util.Collection;
import java.util.Vector;

public class NodeData implements node_data {
    private int currentNode;
    private static int nodeCount;
    private Vector<node_data> myNeighbors;

    public NodeData() {
        this.currentNode = nodeCount;
        nodeCount++;
        myNeighbors = new Vector<node_data>();
        System.out.println("creates a new node : " + nodeCount );
    }
    @Override
    public int getKey() {
        return this.currentNode;
    }

    @Override
    public Collection<node_data> getNi() {
        // convert the contents into string
        myNeighbors.toString();
        for (node_data number : myNeighbors) {
            System.out.println("the neighbors of " + getKey() + " are: " + number.getKey() + " ");
        }
        return this.myNeighbors;
    }

    @Override
    public boolean hasNi(int key) {
        if(myNeighbors.contains(key))
            return true;

        return false;
    }

    @Override
    public void addNi(node_data t) {
        myNeighbors.add(t);
    }

    @Override
    public void removeNode(node_data node) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
