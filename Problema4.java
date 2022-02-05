import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Problema4 {

    public class Edge{

        public final Vertex target;
        public final double weight;
        public Edge(Vertex target, double weight){
            this.target = target;
            this.weight = weight;
        }
    }

    public class Vertex implements Comparable<Vertex>{
          public final String name;
          //guardamos una referencia a los vecinos del vertice
          public ArrayList<Edge> vecinos;
          public LinkedList<Vertex> path;
          //Se inicializan todas las distnacias en infinito
          public double minDistance = Double.POSITIVE_INFINITY;
          public Vertex previous;
          public int compareTo(Vertex other){
              return Double.compare(minDistance,other.minDistance);		
          }
          public Vertex(String name){
              this.name = name;
              vecinos = new ArrayList<Edge>();
              path = new LinkedList<Vertex>();
          }
          public String toString(){
              return name;
          }	
    }
    public class Graph {
        private int numvertices;
        //usamos listas para la implementación del grafo
        private ArrayList<Vertex> vertices;
       
        public Graph(int p_numvertices) 
        {
            this.numvertices = p_numvertices;
            vertices = new ArrayList<Vertex>(numvertices);
            for(int i=0;i<numvertices;i++)
            {
                vertices.add(new Vertex("v"+Integer.toString(i)));
            }
        }
        public void addEdge(int source, int destination, int weight) 
        {
            Vertex s = vertices.get(source);
		    Edge new_edge = new Edge(vertices.get(destination),weight);
		    s.vecinos.add(new_edge);
        }

        public ArrayList<Vertex> getVertices() {
            return vertices;
        }
        
        public Vertex getVertex(int vert){
            return vertices.get(vert);
        }
           
        public void dijkstra_calcularCaminosCostoMinimo(Vertex source){     
            source.minDistance = 0;
            PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
            queue.add(source);
            
            while(!queue.isEmpty()){
                
                Vertex u = queue.poll();
            
                for(Edge vecino:u.vecinos){
                    Double newDist = u.minDistance+vecino.weight;
                    //si es menor..
                    if(vecino.target.minDistance>newDist){
                        // Se elimina el nodo de la cola y se actualiza la distancia minima
                        queue.remove(vecino.target);
                        vecino.target.minDistance = newDist;
                        
                        // Se actualiza el past
                        vecino.target.path = new LinkedList<Vertex>(u.path);
                        vecino.target.path.add(u);
                        
                        //Se vuelve a agregar el nodo a la cola.
                        queue.add(vecino.target);					
                    }
                }
            }
        }
   
    }
    public static void main(String[] args) { 
        Problema4 instance = new Problema4();    
        try {
            BufferedReader br = new BufferedReader(new FileReader("Problema4.in"));
            String line = "";
            int n = Integer.parseInt(br.readLine());
            // Create a new graph.
		    Graph g = instance.new Graph(n);
            while (!((line = br.readLine()).equals("0"))) {
                String[] edge = line.split(" ");
                int v1 = Integer.parseInt(edge[0]);
                int v2 = Integer.parseInt(edge[1]);
                int costo = Integer.parseInt(edge[2]);
                g.addEdge(v1, v2, costo);
            }
            g.dijkstra_calcularCaminosCostoMinimo(g.getVertex(0));
            System.out.println("Vuelos de menor costo desde Bogotá (v0) hacia las diferentes ciudades");
            // Imprimimos caminos y distancia.
            for(Vertex v:g.getVertices()){
                System.out.print("Vertice - "+v+" , CostoTotal - "+ v.minDistance+" , Camino - ");
                //en v.path se guardan los caminos 
                for(Vertex pathvert:v.path) {
                    System.out.print(pathvert+" ");
                }
                System.out.println(""+v);
            }

        } catch (IOException e) {
            System.out.println("Error leyendo la entrada");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("error leyendo la línea ");
            e.printStackTrace();
            throw e;
        }
    }

}