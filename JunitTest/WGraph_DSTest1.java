package ex1.JunitTest;

import ex1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest1 {
    weighted_graph Gr =new WGraph_DS();
    weighted_graph_algorithms GrW = new WGraph_Algo();
    @Test
    void getNode() {
        Gr.addNode(0);

  node_info ans=Gr.getNode(0);

       assertNotNull(ans);
    }

    @Test
    void hasEdge() {
        Gr.addNode(2);
       Gr.addNode(6);
        Gr.connect(2,6,3);
        boolean b = Gr.hasEdge(2,6);
        assertTrue(b);


    }

    @Test
    void getEdge() {
        Gr.addNode(2);
        Gr.addNode(6);
        Gr.connect(2,6,3);
        double b =Gr.getEdge(2,6);
        assertEquals(b,3);

    }

    @Test
    void addNode() {
        Gr.addNode(2);
        Gr.addNode(6);
        assertEquals(2,Gr.nodeSize());

    }

    @Test
    void connect() {
        Gr.addNode(2);
        Gr.addNode(6);
        Gr.connect(2,6,7);
        assertNotNull(Gr.getV(2));
        assertNotNull(Gr.getV(6));
    }

    @Test
    void getV() {
        Gr.addNode(2);
        Gr.addNode(6);
        assertEquals(2,Gr.getV().size());
    }

    @Test
    void testGetV() {
        Gr.addNode(2);
        Gr.addNode(6);
        Gr.connect(2,6,7);
        assertEquals(1,Gr.getV(2).size());
    }

    @Test
    void removeNode() {

    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}