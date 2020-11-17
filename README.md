# OOP-EX1

In this project we built a directed graph which is also a weighted graph. Also in this project you will find some algorithms that can be done on a graph that is both directed and weighted.

For more information:

https://en.wikipedia.org/wiki/Directed_graph

https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)

Here are the departments that build our graph:

# NodeInfo class:
This class represents the vertices, when each vertex has the following properties:

Key: An integer representing the "ID" of the vertex. The key must be unique, meaning there must be no more vertex in the graph with the same key. Note that once the vertex is created, the key field cannot be reset.

Tag: An integer to indicate that something happened or did not happen usually on this program, with this parameter, we checked whether or not we "visited" with a vertex. This parameter is designed for programmers to help us with the algorithms.

Info: A string that can indicate more information about the vertex. Usually in our program we used this field to keep from which vertex the shortest way to get to that vertex. This parameter is designed for programmers to help us with the algorithms.

# WGraph_DS class:

 im using 2 HashMaps in this class One inside the other:On the one hand the keys which are all the vertices on the other hand the Hashmap of all the neighbors which are the edges with the appropriate weight.
 I chose the HashMap data structure because it is built so that each value has its own key what is required in the graph that each vertex is unique with its    values and also because the average run times of operations in this structure are (O (1)).
 And it helped me a lot in realizing the graph department.
 
The methods of this class  :

getV - return a collection that contain all the vertecis in the graph.
getV(int node_id) - return collection of Neigbors with that node_id.
removeNode - remove node from the graph,and cancel all his edges.
removeEdge - remove edge with two nodes.
addNode - add node to the graph.
getNode - return the node based on given key.
connect - initialize an edge with 2 given nodes, and set their weight.
hasEdge - boolean method that check if there is edge between two nodes.
getEdge - if there edge, then return the shortest path based on the cheaper weight from src node to destination node.
equals - overriding method to override the basic equals function.
in the private class NodeData i've implement the basic method and constructors for a node in the graph such as : get/set Key,get/set info, etc.


# WGraph_Algo class:
The WGraph_Algo class contains all algorithms that can be run on a graph. The algorithms are:

The methods of this class:

Init: Allows to insert a graph into the Graph Algo class so that we can run algorithms on it.

Init from file: Allows to insert a graph from serializable file into the Graph Algo class so that we can run algorithms on it.

Save to file: Allows to save a graph into a serializable file.

load - load a graph from given name.

isConnected: Checks whether the graph is strongly related, that is, if for any two vertices in the graph we mark them in u, v there is a path between u and v and there is a path between v and u.

Shortest path dist: The algorithm finds the shortest way between the node Src and the node Dest, the shortest way is the way the least amount of weight is passed.

shortestPath: The algorithm returns the the shortest path between src to dest based Dijkstra - as an ordered List of nodes,for example:
Dijkstra - method used to find the shortest path in directed/undirected weighted graph.
src -> n1 ->n2 ->...-> dest

Copy:The algorithm compute a deep copy of this graph.


            B    1     D   6
       5  .-+---------.'-------F
       .-'  |       .' |
    A.'     |2    .'   |
      `.    |   .'4    |3
       1`-. | .'       |
           `.'---------+
            C     8    E
shortest path between A and F: [A, C, B, D, F]
shortest path between C and F: [C, B, D, F]
shortest path between D and A: [D, B, C, A]
Reference:

https://www.youtube.com/watch?v=9wV1VxlfBlI
