package trainNetworkGraphBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphAdjacencyListWeighted {


    //HashMap to store all nodes/vertices of the graph
    private static HashMap<Integer,Node> graph = new HashMap<Integer,Node>();

    //Default Constructor
    public GraphAdjacencyListWeighted(){
    }

    /**
     * Edge --- class to store an edge wuth End vertex and weight of the edge
     */
    static class Edge{

        private int endVertex;
        private int weight;

        public Edge(int endVertex, int weight){
            this.endVertex = endVertex;
            this.weight = weight;
        }

        //Getter method for end vertex
        public int getEndVertex(){
            return endVertex;
        }

        //Getter method for weight of the edge
        public int getWeight(){
            return weight;
        }

    }

    /**
     * Node --- class to store each vertex along with adjacent vertices.
     */
    static class Node{

        private int id;
        private ArrayList<Edge> adjacent;

        public Node(int id){
            this.id = id;
            adjacent = new ArrayList<Edge>();
        }

        //Getter method for start vertex
        public int getId(){
            return id;
        }

        //Getter method for adjacent vertices
        public ArrayList<Edge> getAdjacent(){
            return adjacent;
        }

        //add edge to the adajcent list
        public void addAdjacent(int endVertex, int weight){
            adjacent.add(new Edge(endVertex,weight));
        }

        //To print Node
        public String toString(){
            String msg = "";
            for(Edge node: adjacent)
                msg = msg + node.getEndVertex() + " ";
            return msg;
        }
    }

    /**
     * Create and get node corresponding to a vertex.
     * @param id
     * @return Node object.
     */
    private static Node getNode(int id){
        if(graph.containsKey(id))
            return graph.get(id);
        else{
            Node node = new Node(id);
            graph.put(id, node);
            return node;
        }
    }

    /**
     * Adds edge between sorce and destination.
     * @param source index
     * @param destination index
     * @param weight of the edge
     * @return No return value.
     */
    public void add(int source, int destination, int weight){

        //Get nodes corresponding to source and destination vertices.
        Node s = getNode(source);
        Node d = getNode(destination);

        //add nodes to adjacent list
        s.addAdjacent(destination,weight);
        d.addAdjacent(source,weight);
    }

    /**
     * Finds all adjacent vertices of the given vertex
     * @param
     * @return ArrayList<Edge> storing adjacent vertices of vertex
     */
    public ArrayList<Edge> findAdjacent(int index){
        Node node = getNode(index);
        return node.getAdjacent();
    }

    /**
     * Finds whether vertices start and end are connected
     * @param source index
     * @param destination index
     * @return true if start and end are part of same edge else returns false.
     */
    public boolean isConnected(int source, int destination){
        Node s = getNode(source);
        Node d = getNode(destination);

        ArrayList<Edge> sourceList = s.getAdjacent();
        for(Edge edge: sourceList){
            if(edge.endVertex == destination)
                return true;
        }
        return false;
    }

    /**
     * Main Method
     * @param string array containing
     * the command line arguments.
     * @return No return value.
     */
    public static void main(String args[]){

        GraphAdjacencyListWeighted graph = new GraphAdjacencyListWeighted();

        //Add edges of the graph.
        graph.add(1,2,5);
        graph.add(1,4,6);
        graph.add(1,5,10);
        graph.add(2,3,1);
        graph.add(2,5,2);
        graph.add(2,6,7);
        graph.add(3,6,8);
        graph.add(4,5,3);
        graph.add(5,6,4);

        //Find adjacent of vertex 1 and print them.
        ArrayList<Edge> adjacent = graph.findAdjacent(1);
        System.out.print("Adjacent of 1 are: ");
        for(Edge i: adjacent)
            System.out.print(i.getEndVertex() + " ");


        //Find whether vertices 2 and 1 are connected.
        System.out.println();
        System.out.println("2 and 1 are connected: "+graph.isConnected(2,1));
    }

}



