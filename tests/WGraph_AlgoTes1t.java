package ex1.tests;
import ex1.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTes1t {
    weighted_graph_algorithms kobi =new WGraph_Algo();
    weighted_graph kob = new WGraph_DS();


    @Test
    void init() {

    }

    @Test
    void getGraph() {
        kob.addNode(1);
        kob.addNode(2);
        kob.addNode(3);
        kobi.init(kob);
        assertEquals(3,kobi.getGraph().nodeSize());

    }

    @Test
    void copy() {

    }

    @Test
    void isConnected() {
        kob.addNode(1);
        kob.addNode(2);
        kob.addNode(3);
        kob.addNode(4);
        kob.addNode(5);
        kob.connect(1,2,4);
        kob.connect(4,3,13);
        kob.connect(5,1,7);
        kob.connect(3,1,5);
        kobi.init(kob);
        assertTrue(kobi.isConnected());
    }

    @Test
    void shortestPathDist() {
        kob.addNode(1);
        kob.addNode(2);
        kob.addNode(3);
        kob.addNode(4);
        kob.addNode(5);
        kob.connect(1,2,4);
        kob.connect(4,3,13);
        kob.connect(5,1,7);
        kob.connect(3,1,5);

    }

    @Test
    void shortestPath() {
        kob.addNode(1);
        kob.addNode(2);
        kob.addNode(3);
        kob.addNode(4);
        kob.addNode(5);
        kob.connect(1,2,4);
        kob.connect(4,3,13);
        kob.connect(5,1,7);
        kob.connect(3,1,5);
        kob.connect(4,2,6);
        kobi.init(kob);
        List<node_info> list=new LinkedList<>();
  list= kobi.shortestPath(4,1);
        System.out.println(kobi.shortestPath(4,1));
        int[] checkKey = {4, 2, 1};
        int i = 0;
        for(node_info n:list) {

            assertEquals(n.getKey(), checkKey[i]);
            i++;
        }

    }

    @Test
    void save() {
        kobi.init(kob);
       assertTrue(kobi.save("MyGraph.txt"));

    }

    @Test
    void load() {
        kobi.init(kob);
       kobi.load("MyGraph.txt");
       assertTrue(kobi.load("MyGraph.txt"));
    }
}