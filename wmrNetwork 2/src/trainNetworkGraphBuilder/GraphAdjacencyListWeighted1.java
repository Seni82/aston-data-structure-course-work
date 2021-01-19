package trainNetworkGraphBuilder;
import trainNetworkHelper.TrainNetworkUtilityClass;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class GraphAdjacencyListWeighted1 {

    private static Map<String, Node> graph = new LinkedHashMap<>();


    public GraphAdjacencyListWeighted1(){}

    public static Map<String, Node> getGraph(){
        return graph;
    }


    static class TrainLine{
        String trainLineName;
    }


    static class Edge {
        //Stores an edge with end vertex and weight of the edge
        private String endStationVertex;
        private int weight;


        public Edge(String endStationVertex, int weight){
            this.endStationVertex = endStationVertex;
            this.weight = weight;
        }

        public String getEndStationVertex(){
            return endStationVertex;
        }

        public int getWeight(){
            return weight;
        }
    }


   static class Node {

       private String startStationVertex;
       private ArrayList<Edge> adjacentStationVertex;

       public Node(String startStationVertex) {
           this.startStationVertex = startStationVertex;
           adjacentStationVertex = new ArrayList<Edge>();
       }

       public String getStationVertex() {
           return startStationVertex;
       }


       public ArrayList<Edge> getAdjacent() {
           return adjacentStationVertex;
       }

       //Add edge to the adjacent list
       public void addAdjacent(String endStationVertex, int weight) {
           adjacentStationVertex.add(new Edge(endStationVertex, weight));
       }

       //print nodes
       public String toString() {
           String msg = "";
           for (Edge node : adjacentStationVertex) {
               msg = msg + node.getEndStationVertex();
           }
           return msg;
       }
   }

    private static Node getVertexNode(String startStationVertex) {
        if (graph.containsKey(startStationVertex)) {
            return graph.get(startStationVertex);
        } else {
            Node node = new Node(startStationVertex);
            graph.put(startStationVertex, node);
            return node;
        }
    }

    public void addEdges(String source, String destination, int weight){
        Node src = getVertexNode(source);
        Node dest = getVertexNode(destination);

        src.addAdjacent(destination, weight);
        dest.addAdjacent(source, weight);
    }

    //find all adjacent vertices of a given vertex
    public ArrayList<Edge> findAdjacent(String stationVertex) {
        Node node = getVertexNode(stationVertex);
        return node.getAdjacent();
    }

    public boolean isConnected(String source, String destination) {
        Node src = getVertexNode(source);
        Node dest = getVertexNode(destination);

        ArrayList<Edge> sourceList = src.getAdjacent();
        for (Edge edge : sourceList) {
            if (edge.getEndStationVertex() == destination)
                return true;
        }
        return false;
    }

    public GraphAdjacencyListWeighted1 readFileForNewImplementationGraph(){
        GraphAdjacencyListWeighted1 mapForNewImpl = new GraphAdjacencyListWeighted1();
        if(TrainNetworkUtilityClass.mainNetworkDataReader.hasNext()){
            TrainNetworkUtilityClass.mainNetworkDataReader.nextLine();
        }
        while(TrainNetworkUtilityClass.mainNetworkDataReader.hasNextLine()){
            String[] trainNetworkData = TrainNetworkUtilityClass.mainNetworkDataReader.nextLine().split(",");
            String trainLine = trainNetworkData[0]; String fromStation = trainNetworkData[1];
            String toFromStation = trainNetworkData[2]; int travelTime = Integer.parseInt(trainNetworkData[3]);
            mapForNewImpl.addEdges(fromStation, toFromStation, travelTime);
        }
        return mapForNewImpl;
    }

}


