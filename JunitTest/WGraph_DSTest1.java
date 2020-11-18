package ex1.tests;

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
        Gr.addNode(2);
        Gr.addNode(6);
        Gr.removeNode(2);
        assertEquals(1,Gr.nodeSize());
    }

    @Test
    void removeEdge() {
        Gr.addNode(2);
        Gr.addNode(6);
        Gr.connect(2,6,7);
        Gr.removeEdge(2,6);
        assertEquals(-1,Gr.getEdge(2,6));
    }

    @Test
    void nodeSize() {
        Gr.addNode(2);
        Gr.addNode(6);
        assertEquals(2,Gr.nodeSize());

    }

    @Test
    void edgeSize() {
        Gr.addNode(3);
        Gr.addNode(2);
        Gr.addNode(6);
        Gr.connect(2,6,7);
        Gr.connect(2,3,1);
        assertEquals(2,Gr.edgeSize());

    }

    @Test
    void getMC() {
        Gr.addNode(3);
        Gr.addNode(2);
        Gr.addNode(6);
        Gr.connect(2,6,7);
        Gr.connect(2,3,1);
        assertEquals(5,Gr.getMC());
    }
}
