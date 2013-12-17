package ldh;
import java.util.*;

import org.junit.Assert;

//Esta clase es el vertice en si 
class Vertex implements Comparable<Vertex>{
    public final String name; //Nombre del vertice
    public Edge[] adjacencies; //Vector de nodos vecinos
    public double minDistance = Double.POSITIVE_INFINITY; 
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}

//Esto representa un arco donde guarda el objetivo del enlace y su peso.
class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight){ target = argTarget; weight = argWeight; }
}

public class Dijkstra{
	
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();
		    // Visit each edge exiting u
	        for (Edge e : u.adjacencies){
	        	Vertex v = e.target;
	            double weight = e.weight;
	            double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance){
				    vertexQueue.remove(v);
				    v.minDistance = distanceThroughU ;
				    v.previous = u;
				    vertexQueue.add(v);
				}
	        }
	    }
    }

    public static List<Vertex> getShortestPathTo(Vertex target){
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args){
        final Vertex v0 = new Vertex("La Laguna");
        final Vertex v1 = new Vertex("S/C de tenerife");
		final Vertex v2 = new Vertex("La Orotava");
		final Vertex v3 = new Vertex("Icod de los Vinos");
		final Vertex v4 = new Vertex("Puerto de la Cruz");
	
		v0.adjacencies = new Edge[]{ new Edge(v1, 5), new Edge(v2, 10),new Edge(v3, 8) };
		v1.adjacencies = new Edge[]{ new Edge(v0, 5), new Edge(v2, 3), new Edge(v4, 7) };
		v2.adjacencies = new Edge[]{ new Edge(v0, 10), new Edge(v1, 3) };
		v3.adjacencies = new Edge[]{ new Edge(v0, 8), new Edge(v4, 2) };
		v4.adjacencies = new Edge[]{ new Edge(v1, 7), new Edge(v3, 2) };
		Vertex[] vertices = { v0, v1, v2, v3, v4 };
        computePaths(v0);
        List<Vertex> path;
        for (Vertex v : vertices){
		    System.out.println("Distancia a " + v + ": " + v.minDistance);
		    path = getShortestPathTo(v);
		    System.out.println("Camino: " + path);
        }
    }
}