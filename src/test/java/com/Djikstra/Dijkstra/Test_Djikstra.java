package ldh;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class Test_Djikstra extends TestCase{
	Vertex v0 = new Vertex("La Laguna");
    Vertex v1 = new Vertex("S/C de tenerife");
    Vertex v2 = new Vertex("La Orotava");
    Vertex v3 = new Vertex("Icod de los Vinos");
    Vertex v4 = new Vertex("Puerto de la Cruz");
    Vertex[] vertices = { v0, v1, v2, v3, v4 };
    
	public void setUp(){
	    v0.adjacencies = new Edge[]{ new Edge(v1, 5), new Edge(v2, 10),new Edge(v3, 8) };
		v1.adjacencies = new Edge[]{ new Edge(v0, 5), new Edge(v2, 3), new Edge(v4, 7) };
		v2.adjacencies = new Edge[]{ new Edge(v0, 10), new Edge(v1, 3) };
		v3.adjacencies = new Edge[]{ new Edge(v0, 8), new Edge(v4, 2) };
		v4.adjacencies = new Edge[]{ new Edge(v1, 7), new Edge(v3, 2) };
	}
	
	@Test
	public void test() {
		Dijkstra.computePaths(v0);
		//Soluciones por iteracion
		ArrayList<Vertex> x1 = new ArrayList<Vertex>(){{add(v0);}};
		ArrayList<Vertex> x2 = new ArrayList<Vertex>(){{add(v0);add(v1);}};
		ArrayList<Vertex> x3 = new ArrayList<Vertex>(){{add(v0);add(v1);add(v2);}};
		ArrayList<Vertex> x4 = new ArrayList<Vertex>(){{add(v0);add(v3);}};
		ArrayList<Vertex> x5 = new ArrayList<Vertex>(){{add(v0);add(v3);add(v4);}};
		ArrayList<ArrayList<Vertex>> pruebas = new ArrayList<ArrayList<Vertex>>();
		pruebas.add(x1);
		pruebas.add(x2);
		pruebas.add(x3);
		pruebas.add(x4);
		pruebas.add(x5);
		
		List<Vertex> path = null;
		for (int i = 0; i < vertices.length; i++){
			path = Dijkstra.getShortestPathTo(vertices[i]);
			Assert.assertEquals(pruebas.get(i), path);
	    }

	}

}
