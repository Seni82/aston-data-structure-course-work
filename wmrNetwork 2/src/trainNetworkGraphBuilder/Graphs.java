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
    private Map<String, List<TrainNetworkNode>> adjacencyListGraphMap;


    /*
      Map initialisation with constructor!
     */
    public Graphs() {
        adjacencyListGraphMap = new LinkedHashMap<>();
    }


    /*
     Add nodes to graph.
     */
    public void addEdge(String trainLine, String fromToStation, String toFromStation, int travelTime) {
        if (!adjacencyListGraphMap.containsKey(fromToStation)) {
            adjacencyListGraphMap.put(fromToStation, new LinkedList<>());
        }
        adjacencyListGraphMap.get(fromToStation).add(new TrainNetworkNode(trainLine, fromToStation, toFromStation, travelTime));
    }


    /*
      Add bi-directional node relationship to the graph.
     */
    public void addUndirectedEdge(String trainLine, String fromToStation, String toFromStation, int travelTime) {
        addEdge(trainLine, fromToStation, toFromStation, travelTime);
        addEdge(trainLine, toFromStation, fromToStation, travelTime);
    }



    /*
       Consider requesting map as parameter, otherwise make private.
       Split by -> and get the data at index 1 for comparison.
       true if you want to use method as string comparison or false if object
     */
    public boolean isEdgeExistBetween(String fromToStation, String toFromStation) {
        String[] splittedNode={}; String edge = "";
        String addEdge = "";
        ArrayList<String> listOfEdges = new ArrayList<>();
        boolean isEdgePresent = false;
        if(adjacencyListGraphMap.containsKey(fromToStation))
        {
            List<TrainNetworkNode> listOfAdjacentNode = adjacencyListGraphMap.get(fromToStation);
            for(TrainNetworkNode eachConnectedNode : listOfAdjacentNode){
                    splittedNode = eachConnectedNode.toString().trim().split("\\s*->\\s*");
                    if (splittedNode.length != 0) {
                        if(splittedNode.length == 1){
                            addEdge = splittedNode[0];
                        }else {
                            addEdge = splittedNode[1];
                        }
                    }
                    //addEdge = eachConnectedNode.getToFromStation();
                listOfEdges.add(addEdge);
            }
            isEdgePresent = listOfEdges.contains(toFromStation);
            //System.out.println("\nIs edge present for "+ fromToStation+" and "+toFromStation + ": "+isEdgePresent);
            //System.out.println(fromToStation+ " has connection with \n"+ listOfEdges.toString());
        }
        else{
            //System.out.println(fromToStation +" does not exist as a from (key) station in the map" +
              //      "therefore know edge present between "+fromToStation+" and "+ toFromStation);
        }
        return isEdgePresent;
    }




    //does a station have an edge.
    public boolean hasAnEdge(String source){
        if(adjacencyListGraphMap.get(source) != null){
            return true;
        }else return false;
    }



    /*
       This should create a graph representation of all train line.
     */
    public Map<String, List<TrainNetworkNode>> buildGraphForSpecifiedTrainLine(String trainLineName) {
        //Get data as map is more faster and efficient as suppose to arraylist previously used.
        Map<String, ArrayList<TrainNetworkNode>> mapData = TrainNetworkModelledData.getListOfNodesAsAMap();
        String lineName = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLineName);
        ArrayList<TrainNetworkNode> dataForGraph = mapData.get(lineName);
        for (TrainNetworkNode trainNetwork : dataForGraph) {
            addUndirectedEdge(trainNetwork.getTrainLine(),
                    trainNetwork.getFromToStation(),
                    trainNetwork.getToFromStation(),
                    trainNetwork.getTravelTime());
            isEdgeExistBetween(trainNetwork.getFromToStation(), trainNetwork.getToFromStation());
        }
        TrainNetworkUtilityClass.printGraph(adjacencyListGraphMap);
        printAdjacentListOfEachVertex();
        return adjacencyListGraphMap;
    }





    public String buildGraphForTotalTravelTimeForAllLines() {
        ArrayList<String> allLinesAndTravelTime = new ArrayList<String>();
        Map<String, ArrayList<TrainNetworkNode>> mapData = TrainNetworkModelledData.getListOfNodesAsAMap();
        for (String key : mapData.keySet()) {
            allLinesAndTravelTime = new ArrayList<String>();
            String trainLineName = "";
            adjacencyListGraphMap.clear();
            ArrayList<TrainNetworkNode> dataForGraph = mapData.get(key);
            for (TrainNetworkNode trainNetwork : dataForGraph) {
                addUndirectedEdge(trainNetwork.getTrainLine(),
                        trainNetwork.getFromToStation(),
                        trainNetwork.getToFromStation(),
                        trainNetwork.getTravelTime());
                trainLineName = trainNetwork.getTrainLine();
            }
            allLinesAndTravelTime.add(String.format("%s:",trainLineName));
            //String travelTime = calculateTravelTimeBetweenTermini();
            //allLinesAndTravelTime.add(travelTime);
        }
        return allLinesAndTravelTime.toString();
    }


    public String printAdjacentListOfEachVertex(){
        StringBuilder builder = new StringBuilder();
        for(String key : adjacencyListGraphMap.keySet()){
            builder.append(key).append(":");
            for(TrainNetworkNode adj : adjacencyListGraphMap.get(key)){
                builder.append(adj.toString()).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }



    private static void display(String info)
    {
        System.out.println(info);
    }










}















//Entire network map
//List<Map<String, List<TrainNetworkNode>>> arrayListOfMapsOfEachLine = new ArrayList<Map<String, List<TrainNetworkNode>>>();