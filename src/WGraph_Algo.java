package ex1;

import java.io.Serializable;
import java.io.*;
import java.util.*;
import java.io.Serial;

public class WGraph_Algo implements weighted_graph_algorithms{
    private weighted_graph WgA = new WGraph_DS();


    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        this.WgA = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return this.WgA;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return
     */
    @Override
    public weighted_graph copy() {

        weighted_graph toCopy = new WGraph_DS();

        for (node_info curr : this.WgA.getV()) {
            node_info CopyN = new WGraph_DS.NodeInfo(curr.getKey(), curr.getTag(),curr.getInfo());
            toCopy.addNode(CopyN.getKey());
        }

        for (node_info ko : this.WgA.getV()) {
            if (!this.WgA.getV(ko.getKey()).isEmpty() && this.WgA.getV(ko.getKey()) != null) {
                for (node_info n0 : this.WgA.getV(ko.getKey())) {
                    toCopy.connect(ko.getKey(), n0.getKey(), this.WgA.getEdge(ko.getKey(), n0.getKey()));
                }

            }
        }
        return toCopy;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        if (this.WgA.nodeSize() == 0 || this.WgA.nodeSize() == 1)
            return true;
        node_info root = getN(this.WgA);
        Dfs(root);
        for (node_info i : this.WgA.getV()) {
            if (this.WgA.getV(i.getKey()).isEmpty())
                return false;
            if (i.getTag() == 0) {

                return false;
            }
        }

        return true;
    }


    /**
     * set all nodes no visited
     *
     * @param n
     */
    public void stag(node_info n) {
        for (node_info i : this.WgA.getV(n.getKey()))
            i.setTag(0);
    }


    /**
     * get the first node in the graph
     *
     * @param k - weighted_graph
     * @return nodeInfo
     */
    public node_info getN(weighted_graph k) {
        for (node_info s : k.getV()) {
            if (s == null)
                continue;
            return s;
        }
        return null;
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src==dest)
            return 0.0;

        return shortestPath(src, dest) != null ? this.WgA.getNode(dest).getTag() : -1;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        if ((this.WgA.getNode(src) == null) || this.WgA.getNode(dest) == null)
            return null;
        HashMap<node_info, node_info> parents1 = this.Dijkstra(this.WgA.getNode(src));
        List<node_info> shortestPath = new ArrayList<>();
           if (src==dest)
            return shortestPath;
        node_info i = this.WgA.getNode(dest);

        while (i != null) {

            shortestPath.add(0, i);
            i = parents1.get(i);

        }
        //  }
        if (shortestPath.size() == 1)
            return null;



        return shortestPath;
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        System.out.println("start serializable to" + file + "\n");
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream OutOb = new ObjectOutputStream(fileOut);

            OutOb.writeObject(this.WgA);
            OutOb.close();
            fileOut.close();

            System.out.println("Object has benn serialized");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException is caught");
            return false;
        }
        return true;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            FileInputStream file1 = new FileInputStream(file);
            ObjectInputStream obIn = new ObjectInputStream(file1);
            weighted_graph g = (weighted_graph) obIn.readObject();
            init(g);
            file1.close();
            obIn.close();

            System.out.println("Object has benn serialized");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Dfs(deep first search) algo O(V+E)
     * An algorithm that goes through all vertices
     *
     * @param node
     */
    public void Dfs(node_info node) {

        if (node == null)
            return;
        List<node_info> ans = new LinkedList<>();

        Stack<node_info> stack = new Stack<>();
        stack.push(node);

        stag(node);
        node.setTag(1);

        while (!stack.isEmpty()) {

            node_info nod = stack.pop();

            for (node_info ni : this.WgA.getV(nod.getKey()))
                if (ni.getTag() == 0) {
                    ni.setTag(1);
                    stack.push(ni);
                    ans.add(ni);
                }

        }

    }

    /**
     * Dijkstra algorithm
     * An algorithm that passes over all vertices according to their weight
     *
     * @param src
     * @return
     */
    public HashMap<node_info, node_info> Dijkstra(node_info src) {
        if ((src) == null)
            return null;

        HashSet<node_info> visit = new HashSet<>();
        HashMap<node_info, node_info> parents = new HashMap<node_info, node_info>();
        HashMap<node_info, Double> totalPath = new HashMap<>();
        Queue<node_info> minQ = new LinkedList<>();
        totalPath.put(src, 0.0);
        parents.put(src, null);
        minQ.add(src);


        for (node_info n : this.WgA.getV()) {
            if (n != src)
                totalPath.put(n, Double.MAX_VALUE);
        }

        while (!minQ.isEmpty()) {

            node_info index = minQ.poll();

            for (node_info n : this.WgA.getV(index.getKey())) {

                double pathN = totalPath.get(n);
                double path = totalPath.get(index) + this.WgA.getEdge(index.getKey(), n.getKey());

                if (pathN > path) {
                    totalPath.put(n, path);
                    parents.put(n, index);
                    minQ.add(n);
                    n.setTag(path);

                }
                visit.add(n);
            }

        }


        return parents;
    }
}






