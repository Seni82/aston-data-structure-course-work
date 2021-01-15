package trainNetworkGraphBuilder;
import model.TrainNetworkModelledData;
import model.TrainNetworkNode;
import trainNetworkHelper.TrainNetworkUtilityClass;

import java.io.InputStream;
import java.util.*;


public class Graphs {

    /*
       private map field with string as key and list of networkNode as objects.
     */
    private Map<TrainNetworkNode, List<TrainNetworkNode>> adjacencyListGraphMap;


    /*
      Map initialisation with constructor!
     */
    public Graphs() {
        adjacencyListGraphMap = new LinkedHashMap<>();
    }


    /*
     Add nodes to graph.
     */
    public void addUndirectedEdge(TrainNetworkNode node) {
        if (!adjacencyListGraphMap.containsKey(node)) {
            adjacencyListGraphMap.put(node, new LinkedList<>());
        }
        adjacencyListGraphMap.get(node).add(new TrainNetworkNode(node.getTrainLine(), node.getFromToStation(), node.getToFromStation(), node.getTravelTime()));
        adjacencyListGraphMap.get(node).add(new TrainNetworkNode(node.getTrainLine(), node.getToFromStation(), node.getFromToStation(), node.getTravelTime()));
    }



    /*
       Consider requesting map as parameter, otherwise make private.
       Split by -> and get the data at index 1 for comparison.
       true if you want to use method as string comparison or false if object
     */
    public boolean isEdgeExistBetween(TrainNetworkNode source, TrainNetworkNode destination) {
        return adjacencyListGraphMap.containsKey(source) && adjacencyListGraphMap.get(source).contains(destination);
    }



    //does a station have an edge.
    public boolean hasAnEdge(TrainNetworkNode source){
        if(adjacencyListGraphMap.get(source) != null){
            return true;
        }else return false;
    }



    /*
       This should create a graph representation of all train line.
     */
    public Map<TrainNetworkNode, List<TrainNetworkNode>> buildGraphForSpecifiedTrainLine(TrainNetworkNode lineName) {
        //Get data as map is more faster and efficient as suppose to arraylist previously used.
        Map<TrainNetworkNode, ArrayList<TrainNetworkNode>> mapData = TrainNetworkModelledData.getListOfNodesAsAMap();
        ArrayList<TrainNetworkNode> dataForGraph = mapData.get(lineName);
        for (TrainNetworkNode trainNetwork : dataForGraph) {
            addUndirectedEdge(trainNetwork);
            isEdgeExistBetween(trainNetwork, trainNetwork);
        }
        TrainNetworkUtilityClass.printGraph(adjacencyListGraphMap);
        printAdjacentListOfEachVertex();
        TrainNetworkNode source = new TrainNetworkNode("Nuneaton – Coventry","Nuneaton","Bedworth",12);
        //TrainNetworkNode destination = new TrainNetworkNode("Birmingham – Rugby – Northampton – London", "Coventry", "Rugby", 13);
        //dfs_getAllTermini(adjacencyListGraphMap, source, destination);
        BFS_algorithm(source,adjacencyListGraphMap);
        return adjacencyListGraphMap;
    }





    public String buildGraphForTotalTravelTimeForAllLines() {
        ArrayList<String> allLinesAndTravelTime = new ArrayList<String>();
        Map<TrainNetworkNode, ArrayList<TrainNetworkNode>> mapData = TrainNetworkModelledData.getListOfNodesAsAMap();
        for (TrainNetworkNode key : mapData.keySet()) {
            allLinesAndTravelTime = new ArrayList<String>();
            String trainLineName = "";
            adjacencyListGraphMap.clear();
            ArrayList<TrainNetworkNode> dataForGraph = mapData.get(key);
            for (TrainNetworkNode trainNetwork : dataForGraph) {
                addUndirectedEdge(trainNetwork);
                trainLineName = trainNetwork.getTrainLine();
            }
            allLinesAndTravelTime.add(String.format("%s:",trainLineName));
        }
        return allLinesAndTravelTime.toString();
    }


    public void printAdjacentListOfEachVertex(){
        /*
        StringBuilder builder = new StringBuilder();
        for(TrainNetworkNode key : adjacencyListGraphMap.keySet()){
            builder.append(key).append(":");
            for(TrainNetworkNode adj : adjacencyListGraphMap.get(key)){
                builder.append(adj.toString()).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
        */
        for(TrainNetworkNode node : adjacencyListGraphMap.keySet()){
            System.out.println("The " + node.getFromToStation() + " has an edge towards: ");
            for(TrainNetworkNode neighbor : adjacencyListGraphMap.get(node)){
                System.out.print(neighbor.getToFromStation() + "\n");
            }
            System.out.println();
        }
    }



    private static void display(String info)
    {
        System.out.println(info);
    }


    public boolean dfs_getAllTermini(Map<TrainNetworkNode, List<TrainNetworkNode>> map_dfs, TrainNetworkNode source, TrainNetworkNode destination){

        HashSet<TrainNetworkNode> visited = new HashSet<>();
        return pathExists(source, destination, visited, map_dfs);

    }

    public boolean pathExists(TrainNetworkNode source, TrainNetworkNode destination, HashSet<TrainNetworkNode> visited, Map<TrainNetworkNode, List<TrainNetworkNode>> map_dfs){
        if(visited.contains(source)){return false;}

        visited.add(source);
        if(source == destination){
            return true;
        }
        for(TrainNetworkNode neighbor : map_dfs.get(source)){
            if(pathExists(neighbor, destination, visited, map_dfs))
                return true;
        }
        return false;
    }


    public void BFS_algorithm(TrainNetworkNode station, Map<TrainNetworkNode, List<TrainNetworkNode>> map){
        boolean connected = false;
        Set<TrainNetworkNode> visited = new HashSet<>();
        Queue<TrainNetworkNode> shit = new LinkedList<TrainNetworkNode>();
        shit.add(station);
        visited.add(station);
        while(!shit.isEmpty()){
            TrainNetworkNode newStation = shit.poll();
            if(station.getFromToStation().equals(station.getToFromStation())){

                connected = true;
                break;
            }
            for(TrainNetworkNode edge : map.get(newStation)){
                if(!visited.contains(edge)){
                    shit.add(edge);
                    visited.add(edge);
                }
            }
        }
    }
}















//Entire network map
//List<Map<String, List<TrainNetworkNode>>> arrayListOfMapsOfEachLine = new ArrayList<Map<String, List<TrainNetworkNode>>>();