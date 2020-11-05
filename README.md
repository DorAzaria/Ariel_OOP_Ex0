# An Unweighted Undirected Graphs Algorithm Library

This repository represent a collection of algorithms on unweighted undirected graphs i.e. relationships do not have magnitude and are bidirectional.  

![Diagram](https://i.ibb.co/Dzdpq7Y/1-l-J5-Sv-UUDWHMTC9-Tse-PIt1-A.png)

a data structure infrastructure, algorithms and display system.
There are algorithms like duplication capability, connectivity test, shortest path calculation (minimum number of edges) and finding the shortest path.

## File List
  * graph.java  -- This interface represents an undirectional unweighted graph.<br><br>
  * graph_algorithms.java -- This interface represents the "regular" Graph Theory algorithms.<br><br>
  * node_data.java -- This interface represents the set of operations applicable on a node in a graph.<br><br>
  * Graph_DS.java -- Graph_DS is basically a HashMap contains all the nodes in the graph + implements graph.java methods.<br><br>
  * NodeData.java -- NodeData implements node_data,each node has a unique key that's obtained serially by a static counter.<br><br>
  * Graph_Algo.java -- Graph_Algo implements graph_algorithms.java.<br><br>

### NodeData methods
* `NodeData()`
* `NodeData(int key,int t,String i)`
* `int getKey()`
* `Collection<node_data> getNi()`
* `boolean hasNi(int key)`
* `void addNi(node_data t)`
* `void removeNode(node_data node)`
* `String getInfo()`
* `void setInfo(String s)`
* `int getTag()`
* `void setTag(int t)`

### Graph_DS methods
this class owns the nodes of the graph in a HashMap<Integer,node_data> nodes.
* `Graph_DS()` - A default constructor.
* `node_data getNode(int key)` - Returns a specific node from the nodes list by the given key.
  * Time Complexity - O(1).
* `boolean hasEdge(int node1, int node2)` - Returns true if node1 is in node2's neighbors list and vise versa. 
  * Time Complexity - O(1). 
* `void addNode(node_data n)` - Adding a new node to the nodes HashMap DS.
  * Time Complexity - O(1). 
* `void connect(int node1, int node2)` - Adding node1 to node2's neighbors list and vise versa.
  * Time Complexity - O(1).
* `Collection<node_data> getV()` - Returns a Collection view of the values contained in HashMap.
  * Time Complexity - O(1).
* `Collection<node_data> getV(int key)` - Returns a list of neighbors of a given key contained within a new ArrayList.
  * Time Complexity - O(1).
* `node_data removeNode(int key)`
  Delete the node from the nodes HashMap DS and also remove it from every neighbor list it belongs to,
  by using removeEdge().
  * Time Complexity - <strong>O(N)</strong> , |Vertex|=N.
* `void removeEdge(int node1, int node2)` - Remove each given node from the other neighbor list it belongs to.
  * Time Complexity - O(1).
* `int nodeSize()` - return the number of nodes in the graph.
* `int edgeSize()` - return the number of edges in the graph.
* `int getMC()` - return the number of actions from the map counted during its build and delete functions.
### Graph_Algo
  activating algorithms methods for a given graph.
* `Graph_Algo()` - A default constructor.
* `void init(graph g)` - Init the graph on which this set of algorithms operates on.
* `graph copy()` - returns a new deep-copied graph.
  * Time Complexity - <strong>O(N^2)</strong>  ,|Vertex|=N.
* `boolean isConnected()` - true IFF there is a valid path from every node to each. <br>
  This method is solved with the BFS algorithm.
  * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.
* `int shortestPathDist(int src, int dest)` -  return the length between two given key nodes, solved with the BFS algorithm.
  * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.
* `List<node_data> shortestPath(int src, int dest)` - returns the shortest path between src to dest - as an ordered List of nodes , Solved with the BFS algorithm.
  * Time Complexity - O(N+E), |Vertex| = N , |Edge| = E.
* `private void copyNode(graph new_graph, node_data node)` - Adding a new node_data to new_graph initialized with node's fields.
  * Time Complexity - O(1).
* `private void copyNeighbors(graph new_graph, node_data node)` - connect the neighbors with the current node.
  * Time Complexity - O(N).
* `private int connection()` - This method is under the boolean isConnected() method using the BFS algorithm:<br>
  a queue contains a temp path of unvisited node, <br>
  I used a counter to count all the unvisited nodes from this connectivity, <br>
  each node is marked with a tag, 1 - visited, -1 - not visited.<br>
  <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
* `private List<node_data> path(int src, int dest)` - it checks all the edge cases to optimize the run time
  before using the BFS method if it's necessary.<br>  
  returns a LinkedList that contains all the nodes that are connected from src to dest.
  * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
* `private List<node_data> BFS(node_data source, node_data destination)` 
  * About the BFS algorithm: <br>
    I learned about this algorithm via YouTube videos,
    I wanted to make it useful by using the tags of each node. <br>
    Each current node remembers its previous visited node by setting the key value
    of its previous node inside the tag of the current node.<br>
    in other words, (node1)(tag 15)(key 22) <-- prev < -- (node2)(tag 22)(key 13).<br>
    I used a queue to contain a temp. path of the unvisited nodes from src to any node
    until we reach the dest node.<br>
    when we reach it we are sending the path and queue to makeAPath() method, more details found there.<br>
  * <strong>Time Complexity</strong> - O(N+E), |Vertex| = N , |Edge| = E.
* `private List<node_data> makeAPath` - return a LinkedList of node_data, it contains the path from source to destination.
  * Time Complexity - O(N).
* `private void resetTags()` - reset the tags from any value to number -1.
  * Time Complexity - O(N) , |Vertex| = N.
* `private boolean isVisited(node_data node)`
* `private void markAsVisited(node_data node)`
* `private node_data getPrevious(node_data node)`
* `private void setPrevious(node_data src, node_data des)`
## Sources 
https://www.youtube.com/watch?v=oDqjPvD54Ss&t=371s&ab_channel=WilliamFiset
