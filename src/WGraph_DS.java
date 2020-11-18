package ex1;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, java.io.Serializable {
    private int v;

    private HashMap<node_info, HashMap<node_info, Double>> Wgr = new HashMap<node_info, HashMap<node_info, Double>>();
    private int EdgeS;
    private int Mc;


    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        if (!this.Wgr.keySet().isEmpty()) {
            for (node_info n : this.Wgr.keySet()) {
                if (n.getKey() == key)
                    return n;
            }
        }
        return null;
    }


    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if ((node1 != node2) && (getNode(node1) != null && (getNode(node2) != null))) {
            if (!this.Wgr.get(getNode(node2)).isEmpty() && getV(node1).contains(getNode(node2)) && getV(node2).contains(getNode(node1)))
                return true;
        }
        return false;
    }


    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1, node2))
            return this.Wgr.get(getNode(node1)).get(getNode(node2));
        return -1;
    }

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        if (!this.Wgr.containsKey(getNode(key)) && this.Wgr != null) {
            node_info k = new NodeInfo(key, 0,"");

            this.Wgr.put(k, new HashMap<node_info, Double>());

            this.v++;
            this.Mc++;
        }

    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (node1 == node2)
            return;
        if (!this.getV().contains(getNode(node1)) || !this.getV().contains(getNode(node2)))
            return;
        if (getNode(node1) == null || getNode(node2) == null)
            return;

        if (!hasEdge(node1, node2)) {
            node_info x = getNode(node1);
            node_info y = getNode(node2);

            this.Wgr.get(getNode(node1)).put(y, w);
            this.Wgr.get(getNode(node2)).put(x, w);
            this.EdgeS++;
            this.Mc++;
        }

        node_info x = getNode(node1);
        node_info y = getNode(node2);
        this.Wgr.get(getNode(node1)).put(y, w);
        this.Wgr.get(getNode(node2)).put(x, w);

    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV() {

        return this.Wgr.keySet();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: this method can run in O(k) time, k - being the degree of node_id.
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (this.Wgr.keySet().contains(getNode(node_id))) {
            return this.Wgr.get(getNode(node_id)).keySet();

        }
        return null;

    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {
        if (getNode(key) == null)
            return null;
        node_info k = new NodeInfo(key, getNode(key).getTag(),getNode(key).getInfo());

        if (this.Wgr.keySet().contains(getNode(key))) {

            if (!this.Wgr.get(getNode(key)).isEmpty() && this.Wgr.get(getNode(key)) != null) {

                for (node_info i : getV(key)) {
                    getV(i.getKey()).remove(getNode(key));
                    this.EdgeS--;
                }
                this.Wgr.keySet().remove(getNode(key));
                this.Mc++;

            }
        }

        this.Wgr.keySet().remove(getNode(key));
        this.Mc++;

        return k;
    }

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (!this.Wgr.containsKey(getNode(node1)) || !this.Wgr.containsKey(getNode(node2)))
            return;
        if (!this.Wgr.get(getNode(node1)).isEmpty() && !this.Wgr.get(getNode(node2)).isEmpty()) {
            this.Wgr.get(getNode(node1)).remove(getNode(node2));
            this.Wgr.get(getNode(node2)).remove(getNode(node1));
            this.Mc++;
            this.EdgeS--;
        }
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return this.Wgr.keySet().size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return this.EdgeS;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return
     */
    @Override
    public int getMC() {
        return this.Mc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        return this.toString().equals(wGraph_ds.toString());

    }

    @Override
    public int hashCode() {
        return Objects.hash(v, Wgr, EdgeS, Mc);
    }


    @Override
    public String toString() {

        int counter = 0;
        String string = "";
        Set<node_info> keys = Wgr.keySet();
        for (node_info key : keys) {
            if (counter == 0) {
                string = string + key + "--->" + Wgr.get(key).toString();
            } else {
                string = string + "\n" + key + "--->" + Wgr.get((key).toString());
            }
            counter++;
        }
        return string;
    }

    public static class NodeInfo implements node_info, java.io.Serializable {


        private int key = vertex++;
        private String keyInfo;
        private double tag;
        private static int vertex = 0;


        public NodeInfo() {
            this.key = vertex++;

            this.tag = 0.0;
            this.keyInfo = "";

        }

        public NodeInfo(int k, double w, String s) {
            this.key = k;
            this.tag = w;
            this.keyInfo = s;

        }

        /**
         * Return the key (id) associated with this node.
         * Note: each node_data should have a unique key.
         *
         * @return
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /**
         * return the remark (meta data) associated with this node.
         *
         * @return
         */
        @Override
        public String getInfo() {
            return this.keyInfo;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.keyInfo = s;
        }

        /**
         * Temporal data (aka distance, color, or state)
         * which can be used be algorithms
         *
         * @return
         */
        @Override
        public double getTag() {
            return this.tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.tag = t;
        }

        @Override
        public String toString() {
            return " NodeInfo {" + " key = " + key + " }";

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeInfo nodeInfo = (NodeInfo) o;
            return this.toString().equals(nodeInfo.toString());

        }

        @Override
        public int hashCode() {
            return Objects.hash(key, keyInfo);
        }


    }
}














